package com.wai.tool;

import android.annotation.SuppressLint;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class MyToken {
	public MyToken() {
		this.buff = new StringBuffer();
		this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	public String getToken() {
		return this.getCode(this.time + "ada7d96dcb62d11432380caf9954590b");
	}
	
    public String getTime() {
    	return this.time;
    }
    
    public String getCode(String code) {
    	try {
			byte [] dig = MessageDigest.getInstance("MD5").digest(code.getBytes("UTF-8"));  
			for (int i = 0; i < dig.length; i++) {
				if (Integer.toHexString(0xFF & dig[i]).length() == 1)  
					this.buff.append("0").append(Integer.toHexString(0xFF & dig[i]));  
	            else  
	            	this.buff.append(Integer.toHexString(0xFF & dig[i]));
			}
			
			return this.buff.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buff.toString();
		}
    }
    
    private StringBuffer buff;
    private String time;
}