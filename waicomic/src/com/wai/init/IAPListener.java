package com.wai.init;

import java.util.HashMap;

import sms.purchasesdk.cartoon.OnSMSPaycodeListener;
import sms.purchasesdk.cartoon.PurchaseCode;
import sms.purchasesdk.cartoon.SMSPaycode;
import android.content.Context;
import android.util.Log;

import com.wai.preference.CommonPreference;

public  class IAPListener implements OnSMSPaycodeListener {
	private final String TAG = "IAPListener";
	private Context context;
	private IAPHandler iapHandler;

	public IAPListener(Context context, IAPHandler iapHandler) {
		this.context = context;
		this.iapHandler = iapHandler;
	}

	@Override
	public void onBillingFinish(int code, HashMap arg1) {
		Log.d(TAG, "billing finish, status code = " + code);
		String result = "��������������ɹ�";
		// ��Ʒ��Ϣ
		String paycode = null;
		// ��Ʒ�Ľ��� ID���û����Ը����������ID����ѯ��Ʒ�Ƿ��Ѿ�����
		String tradeID = null;
		if (code == PurchaseCode.ORDER_OK){
			/**
			 * ��Ʒ����ɹ������Ѿ����� ��ʱ�᷵����Ʒ��paycode��orderID,�Լ�ʣ��ʱ��(����������Ʒ)
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
				for(Object str : arg1.keySet()){
					System.out.println(str+":");
					System.out.println(arg1.get(str));
				}
//				System.out.println(arg1.keySet());
//				System.out.println(arg1.get("userdata")+"::"+paycode+";;"+tradeID);
				CommonPreference.setBooleanValue(String.valueOf(arg1.get("userdata")), true);
			}
		} else {
			/**
			 * ��ʾ����ʧ�ܡ�
			 */
			result = "���������" + SMSPaycode.getReason(code);
			
		}
//		context.dismissProgressDialog();
		System.out.println(result);

	}

}
