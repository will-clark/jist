package org.willclark.jist;

public class StringUtil {

	public static String toString(Object obj) {
		if (obj == null) return "";
		return obj.toString();
	}

	public static boolean isEmpty(String str) {
		if (str == null || str.equals("")) return true;		
		return false;
	}
	
	public static boolean isNotEmpty(String str) {
		return isEmpty(str) ? false : true;
	}
	
}
