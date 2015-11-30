package com.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 给文件增加一行数据。
 * 
 * @author 赵学庆，Java世纪网(java2000.net)
 * 
 */
public class FileInsertRow {

	/**
	 * 在文件里面的指定行插入一行数据
	 * 
	 * @param inFile
	 *            文件
	 * @param lineno
	 *            行号
	 * @param lineToBeInserted
	 *            要插入的数据
	 * @throws Exception
	 *             IO操作引发的异常
	 */
	public void insertStringInFile(File inFile, int lineno,
			String lineToBeInserted) throws Exception {
		// 临时文件
		File outFile = File.createTempFile("name", ".tmp");
		// 输入
		FileInputStream fis = new FileInputStream(inFile);
		BufferedReader in = new BufferedReader(new InputStreamReader(fis));
		// 输出
		FileOutputStream fos = new FileOutputStream(outFile);
		PrintWriter out = new PrintWriter(fos);
		// 保存一行数据
		String thisLine;
		// 行号从1开始
		int i = 1;
		while ((thisLine = in.readLine()) != null) {
			// 如果行号等于目标行，则输出要插入的数据
			if (i == lineno) {
				out.println(lineToBeInserted);
			}
			
			if (thisLine.contains(lineToBeInserted)|| thisLine.contains("org.bytedeco.javacv")) {
				//System.out.println("found same line,skip "+ Integer.toString(i));
				i++;
				continue;
			}
			
			// 输出读取到的数据
			out.println(thisLine);
			// 行号增加
			i++;
		}
		// 去重

		out.flush();
		out.close();
		in.close();
		// 删除原始文件
		inFile.delete();
		// 把临时文件改名为原文件名
		outFile.renameTo(inFile);
	}
}
