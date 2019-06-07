package com.thread;

public class Thread1 extends Thread{

	public static void main(String[] args) {
		Thread1 t = new Thread1();
		t.start();
	}
	
	public void run(){
		System.out.println("====thread1启动了");
	}
}
