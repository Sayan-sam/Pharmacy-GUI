import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoggedInPage {

	jdbcmethods jm = new jdbcmethods();
	JFrame loggedInFrame = new JFrame("PHARMACY GUI");
	JLabel heading, sidePic, dispUser;
	JButton purchase, billing, accounts, settings;
	JMenuItem logOut, changePw, adminPurchase, adminSales, userManagement;
	JPopupMenu pm = new  JPopupMenu("SettingsPopUp");
	
	
	LoggedInPage(String username)
	{
		heading = new JLabel("HERE WILL GO THE HEADING", SwingConstants.CENTER);
		sidePic = new JLabel("Here will go the demonstration pic", SwingConstants.CENTER);
		settings = new JButton("SETTINGS");
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
					new AdminPurchase(username);
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
					new AdminSales(username);
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
					new ManageUsers(username);
					
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
		
		
		heading.setBackground(Color.blue);
		heading.setOpaque(true);
		heading.setBounds(0,0,1080,80);
		
		sidePic.setBackground(Color.red);
		sidePic.setOpaque(true);
		sidePic.setBounds(720,80,360,645);
		
		dispUser.setForeground(Color.red);
		dispUser.setBackground(Color.pink);
		dispUser.setOpaque(true);
		dispUser.setBounds(10,80,500,30);
		dispUser.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		settings.setBounds(640,80,80,30);
		settings.setFont(new Font("Times New Roman", Font.PLAIN, 9));
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				pm.show(settings, 0, 30);
			}
		});
		
		billing.setBounds(280,260,160,60);
		billing.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		billing.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new CustomerLogin(username);
				loggedInFrame.dispose();
				
			}});
		
		purchase.setBounds(280,360,160,60);
		purchase.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		purchase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new BuyingPage(username);
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
				new Accounts(username);
				loggedInFrame.dispose();
			}
			
		});
		
		loggedInFrame.add(heading);
		loggedInFrame.add(sidePic);
		loggedInFrame.add(settings);
		loggedInFrame.add(purchase);
		loggedInFrame.add(billing);
		loggedInFrame.add(accounts);
		loggedInFrame.add(dispUser);
		loggedInFrame.setIconImage(new ImageIcon("Pharmacy.png").getImage());

		
		loggedInFrame.setSize(1080,720);
		loggedInFrame.getContentPane().setBackground(Color.pink);
		loggedInFrame.setLocationRelativeTo(null);
		loggedInFrame.setLayout(null);
		loggedInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loggedInFrame.setVisible(true);
		loggedInFrame.setResizable(false);
	}
}
