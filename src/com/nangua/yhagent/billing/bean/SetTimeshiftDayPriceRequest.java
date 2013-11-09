package com.nangua.yhagent.billing.bean;

import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.base.Auth;
import com.nangua.yhagent.billing.bean.base.Service;

@XmlRootElement(name="Request")
public class SetTimeshiftDayPriceRequest {
 public Auth  Auth ;
 public Service  Service ;
 public RequestInfo RequestInfo;
 static class RequestInfo{
	 public String HotelCode;
	 public String DayPrice;
 }
}
