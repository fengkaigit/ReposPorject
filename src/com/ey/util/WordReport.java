package com.ey.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 
        * Desc:     WORD模板操作封装类
        * @version 1.0  
        * @author dongxiaoping@ieds.com.cn  
        * @update 2012-10-19 上午11:53:37
 */
public class WordReport {
	/**
	 * 
			* Desc:  根据word模板与查询数据生成WORD报表
	        * @param stream
	        * @param dataMap
	        * @param details
	        * @return
	        * @throws DocumentException     
	        * @version 1.0  
	        * @author dongxiaoping@ieds.com.cn  
	        * @update 2012-10-19 上午11:54:20
	 */
	public static String buildWordReport(InputStream stream,
			Map<String, Object> dataMap, List<List<Map<String, Object>>> details)
			throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(stream);
		List list = doc.selectNodes("//w:t");
		Iterator<Element> iter = list.iterator();
		while (iter.hasNext()) {
			Element el = iter.next();
			String text = el.getTextTrim();
			if (text != null && text.indexOf("@") > -1
					&& text.indexOf("@@") == -1) {
				Object obj = dataMap.get(text.substring(1));
				if (obj != null) {
					el.setText(obj + "");
				} else {
					el.setText("");
				}
			}

		}
		if (details != null && details.size() > 0) {
			extendTable(details, doc);
		}
		return doc.asXML();
	}
	static void extendTable(List<List<Map<String, Object>>> details, Document doc){
		List<Element> tableList=doc.selectNodes("//w:tbl");
		if(tableList.size()==0){
			return;
		}
		int index = 0;
		for(Element tableEle:tableList){
			extendedTr(details,tableEle,index);
			index++;
		}
	}
	static void extendedTr(List<List<Map<String, Object>>> details, Element tableEle,int index) {
		
		List<Element> listtr = tableEle.selectNodes("w:tr");
		//System.out.println(listtr.size());
		boolean extended = false;
		int j = 0;
		Map<Integer, List<Element>> appendMap = new HashMap();
		for (Element tr : listtr) {
			if (isExtended(tr) && (index < details.size())) {
				if(!extended){
					extended=true;
				}
				List<Map<String, Object>> dm = details.get(index);
				List<Element> tds = tr.selectNodes("w:tc/w:p/w:r/w:t");
				//System.out.println("tds=" + tds.size());
				List<Element> extendtr = new ArrayList();
				int i = 0;
				Element emptyElement = null;
				for (Map<String, Object> _dm : dm) {
					//System.out.println(_dm);
					if (i == 0) {
						for (Element td : tds) {
							String text = td.getTextTrim();
							System.out.println("text:"+text);
							if (text != null && text.indexOf("@@") > -1) {
								if(emptyElement==null){
									emptyElement =  tr.createCopy();
								}
								td.setText(getStrText(_dm,text));
								//Object obj = _dm.get(text.substring(2));
								/*if (obj != null) {
									
								} else {
									td.setText("");
								}*/
							}
						}
					} else {
						Element newElement = emptyElement.createCopy();
						List<Element> newtds = newElement
								.selectNodes("w:tc/w:p/w:r/w:t");
						for (Element td : newtds) {
							//System.out.println("obj;1111111111111111");
							String text = td.getTextTrim();
							//System.out.println(text);
							
							if (text != null && text.indexOf("@@") > -1) {
								td.setText(getStrText(_dm,text));
								/*Object obj = _dm.get(text.substring(2));
								//System.out.println("obj;"+obj);
								if (obj != null) {
									td.setText(obj + "");
								} else {
									td.setText("");
								}*/
							}

						}
						//System.out.println("will add:" + newElement);
						//System.out.println("will add:" + newElement.asXML());
						extendtr.add(newElement);

					}
					i++;
				}
				if (extendtr.size() > 0) {
					appendMap.put((j + 1), extendtr);
				}

				index++;
			}
			j++;
		}
		Set<Integer> sets = appendMap.keySet();
		List<Integer> _list = new ArrayList();
		for (Integer set : sets) {
			_list.add(set);

		}
		if (_list.size() > 0) {
			Collections.sort(_list);
			for(int i=_list.size()-1;i>=0;i--){
				Integer li = _list.get(i);
				//System.out.println("li=" + li);
				List<Element> eles = appendMap.get(li);
				listtr.addAll(li, eles);
			}
		}
		if(extended){
			List<Element> headdesc = tableEle.selectNodes("w:tblPr");
			List<Element> headdef = tableEle.selectNodes("w:tblGrid");
			if(headdesc!=null){
				listtr.addAll(0, headdesc);
			}
			if(headdesc!=null){
				listtr.addAll(1, headdef);
			}
			tableEle.setContent(listtr);
		}
	}

	static boolean isExtended(Element trElement) {
		List<Element> tds = trElement.selectNodes("w:tc/w:p/w:r/w:t");
		//System.out.println("tds=" + tds.size());
		for (Element td : tds) {
			String text = td.getTextTrim();
			if (text != null && text.indexOf("@@") > -1) {
				return true;
			}
		}
		return false;
	}
	static String getStrText(Map<String, Object> map,String text){
		Set<String> sets = map.keySet();
		for(String set:sets){
			Object obj = map.get(set);
			if(obj instanceof Class){
				continue;
			}
			String value = null;
			if(obj==null){
				value="";
			}else{
				if(obj instanceof String){
					value = (String)obj;
				}else{
					value = obj.toString();
				}
			}
			text = text.replaceAll("@@"+set, value);
		}
		return text;		
	}
	public static String buildInfoWordReport(Map<String,Object> dataMap,List<Map<String,Object>> dataList,InputStream in)throws DocumentException{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(in);
		Element root = doc.getRootElement();
		Element wbody = root.element("body");
		List<Element>  wts = wbody.selectNodes("//w:p/w:r/w:t");
		Element wp = null;
		Element emptyWp = null;
		for(Element element:wts){
			if(!element.getTextTrim().equals("")){
				wp = element.getParent().getParent();
				break;
			}
		}
		
		int len = dataList.size();
		for(int i = 0 ;i < len ;i ++){
			Map<String,Object> map = dataList.get(i);
			if(i == 0){
				if(emptyWp==null&&wp!=null)
					emptyWp = wp.createCopy();
				 for(Element ele:wts){
					   ele.setText(getText(map,ele.getText()));
				  }
				
			}else{
				Element newWp = emptyWp.createCopy();
				wbody.add(newWp);
				List<Element> newWps = newWp.selectNodes("//w:r/w:t");
				 for(Element ele:newWps){
					  ele.setText(getText(map,ele.getText()));
				 }
				
			}
			
		}
		return doc.asXML();
	}
	public static String buildDuBanWordReport(Map<String,Object> dataMap,List<Map<String,Object>> dataList,InputStream in)throws DocumentException{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(in);
		Element root = doc.getRootElement();
		Element wbody = root.element("body");
		Element wsect = wbody.element("sect");
		List<Element> listWt = wsect.selectNodes("//w:t");
		Element empteySect = null;
		int len = dataList.size();
		List<Element> expandSet = new ArrayList();
		for(int i = 0 ;i < len ;i ++){
			Map<String,Object> map = dataList.get(i);
			if(i == 0){
				if(empteySect==null)
					empteySect = wsect.createCopy();
				 for(Element ele:listWt){
					 if(dataMap!=null){
						 String text = ele.getText();
						 String newText = getText(dataMap,text);
						 if(!text.equals(newText)){
							 ele.setText(newText);
							 continue;
						 }
					  }
					   ele.setText(getText(map,ele.getText()));
				  }
				
			}else{
				Element newEle = empteySect.createCopy();
				wbody.add(newEle);
				List<Element> newWts = newEle.selectNodes("//w:t");
				 for(Element ele:newWts){
					 if(dataMap!=null){
					     String text = ele.getText();
					     String newText = getText(dataMap,text);
					     if(!text.equals(newText)){
					    	newEle.remove(ele.getParent().getParent().getParent());
						    continue;
					     }
					 }
					  ele.setText(getText(map,ele.getText()));
				 }
				
			}
			
		}
		return doc.asXML();
	}
	static String getText(Map<String,Object> map,String text){
		if (text!=null&&!text.equals("")) {
		    for(Map.Entry<String,Object> entry:map.entrySet()){
		       if(text.indexOf(entry.getKey()) > -1){
		    	   Object value = entry.getValue();
		    	   if(value instanceof Class){
						continue;
					}
		    	   if(value==null)
						value="";
		    	   text = text.replace(entry.getKey(),value+"");
		       }
		     }
	       }
		return text;
	}
	public static void main(String args[]) throws FileNotFoundException,
			DocumentException {
		if(true){
			String a = "督办号：@@sno";
			String[] as = a.split("@@");
			System.out.println(as[0]+as[1]);
			//return;
		}
		File file = new File("e:/xinximoban.xml");
		List<List<Map<String, Object>>> details = new ArrayList();
		List<Map<String, Object>> d1 = new ArrayList();
		List<Map<String, Object>> d2 = new ArrayList();
		for (int i = 0; i < 3; i++) {
			Test test = new Test();
			test.setValue("sno " + i);
			test.setValue1("mettingTitle " + i);
			test.setValue2("content " + i);
			test.setValue3("value13 " + i);
			test.setValue4("value14 " + i);
			test.setValue5("value15 " + i);
			test.setValue6("value16 " + i);
			test.setValue7("value17 " + i);
			test.setSno("[2012]"+i+"号");
			test.setMeetingTitle("会议主题会议主题会议主题会议主题会议主题会议主题");
			test.setContent("会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容");
			Map<String, Object> dataMap = new HashMap();
			ConvertUtil.copyProperties(dataMap, test);
			dataMap.put("title", "标题标题标题标题");
			dataMap.put("content", "会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容");
			dataMap.put("repOper", "某某人");
			//System.out.println(dataMap);
			d1.add(dataMap);
		}
		for (int i = 0; i < 5; i++) {
			Test test = new Test();
			test.setValue("sno " + i);
			test.setValue1("mettingTitle " + i);
			test.setValue2("content " + i);
			test.setValue3("value3 " + i);
			test.setValue4("value4 " + i);
			test.setValue5("value5 " + i);
			test.setValue6("value6 " + i);
			test.setValue7("value7 " + i);
			test.setSno("[2012]"+(i+3)+"号");
			test.setMeetingTitle("会议主题");
			test.setContent("会议内容");
			Map<String, Object> dataMap = new HashMap();
			ConvertUtil.copyProperties(dataMap, test);
			dataMap.put("title", "标题标题标题标题");
			dataMap.put("content", "会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容会议内容");
			dataMap.put("repOper", "某某人");
			d2.add(dataMap);
		}
		details.add(d1);
		details.add(d2);
		Map<String, Object> map = new HashMap();
		map.put("value1", "dd");
		String content = buildWordReport(new FileInputStream(file), map,
				details);
		System.out.println(content);
	}

}
