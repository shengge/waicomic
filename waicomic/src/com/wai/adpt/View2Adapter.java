package com.wai.adpt;

import java.math.BigDecimal;
import java.util.List;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import com.wai.R;
import com.wai.model.KeepList;

@SuppressLint("UseSparseArrays")
public class View2Adapter extends BaseAdapter {
	public View2Adapter(Context context, FinalDb fdata, List<JSONObject> data, FinalBitmap bmap, DisplayMetrics pixel) {
		try {
			this.filter = LayoutInflater.from(context);
			this.metric = pixel;
			this.fdat = fdata;
			this.bmap = bmap;
			this.json = data;
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = this.filter.inflate(R.layout.item7, null);
		try {
			this.imag = (ImageView) convertView.findViewById(R.id.image1);
			this.high = Integer.parseInt(this.json.get(position).getString("height"));
			this.high = new BigDecimal(468).divide(new BigDecimal(this.json.get(position).getString("width")), 200, BigDecimal.ROUND_UP).multiply(new BigDecimal(this.high)).intValue();
			this.bmap.display(this.imag, this.json.get(position).getString("image").replace(".water.jpg", ""), 468, this.high);
			this.high = new BigDecimal(this.metric.widthPixels).divide(new BigDecimal(468), 200, BigDecimal.ROUND_UP).multiply(new BigDecimal(this.high)).intValue();
			this.imag.setLayoutParams(new ListView.LayoutParams(this.metric.widthPixels, this.high));
			
			this.keep = this.fdat.findAllByWhere(KeepList.class, "pmk = ".concat(this.json.get(position).getString("sid")));
			if(this.keep.size() > 0 && this.keep.get(0).getSid() != this.json.get(position).getInt("bid")) {
				this.keep.get(0).setSid(this.json.get(position).getInt("bid"));
				this.keep.get(0).setSmk(position);
				this.fdat.update(this.keep.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return convertView;
	}

	private DisplayMetrics metric;
	private LayoutInflater filter;
	private List<JSONObject> json;
	private List<KeepList> keep;
	private FinalBitmap bmap;
	private ImageView imag;
	private FinalDb fdat;
	private Integer high;
}	
/*@SuppressLint("UseSparseArrays")
public class View2Adapter extends BaseAdapter {
	public View2Adapter(Context context, TextView text, Integer index, String data, FinalBitmap bmap, DisplayMetrics pixel) {
		try {
			this.filter = LayoutInflater.from(context);
			this.metric = pixel;
			this.bmap = bmap;
			this.text = text;
			this.mark = index;
			this.data = new JSONArray(data);
			this.json = new ArrayList<JSONObject>();
			this.numb = new HashMap<Integer, Integer>();
			this.jsonChanged(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void jsonChanged(Integer index) {
		try {
			this.mark = index;
//			if(this.mark > index) {
//				return;
//			}
			
			this.list = this.data.getJSONObject(this.mark).getJSONArray("pics");

			for(int i = 0; i < this.list.length(); i++) {
				this.json.add(this.list.getJSONObject(i));
			}
			
			this.numb.put(this.mark, this.json.size());
			this.notifyDataSetChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNumb() {
		try {
			return this.mark > 0 ? this.numb.get(this.mark - 1) : this.mark;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getTota() {
		return this.numb.get(this.mark) - this.getNumb();
	}
	
	public int getPage() {
		return this.mark + 1;
	}
	
	public int getSize() {
		return this.data.length() - 1;
	}
	
	public int getCount() {
		return this.json != null ? json.size() : 0;
	}

	public Object getItem(int position) {
		return this.json.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		System.out.println(position);
		convertView = this.filter.inflate(R.layout.item5, null);
		
		try {
			this.imag = (ImageView) convertView.findViewById(R.id.image1);
			this.dete = new GestureDetector(this.filter.getContext(), new View3OnClick(this.filter.getContext(), this.imag));
			
			this.high = this.json.get(position).getInt("height") * (468 / this.json.get(position).getInt("width"));
			this.bmap.display(this.imag, this.json.get(position).getString("image").replace(".water.jpg", ""), 468, this.high);
			
			this.high = new BigDecimal(this.metric.widthPixels).divide(new BigDecimal(468), 200, BigDecimal.ROUND_UP).multiply(new BigDecimal(this.high)).intValue();
			this.imag.setLayoutParams(new ListView.LayoutParams(this.metric.widthPixels, this.high));
//			this.imag.setOnTouchListener(new OnTouchListener() {
//				public boolean onTouch(View view, MotionEvent event) {
//					return dete.onTouchEvent(event);
//				}
//			});
			
//			this.text.setText("ตฺ" + (this.mark + 1) + "ีย " + (position + 1 - (this.mark > 0 ? this.numb.get(this.mark - 1) - 2 : 0)) + "/" + this.getTota());
//			System.out.println("ตฺ" + (this.mark + 1) + "ีย " + (position + 1) + "-" + (this.mark > 0 ? this.numb.get(this.mark - 1) : 0) + " is " + (position + 1 - (this.mark > 0 ? this.numb.get(this.mark - 1) - 2 : 0)) + "/" + this.getTota());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return convertView;
	}
	
	private Map<Integer, Integer> numb;
	private DisplayMetrics metric;
	private LayoutInflater filter;
	private List<JSONObject> json;
	private GestureDetector dete;
	private FinalBitmap bmap;
	private ImageView imag;
	private TextView text;
	private JSONArray list;
	private JSONArray data;
	private Integer mark;
	private Integer high;
}*/