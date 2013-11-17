package com.nangua.yhagent.billing.bean.base;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.nangua.yhagent.billing.bean.requestinfo.AdTimeshiftLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.AddVodLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.GetProgramListRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.GetProgramPriceRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.ListTimeshiftLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.ListVodLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.SetProgramPriceRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.SetTimeshiftDayPriceRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.VodDayPriceRequestInfo;



@XmlSeeAlso({AddVodLogRequestInfo.class,AdTimeshiftLogRequestInfo.class,
	GetProgramListRequestInfo.class,ListTimeshiftLogRequestInfo.class,
	ListVodLogRequestInfo.class,SetProgramPriceRequestInfo.class,
	SetTimeshiftDayPriceRequestInfo.class,VodDayPriceRequestInfo.class,
	GetProgramPriceRequestInfo.class}) 
public class RequestInfo {
   
}
