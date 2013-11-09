package com.nangua.yhagent.billing.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.nangua.yhagent.billing.bean.base.Auth;
import com.nangua.yhagent.billing.bean.base.Request;
import com.nangua.yhagent.billing.bean.base.RequestInfo;
import com.nangua.yhagent.billing.bean.base.Service;

public class AddVodLogRequest extends Request {
	
	private AddVodLogRequestInfo requestInfo;
	@Override
	public AddVodLogRequestInfo getRequestInfo(){
		return requestInfo;
	} 
	public void setRequestInfo(AddVodLogRequestInfo requestInfo){
		this.requestInfo=requestInfo; 
	} 

	static class AddVodLogRequestInfo  extends RequestInfo{
		@XmlElementWrapper(name = "VodLogs")
		@XmlElement(name = "Log")
		public List<VodLog> vodLogs;
	}
	public static void main(String []args){
		AddVodLogRequest request=new AddVodLogRequest();
		request.Auth=new Auth();
		request.Service=new Service();
		AddVodLogRequestInfo requestInfo=new AddVodLogRequestInfo();
		
		request.setRequestInfo(requestInfo);
		System.out.println(request.toXml());
	} 
}
