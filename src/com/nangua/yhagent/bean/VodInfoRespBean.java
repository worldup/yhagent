package com.nangua.yhagent.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
public class VodInfoRespBean  implements XBean{
	private String cmdId = "0x0040";

	private String RoomNo = "";

	private String PlayNo = "";

	private String FileTypeName = "";

	private String FileName_CH = "";

	private String StartTime = "";

	private String EndTime = "";

	private String Fee = "";

	private String ErroMsg = "";

	private String ErrorCode = "";

	@XmlAttribute(name = "cmdId")
	public String getCmdId() {
		return cmdId;
	}

	public VodInfoRespBean setCmdId(String cmdId) {
		this.cmdId = cmdId;
		return this;
	}

	public String getEndTime() {
		return EndTime;
	}

	public VodInfoRespBean setEndTime(String endTime) {
		EndTime = endTime;
		return this;
	}

	public String getErroMsg() {
		return ErroMsg;
	}

	public VodInfoRespBean setErroMsg(String erroMsg) {
		ErroMsg = erroMsg;
		return this;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public VodInfoRespBean setErrorCode(String errorCode) {
		ErrorCode = errorCode;
		return this;
	}

	public String getFee() {
		return Fee;
	}

	public VodInfoRespBean setFee(String fee) {
		Fee = fee;
		return this;
	}

	public String getFileName_CH() {
		return FileName_CH;
	 
	}

	public VodInfoRespBean setFileName_CH(String fileName_CH) {
		FileName_CH = fileName_CH;
		return this;
	}

	public String getFileTypeName() {
		return FileTypeName;
	}

	public VodInfoRespBean setFileTypeName(String fileTypeName) {
		FileTypeName = fileTypeName;
		return this;
	}

	public String getPlayNo() {
		return PlayNo;
	}

	public VodInfoRespBean setPlayNo(String playNo) {
		PlayNo = playNo;
		return this;
	}

	public String getRoomNo() {
		return RoomNo;
	}

	public VodInfoRespBean setRoomNo(String roomNo) {
		RoomNo = roomNo;
		return this;
	}

	public String getStartTime() {
		return StartTime;
	}

	public VodInfoRespBean setStartTime(String startTime) {
		StartTime = startTime;
		return this;
	}

}
