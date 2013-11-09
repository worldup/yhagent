package com.nangua.yhagent.billing.bean.base;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="Request")
public abstract class Request {
	public Auth Auth;

	public Service Service;

	
	@XmlElement(name = "RequestInfo")
	public  abstract RequestInfo getRequestInfo(); 
 
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
