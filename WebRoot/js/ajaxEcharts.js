/* ajaxEcharts是自己编写 */


//1.折线图可视化化

//2.折线样式设置(这里面的属性都是Echarts，可以根据自己想法从里面选取属性)
var option = {
	    title: {
	        text: '数据监测',
	        subtext: 'xxx'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['湿度','温度','光照强度','甲烷']
	    },
	    toolbox: {
	        show: true,
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            dataView: {readOnly: false},
	            magicType: {type: ['line','bar']},
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data:[]
	    },
	    yAxis: {
	        type:'value',
	        axisLabel: {
	            formatter: '{value}'
	        }
	    },
	    series: [
	        {
	            name:'湿度',
	            type:'line',
	            data:[],
	            markPoint: {
	                data: [
	                    {type: 'max', name: '最高值'},
	                    {type: 'min', name: '最低值'}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name:'平均值'}
	                ]
	            }
	        },
	        {
	            name:'温度',
	            type:'line',
	            data:[],
	            markPoint: {
	                data: [
	                    {type: 'max', name: '最高值'},
	                    {type: 'min', name: '最低值'}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name:'平均值'}
	                ]
	            }
	        },{
	            name:'光照强度',
	            type:'line',
	            data:[],
	            markPoint: {
	                data: [
	                    {type: 'max', name: '最高值'},
	                    {type: 'min', name: '最低值'}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name:'平均值'}
	                ]
	            }
	        },{
	            name:'甲烷',
	            type:'line',
	            data:[],
	            markPoint: {
	                data: [
	                    {type: 'max', name: '最高值'},
	                    {type: 'min', name: '最低值'}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name:'平均值'}
	                ]
	            }
	        }
	    ]
	};

var myChart;
myChart = echarts.init(document.getElementById('line'));   //pie是jsp里面的div的id属性
//myChart.setOption(option);

 window.onload = function () {	 
	 		intChar();
            ws();
        }
var values=[]; 
function intChar(){
	myChart.showLoading();
	option.xAxis.data.length=0;
	for(var i=0;i<option.series.length;i++) 
		option.series[i].data.length=0;
	$.ajax({
	    type : "post",
	    async : false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
	    url : "http//:47.101.158.218/MyProject3/DatasOperation/DataSerch",//请求发送到JsChartInit处
	    data : {Cont:'10',product_id:'001'},
	    dataType :"json",        //返回数据形式为json
	    success : function(result) {
	    	//alert(result);
	        //请求成功时执行该函数内容，result即为服务器返回的json对象
	    	var re=result.DataObj;
	    	var len=re.length;
	            for(var i=0;i<len;i++){
	            	var data=re[i];
	            	var dateTime=new Date(data.DataTime).toLocaleTimeString();
	            		option.xAxis.data.push(dateTime);
	            		option.series[0].data.push(data.Humi);
	            		option.series[1].data.push(data.Temp);
	            		option.series[2].data.push(data.Light);
	            		option.series[3].data.push(data.CH4);
	            }
	            
	            myChart.setOption(option);
	            //隐藏加载动画略
	            myChart.hideLoading();
	            
	    },
	    error : function(errorMsg) {
	        //请求失败时执行该函数
	        alert("请求数据失败!");
	        myChart.hideLoading();
	    }
	});
	
}
 function ws() {
            var socket=null;
            
            if (!window.WebSocket) {
                window.WebSocket = window.MozWebSocket;
                alert("加载ing..");
            }
            if (window.WebSocket) {
                socket = new WebSocket("ws://47.101.158.218//MyProject3/websocket");
                socket.onmessage = function (event) {
                    var data = event.data
                    var axisData=(new Date()).toLocaleTimeString();
                    var mdata=JSON.parse(data).DataObj;
                   if(option.xAxis.data.length<10){
                	   option.xAxis.data.push(axisData);
                	   option.series[0].data.push(mdata.Humi);
                	   option.series[1].data.push(mdata.Temp);
                	   option.series[2].data.push(mdata.Light);
                	   option.series[3].data.push(mdata.CH4);      	   
                   }else {
                   option.xAxis.data.shift();
                   option.series[0].data.shift();
            	   option.series[1].data.shift();
            	   option.series[2].data.shift();
            	   option.series[3].data.shift();
                   option.xAxis.data.push(axisData);
                   option.series[0].data.push(mdata.Humi);
            	   option.series[1].data.push(mdata.Temp);
            	   option.series[2].data.push(mdata.Light);
            	   option.series[3].data.push(mdata.CH4);
                   }
                   myChart.setOption(option);
                };
                socket.onopen = function (event) {
                	//alert("连接开启");
                };
                socket.onclose = function (event) {
                	alert("连接被关闭");
                };
            } else {
                alert("你的浏览器不支持 WebSocket！");
            };
        }
 
 
 
 
 