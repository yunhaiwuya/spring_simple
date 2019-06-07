package com.rabbitmq.exchange;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ReceiveLogsDirect2 {
	 // 交换器名称
    private static final String EXCHANGE_NAME = "direct_logs";
    // 路由关键字
    private static final String[] routingKeys = new String[]{"error"};

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        //获取匿名队列名称
        String queueName = channel.queueDeclare().getQueue();
        //根据路由关键字进行多重绑定
        for (String severity : routingKeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, severity);
            System.out.println("ReceiveLogsDirect2 exchange:"+EXCHANGE_NAME+", queue:"+queueName+", BindRoutingKey:" + severity);
        }
        System.out.println("ReceiveLogsDirect2 Waiting for messages");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                String message = new String(body, "UTF-8");
//                System.out.println("ReceiveLogsDirect2 Received '" + envelope.getRoutingKey() + "':'" + message + "'");
                
                //记录日志到文件：
			    print2File( "["+ envelope.getRoutingKey() + "] "+message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
    
    private static void print2File(String msg) {
		try {
			String dir = ReceiveLogsDirect2.class.getClassLoader().getResource("").getPath();
			String logFileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			File file = new File(dir, logFileName + ".log");
			FileOutputStream fos = new FileOutputStream(file, true);
			fos.write((new SimpleDateFormat("HH:mm:ss").format(new Date())+" - "+msg + "\r\n").getBytes());
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  

}
