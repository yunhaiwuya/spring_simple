package com.rabbitmq.exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 转发——广播
 * @author cjm
 *
 */
public class RoutingSendDirect {

	//交换器名称
	private static final String EXCHANGE_NAME = "direct_logs";
    // 路由关键字
    private static final String[] routingKeys = new String[]{"info" ,"warning", "error"};
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");//注意是direct
        //发送信息
        for(int i=0;i<3;i++){
        	//每一次发送一条不同严重性的日志
        	String severity = routingKeys[i%3];
        	// 发送的消息
        	String message = "Hello World"+ i;
        	//参数1：exchange name
			//参数2：routing key
			channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
			System.out.println(" [x] Sent '" + severity +"':'"+ message + "'");
        }
        /*for (String routingKey:routingKeys){
            String message = "RoutingSendDirect Send the message level:" + routingKey;
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes());
            System.out.println("RoutingSendDirect Send"+routingKey +"':'" + message);
        }*/
        channel.close();
        connection.close();
    }
}
