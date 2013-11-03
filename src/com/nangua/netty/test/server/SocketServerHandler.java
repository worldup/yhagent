package com.nangua.netty.test.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.string.StringDecoder;

import java.util.List;

public class SocketServerHandler {
	 private static final ThreadLocal<StringBuilder> BUILDERS = new ThreadLocal<StringBuilder>() {
	        @Override
	        protected StringBuilder initialValue() {
	            return new StringBuilder(512);
	        }

	        @Override
	        public StringBuilder get() {
	            StringBuilder builder = super.get();
	            builder.setLength(0);
	            return builder;
	        }
	    };
	    public static String readLine(ByteBuf buffer){
			 StringBuilder sb = BUILDERS.get();
		        int lineLength = 0;
		        while (buffer.readableBytes()>0) {
		            byte nextByte = buffer.readByte();
		            if (nextByte == HttpConstants.CR) {
		                nextByte = buffer.readByte();
		                if (nextByte == HttpConstants.LF) {
		                	
		                    return sb.toString();
		                }
		            } else if (nextByte == HttpConstants.LF) {
		                return sb.toString();
		            } else {
		                
		                lineLength ++;
		                sb.append((char) nextByte);
		            }
		        }
		        return sb.toString();
	    }
	static ChannelInboundHandlerAdapter handler1=new ByteToMessageDecoder(){

		@Override
		protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
			
		  
		
		}
		
	};
	static ChannelInboundHandlerAdapter handler2=new StringDecoder(){
	    @Override
	    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
	        System.out.println(msg.toString());
	    }
 
		
	};
}
