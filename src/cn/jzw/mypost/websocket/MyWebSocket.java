package cn.jzw.mypost.websocket;

import java.io.IOException;
import java.text.ParseException;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSONObject;

import cn.jzw.mypost.servelet.MyJsonUtil;


@ServerEndpoint("/websocket")
public class MyWebSocket {
	private static int onlineCount = 0;
	private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
	private Session session;
	 @OnOpen
	  public void onOpen(Session session/*,@PathParam("param")String param*/) throws IOException, ParseException{
		 this.session = session;
	     webSocketSet.add(this);     //加入set中
	     addOnlineCount();           //在线数加1
	   //  String jsonString=MyJsonUtil.getJsonStringFoMySql(10);
	    // session.getBasicRemote().sendText(jsonString);
	     System.out.println("有新连接加入！当前在线人数为"+getOnlineCount());
	 /*    JSONObject jsonObj =new JSONObject();
			JSONObject dataobj =new JSONObject();
			dataobj.put("Temp", getRandomValue());
			dataobj.put("Humi", getRandomValue());
			dataobj.put("CH4", getRandomValue());
			dataobj.put("Light",new Random().nextInt(300));
			jsonObj.put("DataObj", dataobj);
			sendMessage(jsonObj.toJSONString());*/
	 }
	 @OnClose
	 public void onClose(){
		 webSocketSet.remove(this);  //从set中删除
		 subOnlineCount();           //在线数减1
		 System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	 }
	 
	 @OnError
	 public void onError(Session session, Throwable error){
	     System.out.println("发生错误");
	     error.printStackTrace();
	 }
	 
	 
	 @OnMessage
	 public void onMessage(Session session,String message) throws IOException{
		 
	 }
	 public static void sendMessage(String message) throws IOException{
		 
		 for (MyWebSocket myWebSocket : webSocketSet) {
			 myWebSocket.session.getBasicRemote().sendText(message);
			 System.out.println("发送中...");
		}
	       // this.session.getBasicRemote().sendText(message);
	        //this.session.getAsyncRemote().sendText(message);
	    
	 }
	 public  synchronized Session getSession() {
	 return session;
	 }
	 public static synchronized int getOnlineCount() {
	        return onlineCount;
	    
	    
	 }

	public static synchronized void addOnlineCount() {
	    	MyWebSocket.onlineCount++;
	}

	 public static synchronized void subOnlineCount() {
	    	MyWebSocket.onlineCount--;
	 }
	 
	 private static float getRandomValue(){
	        float value = new Random().nextFloat() * 7 + 10;
	        return value;
	    }
	
}
