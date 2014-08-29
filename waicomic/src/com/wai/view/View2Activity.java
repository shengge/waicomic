package com.wai.view;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ListView;
import com.wai.R;
import com.wai.adpt.View2Adapter;
import com.wai.tool.MyToken;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class View2Activity extends Activity {
	private ArrayList<JSONObject> dlist;
	private DisplayMetrics pixel;
	private Display displ;
	private MyToken token;
	private ListView vlist;
	private JSONObject jsons;
	private FinalDb fdata;
	private FinalHttp fhttp;
	private AjaxParams param;
	private FinalBitmap fbmap;
	private View2Adapter adapt;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.view2);
		
		this.pixel = new DisplayMetrics();
		
		this.displ = this.getWindowManager().getDefaultDisplay();
		this.displ.getMetrics(pixel);
		
		this.fdata = FinalDb.create(this, "mhbox.db");
		this.fbmap = FinalBitmap.create(this);
		
		try {
			this.jsons = new JSONObject(this.getIntent().getStringExtra("chapters"));
			
			this.token = new MyToken();
			this.param = new AjaxParams();
			this.param.put("id", this.jsons.getString("id"));
			this.param.put("time", this.token.getTime());
			this.param.put("token", this.token.getToken());
			
			this.fhttp = new FinalHttp();
			this.fhttp.post("http://www.yoodm.com/wai/slap", this.param, new AjaxCallBack(){
//				this.fhttp.post("http://ttt.k76.com/wai/slap", this.param, new AjaxCallBack(){
				public void onSuccess(Object t) {
					super.onSuccess(t);
					try {
						JSONArray data = new JSONObject(t.toString()).getJSONArray("data");
						dlist = new ArrayList<JSONObject>();
						for(int i = 0; i < data.length(); i++) {
							dlist.add(data.getJSONObject(i));
						}
						System.out.println(t.toString());
						adapt = new View2Adapter(View2Activity.this, fdata, dlist, fbmap, pixel);
						
						vlist = (ListView) findViewById(R.id.vlist1);
						vlist.setAdapter(adapt);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}