package com.nangua.yhagent.billing.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.nangua.yhagent.billing.bean.base.Auth;
import com.nangua.yhagent.billing.bean.base.Service;
import com.nangua.yhagent.billing.bean.requestinfo.AdTimeshiftLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.AddVodLogRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.GetProgramListRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.GetProgramPriceRequestInfo;
import com.nangua.yhagent.billing.bean.requestinfo.ListTimeshiftLogRequestInfo;
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
    @Autowired
    private DirectXmlCommand dcommand;
	// ȡ��ӰƬ�б�ӿ�
    private Auth auth;
    
	public Auth getAuth() {
		return auth;
	}
	public void setAuth(Auth auth) {
		this.auth = auth;
	}
	public void getProgramList(GetProgramListRequestInfo requestInfo) {
		 
		 Service service =new Service();
		 service.setBusiness("VOD").setFunction("getProgramList");
		 command.setData(requestInfo, auth, service).execute();
	 
	}
    //���ý�Ŀ�۸�ӿ� setProgramPrice ����
	public void setProgramPrice(SetProgramPriceRequestInfo requestInfo){
		 
		 Service service =new Service();
		 service.setBusiness("VOD").setFunction("setProgramPrice");
		 command.setData(requestInfo, auth, service).execute();
	}
	//���õ㲥�����շѼ۸�ӿ� setVodDayPrice
	public void setVodDayPrice(VodDayPriceRequestInfo requestInfo){
		 
		 Service service =new Service();
		 service.setBusiness("VOD").setFunction("setVodDayPrice");
		 command.setData(requestInfo, auth, service).execute();
	}
	//ȡ�ý�Ŀ�۸�ӿ� getProgramPrice
	public void getProgramPrice(GetProgramPriceRequestInfo requestInfo){
		 
		 Service service =new Service();
		 service.setBusiness("VOD").setFunction("getProgramPrice");
		 command.setData(requestInfo, auth, service).execute();
	}
	//��ӵ㲥��־�ӿ� addVodLog
	public void addVodLog(AddVodLogRequestInfo requestInfo){
		 
		 Service service =new Service();
		 service.setBusiness("VOD").setFunction("addVodLog");
		 command.setData(requestInfo, auth, service).execute();
	}
	//ȡ��ָ������㲥��־�ӿ� listVodLog
	public void listVodLog(ListVodLogRequestInfo requestInfo){
		  
		 Service service =new Service();
		 service.setBusiness("VOD").setFunction("listVodLog");
		 command.setData(requestInfo, auth, service).execute();
	}
	//����ʱ�ư����շѼ۸�ӿ� setTimeshiftDayPrice
	public void setTimeshiftDayPrice(SetTimeshiftDayPriceRequestInfo requestInfo){
		 
		 Service service =new Service();
		 service.setBusiness("TIMESHIFT").setFunction("setTimeshiftDayPrice");
		 command.setData(requestInfo, auth, service).execute();
	}
	//���ʱ����־�ӿ� adTimeshiftLog
	public void adTimeshiftLog(AdTimeshiftLogRequestInfo requestInfo){
		 
		 Service service =new Service();
		 service.setBusiness("TIMESHIFT").setFunction("adTimeshiftLog");
		 command.setData(requestInfo, auth, service).execute();
	}
	//ȡ��ָ������ʱ����־�ӿ� listTimeshiftLog
	public void listTimeshiftLog(ListTimeshiftLogRequestInfo requestInfo){
		 
		 Service service =new Service();
		 service.setBusiness("TIMESHIFT").setFunction("listTimeshiftLog");
		 command.setData(requestInfo, auth, service).execute();
	}
	public void sendXmlDirectly(String cmdBody){
		dcommand.setCmdBody(cmdBody).execute();
	 
	}
	public void releaseResource(){
		channelFactory.destroy();
	}
}
