package com.etoak.broker;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动一个broker
 */
public class BrokerStart
{
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac =
				new ClassPathXmlApplicationContext("broker.xml");
	}
}
