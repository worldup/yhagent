package com.nangua.yhagent.billing.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.ClientCookieEncoder;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringEncoder;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.DiskAttribute;
import io.netty.handler.codec.http.multipart.DiskFileUpload;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.ErrorDataEncoderException;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is meant to be run against {@link HttpUploadServer}.
 */
public class HttpUploadClient {

    private static final Logger logger = Logger.getLogger(HttpUploadClient.class.getName());

    private final String baseUri;
    private final String filePath;

    public HttpUploadClient(String baseUri, String filePath) {
        this.baseUri = baseUri;
        this.filePath = filePath;
    }

    public void run() throws Exception {
        String postSimple, postFile, get;
        if (baseUri.endsWith("/")) {
            postSimple = baseUri + "formpost";
            postFile = baseUri + "formpostmultipart";
            get = baseUri + "formget";
        } else {
            postSimple = baseUri + "/formpost";
            postFile = baseUri + "/formpostmultipart";
            get = baseUri + "/formget";
        }
        URI uriSimple;
        try {
            uriSimple = new URI(postSimple);
        } catch (URISyntaxException e) {
            logger.log(Level.WARNING, "Invalid URI syntax", e);
            return;
        }
        String scheme = uriSimple.getScheme() == null ? "http" : uriSimple.getScheme();
        String host = uriSimple.getHost() == null ? "localhost" : uriSimple.getHost();
        int port = uriSimple.getPort();
        if (port == -1) {
            if ("http".equalsIgnoreCase(scheme)) {
                port = 80;
            } else if ("https".equalsIgnoreCase(scheme)) {
                port = 443;
            }
        }

        if (!"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme)) {
            logger.log(Level.WARNING, "Only HTTP(S) is supported.");
            return;
        }

        boolean ssl = "https".equalsIgnoreCase(scheme);

        URI uriFile;
        try {
            uriFile = new URI(postFile);
        } catch (URISyntaxException e) {
            logger.log(Level.WARNING, "Error: ", e);
            return;
        }
        File file = new File(filePath);
        if (!file.canRead()) {
            logger.log(Level.WARNING, "A correct path is needed");
            return;
        }

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();

        // setup the factory: here using a mixed memory/disk based on size threshold
        HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE); // Disk if MINSIZE exceed

        DiskFileUpload.deleteOnExitTemporaryFile = true; // should delete file on exit (in normal exit)
        DiskFileUpload.baseDirectory = null; // system temp directory
        DiskAttribute.deleteOnExitTemporaryFile = true; // should delete file on exit (in normal exit)
        DiskAttribute.baseDirectory = null; // system temp directory

        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(new HttpUploadClientIntializer(ssl));

            // Simple Get form: no factory used (not usable)
            List<Entry<String, String>> headers = formGet(b, host, port, get, uriSimple);
            if (headers == null) {
                factory.cleanAllHttpDatas();
                return;
            }

            // Simple Post form: factory used for big attributes
            List<InterfaceHttpData> bodylist = formPost(b, host, port, uriSimple, file, factory, headers);
            if (bodylist == null) {
                factory.cleanAllHttpDatas();
                return;
            }

