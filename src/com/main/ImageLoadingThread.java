package com.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

class ImageLoadingThread extends Thread {
	private String url;
	private String mDir;
	private static String ITEM_IMG = "img src";
	private static String ITEM_TEXT = " <br />";
	private Document doc2;
	private File file;
	private FileOutputStream out;
	
	public ImageLoadingThread( String url, String dir) {
		this.url = url;
		this.mDir = dir;
		
		if ( (url.contains("xiaoshuoqu")||url.contains("novel")||url.contains("tar.gz"))) {
			try {
				 out = new FileOutputStream(dir);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		try{
		// handle text
		if ( (url.contains("xiaoshuoqu")||url.contains("novel"))) {
			String[] lines = ExtractUsefulContent(url,".news");
			for (String s : lines){
			
				if(s.isEmpty()) continue;
				if(!s.contains(ITEM_TEXT)) continue;
				int first = s.indexOf(ITEM_TEXT);
				out.write(s.substring(first+ITEM_TEXT.length()).trim().concat("\n").getBytes("utf-8"));
				//System.out.println(s.substring(first).trim());
			}
		}else if(url.contains("tupianqu")){
			String[] lines = ExtractUsefulContent(url,".news");
			for (String s : lines) {
				String sub_img_url = null;	
				// handle image
				String s1 = "http";
				String s2= "jpg";
				if ( s.contains(s1)&& s.contains(s2)) {	
					int beginIndex = s.indexOf(s1);
					int endIndex = s.indexOf(s2)+s2.length();
					 sub_img_url = s.substring(beginIndex, endIndex);		
				}
				if(null!=sub_img_url)
					loadImage(sub_img_url.trim(), mDir);
			}
		}else{
			loadCommonFile(url,mDir);
		}
			Thread.sleep(1);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void loadImage(String img_url, String img_dir) {
		URL url;
		if (img_url.isEmpty() || img_dir.isEmpty())
			return;
		try {
			url = new URL(img_url);
			//根据响应获取文件大小
			HttpURLConnection urlcon=(HttpURLConnection)url.openConnection();
			//获取相应的文件长度
			int fileLength=urlcon.getContentLength();
			String[] arrayStr = img_url.split("/");
			String real_dir = img_dir.concat("/").concat(arrayStr[arrayStr.length - 1]);
		//	System.out.println(real_dir);
			File outFile = new File(real_dir);
			if (outFile.exists()) {
				if(fileLength!= outFile.length()){
					outFile.delete();
					System.out.println("file "+outFile.getName()+" is not completed,reload");
					outFile.createNewFile();
				}else{
					return;
				}
			}else{ 
				outFile.createNewFile();
			}
			OutputStream os = new FileOutputStream(outFile);
			InputStream is = url.openStream();
			byte[] buff = new byte[1024];
			while (true) {
				int readed = is.read(buff);
				if (readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				os.write(temp);
			}
			is.close();
			os.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	private static void loadCommonFile(String file_url, String file_dir) {
		URL url;
		if (file_url.isEmpty() || file_url.isEmpty())
			return;
		try {
			url = new URL(file_url);
		//	System.out.println(real_dir);
			File outFile = new File(file_dir);
			if (outFile.exists()) {
				outFile.delete();
			}
			outFile.createNewFile();
			OutputStream os = new FileOutputStream(outFile);
			InputStream is = url.openStream();
			byte[] buff = new byte[1024];
			while (true) {
				int readed = is.read(buff);
				if (readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				os.write(temp);
			}
			is.close();
			os.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	// 1.利用PrintStream写文件
	public void PrintStreamDemo() {
		try {
			FileOutputStream out = new FileOutputStream("D:/test.txt");
			PrintStream p = new PrintStream(out);
			for (int i = 0; i < 10; i++)
				p.println("This is " + i + " line");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 2.利用StringBuffer写文件
	public void StringBufferDemo() throws IOException {
		File file = new File(mDir);
		if (!file.exists())
			file.createNewFile();
		FileOutputStream out = new FileOutputStream(file, true);
		for (int i = 0; i < 10000; i++) {
			StringBuffer sb = new StringBuffer();
			sb.append("这是第" + i + "行:前面介绍的各种方法都不关用,为什么总是奇怪的问题 ");
			out.write(sb.toString().getBytes("utf-8"));
		}
		out.close();
	}
	
	/**
	 * @param url
	 * @param section
	 * @return your specialized section in a html
	 * @throws IOException
	 */
	public static String[] ExtractUsefulContent(String url,String section) throws IOException {
		Elements items = null;
		Document doc = Jsoup
				.connect(url)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
				.timeout(0).get();
		//System.out.println(doc.toString());
		// step 2: fetch needed item from html content	
		if(section!=null)
			items = doc.select(section);
		else
			return doc.toString().split("\n");
		if (items.isEmpty()) {
			System.out.println("can not find your specialized item.section : "+section);
		}
		return items.toString().split("\n");
	}
	
}



