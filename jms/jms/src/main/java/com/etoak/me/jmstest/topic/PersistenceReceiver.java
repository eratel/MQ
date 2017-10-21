package com.etoak.me.jmstest.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 分发 订阅  持久化 接受
 *  需要提前启动一次   需要注册到mq中去  等关闭时mq 才会保存消息
 *  等待节点  恢复使用发送消息
 */
public class PersistenceReceiver
{
    private static final String url = "tcp://192.168.175.130:61616";

    public static void main(String[] args)
        throws Exception
    {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        //注册到 mq
        connection.setClientID("sjk");
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Topic destination = session.createTopic("MyTopic");
        //修改为持久接受消息
        TopicSubscriber ts = session.createDurableSubscriber(destination, "T1");
        connection.start();

        //阻塞
        TextMessage message = (TextMessage)ts.receive();
        while (message != null)
        {
            System.out.print("end" + message.getText());
            //签收
            ts.receive();
        }
        session.commit();
        session.close();
        connection.close();
    }
}
