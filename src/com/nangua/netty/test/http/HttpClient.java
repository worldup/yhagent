package com.nangua.netty.test.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.ClientCookieEncoder;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;

public class HttpClient {
	public static void main(String []args){
		 EventLoopGroup group = new NioEventLoopGroup();
	        try {
	            Bootstrap b = new Bootstrap();
	            b.group(group)
	             .channel(NioSocketChannel.class)
	              
	             .handler(new HttpClientInit());

	            Channel ch = b.connect("www.sohu.com", 80).sync().channel();

	            // Prepare the HTTP request.
	            HttpRequest request = new DefaultHttpRequest(
	                    HttpVersion.HTTP_1_1, HttpMethod.GET, "/");
	            request.headers().set(HttpHeaders.Names.HOST, "www.sohu.com");
	            request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
	           

	            // Set some example cookies.
	            request.headers().set(
	                    HttpHeaders.Names.COOKIE,
	                    ClientCookieEncoder.encode(
	                            new DefaultCookie("my-cookie", "foo"),
	                            new DefaultCookie("another-cookie", "bar")));

	            // Send the HTTP request.
	            ch.writeAndFlush(request);

	            // Wait for the server to close the connection.
	            ch.closeFuture().sync();
	           // ch.close();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }finally {
	            // Shut down executor threads to exit.
	            group.shutdownGracefully();
	        }
	}
}
