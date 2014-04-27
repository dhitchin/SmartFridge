package com.dhitchin.smartFridge.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.dhitchin.smartFridge.model.Fridge;
import com.dhitchin.smartFridge.model.FridgeItem;
import com.dhitchin.smartFridge.view.AddItemDialog;
import com.dhitchin.smartFridge.view.Disp;
import com.dhitchin.smartFridge.view.Login;
import com.dhitchin.smartFridge.view.SetThresholdDialog;

public class FridgeController implements ActionListener, ListSelectionListener, WindowFocusListener {

	Fridge fridgeModel;
	Disp fridgeView;
	LoginController login;
	Login loginView;
	AddItemDialog addView;
	AddItemController addController;
	
	private FridgeItem selectedItem;
	
	
	public FridgeController(){
		super();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae){
		System.out.println(ae.getActionCommand());
		addView = new AddItemDialog(fridgeView.getFrame());
		addController = new AddItemController();
		if(ae.getActionCommand() == "Add New Item"){
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run(){
					addController.setModel(fridgeModel);
					addController.setView(addView);
					addView.addController(addController);
					
					addView.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e){
							fridgeModel.updateLowItems();
						}
					});
					
					addView.setVisible(true);
				}
			});
		}
		if(ae.getActionCommand() == "Display Shopping List"){
			//Make a window of list of low items
		}
		if(ae.getActionCommand() == "Add"){
			//
		}
		if(ae.getActionCommand() == "Remove"){
			selectedItem = fridgeModel.getItemWithName(fridgeView.getSelectedItem());
			if(selectedItem != null){
				int newAmnt = selectedItem.getCurrAmnt() - 1;
				selectedItem.setCurrAmnt(newAmnt);
				fridgeView.updateSelectedItem(selectedItem.getName(), selectedItem.getExpDateString(), selectedItem.getCurrAmnt(), selectedItem.getThreshold());
				if(selectedItem.isUnderThreshold()){
					fridgeView.setLowItemWarning();
				}
			}
			System.out.println(selectedItem);
		}
		if(ae.getActionCommand() == "Change Threshold"){
			SetThresholdDialog std = new SetThresholdDialog(selectedItem);
			std.setVisible(true);
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
		login = new LoginController(""+fridgeModel.getUserPasscode(), ""+fridgeModel.getAdminPasscode());
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
				login.setFridge(fridgeModel);
				loginView.addController(login);
				fridgeView.getFrame().setVisible(false);
				loginView.setVisible(true);
			}
		});
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		if(fridgeModel.hasLowItems()) fridgeView.setLowItemWarning();
		else fridgeView.clearLowItemWarning();
		
		fridgeView.update(fridgeModel, fridgeModel.getItemNames());
		
		selectedItem = fridgeModel.getItemWithName(fridgeView.getSelectedItem());
		if(selectedItem != null)
			fridgeView.updateSelectedItem(selectedItem.getName(), selectedItem.getExpDateString(), selectedItem.getCurrAmnt(), selectedItem.getThreshold());
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		//do nothing
	}
}
