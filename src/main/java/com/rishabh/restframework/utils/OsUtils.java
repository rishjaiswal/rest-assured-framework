package com.rishabh.restframework.utils;

public class OsUtils {

	private static String OS = null;

	/**
	 * To get the OS name
	 */
	public static String getOsName() {
		if (OS == null) {
			OS = System.getProperty("os.name").toLowerCase();
		}
		return OS;
	}

	/**
	 * To check OS name is Windows
	 * 
	 * @return : true when OS name is windows else false
	 */
	public static boolean isWindows() {
		return getOsName().contains("win");
	}

	/**
	 * To check OS name is MAC OS
	 * 
	 * @return : true when OS name is Mac else false
	 */
	public static boolean isMac() {
		System.out.println(getOsName());
		return getOsName().contains("mac");
	}

	/**
	 * To check OS name is Unix
	 * 
	 * @return : true when OS name is Unix else false
	 */
	public static boolean isUnix() {
		return getOsName().contains("nux");
	}

}