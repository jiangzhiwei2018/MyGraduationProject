package cn.jzw.mysql.databases;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;

import cn.jzw.mypost.servelet.MyJsonUtil;
import cn.jzw.mypost.servelet.ServletUtil;
import cn.jzw.mypost.websocket.MyWebSocket;
import cn.jzw.mysql.databody.UserDatas;
import cn.jzw.mysql.databody.UsersLibary;

public class MainTest {

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
	/*	UsersLibary mUser=new UsersLibary();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mUser.setCreatDate(df.format(new Date()));
		
		*/
		MyUsersLibarySqlUtil mUtil=new MyUsersLibarySqlUtil();
	/*	UsersLibary mUsers=new UsersLibary();
		mUsers.setUserID("001");
		mUsers.setUserName("001");
		mUsers.setUserPasswd("jzw166998");*/
	//	mUtil.insert(mUsers);
		MyUserDataSqlUtil  mDataUtil=new MyUserDataSqlUtil();
		UserDatas mUser[]=new UserDatas[10];
	for (int i = 0; i <10; i++) {
			mUser[i]=new UserDatas();
			mUser[i].setUserID("001");
			mUser[i].setUserName("Test1");
			mUser[i].setCH4_CON(getRandomValue());
			mUser[i].setHumidity(getRandomValue());
			mUser[i].setTemperature(getRandomValue());
			mUser[i].setLocation("江西鹰潭");
			mUser[i].setLightIntensity(new Random().nextInt(300));
			mDataUtil.insert(mUser[i]);
		}
	  System.out.println(MyJsonUtil.getJsonArrayFoMySql("001",2).toJSONString());
		//System.out.println(ServletUtil.isRightUser(mUsers));
	//	mDataUtil.delete(mUser[0]);
	//	System.out.println(ServletUtil.isUserInDataBase((UserDatas) mUser));	
		/*JSONObject jsonObj =new JSONObject();
		JSONObject dataobj =new JSONObject();
		dataobj.put("Temp", getRandomValue());
		dataobj.put("Humi", getRandomValue());
		dataobj.put("CH4", getRandomValue());
		dataobj.put("Light",new Random().nextInt(300));
		jsonObj.put("DataObj", dataobj);
		MyWebSocket.sendMessage(jsonObj.toJSONString());*/
	}
	private static float getRandomValue(){
        float value = new Random().nextFloat() * 7 + 10;
        return value;
    }
}
