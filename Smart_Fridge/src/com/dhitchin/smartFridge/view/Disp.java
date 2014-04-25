package com.dhitchin.smartFridge.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.dhitchin.smartFridge.controller.FridgeController;

public class Disp implements Observer{
	private JFrame frmSmartfridge;
	JLabel lblExpDate;
	JLabel lblCurrThreshold;
	JLabel lblCurrAmt;
	JLabel lblCurrName;
	JButton btnUpdateItem, btnEmailShoppingList, btnDisplayShoppingList, btnAddNewItem;
	JList<String> itemList;
	
	public JFrame getFrame(){
		return frmSmartfridge;
	}
	
	public String getSelectedItem(){
		return itemList.getSelectedValue();
	}

	/**
	 * Create the application.
	 */
	public Disp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSmartfridge = new JFrame();
		frmSmartfridge.setTitle("SmartFridge");
		frmSmartfridge.setBounds(100, 100, 380, 480);
		frmSmartfridge.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSmartfridge.setMinimumSize(new Dimension(300, 450));
		
		itemList = new JList<String>();
		itemList.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblItemList = new JLabel("Items in Fridge");
		lblItemList.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemList.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JPanel panelSelectedItem = new JPanel();
		panelSelectedItem.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Selected Item", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		btnUpdateItem = new JButton("Update Item");
		
		btnEmailShoppingList = new JButton("Email Shopping List");
		
		btnDisplayShoppingList = new JButton("Display Shopping List");
		
		btnAddNewItem = new JButton("Add New Item");
		GroupLayout groupLayout = new GroupLayout(frmSmartfridge.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblItemList, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(itemList, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelSelectedItem, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addComponent(btnDisplayShoppingList, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
						.addComponent(btnEmailShoppingList, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAddNewItem, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
						.addComponent(btnUpdateItem, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblItemList)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(itemList, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(panelSelectedItem, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUpdateItem)
							.addGap(18)
							.addComponent(btnAddNewItem)
							.addGap(18)
							.addComponent(btnDisplayShoppingList)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEmailShoppingList)
							.addGap(6)))
					.addContainerGap())
		);
		
		panelSelectedItem.setLayout(new BoxLayout(panelSelectedItem, BoxLayout.Y_AXIS));
		JLabel lblName = new JLabel("Name:");
		
		JLabel lblCurrentAmount = new JLabel("Current Amount:");
		
		panelSelectedItem.add(lblName);
		
		lblCurrName = new JLabel("");
		panelSelectedItem.add(lblCurrName);
		
		panelSelectedItem.add(Box.createRigidArea(new Dimension(0, 5)));
		panelSelectedItem.add(lblCurrentAmount);
		
		lblCurrAmt = new JLabel("");
		panelSelectedItem.add(lblCurrAmt);
		
		panelSelectedItem.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JLabel lblThreshold = new JLabel("Threshold:");
		panelSelectedItem.add(lblThreshold);
		
		lblCurrThreshold = new JLabel("");
		panelSelectedItem.add(lblCurrThreshold);
		
		panelSelectedItem.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JLabel lblExpirationDate = new JLabel("Expiration Date:");
		panelSelectedItem.add(lblExpirationDate);
		
		lblExpDate = new JLabel("");
		panelSelectedItem.add(lblExpDate);
		frmSmartfridge.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmSmartfridge.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmAdminOptions = new JMenuItem("Admin Options");
		mnFile.add(mntmAdminOptions);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
	}
	
	public void updateSelectedItem(String name, String expDate, double amount, double threshold){
		lblCurrName.setText(name);
		lblExpDate.setText(expDate);
		lblCurrAmt.setText(""+amount);
		lblCurrThreshold.setText(""+threshold);
	}
	
	public void update(Observable obs, Object obj){
		if(obj instanceof String[]) itemList.setListData((String[])obj);
	}
	
	public void addController(FridgeController controller){
		btnAddNewItem.addActionListener(controller);
		btnDisplayShoppingList.addActionListener(controller);
		btnEmailShoppingList.addActionListener(controller);
		btnUpdateItem.addActionListener(controller);
		itemList.addListSelectionListener(controller);
	}
}
