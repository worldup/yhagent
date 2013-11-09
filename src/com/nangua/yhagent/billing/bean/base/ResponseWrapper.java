package com.nangua.yhagent.billing.bean.base;

public class ResponseWrapper {
	public static <T extends ResponseResult>   Response genResponse  ( final T t) {
		return new Response() {
			public ResponseResult getResponseResult() {
				return t;
			}
		};

	}
}
