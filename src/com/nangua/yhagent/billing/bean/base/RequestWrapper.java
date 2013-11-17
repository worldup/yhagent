package com.nangua.yhagent.billing.bean.base;

import com.nangua.yhagent.billing.bean.requestinfo.AddVodLogRequestInfo;

public class RequestWrapper {
	public static <T extends RequestInfo>   Request genRequest(final T t) {
		Request request=new   Request();
		request.setRequestInfo(t) ;
		return request;
		 

	}
	public static void main(String []args){
		Request request=RequestWrapper.genRequest(new AddVodLogRequestInfo());
		request.Service=new Service();
		request.Service.setBusiness("!111");
		request.Service.setFunction("22222");
		 
		System.out.println(request.toXml());
	}
}
