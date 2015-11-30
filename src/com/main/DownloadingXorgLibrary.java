package com.main;

import java.io.File;
import java.io.IOException;

public class DownloadingXorgLibrary {
	private static String IMAGE_URL = "url";
	private static String IMAGE_DIR = "dir";
	private static String ITEM_HREF = "href";
	private static String ITEM_TITLE = "title";
	public static String mBaseDir = "/home/liufangbao/Opensource/";
	public static String mCurrentUrl ="http://cgit.freedesktop.org";
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Runtime runtime=Runtime.getRuntime();
		Runtime runtime2=Runtime.getRuntime();
		String []cmdarray1 = {"cd","/home/liufangbao/Opensource",null};
		String []cmdarray2 = {"git","clone",null};
		String[] lines = ImageLoadingThread.ExtractUsefulContent(mCurrentUrl,null);
		String target = "xdpyinfo";
		for(String s:lines){
			if(s.contains(ITEM_HREF) && s.contains(ITEM_TITLE) &&s.contains(target))
			{
				String folder = HtmlRequest.parseItem(s,ITEM_HREF);
				String pageUrl = mCurrentUrl+folder;
				File f =new File(cmdarray1[1]);
				if(!f.exists()){
					f.mkdir();
				}
				System.out.println(pageUrl);
				String[] pageFileLines = ImageLoadingThread.ExtractUsefulContent(pageUrl,null);
				for(String s2:pageFileLines){
					if(s2.contains(ITEM_HREF)&&s2.contains("git://"))
					{
						//System.out.println(s2);
						String gitUrl = HtmlRequest.parseItem(s2,ITEM_HREF);
						
						if(gitUrl.contains(target)){
							System.out.println("begin downloading "+target+" from "+gitUrl + "into "+mCurrentUrl);
							cmdarray2[2] = gitUrl;
							try{
								runtime.exec(cmdarray1);
								runtime2.exec(cmdarray2);
								}catch(Exception e){
								System.out.println("Error!");
							}
						}
					}
				}
			}
		}
	}

}
