package com.ey.util;

public class DoubleUtil {
	public static Double fixedDoubleAsHalf(Double value){
		if(value!=null){
			value = new java.math.BigDecimal(Double.toString(value)).setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return value;
	}
}
