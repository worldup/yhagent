package com.nangua.yhagent.pms.main;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nangua.yhagent.pms.http.handler.YHAgentHttpHandler;
@Service
public class HttpInit extends ChannelInitializer<SocketChannel>  {
	@Autowired
	private YHAgentHttpHandler yhAgentHandler; 
	@Override
    public void initChannel(SocketChannel ch) throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline p = ch.pipeline();

         

        p.addLast("http",new HttpClientCodec());
        // Remove the following line if you don't want automatic content compression.
        //p.addLast("deflater", new HttpContentCompressor());
        p.addLast("handler", yhAgentHandler);
    }
}
