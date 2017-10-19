package com.etoak.me.jmstest.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 非持久  topic
 */
public class NoPersistenceReceiver
{
    private static final String url = "tcp://192.168.175.130:61616";

    public static void main(String[] args)
        throws Exception
    {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("MyTopic");
        MessageConsumer consumer = session.createConsumer(destination);
        //阻塞
        TextMessage message = (TextMessage)consumer.receive();
        while (message != null)
        {
            System.out.print("end" + message.getText());
            //签收
            consumer.receive();
        }
        session.commit();
        session.close();
        connection.close();
    }
}
