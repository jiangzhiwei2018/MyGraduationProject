<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%">
<head>
<link rel="shortcut icon" href="resources/bitbug_favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入echarts.js


<!-- 引入jquery -->
<script src="js/jquery-3.4.1.js" type="text/javascript" charset="utf-8"></script>
<!-- 引入echarts.js -->
 <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-gl/dist/echarts-gl.min.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-stat/dist/ecStat.min.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/china.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/world.js"></script>
       <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js"></script>
<title>数据检测可视化</title>

<style> 
	div{ float:left} 
</style> 
</head>
<body style="height: 100%; margin: 0">
	<!-- 定义一个div存放可视化图表 -->
	<div id="line" style="height:100%; Width:100%"></div>

	<!-- 引入自己编写的ajaxEcharts.js文件 -->
	<script src="${pageContext.servletContext.contextPath}/js/ajaxEcharts.js" type="text/javascript" charset="utf-8">
	</script>

</body>
</html>