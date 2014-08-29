package com.wai.adpt;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxParams;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.wai.R;

public class AList4Adapter extends BaseAdapter {
	public AList4Adapter(Context context, FinalDb fdata, FinalHttp fhttp, AjaxParams param, FinalBitmap fbmap) {
		this.filter = LayoutInflater.from(context);
	}
	
	public int getCount() {
		return 10;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = this.filter.inflate(R.layout.item4, null);		
		return convertView;
	}

	private LayoutInflater filter;
}