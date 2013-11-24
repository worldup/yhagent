package com.nangua.yhagent.billing.http.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nangua.yhagent.billing.bean.xml.Helper;
@Service
@Sharable
public class Msg2ServiceHandler  extends MessageToMessageDecoder {
	 @Value("${yhagent.billing.encoding}")
	 private String encoding; 
	 
	@Override
	protected void decode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
		 
		com.nangua.yhagent.billing.bean.base.Service service =decodeMsg(msg.toString());
		out.add(service);
	}
   private com.nangua.yhagent.billing.bean.base.Service decodeMsg(String msg){
	  String business= Helper.findServiceBusiness(msg);
	  String function= Helper.findServiceFunction(msg);
	   com.nangua.yhagent.billing.bean.base.Service service=new com.nangua.yhagent.billing.bean.base.Service(business,function);
	   
	  
	   return service;
   }
}
