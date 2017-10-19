package com.etoak.topic;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息接受者
 */
public class AppConsumer {

	private static final String url ="tcp://192.168.175.130:61616";
	private static final String topicName = "topic-etoak";
	
	public static void main(String[] args) throws JMSException {
		//1.創建ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		//2.創建Connection
		Connection connection = connectionFactory.createConnection();
		//3.啟動連接
		connection.start();
		//4.創建會話 參數：是否在事務中處理
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.創建一個目標
		Destination destination= session.createTopic(topicName);
		//6.創建一個消費者
		MessageConsumer consumer = session.createConsumer(destination);
		//7.創建一個監聽器
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				//8.獲取消息
				TextMessage tm = (TextMessage)message;
				try {
					System.out.println("接收消息：" + tm.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
		//9.關閉連接
		//connection.close();
		
	}	

}
