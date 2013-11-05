package com.nangua.yhagent.pms.command.response;

import com.nangua.yhagent.pms.bean.ChgRoomInfoRespBean;
import com.nangua.yhagent.pms.bean.CommonRespBean;
import com.nangua.yhagent.pms.bean.VodInfoRespBean;
import com.nangua.yhagent.pms.bean.XBean;
import com.nangua.yhagent.pms.bean.xml.Helper;
import com.nangua.yhagent.pms.command.Constants;

public class Parser {
	 
	public static XBean parse(String result){
		String cmdId=Helper.getRespCmdId(result);
		 
		if(Constants.REGISTEROOM.equals(cmdId)){
			return  Helper.genType(result, CommonRespBean.class);
		}
		else if(Constants.UNREGISTEROOM.equals(cmdId)){
			return  Helper.genType(result, CommonRespBean.class);
		}
		else if(Constants.CHGROOM.equals(cmdId)){
			return  Helper.genType(result, CommonRespBean.class);
		}
		else if(Constants.GETVODINFO.equals(cmdId)){
			return  Helper.genType(result, VodInfoRespBean.class);
		}
		else if(Constants.GETCHGROOMINFO.equals(cmdId)){
			return  Helper.genType(result, ChgRoomInfoRespBean.class);
		}
		return null;
		 
	}
   
}
