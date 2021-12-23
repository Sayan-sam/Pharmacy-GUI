import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class ManageUsers {
	

	jdbcmethods jm = new jdbcmethods();
	JFrame stock = new JFrame("User Management");
	JLabel heading, sidePic, stockList, lusername;
	JLabel lName, lDOB, lStatus, lType, lDOJ;
	JButton Update, Delete, Deactivate, Reset;
	JComboBox<?> username;
	String users[];
	
	ManageUsers(String adminusername) throws Exception
	{
		int i;
		for(i = 1; i > 0; i++)
		{
			if(jm.getUsername(i) == null)
				break;
			
		}
		
		users = new String[i];
		users[0] = "Select Username";
		
		for(int j = 1; j<i ; j++)
		{
			users[j] = jm.getUsername(j);
		}
		
		username = new JComboBox<Object>(users);
		username.setBounds(80, 220, 160, 40);
		username.setFont(new Font("Times New Roman", Font.BOLD, 14));
		username.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				
				if(!username.getItemAt(username.getSelectedIndex()).equals("Select Username"))
				{
					
					try
					{
						jm.setAccount((String) username.getItemAt(username.getSelectedIndex()));
						
						lName.setText("Name: "+jm.accountName);
						lType.setText("Type: "+jm.accountType);
						lStatus.setText("Status: "+jm.accountStatus);
						lDOB.setText("Birthday: "+jm.accountDOB);
						lDOJ.setText("Date of Join: "+jm.accountDOJ);
						
						if(jm.accountStatus.equals("Active"))
						{
							Deactivate.setText("Deactivate");
						}
						else
						{
							Deactivate.setText("Activate");
						}
						
						lusername.setVisible(true);
						lName.setVisible(true);
						lStatus.setVisible(true);
						lType.setVisible(true);
						lDOB.setVisible(true);
						lDOJ.setVisible(true);
						Update.setVisible(true);
						Reset.setVisible(true);
						Delete.setVisible(true);
						Deactivate.setVisible(true);
						
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				else
				{
					lusername.setVisible(false);
					lName.setVisible(false);
					lStatus.setVisible(false);
					lType.setVisible(false);
					lDOB.setVisible(false);
					lDOJ.setVisible(true);
					Update.setVisible(false);
					Reset.setVisible(false);
					Delete.setVisible(false);
					Deactivate.setVisible(false);
				}
				
			}
			
		});
		
		lusername = new JLabel("Username");		
		lusername.setBounds(83, 200, 160, 20);
		lusername.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		lName = new JLabel();
		lName.setBounds(80, 300, 200, 20);
		lName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lDOB = new JLabel();
		lDOB.setBounds(80, 380, 200, 20);
		lDOB.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lStatus = new JLabel();
		lStatus.setBounds(420, 300, 200, 20);
		lStatus.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lType = new JLabel();
		lType.setBounds(420, 380, 200, 20);
		lType.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lDOJ = new JLabel();
		lDOJ.setBounds(420, 220, 160, 40);
		lDOJ.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		
		
		Update = new JButton("Create New Account");
		Update.setBounds(80, 500, 200, 40);
		Update.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stock.dispose();
				new CreateUser(adminusername);
			}
			
		});
		
		Delete = new JButton("Delete");
		Delete.setBounds(80, 580, 200, 40);
		Delete.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					jm.deleteAccount((String) username.getItemAt(username.getSelectedIndex()));
					stock.dispose();
					new ManageUsers(adminusername);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			
		});
		
		Deactivate = new JButton();
		Deactivate.setBounds(420, 500, 200, 40);
		Deactivate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Deactivate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					jm.changeStatus((String) username.getItemAt(username.getSelectedIndex()));
					username.setSelectedIndex(0);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			
		});
		
		Reset = new JButton("Reset Pass");
		Reset.setBounds(420, 580, 200, 40);
		Reset.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					jm.changePassword((String) username.getItemAt(username.getSelectedIndex()), "0000");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		
		stock.getContentPane().setLayout(new FlowLayout());
		heading = new JLabel(new ImageIcon("PharmacyM.png"));
		sidePic = new JLabel(new ImageIcon("User.png"));
		stockList = new JLabel("Manage Users", SwingConstants.CENTER);
		
		stockList.setBounds(220,80,300,60);
		stockList.setFont(new Font("Times New Roman", Font.PLAIN,22));
		
		
		heading.setBounds(0,0,1080,80);
		
		sidePic.setBounds(720,80,360,645);
		
		lusername.setVisible(false);
		lName.setVisible(false);
		lStatus.setVisible(false);
		lType.setVisible(false);
		lDOB.setVisible(false);
		Update.setVisible(false);
		Reset.setVisible(false);
		Delete.setVisible(false);
		Deactivate.setVisible(false);
		
		stock.add(heading);
		stock.add(sidePic);
		stock.add(stockList);
		stock.add(username);
		stock.add(lusername);
		stock.add(lName);
		stock.add(lType);
		stock.add(lStatus);
		stock.add(lDOB);
		stock.add(Delete);
		stock.add(Deactivate);
		stock.add(Reset);
		stock.add(Update);
		stock.add(lDOJ);
		

		stock.setIconImage(new ImageIcon("Pharmacy.png").getImage());

		JButton home, back;

		home = new JButton(new ImageIcon("home.png"));
		back = new JButton(new ImageIcon("back.png"));


		home.setBounds(10,90,30,30);
		home.setBorder(BorderFactory.createEmptyBorder());
		home.setContentAreaFilled(false);
		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stock.dispose();
				new LoggedInPage(adminusername);
			}});
		back.setBounds(50,90,30,30);
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setContentAreaFilled(false);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stock.dispose();
				new LoggedInPage(adminusername);
			}});

		stock.add(home);
		stock.add(back);
		
		stock.setSize(1080,720);
		stock.getContentPane().setBackground(Color.pink);
		stock.setLocationRelativeTo(null);
		stock.setLayout(null);
		stock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stock.setVisible(true);
		stock.setResizable(false);
	}
}
