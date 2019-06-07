package com.thread;

/**
 * 设计四个线程,其中两个线程每次对变量i加1,
 * 另外两个线程每次对i减1.
 * @author cjm
 *
 */
public class TestThread1 {

	private int i = 0;
	public static void main(String[] args) {
		TestThread1 t = new TestThread1();
		Add add = t.new Add();
		Sub sub = t.new Sub();
		for(int i=0;i<2;i++){
			new Thread(add,"线程"+i).start();
			new Thread(sub,"线程"+i).start();
		}
	}
	class Add implements Runnable{
		@Override
		public void run(){
			for(int i=0;i<10;i++){
				addOne();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	class Sub implements Runnable{
		@Override
		public void run(){
			for(int i=0;i<10;i++){
				subOne();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized void addOne(){
		i++;
		System.out.println(Thread.currentThread().getName()+"加1的值："+i);
	}
	public synchronized void subOne(){
		i--;
		System.out.println(Thread.currentThread().getName()+"减1的值："+i);
	}
}
