package com.nangua.netty.test.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import com.nangua.netty.test.HttpSnoopClientInitializer;

public class BootClient {
	public static void main(String []args){
		 EventLoopGroup group = new NioEventLoopGroup();
	        try {
	            Bootstrap b = new Bootstrap();
	            b.group(group)
	             .channel(NioSocketChannel.class)
	             .handler(new HttpSnoopClientInitializer(false));

	            // Make the connection attempt.
	            ChannelFuture chF = b.connect("www.baidu.com", 80).addListener(new ChannelFutureListener() {
	                public void operationComplete(ChannelFuture future) {
	                	 System.out.println("connect");
	                }
	            });
	            chF.awaitUninterruptibly();
	            Channel ch=chF.channel();
	            ch.close();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }finally {
	            // Shut down executor threads to exit.
	            group.shutdownGracefully();
	        }
	}
}
