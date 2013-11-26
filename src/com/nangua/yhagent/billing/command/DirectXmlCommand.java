package com.nangua.yhagent.billing.command;

import org.springframework.stereotype.Service;

@Service
public class DirectXmlCommand  extends BaseBillingHttpCommand{
	private String cmdBody;
	public DirectXmlCommand setCmdBody(String cmdBody){
		this.cmdBody=cmdBody;
		return this;
	}
	@Override
	public String cmdBody() {
		 return this.cmdBody;
	}

}
