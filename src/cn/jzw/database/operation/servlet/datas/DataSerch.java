package cn.jzw.database.operation.servlet.datas;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.jzw.mypost.servelet.MyJsonUtil;

@WebServlet("/DatasOperation/DataSerch")
public class DataSerch extends HttpServlet{

	public DataSerch() {
		// TODO Auto-generated constructor stub
		super();
	
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("收到请求");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			String coun=request.getParameter("Cont");
			String product_id=request.getParameter("product_id");
			JSONObject root=new JSONObject();
			if(coun.equals(null) || product_id.equals(null)) {
				root.put("result",false);
				root.put("Realcount",0);
				root.put("DataObj", null);
				printWriter.println(root.toJSONString());
				printWriter.flush();
				printWriter.close();
				return ;
			}
			int con=Integer.parseInt(coun);
			root.put("result",true);
			JSONArray jSONArray=MyJsonUtil.getJsonArrayFoMySql(product_id,con);
			root.put("Realcount",jSONArray.size());
			root.put("DataObj", jSONArray);
			System.out.println("请求ID:"+product_id+" 数据 "+coun+" 个");
			printWriter.println(root.toJSONString());
			System.out.println(root.toJSONString());
			printWriter.flush();
			printWriter.close();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
