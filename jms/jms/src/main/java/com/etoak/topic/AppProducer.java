package com.etoak.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息发送者
 */
public class AppProducer {

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
		//6.創建一個生產者
		MessageProducer mp = session.createProducer(destination);
		
		for(int i = 0;i < 100;i++){
			//7.創建消息
			TextMessage textMessage = session.createTextMessage("test" + i);
			//8.發送消息
			mp.send(textMessage);
			
			System.out.println("發送消息：" + textMessage.getText());
		}
		
		//9.關閉連接
		connection.close();
		
	}	

}
