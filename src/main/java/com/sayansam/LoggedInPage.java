package com.sayansam;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoggedInPage {

	static String username;
	jdbcmethods jm = new jdbcmethods();
	JFrame loggedInFrame = new JFrame("PHARMACY MANAGEMENT");
	JLabel heading, sidePic, dispUser;
	JButton purchase, billing, accounts, settings;
	JMenuItem logOut, changePw, adminPurchase, adminSales, userManagement;
	JPopupMenu pm = new  JPopupMenu("SettingsPopUp");
	
	
	LoggedInPage(String username)
	{
		LoggedInPage.username = username;
		heading = new JLabel(new ImageIcon("pharmacygui/resources/images/PharmacyM.png"));
		sidePic = new JLabel(new ImageIcon("pharmacygui/resources/images/LoggedIn.png"));
		settings = new JButton(new ImageIcon("pharmacygui/resources/images/Settings.png"));
		purchase = new JButton("BUY MEDS");
		billing = new JButton("BILLING");
		accounts = new JButton("ACCOUNTS");
		dispUser = new JLabel("Welcome "+username+"!!!");
		logOut = new JMenuItem("Log Out", SwingConstants.CENTER);
		changePw = new JMenuItem("Change Password", SwingConstants.CENTER);
		adminPurchase = new JMenuItem("Admin Purchases", SwingConstants.CENTER);
		adminSales = new JMenuItem("Admin Sales", SwingConstants.CENTER);
		userManagement = new JMenuItem("User Management", SwingConstants.CENTER);
	
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				loggedInFrame.dispose();
				new StartPage();
			}
		});
		
		changePw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2)
			{
				new ChangePassword();
			}
		});
		
		adminPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2)
			{
				try {
					new AdminPurchase(LoggedInPage.username);
					loggedInFrame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		adminSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2)
			{
				try {
					new AdminSales(LoggedInPage.username);
					loggedInFrame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		userManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2)
			{
				try {
					loggedInFrame.dispose();
					new ManageUsers(LoggedInPage.username);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try
		{
			if(jm.getType(username).equals("Admin"))
			{
				pm.add(adminPurchase);
				pm.add(adminSales);
				pm.add(userManagement);
			}
		}
		catch(Exception e) {}
		pm.add(changePw);
		pm.add(logOut);
		
		
		heading.setBounds(0,0,1080,80);
		
		sidePic.setBounds(720,80,360,645);
		
		dispUser.setForeground(Color.red);
		dispUser.setBackground(Color.decode("#ADD8E6"));
		dispUser.setOpaque(true);
		dispUser.setBounds(10,80,500,30);
		dispUser.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		settings.setBounds(640,90,32,32);
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				pm.show(settings, 0, 32);
			}
		});
		
		billing.setBounds(280,260,160,60);
		billing.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		billing.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new CustomerLogin(LoggedInPage.username);
				loggedInFrame.dispose();
				
			}});
		
		purchase.setBounds(280,360,160,60);
		purchase.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		purchase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new BuyingPage(LoggedInPage.username);
					loggedInFrame.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}});
		
		accounts.setBounds(280,460,160,60);
		accounts.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		accounts.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Accounts(LoggedInPage.username);
				loggedInFrame.dispose();
			}
			
		});
		settings.setBorder(BorderFactory.createEmptyBorder());
		settings.setContentAreaFilled(false);
		
		loggedInFrame.add(heading);
		loggedInFrame.add(sidePic);
		loggedInFrame.add(settings);
		loggedInFrame.add(purchase);
		loggedInFrame.add(billing);
		loggedInFrame.add(accounts);
		loggedInFrame.add(dispUser);
		loggedInFrame.setIconImage(new ImageIcon("pharmacygui/resources/images/Pharmacy.png").getImage());

		
		loggedInFrame.setSize(1080,720);
		loggedInFrame.getContentPane().setBackground(Color.decode("#ADD8E6"));
		loggedInFrame.setLocationRelativeTo(null);
		loggedInFrame.setLayout(null);
		loggedInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loggedInFrame.setVisible(true);
		loggedInFrame.setResizable(false);
	}
}
