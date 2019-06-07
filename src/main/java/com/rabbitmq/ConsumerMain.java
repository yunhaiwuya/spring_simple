package com.rabbitmq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 消费者
 * @author cjm
 *
 */
public class ConsumerMain {

	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-rabbitmq.xml");
		
		@SuppressWarnings("resource")
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/spring-rabbitmq-direct.xml");
        MQProducer mqProducer=(MQProducer) context.getBean("mqProducer");
        mqProducer.sendDateToQueue("spring.test.queueKey1","Hello World spring.test.queueKey1");
        mqProducer.sendDateToQueue("spring.test.queueKey2","Hello World spring.test.queueKey2");

	}

}
