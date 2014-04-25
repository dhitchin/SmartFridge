package com.dhitchin.smartFridge.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FridgeItem {
	private String name = null;
	private Date expDate = new Date();
	private double currAmnt = 0.0;
	private double threshold = 0.0;
	
	public boolean isUnderThreshold(){
		return currAmnt < threshold;
	}
	
	public FridgeItem(){
		super();
	}
	
	public FridgeItem(String name, Date expDate, double currAmnt){
		this.name = name;
		this.expDate = expDate;
		this.currAmnt = currAmnt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getExpDate(){
		return expDate;
	}

	public String getExpDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
		return sdf.format(expDate);
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public double getCurrAmnt() {
		return currAmnt;
	}

	public void setCurrAmnt(double currAmnt) {
		this.currAmnt = currAmnt;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	@Override
	public String toString() {
		return "FridgeItem [name=" + name + ", expDate=" + expDate + "]";
	}
}
