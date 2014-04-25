package com.dhitchin.smartFridge.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Date;

import com.dhitchin.smartFridge.model.Fridge;
import com.dhitchin.smartFridge.model.FridgeItem;
import com.dhitchin.smartFridge.view.AddItemDialog;

public class AddItemController extends KeyAdapter implements ActionListener{
	private FridgeItem newItem = null;
	private AddItemDialog addItemWindow;
	private Fridge fridge;
	
	public AddItemController(){
		super();
	}
	
	public void updateItem(FridgeItem update){
		newItem = update;
	}
	
	public void setView(AddItemDialog aid){
		addItemWindow = aid;
	}
	
	public void setModel(Fridge f){
		fridge = f;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand() == "Cancel"){
			addItemWindow.dispose();
		} else if(ae.getActionCommand() == "Submit"){
			if(newItem == null){
				newItem = new FridgeItem();
				newItem.setName(addItemWindow.getItemName());
				newItem.setExpDate(addItemWindow.getExpDate());
				newItem.setCurrAmnt(addItemWindow.getAmount());
				fridge.addItem(newItem);
			} else {
				newItem.setName(addItemWindow.getItemName());
				newItem.setExpDate(addItemWindow.getExpDate());
				newItem.setCurrAmnt(addItemWindow.getAmount());
			}
			addItemWindow.dispose();
		} else if(ae.getActionCommand() == "Reset"){
			addItemWindow.setTextFields("", new Date(), 0.0);
		}
	}
}