            // Multipart Post form: factory used
            formPostMultipart(b, host, port, uriFile, factory, headers, bodylist);
        } finally {
            // Shut down executor threads to exit.
            group.shutdownGracefully();

            // Really clean all temporary files if they still exist
            factory.cleanAllHttpDatas();
        }
    }

    /**
     * Standard usage of HTTP API in Netty without file Upload (get is not able to achieve File upload due to limitation
     * on request size).
     *
     * @return the list of headers that will be used in every example after
     **/
    private static List<Entry<String, String>> formGet(Bootstrap bootstrap, String host, int port, String get,
            URI uriSimple) throws Exception {
        // Start the connection attempt.
        // No use of HttpPostRequestEncoder since not a POST
        Channel channel = bootstrap.connect(host, port).sync().channel();

        // Prepare the HTTP request.
        QueryStringEncoder encoder = new QueryStringEncoder(get);
        // add Form attribute
        encoder.addParam("getform", "GET");
        encoder.addParam("info", "first value");
        encoder.addParam("secondinfo", "secondvalue ???&");
        // not the big one since it is not compatible with GET size
        // encoder.addParam("thirdinfo", textArea);
        encoder.addParam("thirdinfo", "third value\r\ntest second line\r\n\r\nnew line\r\n");
        encoder.addParam("Send", "Send");

        URI uriGet;
        try {
            uriGet = new URI(encoder.toString());
        } catch (URISyntaxException e) {
            logger.log(Level.WARNING, "Error: ", e);
            return null;
        }

        FullHttpRequest request =
                new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uriGet.toASCIIString());
        HttpHeaders headers = request.headers();
        headers.set(HttpHeaders.Names.HOST, host);
        headers.set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
        headers.set(HttpHeaders.Names.ACCEPT_ENCODING, HttpHeaders.Values.GZIP + ','
                + HttpHeaders.Values.DEFLATE);

        headers.set(HttpHeaders.Names.ACCEPT_CHARSET, "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        headers.set(HttpHeaders.Names.ACCEPT_LANGUAGE, "fr");
        headers.set(HttpHeaders.Names.REFERER, uriSimple.toString());
        headers.set(HttpHeaders.Names.USER_AGENT, "Netty Simple Http Client side");
        headers.set(HttpHeaders.Names.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

        headers.set(HttpHeaders.Names.COOKIE, ClientCookieEncoder.encode(new DefaultCookie("my-cookie", "foo"),
                new DefaultCookie("another-cookie", "bar")));

        // send request
        List<Entry<String, String>> entries = headers.entries();
        channel.writeAndFlush(request).sync();

        // Wait for the server to close the connection.
        channel.closeFuture().sync();

        return entries;
    }

    /**
     * Standard post without multipart but already support on Factory (memory management)
     *
     * @return the list of HttpData object (attribute and file) to be reused on next post
     */
    private static List<InterfaceHttpData> formPost(Bootstrap bootstrap, String host, int port, URI uriSimple,
            File file, HttpDataFactory factory, List<Entry<String, String>> headers) throws Exception {

        // Start the connection attempt
        Channel channel = bootstrap.connect(host, port).sync().channel();

        // Prepare the HTTP request.
        HttpRequest request =
                new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uriSimple.toASCIIString());

        // Use the PostBody encoder
        HttpPostRequestEncoder bodyRequestEncoder = null;
        try {
            bodyRequestEncoder = new HttpPostRequestEncoder(factory, request, false); // false not multipart
        } catch (NullPointerException e) {
            // should not be since args are not null
            e.printStackTrace();
        } catch (ErrorDataEncoderException e) {
            // test if getMethod is a POST getMethod
            e.printStackTrace();
        }

        // it is legal to add directly header or cookie into the request until finalize
        for (Entry<String, String> entry : headers) {
            request.headers().set(entry.getKey(), entry.getValue());
        }

        // add Form attribute
        try {
            bodyRequestEncoder.addBodyAttribute("getform", "POST");
            bodyRequestEncoder.addBodyAttribute("info", "first value");
            bodyRequestEncoder.addBodyAttribute("secondinfo", "secondvalue ???&");
            bodyRequestEncoder.addBodyAttribute("thirdinfo", textArea);
            bodyRequestEncoder.addBodyFileUpload("myfile", file, "application/x-zip-compressed", false);
            bodyRequestEncoder.addBodyAttribute("Send", "Send");
        } catch (NullPointerException e) {
            // should not be since not null args
            e.printStackTrace();
        } catch (ErrorDataEncoderException e) {
            // if an encoding error occurs
            e.printStackTrace();
        }

        // finalize request
        try {
            request = bodyRequestEncoder.finalizeRequest();
        } catch (ErrorDataEncoderException e) {
            // if an encoding error occurs
            e.printStackTrace();
        }

        // Create the bodylist to be reused on the last version with Multipart support
        List<InterfaceHttpData> bodylist = bodyRequestEncoder.getBodyListAttributes();

        // send request
        channel.write(request);

        // test if request was chunked and if so, finish the write
        if (bodyRequestEncoder.isChunked()) {
            // could do either request.isChunked()
            // either do it through ChunkedWriteHandler
            channel.writeAndFlush(bodyRequestEncoder).awaitUninterruptibly();
        }  else {
            channel.flush();
        }

        // Do not clear here since we will reuse the InterfaceHttpData on the
        // next request
        // for the example (limit action on client side). Take this as a
        // broadcast of the same
        // request on both Post actions.
        //
        // On standard program, it is clearly recommended to clean all files
        // after each request
        // bodyRequestEncoder.cleanFiles();

        // Wait for the server to close the connection.
        channel.closeFuture().sync();

        return bodylist;
    }

    /**
     * Multipart example
     */
    private static void formPostMultipart(Bootstrap bootstrap, String host, int port, URI uriFile,
            HttpDataFactory factory, List<Entry<String, String>> headers, List<InterfaceHttpData> bodylist)
            throws Exception {

        // Start the connection attempt
        Channel channel = bootstrap.connect(host, port).sync().channel();

        // Prepare the HTTP request.
        HttpRequest request =
                new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uriFile.toASCIIString());

        // Use the PostBody encoder
        HttpPostRequestEncoder bodyRequestEncoder = null;
        try {
            bodyRequestEncoder = new HttpPostRequestEncoder(factory, request, true); // true => multipart
        } catch (NullPointerException e) {
            // should not be since no null args
            e.printStackTrace();
        } catch (ErrorDataEncoderException e) {
            // test if getMethod is a POST getMethod
            e.printStackTrace();
        }

        // it is legal to add directly header or cookie into the request until finalize
        for (Entry<String, String> entry : headers) {
            request.headers().set(entry.getKey(), entry.getValue());
        }

        // add Form attribute from previous request in formpost()
        try {
            bodyRequestEncoder.setBodyHttpDatas(bodylist);
        } catch (NullPointerException e1) {
            // should not be since previously created
            e1.printStackTrace();
        } catch (ErrorDataEncoderException e1) {
            // again should not be since previously encoded (except if an error
            // occurs previously)
            e1.printStackTrace();
        }

        // finalize request
        try {
            request = bodyRequestEncoder.finalizeRequest();
        } catch (ErrorDataEncoderException e) {
            // if an encoding error occurs
            e.printStackTrace();
        }

        // send request
        channel.write(request);

        // test if request was chunked and if so, finish the write
        if (bodyRequestEncoder.isChunked()) {
            channel.writeAndFlush(bodyRequestEncoder).awaitUninterruptibly();
        } else {
            channel.flush();
        }

        // Now no more use of file representation (and list of HttpData)
        bodyRequestEncoder.cleanFiles();

        // Wait for the server to close the connection.
        channel.closeFuture().sync();
    }

    public static void main(String[] args) throws Exception {
        String baseUri;
        String filePath;
        if (args.length == 2) {
            baseUri = args[0];
            filePath = args[1];
        } else {
            baseUri = "http://localhost:8080";

            File f = File.createTempFile("upload", ".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("Some text data in a file to be posted");
            bw.close();
            filePath = f.getPath();
            f.deleteOnExit();
        }

        logger.info("Posting to " + baseUri + ". Using file " + filePath);
        new HttpUploadClient(baseUri, filePath).run();
    }

    // use to simulate a small TEXTAREA field in a form
    private static final String textArea = "short text";
 
}
