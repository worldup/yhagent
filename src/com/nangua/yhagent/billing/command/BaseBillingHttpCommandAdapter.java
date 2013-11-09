package com.nangua.yhagent.billing.command;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.nangua.yhagent.billing.bean.base.Auth;
import com.nangua.yhagent.billing.bean.base.Request;
import com.nangua.yhagent.billing.bean.base.RequestInfo;
import com.nangua.yhagent.billing.bean.base.RequestWrapper;

@Service
@Scope("prototype")
public   class BaseBillingHttpCommandAdapter  extends BaseBillingHttpCommand{
	private RequestInfo  requestInfo;
	private Auth  auth   ;
	private com.nangua.yhagent.billing.bean.base.Service  service  ;
	public BaseBillingHttpCommandAdapter setData(RequestInfo requestInfo,Auth auth,com.nangua.yhagent.billing.bean.base.Service  service){
	  this.requestInfo=requestInfo;
	  this.auth=auth;
	  this.service=service;
	 return this;
	}
 
	@Override
	public String cmdBody() {
	    Request request= RequestWrapper.genRequest(requestInfo);
	    request.Auth=auth;
	    request.Service=service;
	    return request.toXml();
	}
	 
	 
	 

}
