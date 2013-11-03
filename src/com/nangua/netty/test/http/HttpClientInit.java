package com.nangua.netty.test.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;

public class HttpClientInit extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		 ChannelPipeline p = ch.pipeline();
		 p.addLast("channel1",HttpClientHandler.handler1);
		 p.addLast("channel2",HttpClientHandler.handler2); 
		 p.addLast("http",new HttpClientCodec());
		 p.addLast("channel3",HttpClientHandler.handler3); 
		
	}

}
