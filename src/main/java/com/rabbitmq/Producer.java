package com.rabbitmq;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者
 * @author cjm
 *
 */
public class Producer {

	public final static String QUEUE_NAME = "rabbitMQ.test";
	public static void main(String[] args) throws IOException, TimeoutException {
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置rabbitMQ相关信息
//		factory.setHost("localhost");
		factory.setHost("127.0.0.1");
		factory.setPort(5672);
		factory.setVirtualHost("/");
		factory.setUsername("cjm");
		factory.setPassword("admin_cjm");
		//创建一个新的连接
		Connection con = factory.newConnection();
		//创建一个通道
		Channel channel = con.createChannel();
		/**
		 * 声明一个队列
		 * queueDeclare第一个参数表示队列名称、
		 * 第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、
		 * 第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、
		 * 第四个参数为当所有消费者客户端连接断开时是否自动删除队列、
		 * 第五个参数为队列的其他参数
		 */
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "hello rabbitMQ";
		/**
		 * 发送消息到队列中
		 * basicPublish第一个参数为交换机名称、第二个参数为队列映射的路由key、
		 * 第三个参数为消息的其他属性、第四个参数为发送信息的主体
		 */
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
		System.out.println("producer send ===="+message);
		//关闭通道和连接
		channel.close();
		con.close();
	}
}
