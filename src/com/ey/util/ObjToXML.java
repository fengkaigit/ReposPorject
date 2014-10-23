package com.ey.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
/**  
* Desc:  将对象转换成xml格式串 
* @title 将对象转换成xml格式串   
* @update 2012-7-24 上午10:12:08  
* @version V1.0 
* Note:编写初衷：简单的编写代码即可实现，省去引用专业的jar包。缺点：不支持对象里再用对象作为属性。 
*/
public class ObjToXML {
	public static ObjToXML instance=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List list=new ArrayList();		
		HashMap role=new HashMap();
		role.put("id", "111");
		role.put("name", "呵呵");
		list.add(role);
		list.add(role);
		ObjToXML xmlobj=new ObjToXML();
		String str=xmlobj.toFullXMLStr(list,"root",null);
		System.out.println(str);
	}
	
	public static ObjToXML getInstance(){
		if(instance==null) instance=new ObjToXML();
		return instance;	
	}	

	public String toFullXMLStr(Object object,String xmlrootname,String xmlencoding){	
		String objString=null;
		String xmlstr=null;
		if(object!=null){
			String fullobjname=object.getClass().getName();
			if(xmlrootname==null){
				String[] arrobjname=fullobjname.split("[.]");
				xmlrootname=arrobjname[arrobjname.length-1];			
			}
			//sb.append("<"+xmlrootname+">");
			try{	
				objString=toXMLStr(object);
			}catch(Exception e){
				objString=null;
				System.out.println("xml转换出错:"+e.getMessage());
			}	
			
		}
		if(xmlencoding==null) xmlencoding="UTF-8";
		xmlrootname=((xmlrootname==null)?"root":xmlrootname);
		//sb.append("\n</"+xmlrootname+">");
		try{
			String unformatxmlst="";
			StringBuffer sbxml=new StringBuffer();
			sbxml.append("<"+xmlrootname+">");
			sbxml.append((objString==null?"":objString));
			sbxml.append("\n</"+xmlrootname+">");
			unformatxmlst=sbxml.toString();
			org.dom4j.Document document=DocumentHelper.parseText(unformatxmlst);
			document.setXMLEncoding(xmlencoding);
			xmlstr=document.asXML();
		}catch(Exception e){
			xmlstr=null;		
			System.out.println("xml转换出错:"+e.getMessage());
		}
		return xmlstr;
	}	
	
	public String toXMLStr(Object object){		
		String xmlstr=null;
		StringBuffer sb=new StringBuffer();
		//sb.append("<"+xmlrootname+">");
		try{		
			if(object instanceof List){
				List list=(List)object;				
				for(int i=0;i<list.size();i++){
					Object obj=list.get(i);
					String stritem=toXMLStr(obj);
					if((stritem!=null) && (stritem.length()>0)){
						sb.append("\n<item>");
						sb.append(stritem);						
						sb.append("\n</item>");
					}
				}
			}else if(object instanceof Map){
				Map map=(Map)object;
				Iterator iterator=map.keySet().iterator();
				while (iterator.hasNext()){
					String strkey=(String)iterator.next();
					Object obj=map.get(strkey);
					if(obj!=null) {
						sb.append("\n<").append(strkey).append(">");
						sb.append(org.apache.commons.lang.StringEscapeUtils.escapeXml(obj.toString()));
						sb.append("</").append(strkey).append(">");
					}else{
						sb.append("\n<").append(strkey).append(" />");
					}					
				}
			}else{
				Class cls=null;
				String fullobjname=object.getClass().getName();				
				cls=Class.forName(fullobjname);
				//out.println("<br>名称:"+cls.getSimpleName());
				java.lang.reflect.Field[] f=cls.getDeclaredFields();
				for(int i=0;i<f.length;i++){
					String propname=f[i].getName();
					if("serialVersionUID".equalsIgnoreCase(propname)) continue;
					PropertyDescriptor pd=new PropertyDescriptor(propname,object.getClass());
					Method rm=pd.getReadMethod();
					Object obj=rm.invoke(object);
					//out.println(" 值"+obj);
					if(obj!=null) {
						sb.append("\n<").append(propname).append(">");
						sb.append(org.apache.commons.lang.StringEscapeUtils.escapeXml(obj.toString()));
						sb.append("</").append(propname).append(">");
					}else{
						sb.append("\n<").append(propname).append(" />");
					}	 
				}				
			}
			xmlstr=sb.toString();

		}catch(Exception e){
			xmlstr=null;
			System.out.println("xml转换出错:"+e.getMessage());
		}	
		return xmlstr;
	}
}
