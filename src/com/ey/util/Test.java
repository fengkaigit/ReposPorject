package com.ey.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;

public class Test implements Serializable {
	private String value;
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	private String value5;
	private String value6;
	private String value7;
	private String sno;
	private String meetingTitle;
	private String content;

	public static void main(String[] args) throws Exception {
		File pdfFile = new File("c:/正文.pdf");
		byte[] b = new byte[(int) pdfFile.length()];

		FileInputStream fstream = new FileInputStream(pdfFile);
		DataInputStream in = new DataInputStream(fstream);
		in.read(b, 0, (int) pdfFile.length());
		in.close();
		fstream.close();
		byte[] pbs = Base64.encodeBase64(b);
		FileOutputStream pos = new FileOutputStream("c:/Base64test.txt");
		pos.write(pbs);
		pos.close();
		File file = new File("c:/Base64test.txt");
		FileReader fr = new FileReader(file);

		BufferedReader br = new BufferedReader(fr);
		String content = "";
		String line = null;
		while ((line = br.readLine()) != null) {
			content = content + line;
		}
		byte[] bys = Base64.decodeBase64(content.getBytes());
		FileOutputStream fos = new FileOutputStream("c:/meme.pdf");
		fos.write(bys);
		fos.close();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getValue5() {
		return value5;
	}

	public void setValue5(String value5) {
		this.value5 = value5;
	}

	public String getValue6() {
		return value6;
	}

	public void setValue6(String value6) {
		this.value6 = value6;
	}

	public String getValue7() {
		return value7;
	}

	public void setValue7(String value7) {
		this.value7 = value7;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

}
