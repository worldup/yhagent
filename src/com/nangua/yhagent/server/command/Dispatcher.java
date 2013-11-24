package com.nangua.yhagent.server.command;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.nangua.yhagent.billing.bean.ResultInfo;
import com.nangua.yhagent.billing.bean.base.ResponseResult;
import com.nangua.yhagent.billing.bean.base.Service;
import com.nangua.yhagent.billing.bean.xml.Helper;
import com.nangua.yhagent.job.DBTask;

public class Dispatcher {
	public static final String defaultResult="<?xml version='1.0' encoding='UTF-8'?><Result> <ResultInfo resultcode='-1' errormessage='û����Ӧ' /></Result>";
	//���ؽ�� Ϊxml��ʽ
	public static String dispatch(String command){
		String function=findServiceFunction(command);
		String business=findServiceBusiness(command);
		if("TIMESHIFT".equals(business)){
			
		}else if("VOD".equals(business)){
			
		}else if("BILLING".equals(business)){
			if("addDemondBill".equals(function)){
				DBTask task=new DBTask();
			 boolean exeResult=	task.insertCommand(command);
			 return getResult(exeResult,"BILLING","addDemondBill");
			}
		}
		return defaultResult;
	}
	private static String getResult(boolean resultcode,String business,String function){
		ResponseResult result=new ResponseResult();
		ResultInfo resultInfo=new ResultInfo();
		resultInfo.setErrormessage("");
		if(resultcode){
			resultInfo.setResultcode("200");	
			resultInfo.setErrormessage("�ɹ�");	
		}
		else {
			resultInfo.setResultcode("-999");
			resultInfo.setErrormessage("ִ�г���");
		}
		
		Service service=new Service();
		service.setBusiness(business);
		service.setFunction(function);
		result.setResultInfo(resultInfo);
		result.setService(service);
		return Helper.genXmlStr(result);
	}
	private static String findServiceAttr(String content,String attr){
		 
		    InputSource source = new InputSource(new StringReader(content));
		    XPath xPath = XPathFactory.newInstance().newXPath();
		    NodeList list = null;
		    try {
		        list = (NodeList) xPath.evaluate("/Request/Service/@"+attr, source,
		                XPathConstants.NODESET);
		    } catch (Exception ex) {
		        System.out.println(ex.getMessage());
		    }
		    if (list.getLength()>0) {
		        return list.item(0).getTextContent();
		    }
		    return null;
		}
		private  static String findServiceFunction(String content){
			return findServiceAttr(content,"function");
		}
		private  static String findServiceBusiness(String content){
			return findServiceAttr(content,"business");
		}
}
