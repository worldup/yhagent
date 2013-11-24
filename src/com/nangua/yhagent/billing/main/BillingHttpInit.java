package com.nangua.yhagent.billing.main;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.logging.LoggingHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nangua.yhagent.billing.http.handler.BillingAgentHttpHandler;
import com.nangua.yhagent.billing.http.handler.Msg2ServiceHandler;
@Service
public class BillingHttpInit extends ChannelInitializer<SocketChannel>  {
	@Autowired
	private BillingAgentHttpHandler billingAgentHandler; 
	@Autowired
	private Msg2ServiceHandler resultHandler; 
	@Override
    public void initChannel(SocketChannel ch) throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline p = ch.pipeline();

        p.addLast("log",new  LoggingHandler());

        p.addLast("http",new HttpClientCodec());
        // Remove the following line if you don't want automatic content compression.
        //p.addLast("deflater", new HttpContentCompressor());
        p.addLast("handler", billingAgentHandler);
        p.addLast("resultHandler", resultHandler);
    }
}
