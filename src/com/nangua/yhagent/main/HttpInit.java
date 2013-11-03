package com.nangua.yhagent.main;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;

import com.nangua.yhagent.http.handler.YHAgentHttpHandler;

public class HttpInit extends ChannelInitializer<SocketChannel>  {
	 
	@Override
    public void initChannel(SocketChannel ch) throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline p = ch.pipeline();

         

        p.addLast("http",new HttpClientCodec());
        // Remove the following line if you don't want automatic content compression.
        //p.addLast("deflater", new HttpContentCompressor());
        p.addLast("handler", new YHAgentHttpHandler());
    }
}
