package com.nangua.yhagent.pms.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
public class ChgRoomInfoRespBean implements XBean {
	private String cmdId;

	private String roomNos;

	private String ErrorMsg = "";

	private String ErrorCode = "";

	@XmlElement(name = "RoomNos")
	public String getRoomNos() {
		return roomNos;
	}

	public void setRoomNos(String roomNos) {
		this.roomNos = roomNos;
	}

	@XmlAttribute(name = "cmdId")
	public String getCmdId() {
		return cmdId;
	}

	public void setCmdId(String cmdId) {
		this.cmdId = cmdId;
	}

	@XmlElement(name = "ErrorMsg")
	public String getErrorMsg() {
		return ErrorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}

	@XmlElement(name = "ErrorCode")
	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}
}
