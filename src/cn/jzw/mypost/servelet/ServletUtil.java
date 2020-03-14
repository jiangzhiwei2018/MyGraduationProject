package cn.jzw.mypost.servelet;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jzw.mypost.websocket.MyWebSocket;
import cn.jzw.mysql.databases.MyUserDataSqlUtil;
import cn.jzw.mysql.databases.MyUsersLibarySqlUtil;
import cn.jzw.mysql.databody.UserDatas;
import cn.jzw.mysql.databody.UsersLibary;

public class ServletUtil {
	public static String getBodytxt(HttpServletRequest request) throws IOException {
		BufferedReader br = request.getReader();
		String str,wholeStr ="";
		while((str=br.readLine()) != null){
			wholeStr += str;
		}
		return wholeStr;
	}
	
	public static boolean isUserInDataBase(UserDatas user){
		MyUsersLibarySqlUtil usersUtil=new MyUsersLibarySqlUtil();
		String sql2="SELECT ifnull((select  count(*)  from users where user_id=? limit 1),0)";
		return usersUtil.serchUserIsIn(sql2,user.getUserID())==1;
	}
	public static boolean isUserInDataBase(UsersLibary user){
		MyUsersLibarySqlUtil usersUtil=new MyUsersLibarySqlUtil();
		String sql2="SELECT ifnull((select  count(*)  from users where user_id=? limit 1),0)";
		return usersUtil.serchUserIsIn(sql2,user.getUserID())==1;
	}
	
	public static boolean isRightUser(UsersLibary user) {
		MyUsersLibarySqlUtil usersUtil=new MyUsersLibarySqlUtil();
		String sql2="SELECT ifnull((select  count(*)  from users where user_id=? and user_passwd=? limit 1),0)";
		return usersUtil.serchUserIsIn(sql2,user.getUserID(),user.getUserPasswd())==1;
	}
	public static String getBody(HttpServletRequest req, HttpServletResponse res) throws Exception {
		 MyUsersLibarySqlUtil usersUtil=new MyUsersLibarySqlUtil();
		String str=getBodytxt(req);
		if (MyWebSocket.getOnlineCount()>=1) {
			System.out.println("有在线，准备更新数据");
			MyWebSocket.sendMessage(str);
			System.out.println(str);
		}
		UserDatas mUserDatas=MyJsonUtil.getUserOBJ(str);
		System.out.println(mUserDatas);
		if(!isUserInDataBase(mUserDatas)){
			System.out.println("用户不存在，正在自动注册");
			mUserDatas.setUserPasswd("123");
			usersUtil.insert(mUserDatas) ;
		}
		MyUserDataSqlUtil  dataUtil=new MyUserDataSqlUtil();
		dataUtil.insert(mUserDatas);
        return str;
	  }
	public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
	
	
}
