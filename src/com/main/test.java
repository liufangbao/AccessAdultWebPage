package com.main;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		Runtime runtime=Runtime.getRuntime();
		String []cmdarray = {"mkdir","/home/liufangbao/test_folder"};
		try{

			Process p = runtime.exec(cmdarray);
			p.destroy();	
		}catch(Exception e){

		System.out.println("Error!");

		}
	}

}
