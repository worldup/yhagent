package com.nangua.yhagent.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.avaje.ebean.Ebean;

 

 

 
@Service
public class HttpServer {
	
	
	private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
	@Value("${httpServer.IP}")
	private   String IP ;
	@Value("${httpServer.PORT}")
	private  int PORT ;
	@Autowired
	private HttpRequestRouterHandler httpRequestRouterHandler;
	/**���ڷ��䴦��ҵ���̵߳��߳������ */
	protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2;	//Ĭ��
	/** ҵ������̴߳�С*/
	protected static final int BIZTHREADSIZE = 4;
	private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
	private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);
	protected   void run() throws Exception {
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup);
		b.channel(NioServerSocketChannel.class);
		b.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
		/*		 
				pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
				pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));*/
				pipeline.addLast("http",new HttpServerCodec());
			    
				pipeline.addLast("print",httpRequestRouterHandler);
				
				pipeline.addLast("logger",new LoggingHandler());
			}
		});

		b.bind(IP, PORT).sync();
	}

	protected   void shutdown() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "yhagent-ioc.xml" });
		logger.info("��ʼ����HttpServer������...");
		HttpServer httpServer=context.getBean(HttpServer.class);
		httpServer.run();
		logger.info("httpServer�������Ѿ�����");
		logger.info("�������ݿ�����...");
		Ebean.createSqlQuery("select 1 from dual").findUnique();
		logger.info("���ݿ���������");
//		TcpServer.shutdown();
	}
}
