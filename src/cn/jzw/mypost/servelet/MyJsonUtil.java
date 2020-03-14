package cn.jzw.mypost.servelet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.jzw.mysql.databases.MyUserDataSqlUtil;
import cn.jzw.mysql.databody.MapUtil;
import cn.jzw.mysql.databody.UserDatas;

public  class MyJsonUtil {
	private static int count=0;
	static HashMap<Integer,ArrayList<Double>> datas=new HashMap<Integer, ArrayList<Double>>();
	public final static int MAXCOUNT=10;
	public static String getJsonString(List<UserDatas> list) {
		return getJsonArrayObj(list).toJSONString();
	}
	public static JSONArray getJsonArrayObj(List<UserDatas> list) {
		 JSONArray jSONArray1 = new JSONArray();
		 int size=list.size();
		// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     for (int i = 0; i < size; i++) {
	    	 JSONObject jsonObject1 = new JSONObject();
	    	 jsonObject1.put("DataTime",list.get(i).getDataTime());
	    	 jsonObject1.put("Loca",list.get(i).getLocation());
	    	 jsonObject1.put("CH4",list.get(i).getCH4_CON());
	    	 jsonObject1.put("Humi",list.get(i).getHumidity());
	    	 jsonObject1.put("Light",list.get(i).getLightIntensity());
	    	 jsonObject1.put("Temp",list.get(i).getTemperature()); 
	    	 jsonObject1.put("Id",list.get(i).getUserID()); 
	    	 jSONArray1.add(jsonObject1);
		}
		return jSONArray1;
	}
	public static ArrayList<Double> getNewArr(ArrayList<Double> values , Double newData){
		int size=values.size();
		ArrayList<Double> newArr=new ArrayList<Double>(size);
		for (int i = 0; i <size-1; i++) {
			values.set(i,values.get(i+1));
		}
		values.set(size-1,newData);
		return values;
	}
	
	public static  MyData[] parseData(String data){
		count%=MAXCOUNT;
		JSONArray dataObjArray=JSON.parseObject(data).getJSONArray("DataObj");
		int size=dataObjArray.size();
		MyData mDataObj[]=new MyData[size];
		for (int i = 0;i<size ; i++) {
			JSONObject dataBean =(JSONObject)dataObjArray.get(i);
			Integer ID=dataBean.getIntValue("DataID");
			String name=dataBean.getString("DataName");
			Double vvalue=dataBean.getDouble("DataValue");
			ArrayList<Double> mdata=null;
			if (!datas.containsKey(ID)) {
				datas.put(ID,new ArrayList<Double>(MAXCOUNT));
			}
			mdata=datas.get(ID);
			if (mdata.size()>=MAXCOUNT) {
				mdata=MyJsonUtil.getNewArr(mdata,vvalue);
			}else mdata.add(vvalue);
			mDataObj[i]=new MyData(ID, dataBean.getString("DataName"), mdata);
			System.out.println("DataID:"+ID);
			System.out.println("DataName:"+name);
			System.out.println("DataValue"+vvalue);
		}
		count++;
		return mDataObj;
	}
	public static UserDatas getUserOBJ(String resultJson) {
		UserDatas mUser=new UserDatas();
		JSONObject jsonObject =JSON.parseObject(resultJson);
		String name=jsonObject.getString("USER_NAME");
		String id=jsonObject.getString("USER_ID");
		JSONObject dataObj=jsonObject.getJSONObject("DataObj");
		String location=dataObj.getString("Loca");
		mUser.setUserName(name);
		mUser.setUserID(id);
		mUser.setHumidity(dataObj.getFloatValue("Humi"));	
		mUser.setCH4_CON(dataObj.getFloatValue("CH4"));
		mUser.setLightIntensity(dataObj.getIntValue("Light"));
		mUser.setTemperature(dataObj.getFloatValue("Temp"));
		mUser.setLocation(MapUtil.getAddress(location).getFullLocation());
	/*	mUser.setCo2Concentration(dataObj.getDoubleValue("CO2"));		
		mUser.setO2Concentration(dataObj.getDoubleValue("O2"));*/
		return mUser;
	}
	public static JSONArray getJsonArrayFoMySql(int cont) throws ParseException {
		String sql="select * from data order by  data_id desc limit ?";
		MyUserDataSqlUtil mDataUtil=new MyUserDataSqlUtil();
		return getJsonArrayObj(mDataUtil.serchUserDatas(sql,cont));
	}
	public static JSONArray getJsonArrayFoMySql(String user_id,int cont) throws ParseException {
		String sql="select * from data where user_id=? order by  data_id desc limit ?";
		MyUserDataSqlUtil mDataUtil=new MyUserDataSqlUtil();
		return getJsonArrayObj(mDataUtil.serchUserDatas(sql,user_id,cont));
	}
	public static String getJsonStringFoMySql(int cont) throws ParseException {

		return getJsonArrayFoMySql(cont).toJSONString();
	}
	 
	
}
