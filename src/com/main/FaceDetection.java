package com.main;

public class FaceDetection
{
private JNIOpencv myJNIOpenCV;
private FaceDetection myFaceDetection;

public FaceDetection()
{
   myJNIOpenCV = new JNIOpencv();
   String filename = "lena.jpg";//OpenCV的官方测试图片（人脸识别）
   String cascade = "haarcascade_frontalface_alt.xml";
  
   int[] detectedFaces = myJNIOpenCV.detectFace(40, 40, cascade, filename);
   int numFaces = detectedFaces.length / 4;
    
   System.out.println("numFaces = " + numFaces);
   for (int i = 0; i < numFaces; i++)
    {
       System.out.println("Face " + i + ": " + detectedFaces[4 * i + 0] + " " + detectedFaces[4 * i + 1] + " " + detectedFaces[4 * i + 2] + " " + detectedFaces[4 * i + 3]);
    }
}
   
    public static void main(String args[])
    {
        FaceDetection myFaceDetection = new FaceDetection();  
    }
}