package com.dhitchin.smartFridge.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.dhitchin.smartFridge.model.Fridge;
import com.dhitchin.smartFridge.model.FridgeItem;
import com.dhitchin.smartFridge.view.AddItemDialog;
import com.dhitchin.smartFridge.view.Disp;
import com.dhitchin.smartFridge.view.Login;

public class FridgeController implements ActionListener, ListSelectionListener {

	Fridge fridgeModel;
	Disp fridgeView;
	LoginController login;
	Login loginView;
	AddItemDialog addView;
	AddItemController addController;
	
	private FridgeItem selectedItem = new FridgeItem();
	
	
	public FridgeController(){
		super();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae){
		addView = new AddItemDialog(fridgeView.getFrame());
		addController = new AddItemController();
		if(ae.getActionCommand() == "Update Item" || ae.getActionCommand() == "Add New Item"){
			if(ae.getActionCommand() == "Update Item"){
				addController.updateItem(selectedItem);
				addView.setTextFields(selectedItem.getName(), selectedItem.getExpDate(), selectedItem.getCurrAmnt());
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run(){
					addController.setModel(fridgeModel);
					addController.setView(addView);
					addView.addController(addController);
					
					addView.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e){
							if(selectedItem != null) fridgeView.updateSelectedItem(selectedItem.getName(), selectedItem.getExpDateString(), selectedItem.getCurrAmnt(), selectedItem.getThreshold());
						}
					});
					
					addView.setVisible(true);
				}
			});			
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		selectedItem = fridgeModel.getItemWithName(fridgeView.getSelectedItem());
		if(selectedItem != null)
			fridgeView.updateSelectedItem(selectedItem.getName(), selectedItem.getExpDateString(), selectedItem.getCurrAmnt(), selectedItem.getThreshold());
	}
	
	public void setModel(Fridge f){
		this.fridgeModel = f;
		login = new LoginController(""+fridgeModel.getUserPasscode());
	}
	
	public void setView(Disp d){
		this.fridgeView = d;
		loginView = new Login(fridgeView.getFrame());
	}
	
	public void startApplication(){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run(){
				login.setView(loginView);
				loginView.addController(login);
				fridgeView.getFrame().setVisible(false);
				loginView.setVisible(true);
			}
		});
	}
}
