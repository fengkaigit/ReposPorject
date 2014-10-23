package com.ey.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

public class ListToSort implements Comparator<Object>{
	private String methodName;
	public ListToSort(){
		
	}
    public ListToSort(String methodName){
		this.methodName = methodName;
	}
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		String s1 = null;
		String s2 = null;
		if (o1 instanceof String && o2 instanceof String) {
			s1 = PinyinUtil.hanziToPinyin(o1 + "");
			s2 = PinyinUtil.hanziToPinyin(o2 + "");
		}else{
			if(methodName !=null){
			  try {
				Object obj1 = o1.getClass().getDeclaredMethod(methodName, null).invoke(o1, null);
				Object obj2 = o2.getClass().getDeclaredMethod(methodName, null).invoke(o2, null);
				if(obj1 != null){
					s1 = PinyinUtil.hanziToPinyin(obj1+"");
				}
				if(obj2 != null){
					s2 = PinyinUtil.hanziToPinyin(obj2+"");
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		if (s1.compareTo(s2) > 0) {
			return 1;
		} else {
			return -1;
		}
	}
}
