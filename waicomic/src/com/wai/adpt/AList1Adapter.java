package com.wai.adpt;

import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wai.R;
import com.wai.listener.AList1OnClick;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class AList1Adapter extends BaseAdapter {
	public AList1Adapter(Context context, FinalDb fdata, FinalHttp fhttp, AjaxParams param, FinalBitmap fbmap) {
		this.filter = LayoutInflater.from(context);
		this.fdata = fdata;
		this.fhttp = fhttp; 
		this.param = param;
		this.fbmap = fbmap;
		this.currentPage(1);
	}
	
	public void currentPage(Integer index) {
		this.index = index;
		this.jsons = new ArrayList<JSONObject>();
		if(this.index * 20 > this.jsons.size()) {
			this.param.put("crp", index.toString());
			//this.fhttp.post("http://172.16.11.195/wai/page", this.param, new AjaxCallBack() {
			this.fhttp.post("http://www.yoodm.com/wai/page", this.param, new AjaxCallBack() {
				public void onSuccess(Object t) {
					super.onSuccess(t);
					try {
						array = new JSONObject(t.toString()).getJSONArray("data");
						for(int i = 0; i < array.length(); i++) {
							jsons.add(array.getJSONObject(i));
						}
						
						notifyDataSetChanged();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	public int getCount() {
		return this.jsons != null ? this.jsons.size() / 4 : 0;
	}

	public Object getItem(int position) {
		return this.jsons.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = this.filter.inflate(R.layout.item1, null);
		try {
			this.fbmap.display((ImageView) convertView.findViewById(R.id.image1), this.jsons.get(position * 4 + 0).getString("cover"));
			((ImageView) convertView.findViewById(R.id.image1)).setOnClickListener(new AList1OnClick(this.filter.getContext(), position * 4 + 0, this.jsons, this.fdata));
			((TextView) convertView.findViewById(R.id.text1)).setText(this.jsons.get(position * 4 + 0).getString("title"));
			((TextView) convertView.findViewById(R.id.text1)).setOnClickListener(new AList1OnClick(this.filter.getContext(), position * 4 + 0, this.jsons, this.fdata));
			
			this.fbmap.display((ImageView) convertView.findViewById(R.id.image2), this.jsons.get(position * 4 + 1).getString("cover"));
			((ImageView) convertView.findViewById(R.id.image2)).setOnClickListener(new AList1OnClick(this.filter.getContext(), position * 4 + 1, this.jsons, this.fdata));
			((TextView) convertView.findViewById(R.id.text2)).setText(this.jsons.get(position * 4 + 1).getString("title"));
			((TextView) convertView.findViewById(R.id.text2)).setOnClickListener(new AList1OnClick(this.filter.getContext(), position * 4 + 1, this.jsons, this.fdata));
			
			this.fbmap.display((ImageView) convertView.findViewById(R.id.image3), this.jsons.get(position * 4 + 2).getString("cover"));
			((ImageView) convertView.findViewById(R.id.image3)).setOnClickListener(new AList1OnClick(this.filter.getContext(), position * 4 + 2, this.jsons, this.fdata));
			((TextView) convertView.findViewById(R.id.text3)).setText(this.jsons.get(position * 4 + 2).getString("title"));
			((TextView) convertView.findViewById(R.id.text3)).setOnClickListener(new AList1OnClick(this.filter.getContext(), position * 4 + 2, this.jsons, this.fdata));
			
			this.fbmap.display((ImageView) convertView.findViewById(R.id.image4), this.jsons.get(position * 4 + 3).getString("cover"));
			((ImageView) convertView.findViewById(R.id.image4)).setOnClickListener(new AList1OnClick(this.filter.getContext(), position * 4 + 3, this.jsons, this.fdata));
			((TextView) convertView.findViewById(R.id.text4)).setText(this.jsons.get(position * 4 + 3).getString("title"));
			((TextView) convertView.findViewById(R.id.text3)).setOnClickListener(new AList1OnClick(this.filter.getContext(), position * 4 + 3, this.jsons, this.fdata));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return convertView;
	}

	private LayoutInflater filter;
	private List<JSONObject> jsons;
	private JSONArray array;
	private Integer index;
	private FinalDb fdata;
	private FinalHttp fhttp;
	private AjaxParams param;
	private FinalBitmap fbmap;
}