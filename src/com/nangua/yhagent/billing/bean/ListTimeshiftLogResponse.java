package com.nangua.yhagent.billing.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.base.Service;

@XmlRootElement(name="Result")
public class ListTimeshiftLogResponse {
	public ResultInfo ResultInfo ;
	public Service Service ;
	public ResultData  ResultData;
	static class ResultData{
		public String StartTime;
		public String EndTime;
		public String TotalFee;
		@XmlElementWrapper(name="ChannelLogs")
		@XmlElement(name="Log")
		public List<VodLog> vodLogs;
	}
	

}
