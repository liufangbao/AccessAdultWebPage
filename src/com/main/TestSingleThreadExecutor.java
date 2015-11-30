package com.main;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 要配置一个线程池是比较复杂的，尤其是对于线程池的原理不是很清楚的情况下，很有可能配置的线程池不是较优的，因此在Executors类里面提供了一些静态工厂，生成一些常用的线程池。

 1. newSingleThreadExecutor

 创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。
 此线程池保证所有任务的执行顺序按照任务的提交顺序执行。


 * */
public class TestSingleThreadExecutor {

	public static void main(String[] args) {

		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newSingleThreadExecutor();

		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new MyThread("t1");
		Thread t2 = new MyThread("t2");
		Thread t3 = new MyThread("t3");
		Thread t4 = new MyThread("t4");
		Thread t5 = new MyThread("t5");

		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);

		// 关闭线程池
		pool.shutdown();

	}

}