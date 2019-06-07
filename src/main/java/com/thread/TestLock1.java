package com.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 编写程序实现,子线程循环10次,接着主线程循环20次,
 * 接着再子线程循环10次,主线程循环20次,如此反复,循环50次.
 * 
 * @author cjm
 *
 */
public class TestLock1 {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		final BlockingQueue<String> queue=new ArrayBlockingQueue<String>(16);
		final TestLock tlock = new TestLock();
		new Thread(new Runnable(){
			public void run(){
				for(int i=0;i<50;i++){
					tlock.sub();
				}
			}
		}).start();
		
		for(int i=0;i<50;i++){
			tlock.main();
		}
	}

}
