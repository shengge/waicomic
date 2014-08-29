package com.wai.adpt;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxParams;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.wai.R;
import com.wai.init.IAPHandler;
import com.wai.init.IAPListener;
import com.wai.init.MainActivity;
import com.wai.pay.Constants;
import com.wai.view.Host2Activity;
import com.wai.view.View1Activity;

public class AList3Adapter extends BaseAdapter {
	
	private Context context;
	
	public AList3Adapter(Context context, FinalDb fdata, FinalHttp fhttp, AjaxParams param, FinalBitmap fbmap) {
		this.filter = LayoutInflater.from(context);
		this.context = context;
	}
	
	public int getCount() {
		return 20;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = this.filter.inflate(R.layout.item3, null);
		convertView.findViewById(R.id.text4).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (MainActivity.phoneProviders != Constants.CHINA_MOBILE) {
					Toast.makeText(context, "目前只支持移动手机交易", Toast.LENGTH_SHORT).show();
					return;
				}else{
					//TODO:XXX
					IAPHandler iapHandler = new IAPHandler(MainActivity.mInstance);
					IAPListener iapListener = new IAPListener(context,iapHandler);
					View1Activity.msmsPaycode.smsOrder(context, MainActivity.LEASE_PAYCODE,iapListener);
				}
			}
		});
		return convertView;
	}

	private LayoutInflater filter;
}