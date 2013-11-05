package com.nangua.yhagent.billing.command;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.nangua.yhagent.pms.bean.XBean;
import com.nangua.yhagent.pms.bean.xml.Helper;

@Service
@Scope("prototype")
public   class BaseBillingHttpCommandAdapter  extends BaseBillingHttpCommand{
	private XBean bean;
	private String commandId;
	 
	public BaseBillingHttpCommandAdapter setXBean(XBean bean){
	 this.bean=bean;
	 return this;
	}
	public BaseBillingHttpCommandAdapter setCommandId(String commandId){
		this.commandId=commandId;
		return this;
	}
	@Override
	public String cmdBody() {
	    return Helper.genXmlStr(bean);
	}
	@Override
	public String genCommandId() {
		// TODO Auto-generated method stub
		return commandId;
	}

	 

}
