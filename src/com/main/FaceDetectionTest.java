package com.main;

import com.main.JNIOpencv;

public class FaceDetectionTest {

	public static void main(String[] args) {
		System.out.println("Hello! World");
		System.out.println(System.getProperty("java.library.path"));

			
		JNITest test = new JNITest(new String[]{"JNITest"});
		System.out.println("result:" + test.add(1, 2));
		
		JNIKbs kbs =new JNIKbs(new String[]{"JNIKbs"});
		int [] searchResult= kbs.search("/home/liufangbao/workspace/OOJDesignMode/src/com/main/JNIKbs.java", "JNIKbs", 1);
		System.out.println("searchResult:");
		for(int i:searchResult)
		{
			System.out.print(" "+Integer.toString(i));
		}
		System.out.println("");
		// 初始化JNI调用类JNIOpencv
		JNIOpencv open = new JNIOpencv(new String[] {"opencv_core", "JNIOpencv"});

		// 要检测的图片文件
		String filename = "/home/liufangbao/20351.jpg";
		// OpenCv提供的人间的特征文件
		String cascade = "/home/liufangbao/Opensource/opencv-master/data/haarcascades/haarcascade_frontalface_alt2.xml";
		// 人脸检测，前两个参数为可检测的最小人脸的宽度和高度
		// 返回值为人脸在图中的坐标和宽高，{x, y, width, height}
		int[] faces = open.detectFace(40, 40, cascade, filename);
		if (faces != null && faces.length != 0) {
			// 返回的人脸总数
			System.out.println("faces " + faces.length / 4);
			// 分别输出每个人脸的坐标信息
			for (int temp : faces) {
				System.out.println(temp);
			}
		}

	}
}
