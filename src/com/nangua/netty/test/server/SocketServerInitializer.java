package com.nangua.netty.test.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class SocketServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		 ChannelPipeline p = ch.pipeline();
		 p.addLast("channel1",SocketServerHandler.handler1);
		 p.addLast("channel2",SocketServerHandler.handler2);
		
	}

}
