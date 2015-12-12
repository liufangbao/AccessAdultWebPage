package com.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.adult.HtmlRequest;
import com.adult.ImageLoadingThread;

public class DownlodingX11Package {
	
	private static String IMAGE_URL = "url";
	private static String IMAGE_DIR = "dir";
	private static String ITEM_HREF = "href";
	private static List<Map<String, String>> mHtmlPageUrlList;
	// 创建一个可缓存的线程池。
	private static ExecutorService mPool;
	public static String mBaseDir = "/home/galaxy/Opensource/";
	public static String mCurrentUrl ="http://www.x.org/releases/X11R7.6/src/";
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		mHtmlPageUrlList = new ArrayList<Map<String, String>>();
		String[] lines = ImageLoadingThread.ExtractUsefulContent(mCurrentUrl,null);
		
		for(String s:lines){
			if(s.contains(ITEM_HREF))
			{
				String folder = HtmlRequest.parseItem(s,ITEM_HREF);
				if(folder.contains("?")||folder.contains("."))
					continue;
				String pageUrl = mCurrentUrl+folder;
//				File f =new File(mBaseDir+folder);
//				if(!f.exists()){
//					f.mkdir();
//				}
				//System.out.println(pageUrl);
				String[] pageFileLines = ImageLoadingThread.ExtractUsefulContent(pageUrl,null);
				for(String s2:pageFileLines){
					if(s2.contains(ITEM_HREF))
					{
						String file = HtmlRequest.parseItem(s2,ITEM_HREF);
						if(!file.contains("tar.gz"))
							continue;
						String fileUrl = pageUrl+file;
						//String fileDir = mBaseDir+folder+file;
						String fileDir = mBaseDir+file;
					//	System.out.println(fileUrl);
						
						Map<String, String> url_title = new HashMap<String, String>();
						url_title.put(IMAGE_URL, fileUrl);
						url_title.put(IMAGE_DIR, fileDir);
						mHtmlPageUrlList.add(url_title);
					}
				
			}
			}
		}
		mPool = Executors.newCachedThreadPool();	
		// step 2: load pictures from url and put them into dirs created ahead.
		Iterator iter = mHtmlPageUrlList.iterator();
		while (iter.hasNext()) {
			Map<String, String> ut = (Map<String, String>) iter.next();
			String url = ut.get(IMAGE_URL);
			String dir = ut.get(IMAGE_DIR);
			System.out.println("begin loading files from " + url + "  into   "+ dir + " .....");
			mPool.execute(new ImageLoadingThread(url, dir));
		}
	}

}
