package com.dhitchin.smartFridge;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.dhitchin.smartFridge.controller.FridgeController;
import com.dhitchin.smartFridge.model.Fridge;
import com.dhitchin.smartFridge.view.Disp;

public class SmartFridge {
	static Fridge myFridge;	
	final static String FRIDGE_FILE = "fridge.dat";
		
	public static void main(String[] args) {
		Disp myView = new Disp();
		
		readFridge();
		
		myFridge.addObserver(myView);
		
		FridgeController fridgeControl = new FridgeController();
		fridgeControl.setModel(myFridge);
		fridgeControl.setView(myView);

		myView.addFridge(myFridge);
		myView.addController(fridgeControl);
		
		fridgeControl.startApplication();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		    	writeFridge();
		    }
		}));		
	}
	
	private static void writeFridge(){
		try{
			FileOutputStream fout = new FileOutputStream(FRIDGE_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(myFridge);
			oos.close();
		} catch (IOException ioe){
			System.err.print(ioe);
		}
	}
	
	private static void readFridge(){
		try{
			FileInputStream fin = new FileInputStream(FRIDGE_FILE);
			ObjectInputStream ois = new ObjectInputStream(fin);
			myFridge = (Fridge)ois.readObject();
			ois.close();
		}catch(IOException | ClassNotFoundException ioe){
			System.err.println(ioe);
			myFridge = new Fridge();
		}
	}
}
