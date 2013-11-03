package com.nangua.yhagent.command;

import com.nangua.yhagent.bean.ChgRoomBean;
import com.nangua.yhagent.bean.ChgRoomInfoBean;
import com.nangua.yhagent.bean.RegisterRoomBean;
import com.nangua.yhagent.bean.UnRegisterRoomBean;
import com.nangua.yhagent.bean.VodInfoBean;
import com.nangua.yhagent.main.YHChannelFactory;

public class Invoker {
	private static YHChannelFactory channelFactory;

	static class InvokerHolder {
		static Invoker holder = new Invoker();
	}

	private Invoker() {

	}

	public static Invoker newInstance(YHChannelFactory factory) {
		if (channelFactory == null) {
			channelFactory = factory;
		}
		return InvokerHolder.holder;
	}

	// �����ӿ�
	public void registerRoom(RegisterRoomBean bean) {
		BaseHttpCommandAdapter<RegisterRoomBean> command = new BaseHttpCommandAdapter<RegisterRoomBean>(
				channelFactory);
		command.setXBean(bean).setCommandId("0x0010").execute();
	}

	// �����ӿ�
	public void unRegisterRoom(UnRegisterRoomBean bean) {
		BaseHttpCommandAdapter<UnRegisterRoomBean> command = new BaseHttpCommandAdapter<UnRegisterRoomBean>(
				channelFactory);
		command.setXBean(bean).setCommandId("0x0020").execute();
	}

	// �����ӿ�
	public void chgRoom(ChgRoomBean bean) {
		BaseHttpCommandAdapter<ChgRoomBean> command = new BaseHttpCommandAdapter<ChgRoomBean>(
				channelFactory);
		command.setXBean(bean).setCommandId("0x0030").execute();
	}

	// ��ȡ�㲥��¼�ӿ�
	public void getVodInfo(VodInfoBean bean) {
		BaseHttpCommandAdapter<VodInfoBean> command = new BaseHttpCommandAdapter<VodInfoBean>(
				channelFactory);
		command.setXBean(bean).setCommandId("0x0040").execute();
	}

	// ��ȡ�����б�
	public void getChgRoomInfo(ChgRoomInfoBean bean) {
		BaseHttpCommandAdapter<ChgRoomInfoBean> command = new BaseHttpCommandAdapter<ChgRoomInfoBean>(
				channelFactory);
		command.setXBean(bean).setCommandId("0x0050").execute();
	}
}
