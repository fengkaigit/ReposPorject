package com.ey.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


/**
 * 
        * Desc:  EXCELAPI封装类   
        * @version 1.0  
        * @author dongxiaoping@ieds.com.cn  
        * @update 2012-10-19 上午11:55:33
 */
public class XLSReport {
	/**
	 * 
			* Desc:  根据表头、数据、合并信息生成EXCEL报表
	        * @param datas
	        * @param headers
	        * @param mergeColMap
	        * @param mergeRowMap
	        * @param path
	        * @param fileName
	        * @return
	        * @throws Exception     
	        * @version 1.0  
	        * @author dongxiaoping@ieds.com.cn  
	        * @update 2012-10-19 上午11:56:02
	 */
	public File generating(List<Object[]> datas, List<Object[]> headers,
			Map<String, Integer> mergeColMap, Map<String, Integer> mergeRowMap,
			String path, String fileName) throws Exception {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(Locale.CHINA);
		ws.setEncoding("gbk");
		File file = new File(path + fileName + ".xls");
		WritableWorkbook workbook = Workbook.createWorkbook(file, ws);
		WritableSheet workSheet = workbook.createSheet(fileName, 0);
		creatheader(workSheet, headers, mergeColMap, mergeRowMap,null,true);
		creatBody(workSheet, datas, headers.size());
		if (headers != null && headers.size() > 0) {
			int col = headers.get(0).length;
			for (int i = 0; i < col; i++) {
				workSheet.setColumnView(i, 12);
			}
		}

		workbook.write();
		workbook.close();
		return file;
	}
	/**
	 * 
			* Desc:  根据表头、数据、页脚、合并字号等信息生成EXCEL报表
	        * @param datas
	        * @param headers
	        * @param mergeColMap
	        * @param mergeRowMap
	        * @param path
	        * @param fileName
	        * @param colsize
	        * @param bottoms
	        * @param headSize
	        * @param botSize
	        * @param ifweighthead
	        * @param ifweightbot
	        * @return
	        * @throws Exception     
	        * @version 1.0  
	        * @author dongxiaoping@ieds.com.cn  
	        * @update 2012-10-19 上午11:56:53
	 */
	public File generating(List<Object[]> datas, List<Object[]> headers,
			Map<String, Integer> mergeColMap, Map<String, Integer> mergeRowMap,
			String path, String fileName, int[] colsize, List<String> bottoms, Integer headSize, Integer botSize,
			boolean ifweighthead, boolean ifweightbot) throws Exception {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(Locale.CHINA);
		ws.setEncoding("gbk");
		File file = new File(path + fileName + ".xls");
		WritableWorkbook workbook = Workbook.createWorkbook(file, ws);
		WritableSheet workSheet = workbook.createSheet(fileName, 0);
		creatheader(workSheet, headers, mergeColMap, mergeRowMap,headSize,ifweighthead);
		creatBody(workSheet, datas, headers.size());
		if(bottoms!=null&&bottoms.size()>0){
			createBottom(workSheet,bottoms,botSize,ifweightbot,headers.get(0).length,datas.size()+headers.size());
		}
		
		if (headers != null && headers.size() > 0) {
			if(colsize!=null){
				for(int i=0;i<colsize.length;i++){
					workSheet.setColumnView(i, colsize[i]);
				}
			}else{
				int col = headers.get(0).length;
				for (int i = 0; i < col; i++) {
					workSheet.setColumnView(i, 12);
				}
			}
			
		}

		workbook.write();
		workbook.close();
		return file;
	}



	private void creatheader(WritableSheet workSheet, List<Object[]> headers,
			Map<String, Integer> mergeColMap, Map<String, Integer> mergeRowMap,  Integer headSize, boolean ifweighthead)
			throws Exception {
		int row = 0;
		for (Object[] data : headers) {
			row++;
			int col = 0;
			for (Object d : data) {
				col++;
				if (d == null) {
					continue;
				}
				if ((d + "").trim().equals("")) {
					continue;
				}
				Integer colSpan = mergeColMap.get(row + "_" + col);
				if (colSpan != null) {
					workSheet.mergeCells(col - 1, row - 1, col + colSpan - 2,
							row - 1);
				}
				Integer rowSpan = mergeRowMap.get(row + "_" + col);
				if (rowSpan != null) {
					workSheet.mergeCells(col - 1, row - 1, col - 1, row
							+ rowSpan - 2);
				}
				WritableCellFormat wrappedText = createCellFormat(true,headSize,ifweighthead,true);
				Label label = new Label(col - 1, row - 1, (d == null) ? "" : d
						+ "", wrappedText);
				workSheet.addCell(label);
				// System.out.println("will setting :"+(col - 1)+"->"+(row -
				// 1));
			}

		}

	}
	private void createBottom(WritableSheet workSheet, List<String> bottoms,
			Integer botSize, boolean ifweightbot,int colnum,int rownum) throws Exception {
		// TODO Auto-generated method stub
		for (String data : bottoms) {
			workSheet.mergeCells(0, rownum, colnum-1,rownum );
				WritableCellFormat wrappedText = createCellFormat(false,botSize,ifweightbot,true);
				Label label = new Label(0,  rownum , data, wrappedText);
				workSheet.addCell(label);
				// System.out.println("will setting :"+(col - 1)+"->"+(row -
				// 1));
		}
	}

