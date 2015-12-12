package com.adult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author galaxy this program is developed for loading pictures from
 *         http://47bibi.com/tupianqu/zipai , enjoy yourself!!!!^^__^^
 */

public class HtmlRequest {

	private static String ITEM_HREF = "href";
	private static String ITEM_TITLE = "title";

	private static String IMAGE_URL = "url";
	private static String IMAGE_DIR = "dir";
	private static List<Map<String, String>> mHtmlPageUrlList;

	private String mBaseDir;
	private String mBaseUrl;
	private String mCurrentUrl;
	private String[] mKeyWord;
	// 创建一个可缓存的线程池。
	private static ExecutorService mPool;

	public HtmlRequest(String baseUrl, String currentUrl, String baseDir,String[] KeyWord) {
		this.mBaseUrl = baseUrl;
		this.mBaseDir = baseDir;
		this.mCurrentUrl = currentUrl;
		this.mKeyWord = KeyWord;
		mHtmlPageUrlList = new ArrayList<Map<String, String>>();
		mPool = Executors.newCachedThreadPool();
	}

	public static HtmlRequest createFactory()
	{
		return null;
		
	}
	
	public HtmlRequest() {
		mPool.shutdown();
	}

	public void init() throws IOException {
		// step 1: fetch html content with the url
		String[] lines = ImageLoadingThread.ExtractUsefulContent(mCurrentUrl,".main");
		// step 3:handle the picked item,get the necessary title and  page item index url 
		for (String s : lines) {
			//System.out.println(s);
			if (!s.contains(ITEM_HREF) || !s.contains(ITEM_TITLE)) 
				continue;

				 String real_url = mBaseUrl.concat(parseItem(s, ITEM_HREF));
				 String	sub_title = parseItem(s, ITEM_TITLE);	
			//	System.out.println("real_url= "+real_url +"  sub_title = "+sub_title );
			if (null != mKeyWord) {
				int counter = 0;
				for (String s1 : mKeyWord) {
					if (sub_title.contains(s1)) {
						counter++;
					}
				}
				if (counter <= 0)
					continue;
			}
				
				File file = new File(mBaseDir.concat("/").concat(sub_title));
				if (mCurrentUrl.contains("tupianqu") || mCurrentUrl.contains("pic")) {
					if (!file.exists()) {
						file.mkdir();
					}
				} else if (mCurrentUrl.contains("xiaoshuoqu") || mCurrentUrl.contains("novel")){
					if (!file.exists()) {
						file.createNewFile();
					}
				}
				// add to list set
				Map<String, String> url_title = new HashMap<String, String>();
				url_title.put(IMAGE_URL, real_url);
				url_title.put(IMAGE_DIR, file.getAbsolutePath());
				mHtmlPageUrlList.add(url_title);
		}

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

	

	/**
	 * @param s
	 * @param iTEM
	 * @return 
	 * example:  
	 * 				<a href="/xiaoshuoqu/xiaohua/">情色笑话</a>  
	 * 	return:
	 * 					 /xiaoshuoqu/xiaohua/
	 */
	public static String parseItem(String s, String iTEM) {
		// TODO Auto-generated method stub
		String result =null;
		int position = s.indexOf(iTEM);
		if(position<0){
			return null;
		}
		String s2 = s.substring(position + iTEM.length() + 2);

		int position2 = s2.indexOf("\"");
		if(position2<0){
			return null;
		}
		String s3 = s2.substring(0, position2);

		// System.out.println(s3);
		result = s3;
		return result;
	}
}
