package com.nangua.yhagent.billing.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nangua.yhagent.billing.main.BillingChannelFactory;
import com.nangua.yhagent.pms.bean.RegisterRoomBean;

@Service
public class BillingInvoker {
	@Autowired
	private BillingChannelFactory channelFactory;
    @Autowired
    private BaseBillingHttpCommandAdapter command;
	// 开房接口
	public void test( ) {
		 command.execute("<xml>1</xml>");
	 
	}

	 
	public void releaseResource(){
		channelFactory.destroy();
	}
}
