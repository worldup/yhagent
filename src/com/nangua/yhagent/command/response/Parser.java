package com.nangua.yhagent.command.response;

import com.nangua.yhagent.bean.ChgRoomInfoRespBean;
import com.nangua.yhagent.bean.CommonRespBean;
import com.nangua.yhagent.bean.VodInfoRespBean;
import com.nangua.yhagent.bean.XBean;
import com.nangua.yhagent.bean.xml.Helper;

public class Parser {
	 
	public static XBean parse(String result){
		String cmdId=Helper.getRespCmdId(result);
		 
		if("0x0010".equals(cmdId)){
			return  Helper.genType(result, CommonRespBean.class);
		}
		else if("0x0020".equals(cmdId)){
			return  Helper.genType(result, CommonRespBean.class);
		}
		else if("0x0030".equals(cmdId)){
			return  Helper.genType(result, CommonRespBean.class);
		}
		else if("0x0040".equals(cmdId)){
			return  Helper.genType(result, VodInfoRespBean.class);
		}
		else if("0x0050".equals(cmdId)){
			return  Helper.genType(result, ChgRoomInfoRespBean.class);
		}
		return null;
		 
	}
   
}
