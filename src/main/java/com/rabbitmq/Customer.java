package com.rabbitmq;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 消费者
 * @author cjm
 *
 */
public class Customer {

	private final static String QUEUE_NAME = "rabbitMQ.test";
	public static void main(String[] args) throws IOException, TimeoutException {
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置连接相关信息
//		factory.setHost("localhost");
		factory.setHost("127.0.0.1");
		factory.setPort(5672);
		factory.setVirtualHost("/");
		factory.setUsername("cjm");
		factory.setPassword("admin_cjm");
		//创建一个连接
		Connection con = factory.newConnection();
		//创建一个通道
		Channel channel = con.createChannel();
		//声明要关注的队列
		/**
		 * queue: 队列名称
		 * durable： 是否持久化( 是否持久化, 队列的声明默认是存放到内存中的，如果rabbitmq重启会丢失，如果想重启之后还存在就要使队列持久化，保存到Erlang自带的Mnesia数据库中，当rabbitmq重启之后会读取该数据库)
		 * exclusive：是否排外的
		 * autoDelete：是否自动删除(当最后一个消费者断开连接之后队列是否自动被删除可以通过RabbitMQ Management，查看某个队列的消费者数量，当consumers = 0时队列就会自动删除)
		 * arguments： 队列中的消息
		 */
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println("customer accept ===message");
		// DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，
		// 就会执行回调函数handleDelivery
		Consumer consumer = new DefaultConsumer(channel){
		/**
		 * 后面的是获取生产者发送的信息，其中envelope主要存放
		 * 生产者相关信息（比如交换机、路由key等）body是消息实体
		 */
		@Override
		public void handleDelivery(String consumerTag, Envelope envelope,
                AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException{
			
			String message = new String(body,"UTF-8");
			System.out.println("customer received ==="+message);
		}
		};
		 //自动回复队列应答 -- RabbitMQ中的消息确认机制(ack)
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}

}
