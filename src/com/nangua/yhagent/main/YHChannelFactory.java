package com.nangua.yhagent.main;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class YHChannelFactory {
	EventLoopGroup group = null;
	Bootstrap b = new Bootstrap();
	static class FactoryHolder {
		static YHChannelFactory instance = new YHChannelFactory();
	}

	private YHChannelFactory() {
		group = new NioEventLoopGroup(8);
		
		b.group(group).channel(NioSocketChannel.class).handler(new HttpInit());
	}

	public static YHChannelFactory newInstance() {
		return FactoryHolder.instance;
	}

	public void destroy() {
		// Shut down executor threads to exit.
		group.shutdownGracefully();
		b=null;
	}
	public Channel getYHChannel(){
		Channel result=null;
		try {
			result= b.connect("192.168.0.82", 80).sync().channel();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
