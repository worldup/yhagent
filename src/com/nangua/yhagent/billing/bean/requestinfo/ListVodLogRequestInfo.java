package com.nangua.yhagent.billing.bean.requestinfo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.nangua.yhagent.billing.bean.Occupancy;
import com.nangua.yhagent.billing.bean.base.RequestInfo;

public class ListVodLogRequestInfo extends RequestInfo {
	public String HotelCode;

	public String RoomNumber;

	public String PolicyType;

	@XmlElementWrapper(name = "Occupancies")
	@XmlElement(name = "Occupancy")
	public List<Occupancy> Occupancies;
}
