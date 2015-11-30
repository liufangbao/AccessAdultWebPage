package com.main;

public class FaceDetection {
	private JNIOpencv myJNIOpenCV;
	private FaceDetection myFaceDetection;

	public FaceDetection() {
		myJNIOpenCV = new JNIOpencv();

		// 要检测的图片文件
				String filename = "/home/galaxy/37916.jpg";
				// OpenCv提供的人间的特征文件
				String cascade_face = "/home/galaxy/opencv-3.0.0/data/haarcascades/haarcascade_fullbody.xml";
				String cascade_eye = "/home/galaxy/opencv-3.0.0/data/haarcascades/haarcascade_eye.xml";
				String cascade_nose = "/home/galaxy/opencv-3.0.0/data/haarcascades/haarcascade_smile.xml";
				String cascade_mouth = "/home/galaxy/opencv-3.0.0/data/haarcascades/haarcascade_frontalface_alt.xml";
		int[] detectedFaces = myJNIOpenCV.detectFace(filename, cascade_face,cascade_eye,cascade_nose, cascade_mouth);
		int numFaces = detectedFaces.length / 4;

		System.out.println("numFaces = " + numFaces);
		for (int i = 0; i < numFaces; i++) {
			System.out.println("Face " + i + ": " + detectedFaces[4 * i + 0] + " " + detectedFaces[4 * i + 1] + " "
					+ detectedFaces[4 * i + 2] + " " + detectedFaces[4 * i + 3]);
		}
	}

	public static void main(String args[]) {
		FaceDetection myFaceDetection = new FaceDetection();
	}
}