package com.wai.init;

import sms.purchasesdk.cartoon.SMSPaycode;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.wai.R;
import com.wai.tool.SystemUtils;
import com.wai.view.Host1Activity;
import com.wai.view.Host2Activity;
import com.wai.view.Host3Activity;
import com.wai.view.Host4Activity;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	public static MainActivity mInstance;
	

	// 计费点信息                                                                                                                            
	public static final String LEASE_PAYCODE = "300004301003";
	
	//手机运营商
	public static int phoneProviders;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.active);
		mInstance=this;
        this.getTabHost().addTab(this.getTabHost().newTabSpec("host1").setIndicator((View) LayoutInflater.from(this).inflate(R.drawable.menu1, null)).setContent(new Intent(this, Host1Activity.class)));  
        this.getTabHost().addTab(this.getTabHost().newTabSpec("host2").setIndicator((View) LayoutInflater.from(this).inflate(R.drawable.menu2, null)).setContent(new Intent(this, Host2Activity.class)));  
        this.getTabHost().addTab(this.getTabHost().newTabSpec("host3").setIndicator((View) LayoutInflater.from(this).inflate(R.drawable.menu3, null)).setContent(new Intent(this, Host3Activity.class)));  
        this.getTabHost().addTab(this.getTabHost().newTabSpec("host4").setIndicator((View) LayoutInflater.from(this).inflate(R.drawable.menu4, null)).setContent(new Intent(this, Host4Activity.class)));
        this.getTabHost().setCurrentTab(1);
        phoneProviders = SystemUtils.getProvidersName(this);
        
       /// InitSMSPay();
	}
	
	
	
}