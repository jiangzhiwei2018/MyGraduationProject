package cn.jzw.mysql.databody;

import java.text.SimpleDateFormat;
import java.util.Date;


public class UserDatas  extends UsersLibary{
	private float temperature;
	private double co2Concentration;
 	private float humidity;
 	private String location;
 	private String dataTime=null;
 	private double O2Concentration;
 	private float CH4_CON;
 	private int LightIntensity;
 	private int dataID;
 	
 	public int getDataID() {
		return dataID;
	}
	public void setDataID(int dataID) {
		this.dataID = dataID;
	}
	public UserDatas() {
		// TODO Auto-generated constructor stub
 		userName=null;
 		userID=null;
 		temperature=0;
 		co2Concentration=0;
 		humidity=0;
 		location=null;
 		O2Concentration=0;
 	}
	public float getCH4_CON() {
		return CH4_CON;
	}
	public void setCH4_CON(float cH4_CON) {
		CH4_CON =cH4_CON;
	}
	public int getLightIntensity() {
		return LightIntensity;
	}
	public void setLightIntensity(int lightIntensity) {
		LightIntensity = lightIntensity;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public double getCo2Concentration() {
		return co2Concentration;
	}
	public void setCo2Concentration(double co2Concentration) {
		this.co2Concentration = co2Concentration;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	public double getO2Concentration() {
		return O2Concentration;
	}
	public void setO2Concentration(double o2Concentration) {
		O2Concentration = o2Concentration;
	}
	
	@Override
	public String toString() {
		return "UserDatas [temperature=" + temperature + ", co2Concentration=" + co2Concentration + ", humidity="
				+ humidity + ", location=" + location + ", dataTime=" + dataTime + ", O2Concentration="
				+ O2Concentration + ", CH4_CON=" + CH4_CON + ", LightIntensity=" + LightIntensity + ", dataID=" + dataID
				+ "]";
	}
}
