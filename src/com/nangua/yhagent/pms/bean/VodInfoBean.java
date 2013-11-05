package com.nangua.yhagent.pms.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
public class VodInfoBean implements XBean{
 private String cmdId="0x0040";
 private String roomNo="";
 @XmlAttribute(name="cmdId")
public String getCmdId() {
	return cmdId;
}
public VodInfoBean setCmdId(String cmdId) {
	this.cmdId = cmdId;
	return this;
}
@XmlElement(name="RoomNo")
public String getRoomNo() {
	return roomNo;
}
public VodInfoBean setRoomNo(String roomNo) {
	this.roomNo = roomNo;
	return this;
}
 
}
