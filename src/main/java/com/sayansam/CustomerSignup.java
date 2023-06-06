package com.sayansam;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class CustomerSignup {

	static String username, number;
	jdbcmethods jm = new jdbcmethods();
	JFrame customerSignup = new JFrame("Customer SignUp");
	JLabel lFName, lLName, doB;
	JLabel FNamempt, LNameempt, dobempt;
	JTextField tfFName,tfLName, tdoB;
	JButton proceed, cancel;
	UtilDateModel model = new UtilDateModel();
	
	
	CustomerSignup(String username, String number)
	{
		CustomerSignup.username = username;
		CustomerSignup.number = number;	
		lFName = new JLabel("First Name:");
		lFName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lFName.setBounds(130,30,160,60);
		
		lLName = new JLabel("Last Name:");
		lLName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lLName.setBounds(130,80,160,60);

		doB = new JLabel("Date of Birth: ");
		doB.setFont(new Font("Times New Roman", Font.BOLD, 14));
		doB.setBounds(130,130,160,60);
		
		FNamempt= new JLabel("Field cannot be empty!");
		FNamempt.setForeground(Color.RED);
		FNamempt.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		FNamempt.setBounds(260,65,150,30);
		FNamempt.setVisible(false);		
		
		LNameempt = new JLabel("Field cannot be empty!");
		LNameempt.setForeground(Color.RED);
		LNameempt.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		LNameempt.setBounds(260,115,150,30);
		LNameempt.setVisible(false);		

		dobempt = new JLabel("Field cannot be empty!");
		dobempt.setForeground(Color.RED);
		dobempt.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		dobempt.setBounds(260,165,150,30);
		dobempt.setVisible(false);		

		tfFName = new JTextField("Enter first Name");
		tfFName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tfFName.setBounds(260,45,150,30);
		tfFName.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(tfFName.getText().equals("Enter first Name"))
				{
					tfFName.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(tfFName.getText().equals(""))
				{
					tfFName.setText("Enter first Name");
				}				
			}
			
		});
		
		tfLName = new JTextField("Enter last Name");
		tfLName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tfLName.setBounds(260,95,150,30);
		tfLName.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(tfLName.getText().equals("Enter last Name"))
				{
					tfLName.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(tfLName.getText().equals(""))
				{
					tfLName.setText("Enter last Name");
				}				
			}
			
		});
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(260,145,150,30);
		
		proceed = new JButton("Continue");
		proceed.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		proceed.setBounds(280,230,100,30);
		proceed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try 
				{
					FNamempt.setVisible(false);
					LNameempt.setVisible(false);
					dobempt.setVisible(false);
					if (!(tfFName.getText().equals("Enter first Name") || tfLName.getText().equals("Enter last Name") || datePicker.getModel().getValue() == null)) {
				
						
						Date selectedDate = (Date) datePicker.getModel().getValue();
						SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
						jm.updateCustomer(CustomerSignup.number, tfFName.getText(), tfLName.getText(),
						ft.format(selectedDate));
						System.out.print("Inside IF");
						new BillingPage(CustomerSignup.username, CustomerSignup.number);
						customerSignup.dispose();
					}
					else
					{
						if(tfFName.getText().equals("Enter first Name")) 
						{
							FNamempt.setVisible(true);
						}
						if(tfLName.getText().equals("Enter last Name"))
						{
							LNameempt.setVisible(true);
						}
						if(datePicker.getModel().getValue() == null) 
						{
							dobempt.setVisible(true);
						}
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			
		});
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cancel.setBounds(150,230,100,30);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				customerSignup.dispose();
				try {
					new BillingPage(CustomerSignup.username, CustomerSignup.number);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		customerSignup.add(lFName);
		customerSignup.add(lLName);
		customerSignup.add(doB);
		customerSignup.add(FNamempt);
		customerSignup.add(LNameempt);
		customerSignup.add(dobempt);
		customerSignup.add(proceed);
		customerSignup.add(cancel);
		customerSignup.add(tfFName);
		customerSignup.add(tfLName);
		customerSignup.add(datePicker);
		
		customerSignup.setIconImage(new ImageIcon("pharmacygui/resources/images/Pharmacy.png").getImage());

		
		customerSignup.getRootPane().setDefaultButton(proceed);
		customerSignup.setSize(580,360);
		customerSignup.getContentPane().setBackground(Color.decode("#ADD8E6"));
		customerSignup.setLocationRelativeTo(null);
		customerSignup.setLayout(null);
		customerSignup.setVisible(true);
		customerSignup.setResizable(false);
	}
}
