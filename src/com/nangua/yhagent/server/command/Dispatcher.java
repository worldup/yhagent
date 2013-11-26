package com.nangua.yhagent.server.command;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.nangua.yhagent.billing.bean.ResultInfo;
import com.nangua.yhagent.billing.bean.base.ResponseResult;
import com.nangua.yhagent.billing.bean.xml.Helper;
import com.nangua.yhagent.job.DBTask;
import com.nangua.yhagent.job.IDBTask;
@Service
public class Dispatcher {
	public static final String defaultResult="<?xml version='1.0' encoding='UTF-8'?><Result> <ResultInfo resultcode='-1' errormessage='没有响应' /></Result>";
	@Autowired
	private IDBTask dbTask;
	//返回结果 为xml样式
	public   String dispatch(String command){
		String function=findServiceFunction(command);
		String business=findServiceBusiness(command);
		if("TIMESHIFT".equals(business)){
			
		}else if("VOD".equals(business)){
			
		}else if("BILLING".equals(business)){
			if("addDemondBill".equals(function)){
			 
			 boolean exeResult=	dbTask.insertCommand(command);
			 return getResult(exeResult,"BILLING","addDemondBill");
			}
		}
		return defaultResult;
	}
	private   String getResult(boolean resultcode,String business,String function){
		ResponseResult result=new ResponseResult();
		ResultInfo resultInfo=new ResultInfo();
		resultInfo.setErrormessage("");
		if(resultcode){
			resultInfo.setResultcode("200");	
			resultInfo.setErrormessage("成功");	
		}
		else {
			resultInfo.setResultcode("-999");
			resultInfo.setErrormessage("执行出错");
		}
		
		com.nangua.yhagent.billing.bean.base.Service service=new com.nangua.yhagent.billing.bean.base.Service();
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
