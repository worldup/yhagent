package com.nangua.yhagent.billing.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.base.Auth;
import com.nangua.yhagent.billing.bean.base.Service;
@XmlRootElement(name="Request")
public class VodDayPriceRequest {
	private Auth auth;

	private Service service;

	private RequestInfo requestInfo;

	static class RequestInfo {
		private String hotelCode;

		private String dayPrice;

		@XmlElement(name = "DayPrice")
		public String getDayPrice() {
			return dayPrice;
		}

		public RequestInfo setDayPrice(String dayPrice) {
			this.dayPrice = dayPrice;
			return this;
		}

		@XmlElement(name = "HotelCode")
		public String getHotelCode() {
			return hotelCode;
		}

		public RequestInfo setHotelCode(String hotelCode) {
			this.hotelCode = hotelCode;
			return this;
		}
	}

	@XmlElement(name = "Auth")
	public Auth getAuth() {
		return auth;
	}

	public VodDayPriceRequest setAuth(Auth auth) {
		this.auth = auth;
		return this;
	}

	@XmlElement(name = "RequestInfo")
	public RequestInfo getRequestInfo() {
		return requestInfo;
	}

	public VodDayPriceRequest setRequestInfo(RequestInfo requestInfo) {
		this.requestInfo = requestInfo;
		return this;
	}

	@XmlElement(name = "Service")
	public Service getService() {
		return service;
	}

	public VodDayPriceRequest setService(Service service) {
		this.service = service;
		return this;
	}
}
