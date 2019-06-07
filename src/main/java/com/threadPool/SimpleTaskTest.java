package com.threadPool;

public class SimpleTaskTest extends Task{

	public static void main(String[] args) throws InterruptedException {
		ThreadPoolService service = new ThreadPoolService();  
        service.start();  
        // 执行十次任务  
        for (int i = 0; i < 10; i++) {  
            service.runTask(new SimpleTaskTest());  
        }  
        // 睡眠1秒钟，等待所有任务执行完毕  
        Thread.sleep(1000);  
        service.stop();  
	}

	@Override
	public void deal() {
		// TODO Auto-generated method stub
		System.out.println("======123");
	}

}
