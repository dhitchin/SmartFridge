package com.dhitchin.smartFridge.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.dhitchin.smartFridge.view.Login;

public class LoginController extends KeyAdapter implements ActionListener {
	private String passcode;
	private boolean isCorrect = false;
	Login login;
	
	public LoginController(String pc){
		passcode = pc;
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
