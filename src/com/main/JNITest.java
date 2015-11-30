package com.main;

public class JNITest {
	public JNITest() {
	}

	public JNITest(String[] libraryName) {
		loadLibrary(libraryName);
	}

	private static void loadLibrary(String []libraryName) {
		for(String s:libraryName)
			System.loadLibrary(s);
	}
	
	public native int add(int first,int second);
}
