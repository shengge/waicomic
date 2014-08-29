package com.wai.listener;

import java.util.List;
import net.tsz.afinal.FinalDb;
import org.json.JSONObject;
import android.view.View;
import android.view.View.OnClickListener;
import com.wai.adpt.AList2Adapter;
import com.wai.model.KeepList;

public class AList2OnClick implements OnClickListener {
	public AList2OnClick(AList2Adapter adapt, Integer position, List<JSONObject> jsons, FinalDb fdata) {
		this.adapt = adapt;
		this.index = position;
		this.jsons = jsons;
		this.fdata = fdata;
	}
	
	public void onClick(View view) {
		try {
			this.fdata.delete(this.fdata.findById(jsons.get(this.index).getInt("sid"), KeepList.class));
			this.adapt.removeItem(this.index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Integer index;
	private FinalDb fdata;
	private AList2Adapter adapt;
	private List<JSONObject> jsons;
}