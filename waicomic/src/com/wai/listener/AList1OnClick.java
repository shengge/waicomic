package com.wai.listener;

import java.util.List;
import net.tsz.afinal.FinalDb;
import org.json.JSONObject;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.wai.model.KeepList;
import com.wai.view.View1Activity;

public class AList1OnClick implements OnClickListener {
	public AList1OnClick(Context context, Integer position, List<JSONObject> jsons, FinalDb fdata) {
		this.filter = LayoutInflater.from(context);
		this.index = position;
		this.jsons = jsons;
		this.fdata = fdata;
	}
	
	public void onClick(View view) {
		try {
			this.intent = new Intent(filter.getContext(), View1Activity.class);
			this.intent.putExtra("chapter", this.jsons.get(this.index).toString());
			this.keepls = this.fdata.findAllByWhere(KeepList.class, "pmk = ".concat(this.jsons.get(this.index).getString("sid")));
			if(this.keepls.size() == 0) {
				this.fdata.save(new KeepList(this.jsons.get(this.index).getInt("sid"), 0, 0, 0, 0, 1,this.jsons.get(this.index).getInt("price")));
			}
			
			this.filter.getContext().startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Intent intent;
	private Integer index;
	private FinalDb fdata;
	private LayoutInflater filter;
	private List<KeepList> keepls;
	private List<JSONObject> jsons;
}