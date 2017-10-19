package com.etoak.me.jmstest.quene;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author:jikai.sun
 * @Date: Created in 2017 21:24 2017/10/19 0019
 */
public class QueneSender
{
//    private static final String url = "tcp://192.168.175.130:61616";
    //inner Url
    private static final String url = "tcp://localhost:61616";

    public static void main(String[] args)
        throws JMSException
    {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("myQuen");
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 3; i++)
        {
            TextMessage textMessage = session.createTextMessage("message" + i);
            producer.send(textMessage);
            System.out.print("send message" + i);
        }
        session.commit();
        session.close();
        connection.close();
    }
}












