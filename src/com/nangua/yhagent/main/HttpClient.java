package com.nangua.yhagent.main;

import com.nangua.yhagent.bean.RegisterRoomBean;
import com.nangua.yhagent.bean.UnRegisterRoomBean;
import com.nangua.yhagent.command.Invoker;

public class HttpClient {
	public static void main(String[] args) {

		YHChannelFactory channelFactory = YHChannelFactory.newInstance();
		Invoker invoker = Invoker.newInstance(channelFactory);
		
		  UnRegisterRoomBean uBean = new UnRegisterRoomBean();
		uBean.setRoomNo("002");
		invoker.unRegisterRoom(uBean); 
		RegisterRoomBean bean = new RegisterRoomBean();
		bean.setRoomNo("002").setGuestName("邓鑫").setPurview("101").setSex("0")
				.setAddress("上海市闵行区XX路XX弄").setEmail("abcdedf@163.com")
				.setPhone("13888888888").setCBillPolicy("0").setSzRegDate(
						"2013:03:31 12:03:00").setCCanViewRecord("1");
		invoker.registerRoom(bean);

       /*
		ChgRoomBean cBean = new ChgRoomBean();
		cBean.setPreRoomNo("002").setNewRoomNo("001");*/
		channelFactory.destroy();

	}

}
