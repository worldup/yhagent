package com.nangua.yhagent.billing.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.base.Auth;
import com.nangua.yhagent.billing.bean.base.Service;
import com.nangua.yhagent.billing.bean.xml.Helper;
@XmlRootElement(name = "Request")
public class GetProgramListRequest {

	private Auth auth;

	private Service service;

	@XmlElement(name = "Auth")
	public Auth getAuth() {
		return auth;
	}

	public GetProgramListRequest setAuth(Auth auth) {
		this.auth = auth;
		return this;
	}

	@XmlElement(name = "Service")
	public Service getService() {
		return service;
	}

	public GetProgramListRequest setService(Service service) {
		this.service = service;
		return this;
	}
	public static void main(String []args){
		GetProgramListRequest request=new GetProgramListRequest();
		request.setAuth(new Auth().setAuthToken("11111").setHotelCode("yhHotel")).setService(new Service().setBusiness("ceshi").setFunction("tets"));
		String xmlStr=Helper.genXmlStr(request);
		System.out.println(xmlStr);
		request=Helper.genType(xmlStr, GetProgramListRequest.class);
		System.out.println(request);
	}
}
