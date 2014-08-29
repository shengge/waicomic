package com.wai.listener;

import java.util.List;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Toast;

@SuppressWarnings("unused")
@SuppressLint("HandlerLeak")
public class AListsListener implements OnScrollListener {
	public AListsListener(Context context, List<JSONObject> data, BaseAdapter adpt) {
		this.filt = LayoutInflater.from(context);
		this.json = data;
		this.adpt = adpt;
		this.numb = 10;
		this.last = 0;
		this.hand = new Handler(){
			public void handleMessage(Message msg) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				for(int i = numb - 1; i < numb + 10; i++) {
					
				}
				
				numb = json.size();
//				adpt.notifyDataSetChanged();
			    
			    if(numb >= 29) {
					Toast.makeText(filt.getContext(), "木有更多数据！", Toast.LENGTH_LONG).show();
				}
			};
		};
	}

	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
	}
	
	private List<JSONObject> json;
	private LayoutInflater filt;
	private BaseAdapter adpt;
	private Integer numb;
	private Integer last;
	private Handler hand;
}