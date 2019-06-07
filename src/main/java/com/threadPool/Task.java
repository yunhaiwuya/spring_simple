package com.threadPool;

/**
 * Task（任务）：任务抽象类
 * 为了方便线程对任务的处理，我们需要用Task抽象类来保证任务的具体工作统一
 * 放在deal()方法里来完成，这样也使代码更加规范
 * 
 * @author cjm
 *
 */
public abstract class Task {

	/* 新建 、执行中、已完成*/
	 public enum State {  
	        NEW, 
	        RUNNING, 
	        FINISHED  
	    }  
	    // 任务状态  
	    private State state = State.NEW;  
	    public void setState(State state) {  
	        this.state = state;  
	    }  
	    public State getState() {  
	        return state;  
	    }  
	    public abstract void deal();  
}
