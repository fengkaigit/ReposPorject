package com.ey.util;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import java.util.regex.*;
/**
 * 
        * Desc:     freemark模板解析标签属性
        * @version 1.0  
        * @author lishujun@ieds.com.cn  
        * @update 2012-4-9 下午04:41:58
 */
public class ResolveFreeMarkTemplate {
	private static final  String REGEX_HTML="<@f\\.([^>]*)>";
	private static final String  REGEX_ATTR=".*#ATTRIBUTENAME#='([^']*)'";
	private static final  String ATTRIBUTENAME="#ATTRIBUTENAME#";
	
	/**
	 * 
			* Desc:  解析freemark模板标签属性
	        * @param AttributeName 标签属性名称
	        * @param is 解析的模板
	        * @return     
	        * @version 1.0  
	        * @author lishujun@ieds.com.cn  
	        * @update 2012-4-9 下午04:42:30
	 */
	public List<String> getTemplateAttributeValues(String AttributeName,InputStream is){
		return getTemplateAttributeValue(AttributeName,new InputStreamReader(is));
		
	}
	/**
	 * 
			* Desc:  解析freemark模板标签属性
	        * @param attributeName 标签属性名称
	        * @param file
	        * @return
	        * @throws Exception     
	        * @version 1.0  
	        * @author lishujun@ieds.com.cn  
	        * @update 2012-4-10 上午09:05:31
	 */
	public static List<String> getTemplateAttributeValues(String attributeName,File file) throws Exception{		
		return getTemplateAttributeValue(attributeName,new FileReader(file));	
	}
	private static List<String>  getTemplateAttributeValue(String attributeName,Reader is){
		List<String> attributeValues=new ArrayList<String>();
		BufferedReader reader=null;
		String line="";
		String currRegex=REGEX_HTML.replace(ATTRIBUTENAME,attributeName );
		PatternCompiler compiler =new Perl5Compiler();
		Pattern pattern=null;
		PatternMatcher matcher=new Perl5Matcher();
	try{
			reader=new BufferedReader(is);
			line=reader.readLine();
			int i=0;
			pattern=compiler.compile(currRegex,Perl5Compiler.CASE_INSENSITIVE_MASK);
			while(line!=null){		
				if(matcher.contains(line, pattern)){
					MatchResult result=matcher.getMatch();
					String attributeValue=result.group(1);
					attributeValues.add(attributeValue);
					i++;
				}
				line=reader.readLine();
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return attributeValues;
	}
	/**
	 * 
			* Desc: 解析freemark模板标签属性
	        * @param attributeName 标签属性名称
	        * @param template
	        * @return     
	        * @version 1.0  
	        * @author lishujun@ieds.com.cn  
	        * @update 2012-4-10 上午09:04:51
	 */
	public static List<String> getTemplateAttributeValues(String attributeName,String template){
		List<String> attributeValues=new ArrayList<String>();
		String currRegex=REGEX_ATTR.replace(ATTRIBUTENAME,attributeName);
		PatternCompiler compiler =new Perl5Compiler();
		PatternMatcher matcher=new Perl5Matcher();
		try{
			Pattern pattern= compiler.compile(REGEX_HTML,Perl5Compiler.CASE_INSENSITIVE_MASK);
			Pattern pattern2= compiler.compile(currRegex,Perl5Compiler.CASE_INSENSITIVE_MASK);
			PatternMatcherInput input=new PatternMatcherInput(template);
			while(matcher.contains(input, pattern)){			
				MatchResult mr=matcher.getMatch();
				String regmr=mr.group(1).replace("\"","'");
				if(matcher.contains(regmr, pattern2)){
					MatchResult mr2=matcher.getMatch();
					attributeValues.add(mr2.group(1));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return attributeValues;
	}
	public static List<Object[]> getTemplateAttributeValues(String[] attributeNames,String template,String comName){
		List<Object[]> attributesList = new ArrayList();
		try{
			java.util.regex.Pattern pat = java.util.regex.Pattern.compile("(?i)<@f."+comName+"[^>]*>[\\d\\D]*?(?i)");
			Matcher matcher=pat.matcher(template);
			while(matcher.find()){
				Object[] obj = new Object[attributeNames.length];
				for(int i = 0 ;i<attributeNames.length;i++){
				   obj[i] = getTemplateAttributeValues(attributeNames[i],matcher.group()).get(0);
				}
				attributesList.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return attributesList;
	}
	public static void main(String[] args) {
		String 	tt1="<@f.input name='classId' value='docSendDoc.classId'/><@f.input name='secudgreeId' value='docSendDoc.secudgreeId'> </> <@f.input name='emergencyId' value='docSendDoc.emergencyId'/>";
		List<Object[]> list = new ArrayList();
		try{
			ResolveFreeMarkTemplate ss = new ResolveFreeMarkTemplate();
			list = ss.getTemplateAttributeValues(new String[] {"name","value"},tt1,"input");
			for(Object[] o:list){
			    System.out.println("attributeName="+o[0]+"--->"+o[1]);
		     }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

