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
	// �����ӿ�
	public void registerRoom(RegisterRoomBean bean) {
		 
		command.setXBean(bean).setCommandId(Constants.REGISTEROOM).execute();
	}

	// �����ӿ�
	public void unRegisterRoom(UnRegisterRoomBean bean) {
		 
		command.setXBean(bean).setCommandId(Constants.UNREGISTEROOM).execute();
	}

	// �����ӿ�
	public void chgRoom(ChgRoomBean bean) {
		 
		command.setXBean(bean).setCommandId(Constants.CHGROOM).execute();
	}

	// ��ȡ�㲥��¼�ӿ�
	public void getVodInfo(VodInfoBean bean) {
		 
		command.setXBean(bean).setCommandId(Constants.GETVODINFO).execute();
	}

	// ��ȡ�����б�
	public void getChgRoomInfo(ChgRoomInfoBean bean) {
		 
		command.setXBean(bean).setCommandId(Constants.GETCHGROOMINFO).execute();
	}
	public void releaseResource(){
		channelFactory.destroy();
	}
}
