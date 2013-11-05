package com.nangua.yhagent.pms.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nangua.yhagent.pms.bean.ChgRoomBean;
import com.nangua.yhagent.pms.bean.ChgRoomInfoBean;
import com.nangua.yhagent.pms.bean.RegisterRoomBean;
import com.nangua.yhagent.pms.bean.UnRegisterRoomBean;
import com.nangua.yhagent.pms.bean.VodInfoBean;
import com.nangua.yhagent.pms.main.YHChannelFactory;

@Service
public class Invoker {
	@Autowired
	private YHChannelFactory channelFactory;
    @Autowired
    private BaseHttpCommandAdapter command;
	// 开房接口
	public void registerRoom(RegisterRoomBean bean) {
		 
		command.setXBean(bean).setCommandId(Constants.REGISTEROOM).execute();
	}

	// 销房接口
	public void unRegisterRoom(UnRegisterRoomBean bean) {
		 
		command.setXBean(bean).setCommandId(Constants.UNREGISTEROOM).execute();
	}

	// 换房接口
	public void chgRoom(ChgRoomBean bean) {
		 
		command.setXBean(bean).setCommandId(Constants.CHGROOM).execute();
	}

	// 获取点播记录接口
	public void getVodInfo(VodInfoBean bean) {
		 
		command.setXBean(bean).setCommandId(Constants.GETVODINFO).execute();
	}

	// 获取换房列表
	public void getChgRoomInfo(ChgRoomInfoBean bean) {
		 
		command.setXBean(bean).setCommandId(Constants.GETCHGROOMINFO).execute();
	}
	public void releaseResource(){
		channelFactory.destroy();
	}
}
