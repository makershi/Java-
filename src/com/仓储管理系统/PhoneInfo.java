/**
 * 
 */
package com.仓储管理系统;

import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * @author ThinkPad
 *
 */
public class PhoneInfo {

	private long IMEI;
	private String brand;
	private String model;
	private String color;
	private float weight;
	private float price;
	private String productionDate;
	private String warehouse;
	
	public PhoneInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public PhoneInfo(String brand,String model,String color,float weight,float price,String warehouse) {
		setIMEI();
		setBrand(brand);
		setModel(model);
		setColor(color);
		setWeight(weight);
		setPrice(price);
		setProductionDate();
		setWarehouse(warehouse);
	}

	public void setIMEI(){//IMEI不能重复，后期还要修改
		Random r = new Random();
		int bitNum=1;
		for(int i=0;i<15;i++){
			bitNum=bitNum*10;
		}
        long num = Math.abs(r.nextLong() % (bitNum));
        String s = String.valueOf(num);
		for (int i = 15 - s.length(); i >0 ;i--) {
			s = "0" + s;
		}
		if(s.length()>15){
			s=s.substring(0,15);
		}
		IMEI = Long.parseLong(s);     
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setModel(String model){
		this.model = model;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	public void setWeight(float weight){
		this.weight = weight;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void setProductionDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		this.productionDate = formatter.format(date);//时间戳
//		System.out.println(formatter.format(date));
	}
	
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	
	//查询方法
	
	public Long getIMEI() {
		return IMEI;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public String getColor() {
		return color;
	}
	
	public float getWeight() {
		return weight;
	}
	
	public float getPrice() {
		return price;
	}
	
	public String getProductionDate() {
		return productionDate;
	}
	
	public String getWarehouse() {
		return warehouse;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
