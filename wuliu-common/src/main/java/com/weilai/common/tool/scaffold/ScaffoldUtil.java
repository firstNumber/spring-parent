package com.weilai.common.tool.scaffold;

public class ScaffoldUtil {
	public final static String COLON = ":";
	public final static String COMMA = ",";
	public final static String EMPTY = "";
	public final static String UNDER_LINE = "_";
	public final static String ENDL = "\n";
	public final static String SLASH = "/";
	public final static String BLANK = " ";
	public final static String DOT = ".";

	public static boolean isUpperCase(String s) {
		boolean result = true;
		char ch;
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			if (!(ch >= 'A' && ch <= 'Z')) {
				result = false;
			}
		}
		return result;
	}

	public static boolean isLowerCase(String s) {
		boolean result = true;
		char ch;
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			if (!(ch >= 'a' && ch <= 'z')) {
				result = false;
			}
		}
		return result;
	}
}
