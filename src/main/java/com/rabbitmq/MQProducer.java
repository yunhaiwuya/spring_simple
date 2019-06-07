package com.rabbitmq;


import java.util.Date;

/**
 * 生产者
 * @author cjm
 *
 */
public class MQProducer {

	private Long id;
 	private MQProducer spittle;
    private String message;
    private Date postedTime;

    public MQProducer(Long id, MQProducer spittle,String message, Date postedTime) {
        this.id = id;
        this.spittle = spittle;
        this.message = message;
        this.postedTime = postedTime;
    }

    public Long getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public Date getPostedTime() {
        return this.postedTime;
    }

    public MQProducer getSpittle() {
        return this.spittle;
    }
    
    public void sendDateToQueue(String str1,String str2){
    	System.out.println("====123");
    }
}
