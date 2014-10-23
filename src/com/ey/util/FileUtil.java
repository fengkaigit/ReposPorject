package com.ey.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.HWPFList;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.ParagraphProperties;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class FileUtil {

	public static final int BUFFER_SIZE = 4096;

	private static final Log logger = LogFactory.getLog(FileUtil.class);

	public static int copy(File in, File out) throws IOException {
		return copy(new BufferedInputStream(new FileInputStream(in)), new BufferedOutputStream(new FileOutputStream(out)));
	}

	public static int copy(InputStream in, OutputStream out) throws IOException {
		try {
			int byteCount = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			return byteCount;
		}
		finally {
			try {
				in.close();
			}
			catch (IOException ex) {
				logger.warn("Could not close InputStream", ex);
			}
			try {
				out.close();
			}
			catch (IOException ex) {
				logger.warn("Could not close OutputStream", ex);
			}
		}
	}

	public static int copy(String srcFile, String destDir) throws IOException {
		File in = new File(srcFile);
		File out = new File(makeNormalDirName(destDir) + in.getName());
		return copy(in, out);
	}

	public static int copy(String srcFile, String destFileName, String destDir) throws IOException {
		File in = new File(srcFile);
		File out = new File(makeNormalDirName(destDir) + destFileName);
		return copy(in, out);
	}

	public static String getFileNameWithoutExt(String fileName) {
		int idx = fileName.lastIndexOf(".");
		if (idx >= 0) {
			return fileName.substring(0, idx);
		}
		return fileName;
	}

	public static String getFileShortNameWithoutExt(String fileName) {
		return getFileNameWithoutExt(getShortFileName(fileName));
	}

	public static String getShortFileName(String fileName) {
		fileName = fileName.replace('\\', '/');
		int idx = fileName.lastIndexOf('/') + 1;
		if (idx > 0 && idx < fileName.length()) {
			return fileName.substring(idx);
		}
		return fileName;
	}

	public static boolean isExistsFile(String fileName) {
		return new File(fileName).exists();
	}

	public static void makeFile(String content, String fileName, String encode) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		try {
			fos.write(content.getBytes(encode));
		}
		finally {
			try {
				fos.close();
			}
			catch (IOException e) {
				logger.warn("Could not close OutputStream", e);
			}
		}
	}

	public static String makeFileName(String fileName) {
		if (fileName != null) {
			return fileName.replaceAll("<font[^/>]*>", "").replaceAll("<\\\\font>", "").replaceAll("[\\\\/:\\*\\?\"<>|]", "");
		}
		return "";
	}

	public static String makeNormalDirName(String dir) {
		dir = dir.replace('\\', '/');
		if (dir.charAt(dir.length() - 1) != '/') {
			dir += "/";
		}
		return dir;
	}

	public static boolean checkFile(String filename) {
		File objFile;
		String obj = pathformat(filename);
		objFile = new File(obj);
		if (objFile.exists()) {
			return true;
		}
		return false;
	}

	public static String pathformat(String path) {
		int i = 0;
		int pathlength = path.length();
		String returns = "";
		while (i < pathlength) {
			if (path.substring(i, (i + 1)).equals("\\")) {
				returns = returns + "/";
			}
			else {
				returns = returns + path.substring(i, (i + 1));
			}
			i = i + 1;
		}
		return returns;
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static final boolean createDir(String path) {
		boolean flag = true;
		try {
			File f = new File(path);
			f.mkdir();
		}
		catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public static void writeFile(String path, String content) {
		try {
			RandomAccessFile newfile = new RandomAccessFile(path, "rw");
			newfile.writeBytes(content);
			newfile.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static final boolean deleteFile(String pathName) {
		boolean result = false;
		try {
			File file = new File(pathName);
			file.delete();
			result = true;
		}
		catch (Exception e) {
			result = false;
		}
		return result;
	}

	public static final InputStream getFileInputStream(String dirName, String fileName) {

		FileInputStream f = null;
		try {
			f = new FileInputStream(dirName + "//" + fileName);

		}
		catch (FileNotFoundException e) {}
		InputStream in = new DataInputStream(f);
		return in;
	}

	/**
	 * @param dirName
	 * @param fileName
	 * @return
	 */
	public static final InputStream getFileInputStream(File file) {

		FileInputStream f = null;
		try {
			f = new FileInputStream(file);

		}
		catch (FileNotFoundException e) {}
		InputStream in = new DataInputStream(f);
		return in;
	}

	public static final String[] listFile(String path) {
		String[] result;
		File file = new File(path);
		if (file.canRead() && file.isDirectory()) {
			result = file.list();
			Arrays.sort(result);
			return result;
		}
		return null;
	}

	public ArrayList listAllFile(String path) {
		ArrayList all = new ArrayList();
		addFile(path);
		return all;
	}

	private void addFile(String path) {
		ArrayList all = new ArrayList();
		String[] list = listFile(path);
		File fObject;
		File file = new File(path);
		String filePath;
		for (int i = 0; i < list.length; i++) {
			filePath = file.getPath() + File.separator + list[i];
			fObject = new File(filePath);
			if (fObject.isFile()) {
				all.add(filePath);
			}
			else {
				addFile(filePath);
			}
		}
	}

	public static void copyDirectiory(String file1, String file2) throws IOException {
		(new File(file1)).mkdirs();
		File[] file = (new File(file2)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				FileInputStream input = new FileInputStream(file[i]);
				FileOutputStream output = new FileOutputStream(file1 + "/" + file[i].getName());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (file[i].isDirectory()) {
				copyDirectiory(file1 + "/" + file[i].getName(), file2 + "/" + file[i].getName());
			}
		}
	}

	public static void copyDir(String dir1, String dir2) throws IOException {
		(new File(dir2)).mkdirs();
		File[] file = (new File(dir1)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				FileInputStream input = new FileInputStream(file[i]);
				FileOutputStream output = new FileOutputStream(dir2 + "/" + file[i].getName());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (file[i].isDirectory()) {
				copyDirectiory(dir2 + "/" + file[i].getName(), dir1 + "/" + file[i].getName());
			}
		}
	}

	public static void wirteStringToFile(String writeString, File file) throws IOException {
		FileOutputStream out = new FileOutputStream(file);
		out.write(writeString.getBytes());
		out.close();
	}

	/**
	 * @param bytes
	 * @param file
	 * @throws IOException
	 */
	public static void wirteStringToFile(byte[] bytes, File file) throws IOException {
		FileOutputStream out = new FileOutputStream(file);
		out.write(bytes);
		out.close();
	}

	public static void deleteTree(String strFile) {
		try {
			File file = new File(strFile);
			if (file.isFile()) {
				file.delete();
			}
			else if (file.exists()) {
				String[] lists = file.list();
				for (int i = 0; i < lists.length; i++) {
					deleteTree(file + java.io.File.separator + lists[i]);
				}
				file.delete();
			}
		}
		catch (Exception err) {

		}
	}

	public static void deleteDirFiles(String strDir) {
		try {
			File file = new File(strDir);
			if (file.isDirectory() && file.exists()) {
				String[] lists = file.list();
				for (int i = 0; i < lists.length; i++) {
					File dirfile = new File(file + java.io.File.separator + lists[i]);
					if (dirfile.isFile()) {
						dirfile.delete();
					}
				}
			}
		}
		catch (Exception err) {

		}
	}

	public static String readFile(File file) throws IOException {
		byte[] b = new byte[(int) file.length()];
		try {
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			in.read(b, 0, (int) file.length());
			in.close();
			fstream.close();
		}
		catch (Exception e) {
			System.err.println("File input error");
			e.printStackTrace();
		}
		return new String(b);
	}

	public static void main(String args[]) {
		/*
		 * String url1="C:/Downloads/"; String url2="d:/java/copyfile/copy"; try{
		 * copyDir(url1,url2); } catch (IOException e){}
		 */
	}
	public static void downLoadFile(File file,HttpServletResponse response,String fileName){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		InputStream fis = null;
		try {
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader(
					"Content-disposition",
					"attachment;filename="
							+ URLEncoder.encode(fileName,
									"UTF-8"));
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			fos = response.getOutputStream();
			bos = new BufferedOutputStream(fos);

			int bytesRead = 0;
			byte[] buffer = new byte[fis.available()];
			while ((bytesRead = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			fos.flush();
			bos.close();
			fos.close();
			bis.close();
			fis.close();
	}catch(Exception ex){
		ex.printStackTrace();
	}
	}
	public static void downLoadPdf(File file,HttpServletResponse response,String fileName){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		InputStream fis = null;
		try {
			response.setContentType("application/pdf");
			response.setHeader(
					"Content-disposition",
					"attachment;filename="
							+ URLEncoder.encode(fileName,
									"UTF-8"));
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			fos = response.getOutputStream();
			bos = new BufferedOutputStream(fos);

			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			fos.flush();
			bos.close();
			fos.close();
			bis.close();
			fis.close();
	}catch(Exception ex){
		ex.printStackTrace();
	}
	}
	public static String createFileByTemplateFile(List<Map<String,Object>> dataList,String templateFilePath,String destFilePath){
		String resultPath = null;
		try{
		    FileInputStream in = new FileInputStream(new File(templateFilePath));
		    resultPath = createFileByTemplateFile(dataList,in,destFilePath);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultPath;
	}
	public static String createFileByTemplateFile(List<Map<String,Object>> dataList,InputStream in,String destFilePath){
		String newTemplateFile = null;
		try{
		XWPFDocument document = new XWPFDocument(in);
		List<XWPFParagraph>  listParagraphs = document.getParagraphs();
		XWPFParagraph xwpf = null;
		int num = 0;
		int spaceNum = 0;
		for(XWPFParagraph paragra:listParagraphs){
			if(!paragra.getText().equals("")){
				xwpf = paragra;
			}else{
				spaceNum++;
			}
			num++;
		}
		int paragrapLen = dataList.size()-1;
		List<XWPFRun> runs = xwpf.getRuns();
		for(int i =0 ; i < paragrapLen;i++){
		     XWPFParagraph newXwpf = document.createParagraph();
		     newXwpf = xwpf;
		     document.setParagraph(newXwpf,num);
		     num++;
		}
		File newFile= new File(destFilePath);
	    newTemplateFile = createFile(document,destFilePath.replace(newFile.getName(),"attachPrefix1-"+DateUtil.getTimeNowString(new Date())+".docx"));
		XWPFDocument createDoc = new XWPFDocument(POIXMLDocument.openPackage(newTemplateFile));
		for(int i=0; i < dataList.size();i++){
			 XWPFParagraph paragra = createDoc.getParagraphArray(spaceNum+i);
			 Map<String,Object> map = dataList.get(i);
			 List<XWPFRun> listRun = paragra.getRuns();
			 for(XWPFRun run:listRun){
		         for(Entry<String, Object> e:map.entrySet()){
					 if(run.getText(0).trim().equals(e.getKey())){
						 run.setText(e.getValue()+"",0);
					 }
				 }
			  
		     }
		}
		createFile(createDoc,destFilePath);
		System.gc();
		deleteFile(newTemplateFile);
		}catch(Exception e){
			e.printStackTrace();
		}
		return destFilePath;
	}
	public static String create2003FileByTemplateFile(Map<String,Object> dataMap,List<Map<String,Object>> dataList,InputStream in,String destFilePath){
		try{
		HWPFDocument document = new HWPFDocument(in);
		Range range = document.getRange();
		List<Paragraph> pars = new ArrayList<Paragraph>();
		int parLen = range.numParagraphs();
		for(int i = 0; i < parLen;i++){
			Paragraph par = range.getParagraph(i);
			String text = par.text();
			System.out.println(text);
			if(!text.trim().equals("")){
				pars.add(par);
			}
		}
		/*dataMap = dataList.get(0);
		if(dataMap!=null&&dataMap.size()>0){
			for(Map.Entry<String, Object> entry:dataMap.entrySet()){
				range.replaceText(entry.getKey(), entry.getValue()+"");
			}
		}*/
		if(dataList!=null){
				int paragLen = dataList.size();
				for (int i = 1; i < paragLen; i++) {
					Map<String, Object> vMap = dataList.get(i);
					for(Paragraph p:pars){
						System.out.println(p.text());
						Paragraph newPar = range.insertAfter(p.cloneProperties(),0);
						replaceRunText(vMap, p, newPar);
					}
				}
				if (pars!=null&&pars.size()>0&&paragLen>0) {
					Map<String, Object> vMap = dataList.get(0);
					for(Paragraph p:pars){
						System.out.println(p.text());
						//replaceRunText(vMap, p, null);
						p.delete();
					}
				}
				if (paragLen > 0){
					create2003File(document, destFilePath);
				}
			}
		}catch(Exception e){
		       e.printStackTrace();	
		}
		return destFilePath;
	}
	public static String create2003FileByTemplateFile(Map<String,Object> dataMap,List<Map<String,Object>> dataList,String templateFilePath,String destFilePath){
		String resultPath = null;
		try{
		    FileInputStream in = new FileInputStream(new File(templateFilePath));
		    resultPath = create2003FileByTemplateFile(dataMap,dataList,in,destFilePath);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultPath;
	}
	private static void replaceRunText(Map<String,Object> vMap,Paragraph par,Paragraph newPar){
		   CharacterRun newRun = null;
		   int lenChar = par.numCharacterRuns();
		   List<Paragraph> newPars = new ArrayList<Paragraph>();
	       for(int y=0;y<lenChar;y++){
		      CharacterRun run = par.getCharacterRun(y);
		      if(newPar!=null)
	    	      newRun = newPar.insertAfter(run.text(), run.cloneProperties());
		      for(Map.Entry<String, Object> entry:vMap.entrySet()){
		           if(newRun!=null){
		    	      if(newRun.text().trim().indexOf(entry.getKey()) > -1){
		    	    	  newRun.replaceText(entry.getKey(), entry.getValue()+"");
		    	    	  break;
			          }
		    	      continue;
		           }
		          
		          if(run.text().trim().indexOf(entry.getKey()) > -1){
		        	  run.replaceText(entry.getKey(), entry.getValue()+"");
		        	  break;
	    	      }
		      }
	       }
	}
	private static String  createFile(XWPFDocument document,String destFile) throws Exception{
		FileOutputStream outStream = new FileOutputStream(destFile);
		document.write(outStream);
		outStream.close();
		outStream.flush();
		return destFile;
	}
	private static String create2003File(HWPFDocument document,String destFile) throws Exception{
		FileOutputStream outStream = new FileOutputStream(destFile);
		document.write(outStream);
		outStream.close();
		outStream.flush();
		return destFile;
	}

}
