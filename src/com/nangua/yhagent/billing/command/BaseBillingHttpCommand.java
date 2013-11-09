package com.nangua.yhagent.billing.command;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.nangua.yhagent.billing.bean.base.Auth;
import com.nangua.yhagent.billing.bean.base.Service;
import com.nangua.yhagent.billing.main.BillingChannelFactory;

public abstract class BaseBillingHttpCommand  {
	@Value("${yhagent.billing.context}")
	private String contextPath;
    @Value("${yhagent.encoding}")
    private String encoding;
    @Autowired
	private BillingChannelFactory channelFactory;
	
	 
 
    
	public abstract String cmdBody();
 
	
   
	public void execute() {
		  String xmlContent=cmdBody();
		Channel channel =channelFactory.getYHChannel();
	    HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);

	     // Prepare the HTTP request.
	    // Use the PostBody encoder
	     HttpPostRequestEncoder bodyRequestEncoder = null;
	    try {
	     HttpRequest request =
	             new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, new URI(contextPath).toASCIIString());

	    
	    
	         bodyRequestEncoder = new HttpPostRequestEncoder(factory, request, false); // false not multipart
	         bodyRequestEncoder.addBodyAttribute("reqxml", xmlContent);
	         request = bodyRequestEncoder.finalizeRequest();
	         

		     // send request
		     channel.write(request);
		     // test if request was chunked and if so, finish the write
		     if (bodyRequestEncoder.isChunked()) {
		         // could do either request.isChunked()
		         // either do it through ChunkedWriteHandler
		         channel.writeAndFlush(bodyRequestEncoder).awaitUninterruptibly();
		     }  else {
		         channel.flush();
		     }
		     channel.closeFuture().sync();
	    } catch (Exception e) {
	         // if an encoding error occurs
	         e.printStackTrace();
	     }

	    

	    
	     // Do not clear here since we will reuse the InterfaceHttpData on the
	     // next request
	     // for the example (limit action on client side). Take this as a
	     // broadcast of the same
	     // request on both Post actions.
	     //
	     // On standard program, it is clearly recommended to clean all files
	     // after each request
	     // bodyRequestEncoder.cleanFiles();

	     // Wait for the server to close the connection.
	  


	}
}
