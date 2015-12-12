package com.facedetection;

class JNIBase {
	public JNIBase() {
	}

	public JNIBase(String[] libraryName) {
		loadLibrary(libraryName);
	}

	private static void loadLibrary(String []libraryName) {
		for(String s:libraryName)
			System.loadLibrary(s);
	}
}

public class JNIOpencv extends JNIBase {
	public JNIOpencv(String []libraryName) {
		super(libraryName);
	}

	public JNIOpencv() {
		System.loadLibrary("opencv_highgui");
	}
	
	public native int[] detectFace( String filename,String cascade_face,String cascade_eye,String cascade_nose,String cascade_mouth);
}