package com.thread;

/**
 * 
 * 自己编写代码,实现生产者-消费者模型功能.
 * 内容自由发挥,只需要表达思想.
 * @author cjm
 *
 */
public class TestStudent {

	public static void main(String[] args) {

		final Student s = new Student();
		Thread tset = new Thread(new Runnable(){
			public void run(){
				int num = 10;
				while(num>0){
					if(num % 2==0){
						s.set("刘德华", 56);
					}else{
						s.set("韩梅", 20);
					}
					num--;
				}
			}
		},"生产者");
		
		Thread tget = new Thread(new Runnable(){
			public void run(){
				int num=10;
				while(num>0){
					s.get();
					num--;
				}
			}
		},"消费者");
		
		tset.start();
		tget.start();
	}

}
