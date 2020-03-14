package cn.jzw.database.operation.servlet.datas;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import cn.jzw.mypost.servelet.ServletUtil;
import cn.jzw.mysql.databases.MyUsersLibarySqlUtil;
import cn.jzw.mysql.databody.UsersLibary;
@WebServlet("/UsersOperation/UserRegist")
public class UserRegist extends HttpServlet{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public UserRegist() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			UsersLibary mUser=new UsersLibary();
			System.out.println("有用户尝试注册");
			String id=request.getParameter("userId");
			String usname=request.getParameter("userName");
			String passwd=request.getParameter("passWd");
			mUser.setUserID(id);
			mUser.setUserName(usname);
			mUser.setUserPasswd(passwd);
			PrintWriter printWriter = response.getWriter();			
			JSONObject jsonObject1 = new JSONObject();
			if (id==null || usname==null || passwd==null) {
				jsonObject1.put("result",false);
				jsonObject1.put("reason","Something Empty is not allowed.");
				printWriter.println(jsonObject1.toJSONString());
				printWriter.flush();
				printWriter.close();
				return;
			}
			jsonObject1.put("userID",id);
			if(ServletUtil.isUserInDataBase(mUser)) {
				jsonObject1.put("result",false);
				jsonObject1.put("reason","用户已存在");
				System.out.println("注册失败，用户已存在");
			}else{
				if(new	MyUsersLibarySqlUtil().insert(mUser)==1) {
					jsonObject1.put("result",true);
					jsonObject1.put("reason","successful");
					System.out.println("ID:"+id+"  注册成功");
				}else {
					jsonObject1.put("result",false);
					jsonObject1.put("reason","failed");
					System.out.println("注册失败，SQL执行失败");
				}
			}
			printWriter.println(jsonObject1.toJSONString());
			printWriter.flush();
			printWriter.close();
			
	}	
}
