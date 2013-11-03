package com.nangua.yhagent.http.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;

import java.nio.charset.Charset;

import com.nangua.yhagent.bean.XBean;
import com.nangua.yhagent.command.response.Parser;

public class YHAgentHttpHandler extends SimpleChannelInboundHandler<HttpObject>{
	 @Override
	    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
	        if (msg instanceof HttpResponse) {
	            HttpResponse response = (HttpResponse) msg;
 
	        }
	        if (msg instanceof HttpContent) {
	            HttpContent content = (HttpContent) msg;
 
	            if (content instanceof LastHttpContent) {
	            	XBean bean=Parser.parse(content.content().toString(Charset.forName("GBK")));
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
