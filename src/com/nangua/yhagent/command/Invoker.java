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

	// 开房接口
	public void registerRoom(RegisterRoomBean bean) {
		BaseHttpCommandAdapter<RegisterRoomBean> command = new BaseHttpCommandAdapter<RegisterRoomBean>(
				channelFactory);
		command.setXBean(bean).setCommandId("0x0010").execute();
	}

	// 销房接口
	public void unRegisterRoom(UnRegisterRoomBean bean) {
		BaseHttpCommandAdapter<UnRegisterRoomBean> command = new BaseHttpCommandAdapter<UnRegisterRoomBean>(
				channelFactory);
		command.setXBean(bean).setCommandId("0x0020").execute();
	}

	// 换房接口
	public void chgRoom(ChgRoomBean bean) {
		BaseHttpCommandAdapter<ChgRoomBean> command = new BaseHttpCommandAdapter<ChgRoomBean>(
				channelFactory);
		command.setXBean(bean).setCommandId("0x0030").execute();
	}

	// 获取点播记录接口
	public void getVodInfo(VodInfoBean bean) {
		BaseHttpCommandAdapter<VodInfoBean> command = new BaseHttpCommandAdapter<VodInfoBean>(
				channelFactory);
		command.setXBean(bean).setCommandId("0x0040").execute();
	}

	// 获取换房列表
	public void getChgRoomInfo(ChgRoomInfoBean bean) {
		BaseHttpCommandAdapter<ChgRoomInfoBean> command = new BaseHttpCommandAdapter<ChgRoomInfoBean>(
				channelFactory);
		command.setXBean(bean).setCommandId("0x0050").execute();
	}
}
