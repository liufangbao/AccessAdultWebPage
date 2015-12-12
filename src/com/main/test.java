package com.main;

import java.io.File;

public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("hello");
		
		Car car = Driver.driveCar("bmw");
		car.drive();
		
		Driver bmwdriver = new BmwDriver();
		Car car2 = bmwdriver.driveCar();
		car2.drive();
		
		Car car3 = Driver.driveBmwCar();
		car3.drive();
		
		Driver volvodriver = new VolvoDriver();
		Car car4 = volvodriver.driveCar();
		car4.drive();
		
		
		File f=new File("/home/galaxy/workspace/AccessAdultWebPage/src/com/design_pattern");
		File flist[] = f.listFiles();
		int count=0;
		System.out.println(Integer.toString(flist.length));
		for (File file : flist) {
			if(file.isDirectory())
			{
				System.out.println(Integer.toString(count++)+" entering directory: "+file.getName());
				//package com.design_pattern.Adapter.src;
				String lineToBeInserted="package com.design_pattern."+file.getName()+".src;";
				File flist2[] = new File(file+"/src").listFiles();
				for (File file2 : flist2) {
					if(file2.isFile() && file2.getAbsolutePath().endsWith(".java"))
					{	
						FileInsertRow.insertStringInFile(file2, 1, lineToBeInserted);
					}
				}
				System.out.println(Integer.toString(count++)+" exiting directory: "+file.getName());
			}else{
				System.out.println(Integer.toString(count++)+"not a dir  "+file.getName());
			}
		}	
		
		Runtime runtime=Runtime.getRuntime();
		String []cmdarray = {"mkdir","/home/galaxy/test_folder"};
		try{

			Process p = runtime.exec(cmdarray);
			p.destroy();	
		}catch(Exception e){

		System.out.println("Error!");

		}
	}

}
