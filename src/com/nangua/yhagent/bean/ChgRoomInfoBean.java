package com.nangua.yhagent.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
public class ChgRoomInfoBean implements XBean{
	private String cmdId="0x0050";
	private String roomNo;
	 @XmlAttribute(name="cmdId")
	public String getCmdId() {
		return cmdId;
	}
	public ChgRoomInfoBean setCmdId(String cmdId) {
		this.cmdId = cmdId;
		return this;
	}
	@XmlElement(name="RoomNo")
	public String getRoomNo() {
		return roomNo;
	}
	public ChgRoomInfoBean setRoomNo(String roomNo) {
		this.roomNo = roomNo;
		return this;
	}
	
}
