package com.nangua.yhagent.pms.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
public class CommonRespBean implements XBean{

	private String cmdId = "";

	private String ErrorMsg = "";

	private String ErrorCode = "";
	@XmlAttribute(name="cmdId")
	public String getCmdId() {
		return cmdId;
	}

	public void setCmdId(String cmdId) {
		this.cmdId = cmdId;
	}
  @XmlElement(name="ErrorMsg")
	public String getErrorMsg() {
		return ErrorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	@XmlElement(name="ErrorCode")
	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}
   @Override 
   public String toString(){
	   return "cmdId -> "+cmdId+" ErrorMsg -> "+ErrorMsg+" ErrorCode -> "+ErrorCode;
   }
}
