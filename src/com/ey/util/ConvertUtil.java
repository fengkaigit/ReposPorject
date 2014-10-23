package com.ey.util;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public final class ConvertUtil {

	private static Log log = LogFactory.getLog(ConvertUtil.class);

	public static Object convert(Class targetClass, Object arg) throws Exception {
		return convert(targetClass, new Object[] { arg });
	}

	public static Object convert(Class targetClass, Object[] args) throws Exception {
		Class[] argTypes = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			argTypes[i] = args[i].getClass();
		}
		Constructor ctor = targetClass.getConstructor(argTypes);
		return ctor.newInstance(args);
	}

	/**
	 * Method to convert a ResourceBundle to a Map object.
	 * 
	 * @param rb a given resource bundle
	 * @return Map a populated map
	 */
	public static Map convertBundleToMap(ResourceBundle rb) {
		Map map = new HashMap();

		for (Enumeration keys = rb.getKeys(); keys.hasMoreElements();) {
			String key = (String) keys.nextElement();
			map.put(key, rb.getString(key));
		}
		return map;
	}

	/**
	 * Method to convert a ResourceBundle to a Properties object.
	 * 
	 * @param rb a given resource bundle
	 * @return Properties a populated properties object
	 */
	public static Properties convertBundleToProperties(ResourceBundle rb) {
		Properties props = new Properties();
		for (Enumeration keys = rb.getKeys(); keys.hasMoreElements();) {
			String key = (String) keys.nextElement();
			props.put(key, rb.getString(key));
		}
		return props;
	}

	public static void convertInStreamToOutStream(InputStream in, OutputStream out, int bufferSize)
			throws IOException {
		if (bufferSize > 0) {
			int bytesRead = 0;
			byte[] buffer = new byte[bufferSize];
			while ((bytesRead = in.read(buffer, 0, bufferSize)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}
	}

	public static List convertList(Class targetClass, List list) throws Exception {
		List result = null;
		if (list != null) {
			result = (List) list.getClass().newInstance();
			for (Iterator i = list.iterator(); i.hasNext();) {
				result.add(convert(targetClass, i.next()));
			}
		}
		return result;
	}

	public static List convertList(Class targetClass, String propertyName, List list)
			throws Exception {
		List result = null;
		if (list != null) {
			result = (List) list.getClass().newInstance();
			for (Iterator i = list.iterator(); i.hasNext();) {
				result.add(convert(targetClass, PropertyUtils.getProperty(i.next(), propertyName)));
			}
		}
		return result;
	}

	public static Object[] convertRowToArray(Object row, String[] cnames) {
		Object[] cvalues = null;
		if (row.getClass().equals(Object[].class)) {
			cvalues = (Object[]) row;
		}
		else {
			cvalues = new Object[cnames.length];
			for (int i = 0; i < cnames.length; i++) {
				try {
					cvalues[i] = PropertyUtils.getProperty(row, cnames[i]);
				}
				catch (Exception e) {
				}
			}
		}
		return cvalues;
	}

	public static void copyProperties(Map properties, Object object) {
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(object);
		for (int i = 0; i < pds.length; i++) {
			try {
				String propertyName = pds[i].getName();
				properties.put(propertyName, PropertyUtils.getProperty(object, propertyName));
			}
			catch (IllegalAccessException e) {
			}
			catch (InvocationTargetException e) {
			}
			catch (NoSuchMethodException e) {
			}
		}
	}

	public static void copyProperties(Object object, Map properties) {
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(object);
		for (int i = 0; i < pds.length; i++) {
			String propertyName = pds[i].getName();
			Object propertyValue = properties.get(propertyName);
			if (propertyValue != null) {
				try {
					PropertyUtils.setProperty(object, propertyName, propertyValue);
				}
				catch (IllegalAccessException e) {
					if (log.isDebugEnabled()) {
						log.debug(e.getMessage());
					}
				}
				catch (InvocationTargetException e) {
					if (log.isDebugEnabled()) {
						log.debug(e.getMessage());
					}
				}
				catch (NoSuchMethodException e) {
					if (log.isDebugEnabled()) {
						log.debug(e.getMessage());
					}
				}
			}
		}
	}

	public static void copyProperties(Object to, Object from) {
		try {
			copyProperties(to, from, true);
		}
		catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug(e.getMessage());
			}
		}
	}

	public static void copyProperties(Object to, Object from, boolean ignoreUnknown)
			throws Exception {
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(from);
		for (int i = 0; i < pds.length; i++) {
			try {
				PropertyUtils.setProperty(to, pds[i].getName(), PropertyUtils.getProperty(from,
						pds[i].getName()));
			}
			catch (Exception ex) {
				if (!ignoreUnknown) {
					throw ex;
				}
			}
		}
	}

	public static void copyProperties(Object to, Object from, String getMethodName) {
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(to);
		for (int i = 0; i < pds.length; i++) {
			try {
				Object propValue = from.getClass().getMethod(getMethodName,
						new Class[] { String.class }).invoke(from,
						new Object[] { pds[i].getName() });
				BeanUtils.setProperty(to, pds[i].getName(), propValue);
			}
			catch (IllegalArgumentException e) {
				if (log.isDebugEnabled()) {
					log.debug(e.getMessage());
				}
			}
			catch (SecurityException e) {
				if (log.isDebugEnabled()) {
					log.debug(e.getMessage());
				}
			}
			catch (IllegalAccessException e) {
				if (log.isDebugEnabled()) {
					log.debug(e.getMessage());
				}
			}
			catch (InvocationTargetException e) {
				if (log.isDebugEnabled()) {
					log.debug(e.getMessage());
				}
			}
			catch (NoSuchMethodException e) {
				if (log.isDebugEnabled()) {
					log.debug(e.getMessage());
				}
			}
		}
	}

	/**
	 * Convenience method used by tests to populate an object from a
	 * ResourceBundle
	 * 
	 * @param obj an initialized object
	 * @param rb a resource bundle
	 * @return a populated object
	 */
	public static Object populateObject(Object obj, ResourceBundle rb) {
		try {
			Map map = convertBundleToMap(rb);
			BeanUtils.copyProperties(obj, map);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured populating object: " + e.getMessage());
		}
		return obj;
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @param cindexs
	 * @param cnames
	 */
	public static void convertStringArrayToObject(String[] source, Object target, int[] cindexs,
			String[] cnames) {
		if (cindexs.length != cnames.length || cindexs[cindexs.length - 1] >= source.length) {
			return;
		}
		else {
			for (int i = 0; i < cindexs.length; i++) {
				try {
					int idx = cindexs[i];
					Class propertyClass = PropertyUtils.getPropertyType(target, cnames[i]);
					if (source[idx] != null) {
						if (propertyClass == String.class) {
							PropertyUtils.setProperty(target, cnames[i], source[idx]);
						}
						if (propertyClass == Long.class) {
							Long longValue = Long.valueOf(source[idx]);
							PropertyUtils.setProperty(target, cnames[i], longValue);
						}
						if (propertyClass == Integer.class) {
							Integer integerValue = Integer.valueOf(source[idx]);
							PropertyUtils.setProperty(target, cnames[i], integerValue);
						}
					}
					else {
						PropertyUtils.setProperty(target, cnames[i], null);
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Field[] convertObjectToFieldArray(Class beanClass) {
		try {
			PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(beanClass);
			Field[] fields = new Field[pds.length];
			for (int i = 0; i < pds.length; i++) {
					String propertyName = pds[i].getName();
					fields[i] = new Field(propertyName, propertyName);
			}
			return fields;
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured convertObjectToFieldArray : " + e.getMessage());
			return null;
		}
	}
	
	public static Field[] convertObjectToFieldArray(String[] fieldNames) {
		try {
			Field[] fields = new Field[fieldNames.length];
			for (int i = 0; i < fieldNames.length; i++) {
					fields[i] = new Field(fieldNames[i], fieldNames[i]);
			}
			return fields;
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured convertObjectToFieldArray : " + e.getMessage());
			return null;
		}
	}
}
