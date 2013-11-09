package com.nangua.yhagent.billing.bean.base;

import com.nangua.yhagent.billing.bean.requestinfo.AddVodLogRequestInfo;

public class RequestWrapper {
	public static <T extends RequestInfo>   Request genRequest(final T t) {
		return new Request() {
			public RequestInfo getRequestInfo() {
				return t;
			}
		};

	}
	public static void main(String []args){
		System.out.println(RequestWrapper.genRequest(new AddVodLogRequestInfo()).toXml());
	}
}
