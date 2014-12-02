package com.ey.util;

import java.util.UUID;

public class UUIdUtil {
	public static String getUUId(){
		return UUID.randomUUID().toString();
	}
}
