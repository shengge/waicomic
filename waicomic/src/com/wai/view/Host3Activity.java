package com.wai.view;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxParams;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.wai.R;
import com.wai.adpt.AList5Adapter;
import com.wai.init.MainActivity;
import com.wai.tool.MyToken;

public class Host3Activity extends Activity {
	private InputMethodManager input;
	private ListView vlist;
	private EditText stext;
	private MyToken token;
	private FinalDb fdata;
	private FinalHttp fhttp;
	private AjaxParams param;
	private FinalBitmap fbmap;
	private ImageButton query;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.host3);
		this.token = new MyToken();
		this.fhttp = new FinalHttp();
		this.fbmap = FinalBitmap.create(this);
		this.fdata = FinalDb.create(this, "mhbox.db");
		
		this.param = new AjaxParams();
		this.param.put("time", this.token.getTime());
		this.param.put("token", this.token.getToken());
		
		this.vlist = (ListView) findViewById(R.id.alist1);
		this.vlist.setAdapter(new AList5Adapter(Host3Activity.this, this.fdata, this.fhttp, this.param, this.fbmap));
		
		this.stext = (EditText) this.findViewById(R.id.stext);
		this.stext.setOnEditorActionListener(new TextView.OnEditorActionListener() {  
		    public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
		    	param.put("title", stext.getText().toString());
		    	vlist.setAdapter(new AList5Adapter(Host3Activity.this, fdata, fhttp, param, fbmap));
		    	input = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
				input.hideSoftInputFromWindow(stext.getWindowToken(), 0); 
				stext.clearFocus();
		        return true;  
		    }  
		});
		
		this.stext.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View view, boolean hasFocus) {  
				if(hasFocus) {
					((EditText) view).setText("");
				} else {
					((EditText) view).setText(R.string.type_01);
				}
			}
		});
		
		this.query = (ImageButton) this.findViewById(R.id.query);
		this.query.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				param.put("title", stext.getText().toString());
				vlist.setAdapter(new AList5Adapter(Host3Activity.this, fdata, fhttp, param, fbmap));
				input = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
				input.hideSoftInputFromWindow(stext.getWindowToken(), 0); 
				stext.clearFocus();
			}
		});
	}
}