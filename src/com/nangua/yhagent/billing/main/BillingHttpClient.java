package com.nangua.yhagent.billing.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.nangua.yhagent.billing.bean.Occupancy;
import com.nangua.yhagent.billing.bean.VodLog;
import com.nangua.yhagent.billing.bean.base.Auth;
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

@Service
public class BillingHttpClient {

	public static void main(String[] args) {
		BillingInvoker invoker = null;
		 
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "yhagent-ioc.xml" });
			 
			invoker = context.getBean(BillingInvoker.class);
			 
			Auth auth=new Auth();
			auth.setAuthToken("1111");
			auth.setHotelCode("1");
			invoker.setAuth(auth);
			 
		 
			//    invoker.getProgramList(getProgramList());
			 // invoker.setProgramPrice(setProgramPrice());
			// invoker.setVodDayPrice(setVodDayPrice());
		
		  invoker.addVodLog(addVodLog());//ok
		  
		  invoker.listVodLog(listVodLog());
			// invoker.listTimeshiftLog(listTimeshiftLog());
			 // invoker.adTimeshiftLog(adTimeshiftLog());
		//  invoker.setTimeshiftDayPrice(setTimeshiftDayPrice());//价格不能过低
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (invoker != null)
				invoker.releaseResource();
		 
		}
	}

	// test getProgramList
	static GetProgramListRequestInfo getProgramList() {
		GetProgramListRequestInfo requestInfo = new GetProgramListRequestInfo();
		return requestInfo;
	}

	// test setProgramPrice
	static SetProgramPriceRequestInfo setProgramPrice() {
		SetProgramPriceRequestInfo requestInfo = new SetProgramPriceRequestInfo();
		requestInfo.HotelCode = "1";
		requestInfo.ProgramId = "1";
		requestInfo.ProgramPrice = "16.00";
		return requestInfo;
	}

	// test setVodDayPrice
	static VodDayPriceRequestInfo setVodDayPrice() {
		VodDayPriceRequestInfo requestInfo = new VodDayPriceRequestInfo();
		requestInfo.HotelCode = "YH";
		requestInfo.DayPrice = "200.00";
		return requestInfo;
	}

	// test setProgramPrice
	static GetProgramPriceRequestInfo getProgramPrice() {
		GetProgramPriceRequestInfo requestInfo = new GetProgramPriceRequestInfo();
		requestInfo.HotelCode = "YH";
		requestInfo.ProgramId = "1";
		requestInfo.ProgramPrice = "0";
		return requestInfo;
	}

	// test setProgramPrice
	static AddVodLogRequestInfo addVodLog() {
		AddVodLogRequestInfo requestInfo = new AddVodLogRequestInfo();
		List<VodLog> vodLogs = new ArrayList<VodLog>();
		VodLog vodLog = new VodLog();
		vodLog.setLogId("1232");
		vodLog.setHotelCode("1");
		vodLog.setRoomNumber("10");
		vodLog.setVodClientId("34-40-B5-8D-82-B5");
		vodLog.setProgramId("1");
		vodLog.setStartTime("1");
		vodLog.setEndTime("200");
		vodLog.setTotalTime("199");
		vodLog.setPolicyType("0");
		vodLogs.add(vodLog);
		requestInfo.setVodLogs(vodLogs);
		return requestInfo;
	}

	// test setProgramPrice
	static ListVodLogRequestInfo listVodLog() {
		ListVodLogRequestInfo requestInfo = new ListVodLogRequestInfo();
		requestInfo.HotelCode = "1";
		requestInfo.RoomNumber = "1";
		requestInfo.PolicyType = "0";

		List<Occupancy> occupancies = new ArrayList<Occupancy>();
		Occupancy occupancy = new Occupancy();
		occupancy.RoomNumber = "10";
		occupancy.StartTime = "1";
		occupancy.EndTime = "200";
		occupancies.add(occupancy);
		requestInfo.Occupancies = occupancies;
		return requestInfo;
	}

	// test setProgramPrice
	static SetTimeshiftDayPriceRequestInfo setTimeshiftDayPrice() {
		SetTimeshiftDayPriceRequestInfo requestInfo = new SetTimeshiftDayPriceRequestInfo();
		requestInfo.HotelCode = "1";
		requestInfo.DayPrice = "600.00";
		return requestInfo;
	}

	// test setProgramPrice
	static AdTimeshiftLogRequestInfo adTimeshiftLog() {
		AdTimeshiftLogRequestInfo requestInfo = new AdTimeshiftLogRequestInfo();
		VodLog vodLog = new VodLog();
		vodLog.setHotelCode("1");
		vodLog.setRoomNumber("1");
		vodLog.setVodClientId("34-40-B5-8D-83-B6");
		vodLog.setChannelId("1");
		vodLog.setStartTime("1312983240");
		vodLog.setEndTime("1312983281");
		vodLog.setTotalTime("41");
		requestInfo.vodLogs=new ArrayList<VodLog> ();
		requestInfo.vodLogs.add(vodLog);
		return requestInfo;
	}

	// test setProgramPrice
	static ListTimeshiftLogRequestInfo listTimeshiftLog() {
		ListTimeshiftLogRequestInfo requestInfo = new ListTimeshiftLogRequestInfo();
		requestInfo.HotelCode = "1";
		requestInfo.RoomNumber = "1";
		Occupancy occupancy = new Occupancy();
		occupancy.EndTime = " abc";
		occupancy.StartTime = "edf ";
		occupancy.RoomNumber = "1";
		List<Occupancy> occupancies = new ArrayList<Occupancy>();
		requestInfo.Occupancies = occupancies;
		return requestInfo;
	}

 

}
