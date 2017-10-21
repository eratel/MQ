package com.etoak.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppConsumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac =
				new ClassPathXmlApplicationContext("consumer.xml");
	}
}
