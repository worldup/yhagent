package com.nangua.yhagent.billing.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.base.Service;
import com.nangua.yhagent.billing.bean.xml.Helper;

@XmlRootElement(name="Result")
public class ListVodLogResponse {
 public ResultInfo  ResultInfo;
 public Service  Service ;
 public ResultData ResultData;
 static  class ResultData{
	 public String StartTime;
	 public String EndTime;
	 public String TotalFee;
	 public String PolicyType;
	 @XmlElementWrapper(name="VodLogs")
	 @XmlElement(name="Log")
	 public List<VodLog> vodLogs;
 } 
 public static void main(String []args){
	 final String requestStr="<?xml version='1.0' encoding='UTF-8'?>                         "
	 +"<Result>                                                       "
	 +"    <ResultInfo resultcode='200' errormessage='OK' />          "
	 +"     <Service business='VOD' function='listVodLog' />          "
	 +"     <ResultData>                                              "
	 +"		 <StartTime>1383354000</StartTime>                         "
	 +"		 <EndTime>1383699600</EndTime>                             "
	 +"		 <TotalFee>25.00</TotalFee>                                "
	 +"		<PolicyType>0</PolicyType>    "
	 +"		 <VodLogs>                                                 "
	 +"			<Log>                                                    "
	 +"           	<HotelCode>hotelCode</HotelCode>                   "
	 +"				<RoomNumber>8028</RoomNumber>                          "
	 +"				<VodClientId>34-40-B5-8D-83-B6</VodClientId>           "
	 +"				<ProgramId>1</ProgramId>                               "
	 +"				<ProgramName>2012</ProgramName>                        "
	 +"				<StartTime>1312983240</StartTime>                      "
	 +"				<EndTime>1312983281</EndTime>                          "
	 +"				<TotalTime>41</TotalTime>                              "
	 +"				<Fee>10.00</Fee>                                       "
	 +"			</Log>                                                   "
	 +"			<Log>                                                    "
	 +"           	<HotelCode>hotelCode</HotelCode>                   "
	 +"				<RoomNumber>8028</RoomNumber>                          "
	 +"				<VodClientId>34-40-B5-8D-83-B6</VodClientId>           "
	 +"				<ProgramId>10</ProgramId>                              "
	 +"				<ProgramName>大话西游</ProgramName>                    "
	 +"				<StartTime>1312983340</StartTime>                      "
	 +"				<EndTime>1312983685</EndTime>                          "
	 +"				<TotalTime>345</TotalTime>                             "
	 +"				<Fee>15.00</Fee>                                       "
	 +"			</Log>                                                   "
	 +"		</VodLogs>                                                 "
	 +"    </ResultData>                                              "
	 +"</Result>                                                      ";
	 ListVodLogResponse request1=Helper.genType(requestStr, ListVodLogResponse.class);
	 
	 System.out.println( Helper.genXmlStr(request1));
 }
}
