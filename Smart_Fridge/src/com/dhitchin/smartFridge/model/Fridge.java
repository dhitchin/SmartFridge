package com.dhitchin.smartFridge.model;

import java.util.ArrayList;
import java.util.List;

public class Fridge extends java.util.Observable{
	private ArrayList<FridgeItem> items = new ArrayList<FridgeItem>();
	private int userPasscode = 1234;
	private int adminPasscode = 9999;
	
	public Fridge(){
		super();
	}
	
	public void sendUpdatedList(){
		setChanged();
		notifyObservers(this.getItemNames());
	}
	
	public void addItem(FridgeItem item){
		if(items.contains(item)) items.remove(item);
		items.add(item);
		sendUpdatedList();
	}
	
	public FridgeItem getItemWithName(String name){
		for(FridgeItem i : items){
			if(i.getName().equals(name)) return i;
		}
		return null;
	}
	
	public FridgeItem getItemAt(int index){
		return items.get(index);
	}

	public List<FridgeItem> getItems() {
		return items;
	}
	
	public String[] getItemNames(){
		String[] itemNames = new String[items.size()];
		int i=0;
		for(FridgeItem fi : items){
			itemNames[i] = fi.getName();
			i++;
		}
		return itemNames;
	}

	public int getUserPasscode() {
		return userPasscode;
	}

	public void setUserPasscode(int userPasscode) {
		this.userPasscode = userPasscode;
	}

	public int getAdminPasscode() {
		return adminPasscode;
	}

	public void setAdminPasscode(int adminPasscode) {
		this.adminPasscode = adminPasscode;
	}
}
