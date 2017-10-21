package com.etoak.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 *	配置消息监听器
 */

public class ConsumerMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage)message;
		try {
			System.out.println("接收信息：" + textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
