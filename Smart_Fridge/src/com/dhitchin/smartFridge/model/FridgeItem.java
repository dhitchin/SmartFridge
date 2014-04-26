package com.dhitchin.smartFridge.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FridgeItem {
	private String name = null;
	private Date expDate = new Date();
	private int currAmnt = 0;
	private int threshold = 0;
	
	public boolean isUnderThreshold(){
		return currAmnt < threshold;
	}
	
	public FridgeItem(){
		super();
	}
	
	public FridgeItem(String name, Date expDate, int currAmnt){
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

	public int getCurrAmnt() {
		return currAmnt;
	}

	public void setCurrAmnt(int currAmnt) {
		this.currAmnt = currAmnt;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	@Override
	public String toString() {
		return "FridgeItem [name=" + name + ", expDate=" + expDate + "]";
	}
}
