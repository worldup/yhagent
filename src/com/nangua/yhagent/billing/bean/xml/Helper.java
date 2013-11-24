package com.nangua.yhagent.billing.bean.xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Helper {
	public static <T> String genXmlStr(T bean) {
		StringWriter writer = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(bean.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(bean, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T genType(String xml, Class<T> clazz) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			StringReader reader = new StringReader(xml);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			T type = (T) jaxbUnmarshaller.unmarshal(reader);
			return type;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private static String findServiceAttr(String content,String attr){
	//	String content = "<root cmdId='0x0010'><ErrorMsg>此房间已开</ErrorMsg><ErrorCode>0x0001</ErrorCode></root>";
	    InputSource source = new InputSource(new StringReader(content));
	    XPath xPath = XPathFactory.newInstance().newXPath();
	    NodeList list = null;
	    try {
	        list = (NodeList) xPath.evaluate("//Result/Service/@"+attr, source,
	                XPathConstants.NODESET);
	    } catch (Exception ex) {
	        System.out.println(ex.getMessage());
	    }
	    if (list.getLength()>0) {
	        return list.item(0).getTextContent();
	    }
	    return null;
	}
	public  static String findServiceFunction(String content){
		return findServiceAttr(content,"function");
	}
	public  static String findServiceBusiness(String content){
		return findServiceAttr(content,"business");
	}
	public static String getNode(String content,String nodeName) throws Exception{
		 InputSource source = new InputSource(new StringReader(content));
		    XPath xPath = XPathFactory.newInstance().newXPath();
		    NodeList list = null;
		    try {
		        list = (NodeList) xPath.evaluate("//"+nodeName, source,
		                XPathConstants.NODESET);
		    } catch (Exception ex) {
		        System.out.println(ex.getMessage());
		    }
		   
		    
		    if (list.getLength()>0) {
		    	 TransformerFactory transFactory = TransformerFactory.newInstance();
				    Transformer transformer = transFactory.newTransformer();
				    StringWriter buffer = new StringWriter();
				    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
				    transformer.transform(new DOMSource(list.item(0)),
				          new StreamResult(buffer));
				   
		        return buffer.toString();
		    }
		    return "";
	}
	public static void main(String []args){
	   
 
		 
	}
}
