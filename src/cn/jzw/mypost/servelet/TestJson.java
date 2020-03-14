package cn.jzw.mypost.servelet;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;

public class TestJson {

	
	private static int count=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Double> values=new ArrayList<Double>(10);
		
		for (int i = 0; i<15; i++) {
			count%=10;
			
			if (values.size()>=10) {
				values=getNewArr(values,99.0+i);
			}else values.add((double)i);
			System.out.println(values);
			count++;
		}
		System.out.println(values);
	}
	
	public static ArrayList<Double> getNewArr(ArrayList<Double> values , Double newData){
		int size=values.size();
		ArrayList<Double> newArr=new ArrayList<Double>(size);
		for (int i = 0; i <size-1; i++) {
			values.set(i,values.get(i+1));
		}
		values.set(size-1,newData);
		return values;
	}
	
}
