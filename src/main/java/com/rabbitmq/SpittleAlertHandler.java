package com.rabbitmq;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class SpittleAlertHandler implements MessageListener {

	@Override
	public void onMessage(Message message) {
		
		try {
			String body = new String(message.getBody(),"UTF-8");
			System.out.println(body);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
