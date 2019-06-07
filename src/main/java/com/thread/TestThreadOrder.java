package com.thread;

/**
 * 现在有T1、T2、T3三个线程，
 * 你怎样保证T2在T1执行完后执行，
 * T3在T2执行完后执行
 * join的意思是使得放弃当前线程的执行，并返回对应的线程，例如下面代码的意思就是：
 * 程序在main线程中调用t1线程的join方法，则main线程放弃cpu控制权，
 * 并返回t1线程继续执行直到线程t1执行完毕，所以结果是t1线程执行完后，
 * 才到主线程执行，相当于在main线程中同步t1线程，
 * t1执行完了，main线程才有执行的机会
 *
 * @author cjm
 *
 */
public class TestThreadOrder {

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable(){
			public void run(){
				System.out.println(Thread.currentThread().getName()+"===1");
			}
		});
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				System.out.println(Thread.currentThread().getName()+"===2");
			}
		});
		Thread t3 = new Thread(new Runnable(){
			public void run(){
				System.out.println(Thread.currentThread().getName()+"===3");
			}
		});
		t1.start();
		try {
			//join方法的主要作用就是同步，它可以使得线程之间的并行执行变为串行执行
			//join方法的作用：在A线程中调用了B线程的join()方法时，
			//表示只有当B线程执行完毕时，A线程才能继续执行
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t3.start();
	}

}
