package com.nangua.yhagent.billing.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.nangua.yhagent.billing.bean.base.Auth;
import com.nangua.yhagent.billing.bean.base.Service;
import com.nangua.yhagent.billing.bean.requestinfo.AdTimeshiftLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.AddVodLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.GetProgramListRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.GetProgramPriceRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.ListVodLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.SetProgramPriceRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.SetTimeshiftDayPriceRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.VodDayPriceRequestInfo;
import com.nangua.yhagent.billing.main.BillingChannelFactory;

@org.springframework.stereotype.Service
public class BillingInvoker {
	@Autowired
	private BillingChannelFactory channelFactory;
    @Autowired
    private BaseBillingHttpCommandAdapter command;
	// 取得影片列表接口
	public void getProgramList(GetProgramListRequestInfo requestInfo) {
		 Auth auth=new Auth();
		 auth.setAuthToken("11111").setHotelCode("222");
		 Service service =new Service();
		 service.setBusiness("VOD").setFunction("getProgramList");
		 command.setData(requestInfo, auth, service).execute();
	 
	}
    //设置节目价格接口 setProgramPrice 设置
	public void setProgramPrice(SetProgramPriceRequestInfo requestInfo){
		 Auth auth=new Auth();
		 auth.setAuthToken("11111").setHotelCode("222");
		 Service service =new Service();
		 service.setBusiness("VOD").setFunction("getProgramList");
		 command.setData(requestInfo, auth, service).execute();
	}
	//设置点播按天收费价格接口 setVodDayPrice
	public void setVodDayPrice(VodDayPriceRequestInfo requestInfo){
		 Auth auth=new Auth();
		 auth.setAuthToken("11111").setHotelCode("222");
		 Service service =new Service();
		 service.setBusiness("VOD").setFunction("setVodDayPrice");
		 command.setData(requestInfo, auth, service).execute();
	}
	//取得节目价格接口 getProgramPrice
	public void getProgramPrice(GetProgramPriceRequestInfo requestInfo){
		 Auth auth=new Auth();
		 auth.setAuthToken("11111").setHotelCode("222");
		 Service service =new Service();
		 service.setBusiness("VOD").setFunction("getProgramPrice");
		 command.setData(requestInfo, auth, service).execute();
	}
	//添加点播日志接口 addVodLog
	public void addVodLog(AddVodLogRequestInfo requestInfo){
		 Auth auth=new Auth();
		 auth.setAuthToken("11111").setHotelCode("222");
		 Service service =new Service();
		 service.setBusiness("VOD").setFunction("addVodLog");
		 command.setData(requestInfo, auth, service).execute();
	}
	//取得指定房间点播日志接口 listVodLog
	public void listVodLog(ListVodLogRequestInfo requestInfo){
		 Auth auth=new Auth();
		 auth.setAuthToken("11111").setHotelCode("222");
		 Service service =new Service();
		 service.setBusiness("VOD").setFunction("listVodLog");
		 command.setData(requestInfo, auth, service).execute();
	}
	//设置时移按天收费价格接口 setTimeshiftDayPrice
	public void setTimeshiftDayPrice(SetTimeshiftDayPriceRequestInfo requestInfo){
		 Auth auth=new Auth();
		 auth.setAuthToken("11111").setHotelCode("222");
		 Service service =new Service();
		 service.setBusiness("TIMESHIFT").setFunction("setTimeshiftDayPrice");
		 command.setData(requestInfo, auth, service).execute();
	}
	//添加时移日志接口 adTimeshiftLog
	public void adTimeshiftLog(AdTimeshiftLogRequestInfo requestInfo){
		 Auth auth=new Auth();
		 auth.setAuthToken("11111").setHotelCode("222");
		 Service service =new Service();
		 service.setBusiness("TIMESHIFT").setFunction("setTimeshiftDayPrice");
		 command.setData(requestInfo, auth, service).execute();
	}
	//取得指定房间时移日志接口 listTimeshiftLog
	public void listTimeshiftLog(AdTimeshiftLogRequestInfo requestInfo){
		 Auth auth=new Auth();
		 auth.setAuthToken("11111").setHotelCode("222");
		 Service service =new Service();
		 service.setBusiness("TIMESHIFT").setFunction("listTimeshiftLog");
		 command.setData(requestInfo, auth, service).execute();
	}
	public void releaseResource(){
		channelFactory.destroy();
	}
}
