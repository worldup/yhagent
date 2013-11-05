package com.nangua.yhagent.pms.command;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.nangua.yhagent.pms.main.YHChannelFactory;

public abstract class BaseHttpCommand  {
	@Value("${BaseHttpCommand.contextPath}")
	private String contextPath;
    @Value("${yhagent.encoding}")
    private String encoding;
    @Autowired
	private YHChannelFactory channelFactory;
	
	 
	public abstract String genCommandId();

	public abstract String cmdBody();
 
	
   
	public void execute() {
		Channel ch =channelFactory.getYHChannel();
		String cmdBody = "";
		try {
			cmdBody = java.net.URLEncoder.encode(cmdBody(), encoding);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpRequest request = new DefaultHttpRequest(HttpVersion.HTTP_1_0,
				HttpMethod.GET, contextPath+"?command="
						+ genCommandId() + "&cmdBody=" + cmdBody);

		request.headers().set(HttpHeaders.Names.CONNECTION,
				HttpHeaders.Values.KEEP_ALIVE);

		// Send the HTTP request.
		ch.writeAndFlush(request);

		// Wait for the server to close the connection.
		try {
			ch.closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
