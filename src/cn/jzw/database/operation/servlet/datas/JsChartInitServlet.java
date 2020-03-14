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

import cn.jzw.mypost.servelet.MyJsonUtil;
@WebServlet("/DatasOperation/JsChartInit")
public class JsChartInitServlet extends HttpServlet {

	public JsChartInitServlet() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("收到请求");
			request.setCharacterEncoding("UTF-8");
			String coun=request.getParameter("Cont");
			System.out.println("请求数据 "+coun+" 个");
			JSONArray jSONArray=MyJsonUtil.getJsonArrayFoMySql(Integer.parseInt(coun));
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.println(jSONArray.toJSONString());
			printWriter.flush();
			printWriter.close();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
