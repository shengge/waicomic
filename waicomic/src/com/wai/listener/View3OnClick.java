package com.wai.listener;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;

@SuppressWarnings("unused")
public class View3OnClick extends SimpleOnGestureListener {
	public View3OnClick(Context cotext, ImageView images) {
		this.scales = 1.0f;
		this.images = images;
		this.cotext = cotext;
		this.pointf = new PointF();
		this.marker = new Matrix();
		this.matrix = new Matrix();
		this.metric = new DisplayMetrics();
	}
	
	/*双击事件*/
	public boolean onDoubleTap(MotionEvent event) {
		System.out.println("+++++++++++++++++++++++++++++++onDoubleTap");

		this.scales = scales%1 == 0 ? 1.9f : 1.0f;
		this.matrix.setScale(this.scales, this.scales, event.getX() * this.scales, event.getY() * this.scales);
		this.images.setImageMatrix(matrix);

		return false;
	}
	
	/*坐标起点*/
	public boolean onDown(MotionEvent event) {
//		System.out.println("+++++++++++++++++++++++++++++++onDown");
		
		this.marker.set(matrix);
		this.pointf.set(event.getX(), event.getY());
		
//		System.out.println(this.matrix.toString());
//		System.out.println(this.marker.toString());
		
		return super.onDown(event);
	}
	
	/*单点平移*/
	public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
		System.out.println("+++++++++++++++++++++++++++++++onFling");
		return super.onFling(event1, event2, velocityX, velocityY);
	}
	
	/*单机事件*/
	public boolean onSingleTapConfirmed(MotionEvent event) {
		System.out.println("+++++++++++++++++++++++++++++++onSingleTapConfirmed");
		return super.onSingleTapConfirmed(event);
	}
	
	private Float scales;
	private PointF pointf;
	private Matrix marker;
	private Matrix matrix;
	private DisplayMetrics metric;
	private ImageView images;
	private Context cotext;
}