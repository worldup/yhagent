package com.nangua.yhagent.command;

import com.nangua.yhagent.bean.XBean;
import com.nangua.yhagent.bean.xml.Helper;
import com.nangua.yhagent.main.YHChannelFactory;

public   class BaseHttpCommandAdapter<T extends XBean>  extends BaseHttpCommand{
	private T bean;
	private String commandId;
	public BaseHttpCommandAdapter(YHChannelFactory channelFactory) {
		super(channelFactory);
	 
	}
	public BaseHttpCommandAdapter setXBean(T bean){
	 this.bean=bean;
	 return this;
	}
	public BaseHttpCommandAdapter setCommandId(String commandId){
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
