package com.nangua.yhagent.billing.bean;

import javax.xml.bind.annotation.XmlElement;

public class VodLog {
	private String hotelCode;
	private String roomNumber;
	private String policyType;
	private String vodClientId;
	private String programId;
	private String startTime;
	private String endTime;
	private String totalTime;
	private String channelId;
	@XmlElement(name="EndTime")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@XmlElement(name="HotelCode")
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	@XmlElement(name="PolicyType")
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	@XmlElement(name="ProgramId")
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	@XmlElement(name="RoomNumber")
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	@XmlElement(name="StartTime")
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@XmlElement(name="TotalTime")
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	@XmlElement(name="VodClientId")
	public String getVodClientId() {
		return vodClientId;
	}
	public void setVodClientId(String vodClientId) {
		this.vodClientId = vodClientId;
	}
	@XmlElement(name="ChannelId")
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
}
