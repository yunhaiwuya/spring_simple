package com.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Java5.0及以上版本已经为我们提供了线程池功能，无需再重新实现。
 * 这些类位于java.util.concurrent包
 * @author Administrator
 *
 */
public class ExecutorTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();  
        @SuppressWarnings("rawtypes")
		Future fr = es.submit(new RunnableTest());// 提交任务  
        @SuppressWarnings("rawtypes")
		Future fc = es.submit(new CallableTest());// 提交任务  
        // 取得返回值并输出  
        System.out.println((String) fc.get());  
        // 检查任务是否执行完毕  
        if (fr.isDone()) {  
            System.out.println("执行完毕-RunnableTest.run()");  
        } else {  
            System.out.println("未执行完-RunnableTest.run()");  
        }  
        // 检查任务是否执行完毕  
        if (fc.isDone()) {  
            System.out.println("执行完毕-CallableTest.run()");  
        } else {  
            System.out.println("未执行完-CallableTest.run()");  
        }  
        // 停止线程池服务  
        es.shutdown();  
    }  
}  
class RunnableTest implements Runnable {  
	public void run() {  
		System.out.println("已经执行-RunnableTest.run()");  
	}  
}  
@SuppressWarnings("rawtypes")
class CallableTest implements Callable {  
	public Object call() {  
		System.out.println("已经执行-CallableTest.call()");  
		return "返回值-CallableTest.call()";  
	} 
}
