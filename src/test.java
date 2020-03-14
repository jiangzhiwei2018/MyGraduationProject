import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import okhttp3.*;

public class test {
	static String ss=" ";
	static int count=0;
	 public static void post(String url, HashMap<String, String > paramsMap){  //这里没有返回，也可以返回string
	        OkHttpClient mOkHttpClient = new OkHttpClient();
	        FormBody.Builder formBodyBuilder = new FormBody.Builder();
	        Set<String> keySet = paramsMap.keySet();
	        for(String key:keySet) {
	            String value = paramsMap.get(key);
	            formBodyBuilder.add(key,value);
	        }
	        FormBody formBody = formBodyBuilder.build();
	        Request request = new Request
	                .Builder()
	                .url(url)
	                .addHeader("Host","stat.jseea.cn")
	                .header("Cookie","name=value; JSESSIONID=AD82C7ED11EB80D4A415C110FAC622D4; __jsluid_h=1e994bc81f2388250404f1c1b12a824b; Hm_lvt_6f617ef469c36689efe6fc313f5a79ee=1581230592; __utmc=43812071; __utmz=43812071.1581269094.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utma=43812071.1514875954.1581269094.1582168408.1582172525.8; Hm_lpvt_6f617ef469c36689efe6fc313f5a79ee=1582173964; __utmt=1; __utmb=43812071.5.10.1582172525")
	                .header("User-Agent","Mozilla/5.0 (Linux; U; Android 9; zh-cn; PCGM00 Build/PKQ1.190519.001) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/70.0.3538.80 Mobile Safari/537.36 HeyTapBrowser/20.7.1.5")
	                .header("Language", "zh-CN")
	                .addHeader("Connection", "keep-alive")
	                .header("Cache-Control","max-age=0")
	                .addHeader("Origin", "http://stat.jseea.cn")
	                .header("Upgrade-Insecure-Requests","1")
	                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
	             //   .header("Accept-Encoding","gzip, deflate")
	                .header("Referer","http://stat.jseea.cn/jseea_query/input.do?catlog=1&database=43%2Cdb_631")
	                .header("Content-Type","application/x-www-form-urlencoded")
	                .post(formBody)
	                .build();
	        try (Response response = mOkHttpClient.newCall(request).execute()) {
	        	String result=response.body().string();
	           // System.out.println(result);
	        	System.out.println(result);
	            writeFile(result);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	    }
	 public static void writeFile(String str) {
	        try {
	            File writeName = new File("D:\\Desktop\\write\\output"+count+".html"); // 相对路径，如果没有则要建立一个新的output.txt文件
	            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
	            try (FileWriter writer = new FileWriter(writeName);
	                 BufferedWriter out = new BufferedWriter(writer)
	            ) {
	                out.write(str); // \r\n即为换行
	     
	                out.flush(); // 把缓存区内容压入文件
	               
	                out.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally {
	        	
			}
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i =631; i <=631; i++) {
			 HashMap<String,String> paramsMap = new HashMap<String, String>() ;
				paramsMap.put("8","360681199803050014");
				paramsMap.put("code","6650");
			    paramsMap.put("button","%E7%A1%AE%E5%AE%9A");
		        paramsMap.put("dbName","db_"+i);
		        count=i;
		        post("http://stat.jseea.cn/jseea_query/query.do",paramsMap);
		}
	}

}
