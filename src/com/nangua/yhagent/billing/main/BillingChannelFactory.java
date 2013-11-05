package com.nangua.yhagent.billing.main;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
 
public class BillingChannelFactory {
	@Value("${yhagent.billing.host}")
	private String host;
	@Value("${yhagent.billing.port}")
	private int port;
	@Value("${yhagent.billing.NioEventLoopGroup.ThreadSize}")
	private int maxThreadSize;
	@Autowired
	private BillingHttpInit httpInit;
	EventLoopGroup group = null;
	Bootstrap b = new Bootstrap();
	 
	@PostConstruct 
	 void init() {
		group = new NioEventLoopGroup(maxThreadSize);
		
		b.group(group).channel(NioSocketChannel.class).handler(httpInit);
	}

	public void destroy() {
		// Shut down executor threads to exit.
		group.shutdownGracefully();
		b=null;
	}
	public Channel getYHChannel(){
		Channel result=null;
		try {
			result= b.connect(host,port).sync().channel();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
