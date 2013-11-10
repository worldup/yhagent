package com.nangua.yhagent.billing.bean.base;

import javax.xml.bind.annotation.XmlAttribute;


public class Service  {
public Service(String business,String function){
	this.business=business;
	this.function=function;
}
public Service(){
	
}
 private String business;
 private String function;
 @XmlAttribute(name="business")
public String getBusiness() {
	return business;
}
public Service setBusiness(String business) {
	this.business = business;
	return this;
}
@XmlAttribute(name="function")
public String getFunction() {
	return function;
}
public Service setFunction(String function) {
	this.function = function;
	return this;
}
}
