package com.etoak.me.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 分发 订阅  持久化 接受
 */
public class PersistenceSender
{
    private static final String url = "tcp://192.168.175.130:61616";

    public static void main(String[] args)
        throws JMSException
    {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("MyTopic");
        MessageProducer producer = session.createProducer(destination);

        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();
        for (int i = 0; i < 3; i++)
        {
            TextMessage textMessage = session.createTextMessage("message222" + i);
            producer.send(textMessage);
            System.out.print("start" + i);
        }
        session.commit();
        session.close();
        connection.close();
    }
}












