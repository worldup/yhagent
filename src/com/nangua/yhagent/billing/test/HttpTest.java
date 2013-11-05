package com.nangua.yhagent.billing.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.ErrorDataEncoderException;

import java.net.URI;
import java.util.List;

public class HttpTest {
 public static void main(String []args) throws Exception {
//	 Configure the client.
     EventLoopGroup group = new NioEventLoopGroup();

     // setup the factory: here using a mixed memory/disk based on size threshold
     HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE); // Disk if MINSIZE exceed
     try {
         Bootstrap b = new Bootstrap();
         b.group(group).channel(NioSocketChannel.class).handler(new HttpUploadClientIntializer(false));

         

         // Simple Post form: factory used for big attributes
         List<InterfaceHttpData> bodylist = formPost(b, "localhost", 8888, new URI("/test"), "<xml>haha</xml>",factory);
         if (bodylist == null) {
             factory.cleanAllHttpDatas();
             return;
         }

        
     } finally {
         // Shut down executor threads to exit.
         group.shutdownGracefully();

         // Really clean all temporary files if they still exist
         factory.cleanAllHttpDatas();
     }
 }
 private static List<InterfaceHttpData> formPost(Bootstrap bootstrap, String host, int port, URI uriSimple,
         String xmlContent, HttpDataFactory factory ) throws Exception {

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

      

     // add Form attribute
     try {
    	  
         bodyRequestEncoder.addBodyAttribute("reqxml", xmlContent);
         
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
}
