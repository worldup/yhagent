package com.nangua.yhagent.billing.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.base.Auth;
import com.nangua.yhagent.billing.bean.base.Service;
@XmlRootElement(name="Request")
public class SetProgramPriceRequest {

	private RequestInfo requestInfo;
	private Auth auth;
	private Service service;

	static class RequestInfo {

		private String hotelCode;

		private String programId;

		private String programPrice;

		public String getHotelCode() {
			return hotelCode;
		}

		@XmlElement(name = "HotelCode")
		public RequestInfo setHotelCode(String hotelCode) {
			this.hotelCode = hotelCode;
			return this;
		}

		@XmlElement(name = "ProgramId")
		public String getProgramId() {
			return programId;
		}

		public RequestInfo setProgramId(String programId) {
			this.programId = programId;
			return this;
		}

		@XmlElement(name = "ProgramPrice")
		public String getProgramPrice() {
			return programPrice;
		}

		public RequestInfo setProgramPrice(String programPrice) {
			this.programPrice = programPrice;
			return this;
		}

	}
	@XmlElement(name="RequestInfo")
	public RequestInfo getRequestInfo() {
		return requestInfo;
	}

	public SetProgramPriceRequest setRequestInfo(RequestInfo requestInfo) {
		this.requestInfo = requestInfo;
		return this;
	}
	@XmlElement(name="Auth")
	public Auth getAuth() {
		return auth;
	}

	public SetProgramPriceRequest setAuth(Auth auth) {
		this.auth = auth;
		return this;
	}
	@XmlElement(name="Service")
	public Service getService() {
		return service;
	}

	public SetProgramPriceRequest setService(Service service) {
		this.service = service;
		return this;
	}

}
