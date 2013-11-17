package com.nangua.yhagent.billing.http.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.LastHttpContent;

import java.nio.charset.Charset;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nangua.yhagent.billing.test.HttpUploadClientHandler;
@Service
public class BillingAgentHttpHandler extends SimpleChannelInboundHandler<HttpObject>{
	 @Value("${yhagent.billing.encoding}")
	 private String encoding; 
	 private static final Logger logger = Logger.getLogger(HttpUploadClientHandler.class.getName());
    

	    @Override
	    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
	         
	        if (msg instanceof HttpContent) {
	            HttpContent chunk = (HttpContent) msg;
	             

	            if (chunk instanceof LastHttpContent) {
	                
	                logger.info(chunk.content().toString(Charset.forName(encoding)));
	             
	        }
	        }
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	        cause.printStackTrace();
	        ctx.channel().close();
	    }
}
