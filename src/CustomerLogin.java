import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CustomerLogin {
	
	jdbcmethods jm = new jdbcmethods();
	JFrame customerLogin = new JFrame("Customer LogIn");
	JLabel lphNum, loggin, wanttosign;
	JTextField tfphNum;
	JButton proceed, signup, cancel;

	CustomerLogin(String username)
	{
		lphNum = new JLabel("Enter Your Phone number");
		lphNum.setBounds(100,100,160,60);
		lphNum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		loggin = new JLabel("Logging you in!!");
		loggin.setBounds(210,50,160,60);
		loggin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		loggin.setVisible(false);
		wanttosign = new JLabel("Your number is not registered, do you want to register?");
		wanttosign.setBounds(110,150,350,30);
		wanttosign.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		wanttosign.setVisible(false);
		
		tfphNum = new JTextField("Enter Phone Number");
		tfphNum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tfphNum.setBounds(300, 115, 160, 30);
		tfphNum.addFocusListener((FocusListener) new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
				if(tfphNum.getText().equals("Enter Phone Number"))
				{
					tfphNum.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				if(tfphNum.getText().equals(""))
				{
					tfphNum.setText("Enter Phone Number");
				}
			}
		});
		
		proceed = new JButton("Continue");
		proceed.setBounds(300, 200, 110, 30);
		proceed.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if(jm.customerCheck(tfphNum.getText()))
					{
						signup.setVisible(false);
						new BillingPage(username,tfphNum.getText());
						customerLogin.dispose();
						
					}
					else
					{
						jm.createCustomer(tfphNum.getText());
						signup.setVisible(true);
						wanttosign.setVisible(true);
						proceed.setFont(new Font("Times New Roman", Font.BOLD, 10));
						proceed.setText("Continue except");
						
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		signup = new JButton("Register");
		signup.setVisible(false);
		signup.setBounds(225,250,110,30);
		signup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomerSignup(username, tfphNum.getText());
				customerLogin.dispose();
			}});
		
		
		cancel = new JButton("Cancel");
		cancel.setBounds(150,200,110,30);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				customerLogin.dispose();
				try {
					new LoggedInPage(username);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});

		
		customerLogin.add(lphNum);
		customerLogin.add(loggin);
		customerLogin.add(wanttosign);
		customerLogin.add(tfphNum);
		customerLogin.add(proceed);
		customerLogin.add(cancel);
		customerLogin.add(signup);
		
		customerLogin.setIconImage(new ImageIcon("Pharmacy.png").getImage());

		
		customerLogin.getRootPane().setDefaultButton(proceed);
		customerLogin.setSize(580,360);
		customerLogin.getContentPane().setBackground(Color.decode("#ADD8E6"));
		customerLogin.setLocationRelativeTo(null);
		customerLogin.setLayout(null);
		customerLogin.setVisible(true);
		customerLogin.setResizable(false);
		
		
	}
}
