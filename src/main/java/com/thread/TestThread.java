package com.thread;

public class TestThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread("thread1"){
			public void run(){
//				System.out.println("===thread1启动了");
				System.out.println("run by"+getName());
			}
		};
//		t1.start();
		Runnable r = new Runnable(){
			public void run(){
				System.out.println("===thread2启动了");
			}
		};
		Thread t2 = new Thread(r,"thread2");
//		t2.start();
		System.out.println(t2.getName());
		
		System.out.println(Thread.currentThread().getName());
		for(int i=0;i<10;i++){
			new Thread(""+i){
				public void run(){
					System.out.println("thread: "+getName()+" running");
				}
			}.start();
		}
	}

}
