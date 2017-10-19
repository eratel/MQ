package com.etoak.me.jmstest.quene;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author:jikai.sun
 * @Date: Created in 2017 21:26 2017/10/19 0019
 */
public class QueneReceiver
{
    //    private static final String url = "tcp://192.168.175.130:61616";
    //inner Url
    private static final String url = "tcp://localhost:61616";

    public static void main(String[] args)
        throws Exception
    {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("myQuen");
        MessageConsumer consumer = session.createConsumer(destination);

        int i = 0;
        while (i < 3)
        {
            TextMessage message = (TextMessage)consumer.receive();
            session.commit();
            System.out.print("receiver" + message.getText());
        }
        session.close();
        connection.close();
    }
}
