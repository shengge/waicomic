package com.wai.view;

import java.util.ArrayList;
import java.util.List;

import sms.purchasesdk.cartoon.SMSPaycode;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxParams;
import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.wai.R;
import com.wai.adpt.AList1Adapter;
import com.wai.adpt.AList2Adapter;
import com.wai.adpt.AList3Adapter;
import com.wai.adpt.AList4Adapter;
import com.wai.init.IAPHandler;
import com.wai.init.IAPListener;
import com.wai.tool.MyToken;

public class Host2Activity extends Activity {
	private ViewPager pager;
	private ImageView image;
	private List<View> views;
	private ListView vlist;
	private View viewp;
	private Integer ofset;
	private MyToken token;
	private FinalDb fdata;
	private FinalHttp fhttp;
	private AjaxParams param;
	private FinalBitmap fbmap;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.host2);
		
		this.ofset = 0;
		this.token = new MyToken();
		this.fhttp = new FinalHttp();
		this.views = new ArrayList<View>();
		this.fbmap = FinalBitmap.create(this);
		this.fdata = FinalDb.create(this, "mhbox.db");
		
		this.param = new AjaxParams();
		this.param.put("time", this.token.getTime());
		this.param.put("token", this.token.getToken());
		
		this.InitImageView();
		this.InitTextView();
		this.InitViewPager();
	}

	private void InitViewPager() {
		this.viewp = getLayoutInflater().inflate(R.layout.alist2, null);
		this.vlist = (ListView) viewp.findViewById(R.id.alist2);
		this.vlist.setAdapter(new AList1Adapter(Host2Activity.this, this.fdata, this.fhttp, this.param, this.fbmap));
		this.views.add(this.viewp);
		
		this.viewp = getLayoutInflater().inflate(R.layout.alist2, null);
		this.vlist = (ListView) this.viewp.findViewById(R.id.alist2);
		this.vlist.setAdapter(new AList2Adapter(Host2Activity.this, this.fdata, this.fhttp, this.param, this.fbmap));
		this.views.add(this.viewp);
		
		this.viewp = getLayoutInflater().inflate(R.layout.alist2, null);
		this.vlist = (ListView) this.viewp.findViewById(R.id.alist2);
		this.vlist.setAdapter(new AList3Adapter(Host2Activity.this, this.fdata, this.fhttp, this.param, this.fbmap));
		this.views.add(this.viewp);
		
		this.viewp = getLayoutInflater().inflate(R.layout.alist2, null);
		this.vlist = (ListView) this.viewp.findViewById(R.id.alist2);
		this.vlist.setAdapter(new AList4Adapter(Host2Activity.this, this.fdata, this.fhttp, this.param, this.fbmap));
		this.views.add(this.viewp);
		
		this.pager = (ViewPager) findViewById(R.id.viewpager);
		this.pager.setAdapter(new MyViewPagerAdapter(this.views));
		this.pager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	private void InitTextView() {
		((TextView) findViewById(R.id.text1)).setOnClickListener(new MyOnClickListener(0));
		((TextView) findViewById(R.id.text2)).setOnClickListener(new MyOnClickListener(1));
		((TextView) findViewById(R.id.text3)).setOnClickListener(new MyOnClickListener(2));
		((TextView) findViewById(R.id.text4)).setOnClickListener(new MyOnClickListener(3));
	}

	private void InitImageView() {
		this.image = (ImageView) findViewById(R.id.cursor);
		
		Matrix matrix = new Matrix();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		this.ofset = dm.widthPixels / 4;

		LayoutParams ps = image.getLayoutParams();
		ps.width = ofset;
		
		this.image.setLayoutParams(ps);
		this.image.setImageMatrix(matrix);
	}

	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			this.index = i;
		}

		public void onClick(View v) {
			pager.setCurrentItem(this.index);
		}
	}

	public class MyViewPagerAdapter extends PagerAdapter {
		private List<View> ListViews;

		public MyViewPagerAdapter(List<View> mListViews) {
			this.ListViews = mListViews;
		}

		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(this.ListViews.get(position));
		}

		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(ListViews.get(position), 0);
			return this.ListViews.get(position);
		}

		public int getCount() {
			return this.ListViews.size();
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {
		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int arg0) {
			Animation animation = new TranslateAnimation(ofset * arg0, ofset * arg0, 0, 0);
			
			animation.setFillAfter(true);
			animation.setDuration(300);
			
			image.startAnimation(animation);
		}
	}
}