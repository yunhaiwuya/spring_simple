package com.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Work1 {

	private final static String task_name = "task_queue";
	public static void main(String[] args) throws IOException, TimeoutException {
		final ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("cjm");
		factory.setPassword("admin_cjm");
		Connection con = factory.newConnection();
		final Channel channel = con.createChannel();
		channel.queueDeclare(task_name, true, false, false, null);
		System.out.println("worker1 waitting for message");
		 //每次从队列获取的数量
		channel.basicQos(1);
		final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Worker1  Received '" + message + "'");
                try {
//                    throw  new Exception();
                    doWork(message);
                }catch (Exception e){
                    channel.abort();
                }finally {
                    System.out.println("Worker1 Done");
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        boolean autoAck = false;
        //消息消费完成确认
        channel.basicConsume(task_name, autoAck,consumer);
	}

	@SuppressWarnings({ "static-access" })
	private static void doWork(String task){
		try {
			Thread.sleep(1000); //暂停1秒钟
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Thread.currentThread().interrupted();
		}
	}
}
