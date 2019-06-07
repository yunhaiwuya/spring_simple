package com.thread;

/**
 * 生产者-消费者 模型
 * student类 共享资源
 * @author cjm
 *
 */
public class Student {

	private String name;
	private int age;
	private boolean flag;//标记是否
	
	//生产者
	public synchronized void set(String name,int age){
		if(flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.name = name;
		this.age = age;
		System.out.println(Thread.currentThread().getName()+"name:"+name+"age:"+age);
		this.flag = true;
		this.notify(); //唤醒消费者线程
	}
	
	//消费者
	public synchronized void get(){
		if(!flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"name:"+name+"age:"+age);
		flag = false;
		this.notify();//唤醒生产者线程
	}
}
