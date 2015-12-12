package com.adult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author galaxy
 *步骤说明:
 *1. 根据首页mBaseUrl得到若干个资源类型以及每种资源类型的多个分类,在列表页面中提取每行资源的url;
 *2. 
 */
public class AccessAdultWebDemo {
	//web url
	public static String mBaseUrl = "http://44czcz.com/";
	public static String mBaseUrllAlisa = "http://44pypy.com";
	public static String mBaseUrl2 = "http://197u.com";
	public static String mBaseUrl2Alisa = "http://15yk.com";
	//local dir
	public static String mBaseDir = "/home/galaxy/Downloads";
	//task list
	private static List<DownloadingTask> mDownloadingTaskList = null;
	// thread pool
	private static ExecutorService mPool;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.print("hello world");
		
		mDownloadingTaskList = new ArrayList<DownloadingTask>();

//	    mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"tupianqu","katong",1,2,null)); 
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"tupianqu","yazhou", 1,2,null));
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"tupianqu","oumei", 1,2,null));
	   mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"tupianqu","zipai", 1,20,new String[]{"屁股","",""}));
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"tupianqu","luanlun", 1,2,null));
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"tupianqu","taotu", 1,2,null));
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"tupianqu","tongxing",1,2,null));
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"tupianqu","siwa", 1,2,null));

	
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"xiaoshuoqu","qinggan",1, 1,null));	
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"xiaoshuoqu","xiaoyuan",1,1,null));
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"xiaoshuoqu","renqi", 1,1,null));
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"xiaoshuoqu","wuxia", 1,1,null));
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"xiaoshuoqu","luanlun", 1,1,null));
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"xiaoshuoqu","linglei", 1,1,null));
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"xiaoshuoqu","xiaohua", 1,1,null));
//		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl,mBaseDir,"xiaoshuoqu","jiqiao", 1,1,null));

		/*		
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","piclist1", 1,1,null));	
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","piclist2", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","piclist3", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","piclist4", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","piclist5", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","piclist6", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","piclist7", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","piclist8", 2,2,null));
		*/
		/*
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","piclist9", 1,2,null));	
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","novellist1", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","novellist2", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","novellist3", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","novellist4", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","novellist5", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","novellist6", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","novellist7", 2,2,null));
		mDownloadingTaskList.add(new DownloadingTask(mBaseUrl2,mBaseDir,"htm","novellist8", 2,2,null));		
	*/	
		mPool = Executors.newFixedThreadPool(10*mDownloadingTaskList.size());
		Iterator iter = mDownloadingTaskList.iterator();
		while (iter.hasNext()) {
			// 1.get a object
			DownloadingTask ii = (DownloadingTask) iter.next();
			mPool.execute(ii);
		}
	}

	/*
	 * 
	 * */
	public static byte[] readInputStream(InputStream inputStream)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}
}
