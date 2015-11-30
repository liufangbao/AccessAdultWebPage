package com.main;

import com.main.JNIOpencv;

/*
 * javac ./com/main/JNITest.java
 * javah -jni -classpath . -d ../jni com.main.JNITest
 * cc -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include/linux -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include -I. -fPIC -shared -o ../jni/libJniTest.so ../jni/com_main_JNITest.cpp
 * 
 * javac ./com/main/JNIOpencv.java
 * javah -jni -classpath . -d ../jni com.main.JNIOpencv
 * cc -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include/linux -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include -I../opencv/include -I. -fPIC -shared -o ../jni/libJniOpencv.so ../jni/com_main_JNIOpencv.cpp
 * */

public class FaceDetectionTest {

	public static void main(String[] args) {
		System.out.println("Hello! World");
		System.out.println(System.getProperty("java.library.path"));

		JNITest test = new JNITest(new String[] { "JniTest" });
		System.out.println("result:" + test.add(1, 2));

		// JNIKbs kbs =new JNIKbs(new String[]{"JNIKbs"});
		// int [] searchResult=
		// kbs.search("/home/liufangbao/workspace/OOJDesignMode/src/com/main/JNIKbs.java",
		// "JNIKbs", 1);
		// System.out.println("searchResult:");
		// for(int i:searchResult)
		// {
		// System.out.print(" "+Integer.toString(i));
		// }
		System.out.println("");
		// 初始化JNI调用类JNIOpencv
		JNIOpencv open = new JNIOpencv(new String[] { "opencv_core","opencv_calib3d",
				"opencv_features2d","opencv_flann",
				"opencv_imgcodecs","opencv_ml",
				"opencv_photo","opencv_shape",
				"opencv_stitching","opencv_superres",
				"opencv_video","opencv_videoio",
				"opencv_videostab","opencv_highgui",
				"opencv_imgproc","opencv_objdetect","JniOpencv" });

		// 要检测的图片文件
		String filename = "/home/galaxy/37916.jpg";
		// OpenCv提供的人间的特征文件
		String cascade_face = "/home/galaxy/opencv-3.0.0/data/haarcascades/haarcascade_fullbody.xml";
		String cascade_eye = "/home/galaxy/opencv-3.0.0/data/haarcascades/haarcascade_eye.xml";
		String cascade_nose = "/home/galaxy/opencv-3.0.0/data/haarcascades/haarcascade_smile.xml";
		String cascade_mouth = "/home/galaxy/opencv-3.0.0/data/haarcascades/haarcascade_frontalface_alt.xml";
		// 人脸检测，前两个参数为可检测的最小人脸的宽度和高度
		// 返回值为人脸在图中的坐标和宽高，{x, y, width, height}

		int[] faces = open.detectFace(filename, cascade_face,cascade_eye,cascade_nose, cascade_mouth);
		if (faces.length > 0) {
			// 返回的人脸总数
			System.out.println("faces " + faces.length / 4);
			// 分别输出每个人脸的坐标信息
			for (int temp : faces) {
				System.out.println(temp);
			}
		}

	}
}
