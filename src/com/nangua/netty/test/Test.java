package com.nangua.netty.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.ErrorDataEncoderException;

import java.util.Map.Entry;

public class Test {
	public static void main(String []args) throws Exception{
EventLoopGroup workerGroup = new NioEventLoopGroup();
        
// Configure the client.
EventLoopGroup group = new NioEventLoopGroup();
try {
    Bootstrap b = new Bootstrap();
    b.group(group)
     .channel(NioSocketChannel.class)
     .handler(new HttpSnoopClientInitializer(false));
    for (int i=0;i<2;i++){
    long a =System.currentTimeMillis();
    
    // Make the connection attempt.
    Channel ch = b.connect("hq.sinajs.cn", 80).sync().channel();

   /* // Prepare the HTTP request.
    HttpRequest request = new DefaultHttpRequest(
            HttpVersion.HTTP_1_1, HttpMethod.GET, "/list=sh601006");
    request.headers().set(HttpHeaders.Names.HOST, "hq.sinajs.cn");
    request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
  //  request.headers().set(HttpHeaders.Names.ACCEPT_ENCODING, HttpHeaders.Values.GZIP);

    // Set some example cookies.
    request.headers().set(
            HttpHeaders.Names.COOKIE,
            ClientCookieEncoder.encode(
                    new DefaultCookie("my-cookie", "foo"),
                    new DefaultCookie("another-cookie", "bar")));

    // Send the HTTP request.
    ch.writeAndFlush(request);*/
    HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE); // Disk if MINSIZE exceed
    // Wait for the server to close the connection.
    // Prepare the HTTP request.
    HttpRequest request =
            new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/list=sh600037");
    request.headers().set(HttpHeaders.Names.HOST, "hq.sinajs.cn");
    request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
 
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
    for (Entry<String, String> entry :  request.headers()) {
        request.headers().set(entry.getKey(), entry.getValue());
    }
    
    // add Form attribute
   /* try {
      //  bodyRequestEncoder.addBodyHttpData();
      
    } catch (NullPointerException e) {
        // should not be since not null args
        e.printStackTrace();
    } catch (ErrorDataEncoderException e) {
        // if an encoding error occurs
        e.printStackTrace();
    }
*/
    // finalize request
    try {
        request = bodyRequestEncoder.finalizeRequest();
    } catch (ErrorDataEncoderException e) {
        // if an encoding error occurs
        e.printStackTrace();
    }
    ch.writeAndFlush(request);
    ch.closeFuture().sync();
    System.out.println("1111111111111111111111111111111111a"+(System.currentTimeMillis()-a));
    }
    } finally {
    // Shut down executor threads to exit.
    group.shutdownGracefully();
}
	}
}
