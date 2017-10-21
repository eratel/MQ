package com.etoak.producer;


import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Resource(name="queueDestination")
	private Destination destination;
	
/*	@Resource(name="topicDestination")
	private Destination destination;*/

	@Override
	public void sendMessage(String message) {
		jmsTemplate.send(destination,new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(message);
				return textMessage;
			}

		});
		System.out.println("·¢ËÍÏûÏ¢£º" + message);
	}

}
