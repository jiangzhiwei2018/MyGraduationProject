package cn.jzw.mysql.databases;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;



import cn.jzw.mysql.databody.UserDatas;


/**
 * 查询UserDatas
 * @author 伟
 *
 */
public class MyUserDataSqlUtil extends MysqlManager{
	public List<UserDatas> serchUserDatas(String sql,Object...params){
		Connection conn=this.getconn();
		List<UserDatas> list=new ArrayList<UserDatas>();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=this.prepareStatement(conn, sql, params);
			rs=pst.executeQuery();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				UserDatas wor=new UserDatas();
				wor.setUserID(rs.getString(2));//USER_ID
				wor.setDataTime(df.format(rs.getTimestamp("data_time")));//日期
				wor.setTemperature(rs.getFloat("temperature"));//温度
				wor.setHumidity(rs.getFloat("humidity"));//湿度		
				wor.setLocation(rs.getString("localtion"));//位置信息
			    wor.setUserName(rs.getString("user_name"));//用户名字
			    wor.setCH4_CON(rs.getFloat("CH4_CON"));
			    wor.setLightIntensity(rs.getInt("Light_Inte"));
				list.add(wor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(conn, pst, rs);
		}
		return list;
	}
	
		//查询表
		public List<UserDatas> findAll(){
			String sql="SELECT * FROM `data`";
			return serchUserDatas(sql);
		}
		//添加方法
		public int insert(UserDatas t){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str="INSERT INTO `data`(user_id,data_time,temperature,humidity,CH4_CON,Light_Inte,localtion,user_name) VALUE(?,?,?,?,?,?,?,?)";
			return executeUpdate(str, new Object[]{
					t.getUserID(),
					df.format(new Date()),
					t.getTemperature(),
					t.getHumidity(),
					t.getCH4_CON(),
					t.getLightIntensity(),
					t.getLocation(),
					t.getUserName()		
			});
		}
		
		//修改方法
		public int update(UserDatas t){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sql="UPDATE `data` SET `data_time`=?,`temperature`=?,`humidity`=?,`CH4_CON`=?,`Light_Inte`=?,`localtion`=?,`user_name`=? WHERE user_id=?";
			return executeUpdate(sql, new Object[]{
					df.format(new Date()),//
					t.getTemperature(),
					t.getHumidity(),//
					t.getCH4_CON(),//
					t.getLightIntensity(),//
					t.getLocation(),//
					t.getUserName(),//
					t.getUserID() //ID
					}
			);
		}
		//删除方法
		public int delete(UserDatas e){
			String sql="DELETE FROM `data` WHERE user_id=?";
			return executeUpdate(sql, new Object[]{e.getUserID()});
		}
	
	
}
