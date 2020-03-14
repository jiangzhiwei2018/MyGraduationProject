package cn.jzw.database.operation.servlet.datas;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.jzw.mypost.servelet.ServletUtil;
import cn.jzw.mysql.databases.MyUsersLibarySqlUtil;
import cn.jzw.mysql.databody.UsersLibary;

@WebServlet("/UsersOperation/UserLogin")
public class UserLogin extends HttpServlet{
	public UserLogin() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		System.out.println("有用户尝试登陆");
		UsersLibary mUser=new UsersLibary();
		String id=request.getParameter("userId");
		String usname=request.getParameter("userName");
		String passwd=request.getParameter("passWd");
		System.out.println("id: "+id+" "+"userName: "+usname+" passWD:"+passwd);
		mUser.setUserID(id);
		mUser.setUserName(usname);
		mUser.setUserPasswd(passwd);
		PrintWriter printWriter = response.getWriter();			
		JSONObject jsonObject1 = new JSONObject();
		if (id==null || passwd==null) {
			jsonObject1.put("result",false);
			jsonObject1.put("reason","Something Empty is not allowed.");
			printWriter.println(jsonObject1.toJSONString());
			printWriter.flush();
			printWriter.close();
			System.out.println("用户名或密码为空");
			return;
		}
		if(ServletUtil.isRightUser(mUser) ){
			jsonObject1.put("result",true);
			jsonObject1.put("reason","successful !");
			System.out.println("验证成功");
		}else{
			jsonObject1.put("result",false);
			jsonObject1.put("reason","用户名或密码不正确");	
			System.out.println("验证失败");
		}
		printWriter.println(jsonObject1.toJSONString());
		printWriter.flush();
		printWriter.close();
		
	}
	
}
