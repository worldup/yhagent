package com.nangua.yhagent.billing.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.base.Auth;
import com.nangua.yhagent.billing.bean.base.Service;

@XmlRootElement(name="Request")
public class AdTimeshiftLogRequest {
	public Auth Auth ;
	public Service Service ;
	public RequestInfo RequestInfo;
	static class RequestInfo{
		@XmlElementWrapper(name="ChannelLogs")
		@XmlElement(name="Log")
		public List<VodLog> vodLogs;
	}

}
