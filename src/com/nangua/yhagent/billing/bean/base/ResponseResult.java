package com.nangua.yhagent.billing.bean.base;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.ResultInfo;

@XmlRootElement(name="Result")
public class ResponseResult {
 private ResultInfo resultInfo;
 private Service service;
 @XmlElement(name="ResultInfo")
public ResultInfo getResultInfo() {
	return resultInfo;
}
public void setResultInfo(ResultInfo resultInfo) {
	this.resultInfo = resultInfo;
}
@XmlElement(name="Service")
public Service getService() {
	return service;
}
public void setService(Service service) {
	this.service = service;
}
}
