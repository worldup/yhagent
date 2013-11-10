package com.nangua.yhagent.billing;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
 @XmlRootElement(name="Service1")
public enum BillingService {
	
	LISTVODLOG("VOD","listVodLog");
	private String business;
	 private String function;
	 @XmlAttribute(name="business")
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
		 
	}
	@XmlAttribute(name="function")
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
		 
	}
	private BillingService(String business,String function){
	
		this.business=business;
		this.function=function;
	}
}
