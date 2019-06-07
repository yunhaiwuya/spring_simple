package com.rabbitmq.exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ReceiveLogs1 {
	private static final String EXCHANGE_NAME = "logs";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("cjm");
		factory.setPassword("admin_cjm");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        //产生一个随机的队列名称
        String queueName = channel.queueDeclare().getQueue();
        //对队列进行绑定
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println("ReceiveLogs1 Waiting for messages");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("ReceiveLogs1 Received '" + message + "'");
//                doWork(message);
            }
        };
        //队列会自动删除
        channel.basicConsume(queueName, true, consumer);
    }
    
    @SuppressWarnings({ "unused", "static-access" })
	private static void doWork(String task){
		try {
			Thread.sleep(1000); //暂停1秒钟
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Thread.currentThread().interrupted();
		}
	}

}
