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
import com.wai.view.View2Activity;

public class View2OnClick implements OnClickListener {
	public View2OnClick(Context context, Integer position, List<JSONObject> jsons, FinalDb fdata) {
		this.filter = LayoutInflater.from(context);
		this.index = position;
		this.jsons = jsons;
		this.fdata = fdata;
	}
	
	public void onClick(View view) {
		try {
			this.intent = new Intent(filter.getContext(), View2Activity.class);
			this.intent.putExtra("chapters", this.jsons.get(this.index).toString());
			this.intent.putExtra("position", this.index);
			
			this.keepls = this.fdata.findAllByWhere(KeepList.class, "pmk = ".concat(this.jsons.get(this.index).getString("sid")));
			this.keepls.get(0).setSta(2);
			this.fdata.update(this.keepls.get(0));
			
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