package com.nangua.yhagent.billing.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nangua.yhagent.billing.command.BillingInvoker;

public class BillingHttpClient {
	public static void main(String[] args) {

		ApplicationContext context =
		    new ClassPathXmlApplicationContext(new String[] {"yhagent-ioc.xml"});
		
		BillingInvoker invoker = context.getBean(BillingInvoker.class);
		
		 
		invoker.test();
		 
		
		invoker.releaseResource();

	}

}
