package com.main;

public class JNIKbs {
	public JNIKbs() {
	}

	public JNIKbs(String[] libraryName) {
		loadLibrary(libraryName);
	}

	private static void loadLibrary(String []libraryName) {
		for(String s:libraryName)
			System.loadLibrary(s);
	}
	
	public native int[] search(String dir,String key,int mode);
}
