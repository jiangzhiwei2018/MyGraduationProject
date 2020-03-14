package cn.jzw.mysql.databody;

import java.io.*;
import java.net.*;
import java.net.URLConnection;

import com.alibaba.fastjson.JSONObject;

public class MapUtil {
	/****/
	
	/** 用户申请注册的key，自v2开始参数修改为“ak”，之前版本参数为“key” **/
	private static final String AK = "aXCG8v6sYNIVoSljA4FcUtqE55Xd7EKe";
	/** 若用户所用ak的校验方式为sn校验时该参数必须sn生成 **/
	//private static final String SK = "***************";
	
	private static final String URL = "http://api.map.baidu.com/reverse_geocoding/v3/?ak="+AK+"&output=json&coordtype=wgs84ll&location=";
	
	
	
	/**
	 * 获取位置信息 
	 * @param loacl
	 * @return Location实例
	 */
	public static Location getAddress(String loacl) {
		Location address =null;
		BufferedReader in = null;
		URL url = null;
        URLConnection connection = null;
        try {
            url = new URL(URL+loacl);
            connection = url.openConnection();
            connection.setDoOutput(true);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String line;
            StringBuilder text = new StringBuilder("");
            while ((line = in.readLine()) != null) {
                text.append(line.trim());
            }
            JSONObject result = JSONObject.parseObject(text.toString());
            if (result != null && result.getIntValue("status") == 0) {
            	JSONObject resObj=result.getJSONObject("result");
            	JSONObject resObj2=resObj.getJSONObject("addressComponent");
            	address=new Location(resObj.getString("formatted_address"));
            	address.setCountry(resObj2.getString("country")+":"+resObj2.getString("country_code_iso"));
            	address.setProvince(resObj2.getString("province"));
            	address.setCity(resObj2.getString("city"));
            	address.setStreet(resObj2.getString("district")+
            			resObj2.getString("town")+
            			resObj2.getString("street")+
            			resObj2.getString("street_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return address;
	}
	public static void main(String[] args) {
		System.out.println(getAddress("31.59649036800143,120.24654193664554"));
	}
}
