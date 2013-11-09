package com.nangua.yhagent.billing.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nangua.yhagent.billing.bean.Occupancy;
import com.nangua.yhagent.billing.bean.VodLog;
import com.nangua.yhagent.billing.bean.base.RequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.AdTimeshiftLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.AddVodLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.GetProgramListRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.GetProgramPriceRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.ListTimeshiftLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.ListVodLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.SetProgramPriceRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.SetTimeshiftDayPriceRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.VodDayPriceRequestInfo;
import com.nangua.yhagent.billing.command.BillingInvoker;

public class BillingHttpClient {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "yhagent-ioc.xml" });

		BillingInvoker invoker = context.getBean(BillingInvoker.class);

		invoker.releaseResource();

	}

	// test getProgramList
	static RequestInfo getProgramList() {
		GetProgramListRequestInfo requestInfo = new GetProgramListRequestInfo();
		return requestInfo;
	}

	// test setProgramPrice
	static RequestInfo setProgramPrice() {
		SetProgramPriceRequestInfo requestInfo = new SetProgramPriceRequestInfo();
		requestInfo.hotelCode = "1111";
		requestInfo.programId = "1211";
		requestInfo.programPrice = "122111";
		return requestInfo;
	}
//	 test setVodDayPrice
	static RequestInfo setVodDayPrice() {
		VodDayPriceRequestInfo requestInfo = new VodDayPriceRequestInfo();
		 
		return requestInfo;
	}
//	 test setProgramPrice
	static RequestInfo getProgramPrice() {
		GetProgramPriceRequestInfo requestInfo = new GetProgramPriceRequestInfo();
		requestInfo.HotelCode="111";
		requestInfo.ProgramId = "1211";
		requestInfo.ProgramPrice = "122111";
		 
		return requestInfo;
	}
//	 test setProgramPrice
	static RequestInfo addVodLog() {
		AddVodLogRequestInfo requestInfo = new AddVodLogRequestInfo();
		List<VodLog> vodLogs=new ArrayList<VodLog>();
		VodLog vodLog=new VodLog();
		vodLog.setEndTime("1111");
		vodLogs.add(vodLog);
		requestInfo.vodLogs =vodLogs;
		 
		return requestInfo;
	}
//	 test setProgramPrice
	static RequestInfo listVodLog() {
		ListVodLogRequestInfo requestInfo = new ListVodLogRequestInfo();
		requestInfo.HotelCode = "1111";
		requestInfo.RoomNumber = "1211";
		requestInfo.PolicyType = "122111";
		requestInfo.Occupancies=new ArrayList<Occupancy>();
		return requestInfo;
	}
//	 test setProgramPrice
	static RequestInfo setTimeshiftDayPrice() {
		SetTimeshiftDayPriceRequestInfo requestInfo = new SetTimeshiftDayPriceRequestInfo();
		 
		return requestInfo;
	}
//	 test setProgramPrice
	static RequestInfo adTimeshiftLog() {
		AdTimeshiftLogRequestInfo requestInfo = new AdTimeshiftLogRequestInfo();
		 
		return requestInfo;
	}
	// test setProgramPrice
	static RequestInfo listTimeshiftLog() {
		ListTimeshiftLogRequestInfo requestInfo = new ListTimeshiftLogRequestInfo();
		 
		return requestInfo;
	}
	
}
