package com.nangua.yhagent.billing.bean.base;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="Request")
public     class Request {
	@XmlElement(name="Auth")
	public Auth Auth;

	@XmlElement(name="Service")
  public  Service Service ;
  private  RequestInfo requestInfo;
  
	
	public void setRequestInfo(RequestInfo requestInfo) {
	this.requestInfo = requestInfo;
}

	@XmlElement(name = "RequestInfo")
	public      RequestInfo getRequestInfo() {
		return this.requestInfo;
	}
 
	public String toXml() {
		StringWriter writer = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(this, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
}
