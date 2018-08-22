package org.bicyclesharing.util;

import java.io.IOException;
import java.util.Random;

import javax.xml.ws.http.HTTPException;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

public class MsgUtil {
	// 短信应用SDK AppID
		static int appid = 1400118683; // 1400开头

		// 短信应用SDK AppKey
		static String appkey = "32f18b6197fa3e7fbcb1d2a1db6c1203";

		// 需要发送短信的手机号码
		static String phoneNumbers;

		// 短信模板ID，需要在短信应用中申请
	static	int templateId = 177414; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请

		// 签名
		static String smsSign = "w9zy99"; 
		
		  public static void sendMsg(String number,String parem){
		    	try {
		    	    String[] params = {"5678","2"};
		    	    params[0] = parem;
		    	    SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
		    	    SmsSingleSenderResult result = ssender.sendWithParam("86", number,
		    	        templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
		    	    System.out.println(result);
		    	} catch (HTTPException e) {
		    	    // HTTP响应码错误
		    	    e.printStackTrace();
		    	} catch (JSONException e) {
		    	    // json解析错误
		    	    e.printStackTrace();
		    	} catch (IOException e) {
		    	    // 网络IO错误
		    	    e.printStackTrace();
		    	} catch (org.json.JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (com.github.qcloudsms.httpclient.HTTPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		  //生成随机验证码
		  public static String roundMsg(){
				
	String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
	Random rand = new Random();
	StringBuffer flag = new StringBuffer();
	for (int j = 0; j < 4; j++) 
	{
		flag.append(sources.charAt(rand.nextInt(9)) + "");
	}
	

			  return flag.toString();
		  }
}
