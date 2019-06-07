package com.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {

	private final static String task_name = "task_queue";
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("cjm");
		factory.setPassword("admin_cjm");
		Connection con = factory.newConnection();
		Channel channel = con.createChannel();
		channel.queueDeclare(task_name, true, false, false, null);
		//分发消息
		for(int i=0;i<10;i++){
			String message = "hello rabbitMQ"+i;
			channel.basicPublish("", task_name, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("UTF-8"));
			System.out.println("newTask send "+message);
		}
		channel.close();
		con.close();
	}

}
