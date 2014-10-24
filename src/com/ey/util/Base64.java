package com.ey.util;

public class Base64 {
 
	private Base64() {
		super();
	}

	public static String encode(byte[] d) {
		if (d == null) {
			return null;
		}
		byte data[] = new byte[d.length + 2];
		System.arraycopy(d, 0, data, 0, d.length);
		byte dest[] = new byte[(data.length / 3) * 4];
		for (int sidx = 0, didx = 0; sidx < d.length; sidx += 3, didx += 4) {
			dest[didx] = (byte) ((data[sidx] >>> 2) & 077);
			dest[didx + 1] = (byte) ((data[sidx + 1] >>> 4) & 017 | (data[sidx] << 4) & 077);
			dest[didx + 2] = (byte) ((data[sidx + 2] >>> 6) & 003 | (data[sidx + 1] << 2) & 077);
			dest[didx + 3] = (byte) (data[sidx + 2] & 077);
		}
		for (int idx = 0; idx < dest.length; idx++) {
			if (dest[idx] < 26) {
				dest[idx] = (byte) (dest[idx] + 'A');
			}
			else if (dest[idx] < 52) {
				dest[idx] = (byte) (dest[idx] + 'a' - 26);
			}
			else if (dest[idx] < 62) {
				dest[idx] = (byte) (dest[idx] + '0' - 52);
			}
			else if (dest[idx] < 63) {
				dest[idx] = (byte) '+';
			}
			else {
				dest[idx] = (byte) '/';
			}
		}

		for (int idx = dest.length - 1; idx > (d.length * 4) / 3; idx--) {
			dest[idx] = (byte) '=';
		}
		return new String(dest);
	}

	public static byte[] decode(String str) {
		if (str == null) {
			return null;
		}
		return decode(str.getBytes());
	}

	public static byte[] decode(byte[] data) {
		int tail = data.length;
		while (data[tail - 1] == '=') {
			tail--;
		}
		byte dest[] = new byte[tail - data.length / 4];
		for (int idx = 0; idx < data.length; idx++) {
			if (data[idx] == '=') {
				data[idx] = 0;
			}
			else if (data[idx] == '/') {
				data[idx] = 63;
			}
			else if (data[idx] == '+') {
				data[idx] = 62;
			}
			else if (data[idx] >= '0' && data[idx] <= '9') {
				data[idx] = (byte) (data[idx] - ('0' - 52));
			}
			else if (data[idx] >= 'a' && data[idx] <= 'z') {
				data[idx] = (byte) (data[idx] - ('a' - 26));
			}
			else if (data[idx] >= 'A' && data[idx] <= 'Z') {
				data[idx] = (byte) (data[idx] - 'A');
			}
		}
		int sidx, didx;
		for (sidx = 0, didx = 0; didx < dest.length - 2; sidx += 4, didx += 3) {
			dest[didx] = (byte) (((data[sidx] << 2) & 255) | ((data[sidx + 1] >>> 4) & 3));
			dest[didx + 1] = (byte) (((data[sidx + 1] << 4) & 255) | ((data[sidx + 2] >>> 2) & 017));
			dest[didx + 2] = (byte) (((data[sidx + 2] << 6) & 255) | (data[sidx + 3] & 077));
		}
		if (didx < dest.length) {
			dest[didx] = (byte) (((data[sidx] << 2) & 255) | ((data[sidx + 1] >>> 4) & 3));
		}
		if (++didx < dest.length) {
			dest[didx] = (byte) (((data[sidx + 1] << 4) & 255) | ((data[sidx + 2] >>> 2) & 017));
		}
		return dest;
	}

	public static final void main(String args[]) {
		/*
		try {
			String test = "http://pay.163.com/jsp/cardinforesult.jsp?resultMessage=%B4%CB%BF%A8%D2%D1%D3%DA2008-10-21+01%3A19%3A28%B1%BB%B3%E4%D6%B5";
			String e = Base64.encode(test.getBytes());
			String test2 = "http://pay.163.com/jsp/cardinforesult.jsp?resultMessage=%3Cimg+src%3D%27http%3A%2F%2Fimages.163.com%2Fpcard%2FchargeSuccess.gif%27+%2F%3E+%D2%BB%BF%A8%CD%A8%D0%C5%CF%A2%A3%BA%B4%CB%B5%E3%CA%FD%BF%A8%B5%C4%C3%E6%D6%B5%CE%AA150%B5%E3%A3%AC%C7%EB%D4%DA2009-12-31+00%3A00%3A00%A3%A8%CA%A7%D0%A7%CA%B1%BC%E4%A3%A9%D6%AE%C7%B0%B3%E4%D6%B5%A1%A3%B3%C9%B9%A6%B3%E4%D6%B5%B5%BD%CD%F8%D2%D7%D5%CA%BA%C5%D6%D0%B5%C4%B5%E3%CA%FD%BD%AB%D3%C0%BE%C3%D3%D0%D0%A7%A1%A3";
			String d = new String(Base64.decode(e));
			String sss = java.net.URLDecoder.decode(test2);
			System.out.println("ok   = '" + sss + "'");
			System.out.println("Input = '" + test + "'");
			System.out.println("Encoded = '" + e + "'");
			System.out.println("Decoded = '" + d + "'");
		}
		catch (Exception x) {
			x.printStackTrace();
		}
		*/
	}
}
