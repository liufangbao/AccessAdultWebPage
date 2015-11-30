package com.main;
public class MyThread extends Thread {
	private String name;

	public MyThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(Thread.currentThread().getName() + "正在执行。。。");
		System.out.println(name+ "正在执行。。。");
	}
}