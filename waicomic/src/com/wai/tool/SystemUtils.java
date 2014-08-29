package com.wai.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.wai.pay.Constants;

public class SystemUtils {

	/**
	 * Get phone service provider information
	 * 
	 * @return CHINA_UNICOM = 01; CHINA_MOBILE = 02; CHINA_TELECOM = 03;
	 */
	public static int getProvidersName(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		int providersName = 0;
		String IMSI = tm.getSubscriberId();
		if (!StringUtils.isEmpty(IMSI)) {
			if (IMSI.startsWith("46000") || IMSI.startsWith("46002")|| IMSI.startsWith("46007")) {
				providersName = Constants.CHINA_MOBILE;
			} else if (IMSI.startsWith("46001")) {
				providersName = Constants.CHINA_UNICOM;
			} else if (IMSI.startsWith("46003")) {
				providersName = Constants.CHINA_TELECOM;
			}
		}
		return providersName;
	}

	/**
	 * Access to the local imsi card number
	 * 
	 * @return simSerialNumber
	 */
	public static String getIMSI(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = tm.getSubscriberId();
		return imsi;
	}

	/**
	 * SIM card state: no SIM card is available in the device public static
	 * final int SIM_STATE_ABSENT = 1;
	 * 
	 * @return
	 */
	public static int getSimCardState(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getSimState();
	}

	/**
	 * Determine whether the network is connected
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = cm.getActiveNetworkInfo();
		if (mNetworkInfo != null) {
			return mNetworkInfo.isAvailable();
		}
		return false;
	}

	public static boolean checkNet(Context context) {
		try {
			// 获取手机所有连接管理对象（wi_fi,net等连接的管理）
			ConnectivityManager manger = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (manger != null) {
				NetworkInfo info[] = manger.getAllNetworkInfo();
				if (info != null) {
					for (int i = 0; i < info.length; i++) {
						if (info[i].getState() == NetworkInfo.State.CONNECTED) {
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

}
