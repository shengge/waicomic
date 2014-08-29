package com.wai.adpt;

import java.util.List;
import net.tsz.afinal.FinalDb;
import org.json.JSONObject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wai.R;
import com.wai.init.IAPHandler;
import com.wai.init.MainActivity;
import com.wai.listener.View1OnClick;

public class View1Adapter extends BaseAdapter {
	public View1Adapter(Context context, FinalDb fdata, List<JSONObject> data){
		this.filter = LayoutInflater.from(context);
		this.fdata = fdata;
		this.json = data;
	}

	public int getCount() {
		return this.json != null ? this.json.size() : 0;
	}

	public Object getItem(int position) {
		return this.json.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	public static  View1OnClick mView1OnClick;
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = this.filter.inflate(R.layout.item6, null);
		//IAPHandler iapHandler = new IAPHandler(MainActivity.mInstance);
		try {
			((TextView) convertView.findViewById(R.id.text1)).setText(String.valueOf(position + 1));
			 mView1OnClick = new View1OnClick(this.filter.getContext(), position, this.json, this.fdata);
			((TextView) convertView.findViewById(R.id.text1)).setOnClickListener(mView1OnClick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return convertView;
	}
	
	private LayoutInflater filter;
	private List<JSONObject> json;
	private FinalDb fdata;
}