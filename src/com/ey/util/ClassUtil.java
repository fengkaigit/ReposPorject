package com.ey.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

public class ClassUtil {

	public static Object loadClass(String strClassName, Class[] argsType,
			Object[] args) throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Object returnObj = null;
		Class className = null;
		Constructor constructor = null;
		className = Class.forName(strClassName);
		constructor = className.getConstructor(argsType);
		returnObj = constructor.newInstance(args);
		return returnObj;
	}

	public static Object loadClass(String strClassName)
			throws SecurityException, IllegalArgumentException,
			ClassNotFoundException, NoSuchMethodException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		return loadClass(strClassName, null, null);
	}

	public static Object loadClass(String strClassName, Object defObj) {
		try {
			return loadClass(strClassName, null, null);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		return defObj;
	}

	public static Object invokeMothod(Object classObject, String strMethodName,
			Class[] argsType, Object[] args) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Method concatMethod = classObject.getClass().getMethod(strMethodName,
				argsType);
		return concatMethod.invoke(classObject, args);
	}

	public static Object invokeMothod(Object classObject, String strMethodName)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		return invokeMothod(classObject, strMethodName, null, null);
	}

	public static Object getFieldValue(Object classObject, String strFieldName)
			throws IllegalArgumentException, IllegalAccessException,
			SecurityException, NoSuchFieldException {
		Object retObj = null;
		java.lang.reflect.Field field;
		field = classObject.getClass().getDeclaredField(strFieldName);
		retObj = field.get(classObject);
		return retObj;
	}

	public static String whereClass(String className) {
		String retStr = null;
		if (!className.startsWith("/")) {
			className = "/" + className;
		}
		className = className.replace('.', '/');
		className = className + ".class";
		java.net.URL classUrl = getResource(className);
		if (classUrl != null) {
			String str = classUrl.getFile();
			retStr = str;
		}
		return retStr;
	}

	public static URL getResource(String name) {
		return ClassUtil.class.getResource(name);
	}
	public static void main(String args[]) throws Exception {
		Object obj = ClassUtil.loadClass("com.ey.poundage.Traffic");
		Double d = (Double)ClassUtil.invokeMothod(obj, "getPoundage");
		System.out.println(d);
	}
}
