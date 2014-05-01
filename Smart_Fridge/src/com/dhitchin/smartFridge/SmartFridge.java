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
		
	
	public static void main(String[] args) {
		Disp myView = new Disp();
		
		try{
			FileInputStream fin = new FileInputStream("fridge.dat");
			ObjectInputStream ois = new ObjectInputStream(fin);
			myFridge = (Fridge)ois.readObject();
			ois.close();
		}catch(IOException | ClassNotFoundException ioe){
			System.err.println(ioe);
			myFridge = new Fridge();
		}
		
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
			FileOutputStream fout = new FileOutputStream("fridge.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(myFridge);
			oos.close();
		} catch (IOException ioe){
			System.err.print(ioe);
		}
	}
}
