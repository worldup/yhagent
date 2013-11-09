package com.nangua.yhagent.billing.bean;

import javax.xml.bind.annotation.XmlAttribute;

public class ResultInfo {
	private String resultcode="";
	private String errormessage="";
	@XmlAttribute(name="errormessage")
	public String getErrormessage() {
		return errormessage;
	}
	public ResultInfo setErrormessage(String errormessage) {
		this.errormessage = errormessage;
		return this;
	}
	@XmlAttribute(name="resultcode")
	public String getResultcode() {
		return resultcode;
	}
	public ResultInfo setResultcode(String resultcode) {
		this.resultcode = resultcode;
		return this;
	}
	
}
