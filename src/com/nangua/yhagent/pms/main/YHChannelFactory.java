package com.nangua.yhagent.pms.main;

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
 
public class YHChannelFactory {
	@Value("${YHChannelFactory.host}")
	private String host;
	@Value("${YHChannelFactory.port}")
	private int port;
	@Value("${YHChannelFactory.NioEventLoopGroup.ThreadSize}")
	private int maxThreadSize;
	@Autowired
	private HttpInit httpInit;
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
