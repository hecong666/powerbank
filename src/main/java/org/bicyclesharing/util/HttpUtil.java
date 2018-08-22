package org.bicyclesharing.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @author ����
 * @date 2017��10��23�� ����2:49
 * HttpClient������
 */
public class HttpUtil {
	
	/** 
     * ģ������ 
     *  
     * @param url       ��Դ��ַ 
     * @param map   �����б� 
     * @param encoding  ���� 
     * @return 
     * @throws ParseException 
     * @throws IOException 
     */  
    public static String send(String url, Map<String,String> map,String encoding) throws Exception, IOException{  
        String body = "";  
  
        //����httpclient����  
        CloseableHttpClient client = HttpClients.createDefault();  
        //����post��ʽ�������  
        HttpPost httpPost = new HttpPost(url);  
          
        //װ�����  
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        if(map!=null){  
            for (Entry<String, String> entry : map.entrySet()) {  
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
            }  
        }  
        //���ò��������������  
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));  
  
        System.out.println("�����ַ��"+url);  
        System.out.println("���������"+nvps.toString());  
          
        //����header��Ϣ  
        //ָ������ͷ��Content-type������User-Agent��  
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");  
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
          
        //ִ��������������õ������ͬ��������  
        CloseableHttpResponse response = client.execute(httpPost);  
        //��ȡ���ʵ��  
        HttpEntity entity = response.getEntity();  
        if (entity != null) {  
            //��ָ������ת�����ʵ��ΪString����  
            body = EntityUtils.toString(entity, encoding);  
        }  
        EntityUtils.consume(entity);  
        //�ͷ�����  
        response.close();  
        return body;  
    }  
    
    public static void main(String[] args) throws Exception, IOException {  
        String url="http://php.weather.sina.com.cn/iframe/index/w_cl.php";  
        Map<String, String> map = new HashMap<String, String>();  
        map.put("code", "js");  
        map.put("day", "0");  
        map.put("city", "�Ϻ�");  
        map.put("dfc", "1");  
        map.put("charset", "utf-8");  
        String body = send(url, map,"utf-8");  
        System.out.println("������Ӧ�����");  
        System.out.println(body);  
      
        System.out.println("-----------------------------------");  
      
        map.put("city", "����");  
        body = send(url, map, "utf-8");  
        System.out.println("������Ӧ�����");  
        System.out.println(body);  
    }  
}  