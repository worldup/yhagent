package com.nangua.yhagent.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nangua.yhagent.server.command.Dispatcher;

public class HttpRequestPrintHandler extends ChannelInboundHandlerAdapter {
	Logger logger = LoggerFactory.getLogger(HttpRequestPrintHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {

		if (msg instanceof HttpRequest) {

		} else if (msg instanceof HttpContent) {
			HttpContent chunk = (HttpContent) msg;
			if (chunk instanceof LastHttpContent) {
				String result = chunk.content().toString(
						Charset.forName("UTF-8"));
				logger.info(result);
				String exeResult = Dispatcher.dispatch(result);
				FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK);
				response.content().writeBytes(
						exeResult.getBytes());
				response.headers().set(HttpHeaders.Names.CONTENT_TYPE,
						"text/plain; charset=UTF-8");
				ChannelFuture future = ctx.writeAndFlush(response);
				future.addListener(ChannelFutureListener.CLOSE);

			}
		} else {
			ctx.fireChannelRead(msg);
		}
	}
    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}