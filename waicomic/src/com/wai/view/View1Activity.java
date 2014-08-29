package com.wai.view;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONObject;

import sms.purchasesdk.cartoon.SMSPaycode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wai.R;
import com.wai.adpt.View1Adapter;
import com.wai.init.IAPListener;
import com.wai.init.MainActivity;
import com.wai.model.KeepList;
import com.wai.preference.CommonPreference;
import com.wai.tool.MyToken;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class View1Activity extends Activity {
	private ArrayList<JSONObject> dlist;
	private JSONObject jsons;
	private ImageView image;
	private TextView texts;
	private GridView grid;
	private List<KeepList> keep;
	private MyToken token;
	private FinalDb fdata;
	private FinalHttp fhttp;
	private AjaxParams param;
	private FinalBitmap fbmap;
	public static View1Activity mInstance;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.view1);
		mInstance=this;
		InitSMSPay();
		this.texts = (TextView) this.findViewById(R.id.text0);
		this.texts.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				View1Activity.this.finish();
			}
		});
		
		try {
			this.jsons = new JSONObject(this.getIntent().getStringExtra("chapter"));
			this.image = (ImageView) this.findViewById(R.id.image1);
			this.fdata = FinalDb.create(this, "mhbox.db");
			
			this.fbmap = FinalBitmap.create(this);
			this.fbmap.display(this.image, this.jsons.getString("cover"));
			
			this.texts = (TextView) this.findViewById(R.id.text1);
			this.texts.setText(this.jsons.getString("title"));
			
			this.texts = (TextView) this.findViewById(R.id.text2);
			this.texts.setText(this.jsons.getString("title"));
			
			this.texts = (TextView) this.findViewById(R.id.text3);
			this.texts.setText("作者：".concat(this.jsons.getString("author")));
			
			this.texts = (TextView) this.findViewById(R.id.text4);
			this.texts.setText("更新：".concat(this.jsons.getString("update")));
			
			this.texts = (TextView) this.findViewById(R.id.text7);
			this.texts.setText("漫画状态：".concat(this.jsons.getString("mode").equals("1") ? "连载中" : "已完结"));
			
			this.texts = (TextView) this.findViewById(R.id.text8);
			this.texts.setText("最后更新：".concat(this.jsons.getString("update").split(" ")[0]));
			
			this.token = new MyToken();
			this.param = new AjaxParams();
			this.param.put("id", this.jsons.getString("id"));
			this.param.put("time", this.token.getTime());
			this.param.put("token", this.token.getToken());
			
			this.fhttp = new FinalHttp();
			this.fhttp.post("http://www.yoodm.com/wai/roll", this.param, new AjaxCallBack(){
//				this.fhttp.post("http://ttt.k76.com/wai/roll", this.param, new AjaxCallBack(){
				public void onSuccess(Object t) {
					super.onSuccess(t);
					try {
						JSONArray data = new JSONObject(t.toString()).getJSONArray("data");
						System.out.println(t.toString());
						dlist = new ArrayList<JSONObject>();
						for(int i = 0; i < data.length(); i++) {
							dlist.add(data.getJSONObject(i));
						}
						
						grid = (GridView) findViewById(R.id.grid1);
						grid.setAdapter(new View1Adapter(mInstance, fdata, dlist));
						
						keep = fdata.findAllByWhere(KeepList.class, "pmk = ".concat(jsons.getString("sid")));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mInstance = null;
	}
	public static SMSPaycode msmsPaycode;
	
	// 计费信息
	// 计费信息 (现网环境)   
	public static final String APPID = "300000004301";
	public static final String APPKEY = "F233BDA0B0FAEBC6";
	private void InitSMSPay() {
		//IAPHandler iapHandler = new IAPHandler(this);
        /**
		 * IAP组件初始化.包括下面3步。
		 */
		/**
		 * step1.实例化PurchaseListener。实例化传入的参数与您实现PurchaseListener接口的对象有关。
		 * 例如，此Demo代码中使用IAPListener继承PurchaseListener，其构造函数需要Context实例。
		 */
		//mListener = new IAPListener(this, iapHandler);
		/**
		 * step2.获取Purchase实例。
		 */
		msmsPaycode = SMSPaycode.getInstance();
		/**
		 * step3.向Purhase传入应用信息。APPID，APPKEY。 需要传入参数APPID，APPKEY。 APPID，见开发者文档
		 * APPKEY，见开发者文档
		 */
		try {
			msmsPaycode.setAppInfo(APPID, APPKEY);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		/**
		 * step4. IAP组件初始化开始， 参数PurchaseListener，初始化函数需传入step1时实例化的
		 * PurchaseListener。
		 */
		try {
			msmsPaycode.smsInit(this,View1Adapter.mView1OnClick);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}