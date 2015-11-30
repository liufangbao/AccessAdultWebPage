package com.main;
import java.io.File;
import java.io.IOException;

public class DownloadingTask extends Thread {
	// public static String TEXT_BASE_URL = "http://47bibi.com/xiaoshuoqu/";
	private String mBaseDir, mBaseUrl;
	private String mResourceType, mResourceCategory;
	private int mMaxPageNumber, mMinPageNumber;
	private String[] mKeyWord;



	DownloadingTask(String ResourceType, int max_page_number) {
		this.mResourceType = ResourceType;
		this.mMaxPageNumber = max_page_number;
		this.mMinPageNumber = max_page_number;
	}



	/**
	 * @param ResourceType
	 *            如: 视频区 电影区 图片区 小说区 下载区 ...
	 * @param ResourceCategory
	 *            如下载区里面有: bt亚洲 bt欧美 迅雷下载 ...
	 * @param min_page_number
	 * @param max_page_number
	 */
	DownloadingTask(String base_url, String base_dir, String ResourceType,
			String ResourceCategory, int min_page_number, int max_page_number,
			String[] KeyWord) {

		setBASE_URL(base_url);
		setBASE_DIR(base_dir);
		setResourceType(ResourceType);
		setResourceCategory(ResourceCategory);
		setMax_page_number(max_page_number);
		setMin_page_number(min_page_number);
		this.mKeyWord = KeyWord;

		File file = new File(mBaseDir.concat("/" + ResourceType + "/"+ ResourceCategory));
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public String getBASE_DIR() {
		return mBaseDir;
	}

	public void setBASE_DIR(String bASE_DIR) {
		mBaseDir = bASE_DIR;
	}

	public String getBASE_URL() {
		return mBaseUrl;
	}

	public void setBASE_URL(String bASE_URL) {
		mBaseUrl = bASE_URL;
	}

	public String getResourceType() {
		return mResourceType;
	}

	public void setResourceType(String resourceType) {
		mResourceType = resourceType;
	}

	public int getMin_page_number() {
		return mMinPageNumber;
	}
	public void setMin_page_number(int min_page_number) {
		this.mMinPageNumber = min_page_number;
	}
	public int getMax_page_number() {
		return mMaxPageNumber;
	}

	public void setMax_page_number(int max_page_number) {
		this.mMaxPageNumber = max_page_number;
	}

	public String getResourceCategory() {
		return mResourceCategory;
	}

	public void setResourceCategory(String resourceCategory) {
		mResourceCategory = resourceCategory;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		try {
			String[] lines = ImageLoadingThread.ExtractUsefulContent(mBaseUrl,".menu");
			for (int idx = 0; idx < lines.length; idx++) {
				if (!lines[idx].contains(mResourceType))
					continue;
				if (!lines[idx].contains(mResourceCategory))
					continue;
				if (!lines[idx].contains("href"))
					continue;
				
				String resource = HtmlRequest.parseItem(lines[idx], "href");
				String currentBaseDir = mBaseDir.concat(resource);
				String currentBaseUrl = mBaseUrl.concat(resource);
				System.out.println(currentBaseDir);
				
				String currentPageUrl;		
				for (int idx2 = getMin_page_number(); idx2 <= getMax_page_number(); idx2++) {
					if (idx2 == 1)
						currentPageUrl = currentBaseUrl.concat("index.html");
					else
						currentPageUrl = currentBaseUrl.concat("index_").concat(Integer.toString(idx2)).concat(".html");
					
					System.out.println("\nopening " + currentPageUrl);
					new HtmlRequest(mBaseUrl, currentPageUrl,currentBaseDir,mKeyWord).init();
					Thread.sleep(2);
				}
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
}