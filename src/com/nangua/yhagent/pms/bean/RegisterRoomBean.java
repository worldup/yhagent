package com.nangua.yhagent.pms.bean;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
public class RegisterRoomBean implements XBean {
 private String roomNo="";
 private String guestName="";
 private String purview="";
 private String sex="";
 private String address="";
 private String email="";
 private String phone="";
 private String cBillPolicy="";
 private String szRegDate="";
 private String cCanViewRecord="";
 private String cmdId="0x0010";
 @XmlAttribute(name="cmdId") 
public String getCmdId() {
	return cmdId;
}
public RegisterRoomBean setCmdId(String cmdId) {
	this.cmdId = cmdId;
	return this;
}
@XmlElement(name="Address")
public String getAddress() {
	return address;
}
public RegisterRoomBean setAddress(String address) {
	this.address = address;
	return this;
}
@XmlElement(name="cBillPolicy")
public String getCBillPolicy() {
	return cBillPolicy;
}
public RegisterRoomBean setCBillPolicy(String billPolicy) {
	cBillPolicy = billPolicy;
	return this;
}
@XmlElement(name="cCanViewRecord")
public String getCCanViewRecord() {
	return cCanViewRecord;
}
public RegisterRoomBean setCCanViewRecord(String canViewRecord) {
	cCanViewRecord = canViewRecord;
	return this;
}
@XmlElement(name="Email")
public String getEmail() {
	return email;
}
public RegisterRoomBean setEmail(String email) {
	this.email = email;
	return this;
}
@XmlElement(name="GuestName")
public String getGuestName() {
	return guestName;
}
public RegisterRoomBean setGuestName(String guestName) {
	this.guestName = guestName;
	return this;
}
@XmlElement(name="Phone")
public String getPhone() {
	return phone;
}
public RegisterRoomBean  setPhone(String phone) {
	this.phone = phone;
	return this;
}
@XmlElement(name="Purview")
public String getPurview() {
	return purview;
}
public RegisterRoomBean setPurview(String purview) {
	this.purview = purview;
	return this;
}
@XmlElement(name="RoomNo")
public String getRoomNo() {
	return roomNo;
}
public RegisterRoomBean setRoomNo(String roomNo) {
	this.roomNo = roomNo;
	return this;
}
@XmlElement(name="Sex")
public String getSex() {
	return sex;
}
public RegisterRoomBean setSex(String sex) {
	this.sex = sex;
	return this;
}
@XmlElement(name="szRegDate")
public String getSzRegDate() {
	return szRegDate;
}
public RegisterRoomBean setSzRegDate(String szRegDate) {
	this.szRegDate = szRegDate;
	return this;
}
 public static void main(String []args){
	 RegisterRoomBean bean=new RegisterRoomBean();
	 bean.setAddress("111");
	 StringWriter writer = new StringWriter();
		
	 try {  
		 
          
            JAXBContext jaxbContext = JAXBContext.newInstance(RegisterRoomBean.class);  
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
