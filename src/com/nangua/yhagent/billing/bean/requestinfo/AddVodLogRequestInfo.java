package com.nangua.yhagent.billing.bean.requestinfo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.nangua.yhagent.billing.bean.VodLog;
import com.nangua.yhagent.billing.bean.base.RequestInfo;

public class AddVodLogRequestInfo extends  RequestInfo{
	@XmlElementWrapper(name = "VodLogs")
	@XmlElement(name = "Log")
	public List<VodLog> vodLogs;
} 