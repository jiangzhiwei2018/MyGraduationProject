package cn.jzw.mypost.servelet;

import java.io.Serializable;
import java.util.ArrayList;

public class MyData implements Serializable {
    private	int id;
    private String name;
    private double value;
    private ArrayList<Double> values=new ArrayList<Double>();
    public MyData(int iid ,String nname,ArrayList<Double> vvalue) {
		// TODO Auto-generated constructor stub
    	this.id=iid;
    	this.name=nname;
    	this.values=vvalue;
	}
    public MyData(int iid ,String nname,double vvalue) {
		// TODO Auto-generated constructor stub
    	this.id=iid;
    	this.name=nname;
    	this.value=vvalue;
	}
    public MyData() {
		// TODO Auto-generated constructor stub
    	this.id=Integer.MIN_VALUE;
    	this.name=null;
    	this.value=Float.MIN_VALUE;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public void setValues(ArrayList<Double> values2) {
		this.values = values2;
	}
	public ArrayList<Double> getValues() {
		return this.values;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
