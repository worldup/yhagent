package com.nangua.yhagent.billing.bean.responseresult;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.nangua.yhagent.billing.bean.VodLog;
import com.nangua.yhagent.billing.bean.base.ResponseResult;

public class ListVodLogResponseResult extends ResponseResult{
	 public String StartTime;
	 public String EndTime;
	 public String TotalFee;
	 public String PolicyType;
	 @XmlElementWrapper(name="VodLogs")
	 @XmlElement(name="Log")
	 public List<VodLog> vodLogs;
}
