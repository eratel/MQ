package com.etoak.me.jmstest.broker;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.junit.Test;

import java.net.URI;

/**
 * @Author:jikai.sun
 * @Date: Created in  2017/10/20 0020
 * 嵌入启动 Broker
 */
public class InnerBroker
{
    public static void main(String[] args)throws Exception{
        //borkServer();
        brokFactory();
    }

    private static final String url = "tcp://localhost:61616";
    public static void borkServer()throws Exception{
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector(url);
        brokerService.start();
    }

    public static void brokFactory()throws Exception{
        String properties = "properties:broker.properties";
        BrokerService broker = BrokerFactory.createBroker(new URI(properties));
        broker.addConnector(url);
        broker.start();
    }
}
