package cn.jzw.mysql.databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.jzw.mysql.databody.UserDatas;
import cn.jzw.mysql.databody.UsersLibary;

public class MyUsersLibarySqlUtil extends MysqlManager{
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public List<UsersLibary> serchUserLibary(String sql,Object...params){
		Connection conn=this.getconn();
		List<UsersLibary> list=new ArrayList<UsersLibary>();
		if (conn!=null) {
			System.out.println("连接成功！");
		}
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=this.prepareStatement(conn, sql, params);
			rs=pst.executeQuery();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				UsersLibary wor=new UsersLibary();
				wor.setUserName(rs.getString(1));
				wor.setUserID(rs.getString(2));
				wor.setUserPasswd(rs.getString(3));
				wor.setCreatDate(df.format(rs.getTimestamp(4)));
				list.add(wor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(conn, pst, rs);
		}
		return list;
	}
	
	public int serchUserCount(String sql,Object...params){
		Connection conn=this.getconn();

		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=this.prepareStatement(conn,sql, params);
			rs=pst.executeQuery();
			if(rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(conn, pst, rs);
		}
		return 0;
	}
	public int serchUserIsIn(String sql,Object...params){
		Connection conn=this.getconn();

		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=this.prepareStatement(conn,sql, params);
			rs=pst.executeQuery();
			if(rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(conn, pst, rs);
		}
		return 0;
	}
		//查询表
		public List<UsersLibary> findAll(){
			String sql="SELECT * FROM `users`";
			return serchUserLibary(sql);
		}
		//添加方法
		public int insert(UsersLibary t){
			String str="INSERT INTO `users` (user_name,user_id,user_passwd,create_date) VALUE(?,?,?,?)";
			return executeUpdate(str, new Object[]{
					t.getUserName(),
					t.getUserID(),
					t.getUserPasswd(),
					df.format(new Date())
			});
		}
		//修改方法
		public int update(UsersLibary t){
			String sql="UPDATE `users` SET `user_name`=?,`user_passwd`=?, WHERE user_id=?";
			return executeUpdate(sql, new Object[]{
					t.getUserName(),
					t.getUserPasswd(),
					t.getUserID() //ID
					}
			);
		}
		//删除方法
		public int delete(UsersLibary e){
			String sql="DELETE FROM `users` WHERE user_id=?";
			return executeUpdate(sql, new Object[]{e.getUserID()});
		}
	
	

}
