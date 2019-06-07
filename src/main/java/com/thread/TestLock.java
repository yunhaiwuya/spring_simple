package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JDK1.5以后,出现了Lock和condition,Lock类似于synchronized功能,
 * 用来进行线程同步,Condition功能类似于Object类中的wait和notify方法,
 * 用于线程间的通信
 * 
 * 编写功能类,实现子线程和主线程的功能
 * @author cjm
 *
 */
public class TestLock {

	private boolean flag;
	
	Lock lock = new ReentrantLock();
	Condition con = lock.newCondition();
	
	//子线程要实现的功能
	public void sub(){
		lock.lock();
		try {
			while(flag){
				try {
					con.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(int i=0;i<10;i++){
				System.out.println("sub"+i);
			}
			flag = true;
			con.signal();
		} finally {
			lock.unlock();
		}
	}
	//主线程实现的功能
	public synchronized void main(){
		lock.lock();
		try {
			while(!flag){
				try {
					con.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(int i=0;i<20;i++){
				System.out.println("main"+i);
			}
			flag =false;
			con.signal();
		} finally {
			lock.unlock();
		}
	}
}
