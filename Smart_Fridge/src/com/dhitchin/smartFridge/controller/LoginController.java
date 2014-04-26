package com.dhitchin.smartFridge.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.dhitchin.smartFridge.model.Fridge;
import com.dhitchin.smartFridge.view.Login;

public class LoginController extends KeyAdapter implements ActionListener {
	private String passcode;
	private String adminPasscode;
	private boolean isCorrect = false;
	private boolean isAdmin = false;
	Login login;
	Fridge theFridge;
	
	public boolean isAdmin(){
		return isAdmin;
	}
	
	public void setFridge(Fridge f){
		theFridge = f;
	}
	
	public LoginController(String pc, String adminPc){
		passcode = pc;
		adminPasscode = adminPc;
	}
	
	public void setView(Login l){
		login = l;
	}
	
	public boolean incorrectPasscode(){
		return !isCorrect;
	}
	
	private void checkPasscode(){
		String enteredPasscode = login.getPasscode();
		isCorrect = enteredPasscode.equals(passcode);
		isAdmin = enteredPasscode.equals(adminPasscode);
		if(isAdmin){
			theFridge.setAdmin(true);
			isCorrect = true;
		}
		if(isCorrect){
			login.startProgram();
		} else {
			login.clearPasscode();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent ke){
		if(ke.getKeyCode() == KeyEvent.VK_ENTER) checkPasscode();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Enter"){
			checkPasscode();
		} else {
			login.appendPasscode(e.getActionCommand());
		}
	}

}
