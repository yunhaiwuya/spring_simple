package com.rabbitmq;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Spittle implements Serializable{

	 	private Long id;
	 	private Spittle spittle;
	    private String message;
	    private Date postedTime;

	    public Spittle(Long id, Spittle spittle,String message, Date postedTime) {
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

	    public Spittle getSpittle() {
	        return this.spittle;
	    }
}
