import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Accounts {

	jdbcmethods jm = new jdbcmethods();
	JFrame loggedInFrame = new JFrame("Accounts");
	JLabel heading, sidePic, dispUser;
	JButton purchase, stock, sales, settings;
	JMenuItem logOut, changePw, adminJobs, userManagement;
	JPopupMenu pm = new  JPopupMenu("SettingsPopUp");
	

	
	Accounts(String username)
	{
		heading = new JLabel(new ImageIcon("PharmacyM.png"));
		sidePic = new JLabel(new ImageIcon("Accounts.png"));
		purchase = new JButton("PURCHASE");
		stock = new JButton("STOCK");
		sales = new JButton("SALES");
		dispUser = new JLabel("Welcome "+username+"!!!");
		
		
		heading.setBounds(0,0,1080,80);
		
		sidePic.setBounds(720,80,360,645);
		
		dispUser.setForeground(Color.red);
		dispUser.setBackground(Color.decode("#ADD8E6"));
		dispUser.setOpaque(true);
		dispUser.setBounds(110,80,500,30);
		dispUser.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		
		stock.setBounds(280,260,160,60);
		stock.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		stock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					new Stock(username);
					loggedInFrame.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}});
		
		purchase.setBounds(280,360,160,60);
		purchase.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		purchase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loggedInFrame.dispose();
				try {
					new Purchase(username);
					loggedInFrame.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}});
		
		sales.setBounds(280,460,160,60);
		sales.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		sales.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Sales(username);
					loggedInFrame.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}});
		JButton home, back;
		
		home = new JButton(new ImageIcon("home.png"));
		back = new JButton(new ImageIcon("back.png"));
		
		home.setBounds(10,90,30,30);
		home.setBorder(BorderFactory.createEmptyBorder());
		home.setContentAreaFilled(false);
		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loggedInFrame.dispose();
				new LoggedInPage(username);
			}});
		back.setBounds(50,90,30,30);
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setContentAreaFilled(false);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loggedInFrame.dispose();
				new LoggedInPage(username);
			}});
		
		loggedInFrame.add(heading);
		loggedInFrame.add(sidePic);
		loggedInFrame.add(purchase);
		loggedInFrame.add(stock);
		loggedInFrame.add(sales);
		loggedInFrame.add(dispUser);
		loggedInFrame.add(home);
		loggedInFrame.add(back);
		loggedInFrame.setIconImage(new ImageIcon("Pharmacy.png").getImage());
		
		loggedInFrame.setSize(1080,720);
		loggedInFrame.getContentPane().setBackground(Color.decode("#ADD8E6"));
		loggedInFrame.setLocationRelativeTo(null);
		loggedInFrame.setLayout(null);
		loggedInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loggedInFrame.setVisible(true);
		loggedInFrame.setResizable(false);
	}
	
	
}
