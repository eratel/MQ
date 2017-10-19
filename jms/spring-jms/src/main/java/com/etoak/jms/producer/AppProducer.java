package com.etoak.jms.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppProducer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac =
				new ClassPathXmlApplicationContext("producer.xml");
		ProducerService ps = ac.getBean(ProducerService.class);
		for(int i  =0;i < 100;i++){
			ps.sendMessage("test" + i);
		}
		ac.close();
	}
}
