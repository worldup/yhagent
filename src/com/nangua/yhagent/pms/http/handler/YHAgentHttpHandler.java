package com.nangua.yhagent.pms.http.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.LastHttpContent;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nangua.yhagent.pms.bean.XBean;
import com.nangua.yhagent.pms.command.response.Parser;
@Service
public class YHAgentHttpHandler extends SimpleChannelInboundHandler<HttpObject>{
	 @Value("${yhagent.encoding}")
	 private String encoding; 
	@Override
	    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
	       
	        if (msg instanceof HttpContent) {
	            HttpContent content = (HttpContent) msg;
 
	            if (content instanceof LastHttpContent) {
	            	XBean bean=Parser.parse(content.content().toString(Charset.forName(encoding)));
	            	System.out.println(bean);
	            	ctx.close();
	               
	            }
	        }
	    }

	    @Override
	    public void exceptionCaught(
	            ChannelHandlerContext ctx, Throwable cause) throws Exception {
	        cause.printStackTrace();
	        ctx.close();
	    }
}
