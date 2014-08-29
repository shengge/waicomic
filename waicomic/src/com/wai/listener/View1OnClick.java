package com.wai.listener;

import java.util.HashMap;
import java.util.List;

import net.tsz.afinal.FinalDb;

import org.json.JSONObject;

import sms.purchasesdk.cartoon.OnSMSPaycodeListener;
import sms.purchasesdk.cartoon.PurchaseCode;
import sms.purchasesdk.cartoon.SMSPaycode;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.wai.init.MainActivity;
import com.wai.model.KeepList;
import com.wai.pay.Constants;
import com.wai.preference.CommonPreference;
import com.wai.view.View1Activity;
import com.wai.view.View2Activity;

public class View1OnClick implements OnClickListener,OnSMSPaycodeListener {

	private static final String TAG = "View1OnClick";
	private Context context;

	public View1OnClick(Context context, Integer position,
			List<JSONObject> jsons, FinalDb fdata) {
		this.filter = LayoutInflater.from(context);
		this.index = position;
		this.jsons = jsons;
		this.fdata = fdata;
		this.context = context;
	}
	public String getLeaseCode(int price){
		if(price!=0){
			switch(price){
			case 1:
				return Constants.CONTENT_ID_2;
			case 2:
				return Constants.CONTENT_ID_3;
			case 3:
				return Constants.CONTENT_ID_4;
			case 4:
				return Constants.CONTENT_ID_5;
			case 5:
				return Constants.CONTENT_ID_6;
			case 6:
				return Constants.CONTENT_ID_7;
			case 8:
				return Constants.CONTENT_ID_8;
			case 10:
				return Constants.CONTENT_ID_9;
			case 12:
				return Constants.CONTENT_ID_10;
			case 15:
				return Constants.CONTENT_ID_11;
			case 20:
				return Constants.CONTENT_ID_12;
				default:
					return "0";
			}
		}else{
			return "0";
		}
	}
	public void onClick(View view) {
		try {
			String strLeaseCode="";
			this.keepls = this.fdata.findAllByWhere(KeepList.class, "pmk = "
					.concat(this.jsons.get(this.index).getString("sid")));
			if (this.keepls.size() > 0
					&& this.keepls.get(0).getRid() != this.jsons
					.get(this.index).getInt("cid")) {
				this.keepls.get(0).setRid(
						this.jsons.get(this.index).getInt("cid"));
				this.keepls.get(0).setRmk(this.index);
				this.fdata.update(this.keepls.get(0));
				CommonPreference.setStringValue("intentData", this.jsons.get(this.index).toString());
				System.out.println(jsons.get(index).getInt("cid"));
			}
			int intPrice  = jsons.get(index).getInt("price");
				strLeaseCode = getLeaseCode(intPrice);;
			if (!"0".equals(strLeaseCode)) {
				if (MainActivity.phoneProviders != Constants.CHINA_MOBILE) {
					Toast.makeText(context, "目前只支持移动手机交易", Toast.LENGTH_SHORT).show();
					return;
				}
				//boolean type = false;

//				type = CommonPreference.getBooleanValue(
//						String.valueOf(jsons.get(index).getInt("cid")), false);
				//if (!type) {
					//TODO:XXX    支付代码
//				SMSPaycode msmsPaycode;
//				msmsPaycode = SMSPaycode.getInstance();
//				msmsPaycode.setAppInfo(APPID, APPKEY);
//				try{
//					msmsPaycode.smsInit(this.filter.getContext(), View1OnClick.this);
//				}catch(Exception e){
//					e.printStackTrace();
//				}
				View1Activity.msmsPaycode.smsOrder(this.filter.getContext(),
							strLeaseCode, View1OnClick.this);
					return;
				//}
			}
			toOther(this.jsons.get(this.index).toString(),this.filter.getContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void toOther(String chapters,Context mContext) {
		this.intent = new Intent(mContext, View2Activity.class);
		this.intent.putExtra("chapters", chapters);
		//this.intent.putExtra("position", this.index);
		mContext.startActivity(intent);
	}

	private Intent intent;
	private Integer index;
	private FinalDb fdata;
	private static  LayoutInflater filter;
	private List<KeepList> keepls;
	private List<JSONObject> jsons;

	public static final String APPID = "300000004301";
	public static final String APPKEY = "F233BDA0B0FAEBC6";
	@Override
	public void onBillingFinish(int code, HashMap arg1) {
		Log.d(TAG, "billing finish, status code = " + code);
		String result = "订购结果：订购成功";
		// 商品信息
		String paycode = null;
		// 商品的交易 ID，用户可以根据这个交易ID，查询商品是否已经交易
		String tradeID = null;
		if (code == PurchaseCode.ORDER_OK){
			/**
			 * 商品购买成功或者已经购买。 此时会返回商品的paycode，orderID,以及剩余时间(租赁类型商品)
			 */
			if (arg1 != null) {
//				paycode = (String) arg1.get(OnSMSPaycodeListener.PAYCODE);
//				if (paycode != null && paycode.trim().length() != 0) {
//					result = result + ",Paycode:" + paycode;
//				}
//				
//				tradeID = (String) arg1.get(OnSMSPaycodeListener.TRADEID);
//				if (tradeID != null && tradeID.trim().length() != 0) {
//					result = result + ",tradeid:" + tradeID;
//				}
//				for(Object str : arg1.keySet()){
//					System.out.println(str+":");
//					System.out.println(arg1.get(str));
//				}
//				System.out.println(arg1.keySet());
//				System.out.println(arg1.get("userdata")+"::"+paycode+";;"+tradeID);
				//CommonPreference.setBooleanValue(String.valueOf(""), true);
				String strIntentData = CommonPreference.getStringValue("intentData", "");
				if(!TextUtils.isEmpty(strIntentData)){
					toOther(strIntentData,filter.getContext());
				}
			}
		} else {
			/**
			 * 表示订购失败。
			 */
			result = "订购结果：" + SMSPaycode.getReason(code);
			Toast.makeText(this.filter.getContext(), "订购异常，请稍后再试！", Toast.LENGTH_SHORT).show();
			
		}
//		context.dismissProgressDialog();
		System.out.println(result);

	
	}
}