	private void creatBody(WritableSheet workSheet, List<Object[]> datas,
			int start) throws Exception {
		int row = start + 1;
		for (Object[] data : datas) {
			int col = 1;
			for (Object d : data) {
				WritableCellFormat wrappedText = createCellFormat(false,null,false,true);
				String value = (String) d;
				if (value == null) {
					value = "";
				}
				if (value == "null") {
					value = "";
				}
				if (value.trim().equalsIgnoreCase("null")) {
					value = "";
				}
				Label label = new Label(col - 1, row - 1, value, wrappedText);
				workSheet.addCell(label);
				col++;
			}
			row++;
		}

	}

	private WritableCellFormat createCellFormat(boolean isheader, Integer headSize,boolean weight, boolean haveborder)
			throws Exception {
		// System.out.println(isheader);
		// isheader = true;
		WritableCellFormat wrappedText = null;
		WritableFont font = null;
		if (isheader) {
			int fontsize = 10;
			if(headSize!=null&&headSize.intValue()>0){
				fontsize=headSize;
			}
			font = new WritableFont(WritableFont.ARIAL, fontsize, (weight?WritableFont.BOLD:WritableFont.NO_BOLD),
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);

		} else {
			font = new WritableFont(WritableFont.ARIAL, 10,
					(weight?WritableFont.BOLD:WritableFont.NO_BOLD), false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
		}

		// font.setPointSize(10);
		// font.setColour(Colour.BLACK);
		Alignment align = Alignment.LEFT;
		if (isheader) {
			// font.setBoldStyle(WritableFont.BOLD);
			align =  Alignment.CENTRE;
		}
		wrappedText = new WritableCellFormat(font);
		wrappedText.setLocked(true);
		wrappedText.setAlignment(align);
		wrappedText.setVerticalAlignment(VerticalAlignment.CENTRE);
		if (isheader) {
			// wrappedText.setBackground(Colour.YELLOW);
		}
		if(haveborder){
			wrappedText.setBorder(jxl.format.Border.TOP, BorderLineStyle.THIN);
			wrappedText.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.THIN);
			wrappedText.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
			wrappedText.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
		}
		

		return wrappedText;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/**
		 * public File generating(List<Object[]> datas, List<Object[]> headers,
		 * List<String> mergeDesc, Map<String, Integer> mergeColMap, Map<String,
		 * Integer> mergeRowMap, String path, String fileName)
		 */
		String[] title1 = new String[] { "归属地", "集团单位/小区名称", "地址", "客户联系人",
				"联系电话", "施工方", "", "", "", "", "", "责任人", "工程质量" };
		String[] title2 = new String[] { "", "", "", "", "", "施工单位", "施工负责人",
				"联系电话", "开工时间", "工程进度", "完工时间", "", "" };
		List headers = new ArrayList();
		headers.add(title1);
		headers.add(title2);
		List datas = new ArrayList();
		for (int i = 0; i < 50; i++) {
			String[] data = new String[] { "归属地", "集团单位/小区名称", "地址", "客户联系人",
					"联系电话", "施工单位", "施工负责人", "联系电话", "开工时间", "工程进度", "完工时间",
					"责任人", "工程质量" };
			datas.add(data);
		}
		Map<String, Integer> mergeColMap = new HashMap();
		mergeColMap.put("1_6", new Integer(6));
		Map<String, Integer> mergeRowMap = new HashMap();
		mergeRowMap.put("1_1", new Integer(2));
		mergeRowMap.put("1_2", new Integer(2));
		mergeRowMap.put("1_3", new Integer(2));
		mergeRowMap.put("1_4", new Integer(2));
		mergeRowMap.put("1_5", new Integer(2));
		mergeRowMap.put("1_12", new Integer(2));
		mergeRowMap.put("1_13", new Integer(2));
		List<String> bottoms = new ArrayList();
		bottoms.add("负责人签字");
		XLSReport report = new XLSReport();
		File repFile = report.generating(datas, headers, mergeColMap,
				mergeRowMap, "d:/", "testreport",new int[]{20,20,20,20,20,20,20,20,20,20,20,20,20},bottoms,20,20,true,true);
		
		System.out.println(repFile);
	}

}
