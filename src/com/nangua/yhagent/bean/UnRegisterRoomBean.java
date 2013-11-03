package com.nangua.yhagent.bean;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
public class UnRegisterRoomBean implements XBean{
	private String cmdId="0x0020";
	private String roomNo="";
	@XmlAttribute(name="cmdId")
	public String getCmdId() {
		return cmdId;
	}
	public UnRegisterRoomBean setCmdId(String cmdId) {
		this.cmdId = cmdId;
		return this;
	}
	@XmlElement(name="RoomNo")
	public String getRoomNo() {
		return roomNo;
	}
	public UnRegisterRoomBean setRoomNo(String roomNo) {
		this.roomNo = roomNo;
		return this;
	}
	public static void main(String []args){
		 UnRegisterRoomBean bean=new UnRegisterRoomBean();
		 bean.setRoomNo("111");
		 StringWriter writer = new StringWriter();
			
		 try {  
			 
	          
	            JAXBContext jaxbContext = JAXBContext.newInstance(UnRegisterRoomBean.class);  
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
