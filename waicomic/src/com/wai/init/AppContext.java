package com.wai.init;

import android.app.Application;

public class AppContext extends Application {
	private static AppContext mApplication;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = this;
	}

	public static AppContext getApplication() {
		return mApplication;
	}
}
