package cn.wolfcode.crud.util;

public class StringUtil {
	public static boolean hasLength(String str) {
		return str != null && !str.trim().equals("");
	}
}
