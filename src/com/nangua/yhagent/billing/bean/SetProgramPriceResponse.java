package com.nangua.yhagent.billing.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.base.Service;

@XmlRootElement(name="Result")
public class SetProgramPriceResponse {
	private ResultInfo  resultInfo;
	private Service  service;
	@XmlElement(name="ResultInfo")
	public ResultInfo getResultInfo() {
		return resultInfo;
	}
	public SetProgramPriceResponse setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
		return this;
	}
	@XmlElement(name="Service")
	public Service getService() {
		return service;
	}
	public SetProgramPriceResponse setService(Service service) {
		this.service = service;
		return this;
	}
}
