package com.nangua.yhagent.billing.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.base.Auth;
import com.nangua.yhagent.billing.bean.base.Service;
import com.nangua.yhagent.billing.bean.xml.Helper;
@XmlRootElement(name="Request")
public class ListVodLogRequest {
	public Auth Auth;

	public Service Service;

	public RequestInfo RequestInfo;

	static class RequestInfo {

		public String HotelCode;

		public String RoomNumber;

		public String PolicyType;

		@XmlElementWrapper(name = "Occupancies")
		@XmlElement(name = "Occupancy")
		public List<Occupancy> Occupancies;

	}
	public static void main(String []args){
		ListVodLogRequest request=new ListVodLogRequest();
		request.Auth=new Auth().setAuthToken("11").setHotelCode("22");
		request.Service=new Service().setBusiness("11").setFunction("222");
		request.RequestInfo=new ListVodLogRequest.RequestInfo();
		request.RequestInfo.HotelCode="2222";
		request.RequestInfo.RoomNumber="111";
		request.RequestInfo.PolicyType="2233";
		request.RequestInfo.Occupancies=new ArrayList<Occupancy>();
		Occupancy temp=new Occupancy();
		temp.EndTime="111";
		request.RequestInfo.Occupancies.add(temp);
		request.RequestInfo.Occupancies.add(temp);
		String xmlStr=Helper.genXmlStr(request);
		ListVodLogRequest request1=Helper.genType(xmlStr, ListVodLogRequest.class);
	}
}
