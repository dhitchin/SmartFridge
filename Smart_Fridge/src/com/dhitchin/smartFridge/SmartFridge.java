package com.dhitchin.smartFridge;

import com.dhitchin.smartFridge.controller.FridgeController;
import com.dhitchin.smartFridge.model.*;
import com.dhitchin.smartFridge.view.*;

public class SmartFridge {

	public static void main(String[] args) {
		Fridge myFridge = new Fridge();
		Disp myView = new Disp();
		
		myFridge.addObserver(myView);
		
		FridgeController fridgeControl = new FridgeController();
		fridgeControl.setModel(myFridge);
		fridgeControl.setView(myView);
		
		myView.addController(fridgeControl);
		
		fridgeControl.startApplication();
	}
}
