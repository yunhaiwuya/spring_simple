package com.thread;

public class Thread2 implements Runnable{

	
	public static void main(String[] args) {
		Thread t = new Thread(new Thread2());
		t.start();
	}
	
	@Override
	public void run() {
		System.out.println("=====thread2启动了");
	}

}
