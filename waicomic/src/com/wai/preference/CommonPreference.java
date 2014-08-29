package com.wai.preference;

import com.wai.init.AppContext;

import android.content.SharedPreferences;
import android.preference.PreferenceActivity;

public class CommonPreference {
	private static final String PREF_FILE_NAME = "common_settings";
	
	public static String getStringValue(String keyWord, String defaultValue) {
		SharedPreferences sp = AppContext.getApplication()
				.getSharedPreferences(PREF_FILE_NAME,PreferenceActivity.MODE_PRIVATE);
		return sp.getString(keyWord, defaultValue);
	}
	
	public static void setStringValue(String keyWord, String value) {
		SharedPreferences sp = AppContext.getApplication()
				.getSharedPreferences(PREF_FILE_NAME,PreferenceActivity.MODE_PRIVATE);
		sp.edit().putString(keyWord, value).commit();
	}
	
	public static int getIntValue(String keyWord, int defaultValue) {
		SharedPreferences sp = AppContext.getApplication()
				.getSharedPreferences(PREF_FILE_NAME,PreferenceActivity.MODE_PRIVATE);
		return sp.getInt(keyWord, defaultValue);
	}
	
	public static void setIntValue(String keyWord, int value) {
		SharedPreferences sp = AppContext.getApplication()
				.getSharedPreferences(PREF_FILE_NAME,PreferenceActivity.MODE_PRIVATE);
		sp.edit().putInt(keyWord, value).commit();
	}
	
	public static boolean getBooleanValue(String keyWord, boolean defaultValue) {
		SharedPreferences sp = AppContext.getApplication()
				.getSharedPreferences(PREF_FILE_NAME,PreferenceActivity.MODE_PRIVATE);
		return sp.getBoolean(keyWord, defaultValue);
	}
	
	public static void setBooleanValue(String keyWord, boolean isCheck) {
		SharedPreferences sp = AppContext.getApplication()
				.getSharedPreferences(PREF_FILE_NAME,PreferenceActivity.MODE_PRIVATE);
		sp.edit().putBoolean(keyWord, isCheck).commit();
	}
	

	public static long getLongValue(String keyWord, long defaultValue) {
		SharedPreferences sp = AppContext.getApplication()
				.getSharedPreferences(PREF_FILE_NAME,PreferenceActivity.MODE_PRIVATE);
		return sp.getLong(keyWord, defaultValue);
	}
	
	public static void setLongValue(String keyWord, long value) {
		SharedPreferences sp = AppContext.getApplication()
				.getSharedPreferences(PREF_FILE_NAME,PreferenceActivity.MODE_PRIVATE);
		sp.edit().putLong(keyWord, value).commit();
	}
}
