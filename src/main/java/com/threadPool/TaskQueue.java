package com.threadPool;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * TaskQueue（任务队列）
 * 采用的是FIFO（先进先出）的方式，也就是按照任务到达的先后顺序执行
 * @author cjm
 *
 */
public class TaskQueue {

	 private List<Task> queue = new LinkedList<Task>();  
	    // 添加一项任务  
	    public synchronized void addTask(Task task) {  
	        if (task != null) {  
	            queue.add(task);  
	        }  
	    }  
	    // 完成任务后将它从任务队列中删除  
	    public synchronized void finishTask(Task task) {  
	        if (task != null) {  
	            task.setState(Task.State.FINISHED);  
	            queue.remove(task);  
	        }  
	    }  
	    // 取得一项待执行任务  
	    public synchronized Task getTask() {  
	        Iterator<Task> it = queue.iterator();  
	        Task task;  
	        while (it.hasNext()) {  
	            task = it.next();  
	            // 寻找一个新建的任务  
	            if (Task.State.NEW.equals(task.getState())) {  
	                // 把任务状态置为运行中  
	                task.setState(Task.State.RUNNING);  
	                return task;  
	            }  
	        }  
	        return null;  
	    }  
}
