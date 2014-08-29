package com.aspire.demo;

import java.util.HashMap;

import sms.purchasesdk.cartoon.OnSMSPaycodeListener;
import sms.purchasesdk.cartoon.PurchaseCode;
import sms.purchasesdk.cartoon.SMSPaycode;
import android.content.Context;
import android.util.Log;

public class IAPListener implements OnSMSPaycodeListener {
	private final String TAG = "IAPListener";
	private Demo context;
	private IAPHandler iapHandler;

	public IAPListener(Context context, IAPHandler iapHandler) {
		this.context = (Demo) context;
		this.iapHandler = iapHandler;
	}

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
				paycode = (String) arg1.get(OnSMSPaycodeListener.PAYCODE);
				if (paycode != null && paycode.trim().length() != 0) {
					result = result + ",Paycode:" + paycode;
				}
				tradeID = (String) arg1.get(OnSMSPaycodeListener.TRADEID);
				if (tradeID != null && tradeID.trim().length() != 0) {
					result = result + ",tradeid:" + tradeID;
				}
			}
		} else {
			/**
			 * 表示订购失败。
			 */
			result = "订购结果：" + SMSPaycode.getReason(code);
		}
		context.dismissProgressDialog();
		System.out.println(result);

	}

}
