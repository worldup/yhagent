package com.nangua.yhagent.pms.bean;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
public class ChgRoomBean implements XBean{
 private String cmdId="0x0030";
 private String preRoomNo="";
 private String newRoomNo="";
 @XmlAttribute(name="cmdId")
public String getCmdId() {
	return cmdId;
}
public void setCmdId(String cmdId) {
	this.cmdId = cmdId;
}
@XmlElement(name="NewRoomNo")
public String getNewRoomNo() {
	return newRoomNo;
}
public ChgRoomBean setNewRoomNo(String newRoomNo) {
	this.newRoomNo = newRoomNo;
	return this;
}
@XmlElement(name="PreRoomNo")
public String getPreRoomNo() {
	return preRoomNo;
}
public ChgRoomBean setPreRoomNo(String preRoomNo) {
	this.preRoomNo = preRoomNo;
	return this;
}

public static void main(String []args){
	ChgRoomBean bean=new ChgRoomBean();
	 
	 StringWriter writer = new StringWriter();
		
	 try {  
		 
         
           JAXBContext jaxbContext = JAXBContext.newInstance(ChgRoomBean.class);  
           Marshaller jaxbMarshaller = jaxbContext.createMarshaller();  
           // output pretty printed  
           jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
         
           jaxbMarshaller.marshal(bean,writer); 
           System.out.println(writer.toString()); 
	 }catch(Exception e){
		 e.printStackTrace();
	 }
}
}
