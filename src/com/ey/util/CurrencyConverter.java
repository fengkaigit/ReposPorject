package com.ey.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ey.bo.PaymentBillBO;

public class CurrencyConverter implements Converter {
	protected final Log log = LogFactory.getLog(CurrencyConverter.class);

	protected final DecimalFormat formatter = new DecimalFormat("###,###.00");

	public final Object convert(final Class type, final Object value) {
		// for a null value, return null
		if (value == null) {
			return null;
		} else {
			if (value instanceof String) {
				if (log.isDebugEnabled()) {
					log.debug("value (" + value + ") instance of String");
				}

				try {
					if (StringUtils.isBlank(String.valueOf(value))) {
						return null;
					}

					if (log.isDebugEnabled()) {
						log.debug("converting '" + value + "' to a decimal");
					}

					// formatter.setDecimalSeparatorAlwaysShown(true);
					Number num = formatter.parse(String.valueOf(value));

					return new Double(num.doubleValue());
				} catch (ParseException pe) {
					pe.printStackTrace();
				}
			} else if (value instanceof Double) {
				if (log.isDebugEnabled()) {
					log.debug("value (" + value + ") instance of Double");
					log.debug("returning double: " + formatter.format(value));
				}

				return formatter.format(value);
			}
		}

		throw new ConversionException("Could not convert " + value + " to "
				+ type.getName() + "!");
	}

	/**
	 * 最大值处理
	 * 
	 * @param row
	 * @param col
	 * @param doubleValue
	 * @return
	 */
	public static Double getMax(int row, int col, Object[][] doubleValue) {
		Double maxValue = new Double(0.00);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Double temp = (Double) doubleValue[i][j];
				if (maxValue.compareTo(temp) < 0) {
					maxValue = temp;
				}
			}
		}
		return maxValue;
	}

	/**
	 * 最大值处理
	 * 
	 * @param row
	 * @param col
	 * @param doubleValue
	 * @return
	 */
	public static Double getMax(List<PaymentBillBO> records) {
		Double maxValue = new Double(0.00);
		for (PaymentBillBO record : records) {
			Double temp = record.getMoney();
			if (maxValue.compareTo(temp) < 0) {
				maxValue = temp;
			}
		}
		return maxValue;
	}

	/**
	 * 平均值处理
	 * 
	 * @param row
	 * @param col
	 * @param doubleValue
	 * @return
	 */
	public static Double[] getAvg(int row, int col, Object[][] doubleValue) {
		Double[] avgValue = new Double[col];
		for (int i = 0; i < col; i++) {
			avgValue[i] = new Double(0.00);
			double hValue = 0.00;
			for (int j = 0; j < row; j++) {
				Double temp = (Double) doubleValue[j][i];
				hValue = hValue + temp.doubleValue();
			}
			avgValue[i] = new Double(hValue / row);
		}
		return avgValue;
	}

	/**
	 * 外部占比处理
	 * 
	 * @param row
	 * @param doubleValue
	 * @return
	 */
	public static BigDecimal[] getPercent(int row, Object[] doubleValue) {
		BigDecimal[] percentValue = new BigDecimal[row];
		// double hValue = 0.00;
		DecimalFormat df = new DecimalFormat("####.00");

		/*
		 * for(int i=0;i<row;i++){ Double temp = (Double)doubleValue[i]; hValue
		 * = hValue + temp.doubleValue(); }
		 */
		for (int i = 0; i < row; i++) {
			// percentValue[i] = new BigDecimal(0.00);
			Double temp = (Double) doubleValue[i];
			// System.out.println("temp.doubleValue():"+temp.doubleValue());
			BigDecimal bd = new BigDecimal(df.format(temp.doubleValue()));
			bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
			percentValue[i] = bd;
			// System.out.println("percentValue[i]:"+percentValue[i]);
			// String s = df.format(percentValue[i]);
			// percentValue[i] = Double.valueOf(s);
		}
		return percentValue;
	}

	/**
	 * 最小值处理
	 * 
	 * @param row
	 * @param col
	 * @param doubleValue
	 * @return
	 */
	public static Double getMin(int row, int col, Object[][] doubleValue) {
		Double minValue = new Double(0.00);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Double temp = (Double) doubleValue[i][j];
				if (minValue.compareTo(temp) > 0) {
					minValue = temp;
				}
			}
		}
		return minValue;
	}
}
