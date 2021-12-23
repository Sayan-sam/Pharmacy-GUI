import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartPage implements ActionListener{
	
	jdbcmethods jm = new jdbcmethods();
	JFrame startFrame = new JFrame("PHARMACY GUI");
	JButton logIn;
	JLabel heading, lbUserId, lbPassword, lbVerify;
	JTextField tfUserId;
	JPasswordField tfPassword;
	
	StartPage()
	{
		heading = new JLabel(new ImageIcon("Welcome.png"));
		logIn = new JButton("Log In");
		lbUserId = new JLabel("UserId: ", SwingConstants.CENTER);
		lbPassword = new JLabel("Password: ", SwingConstants.CENTER);
		tfUserId = new JTextField();
		tfPassword = new JPasswordField();
		lbVerify = new JLabel("Incorrect username or Password...", SwingConstants.CENTER);
		
		
		
		heading.setBounds(0,0,1080,80);
		
		logIn.setBounds(460,500,160,60);
		logIn.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		logIn.addActionListener(this);
		
		lbUserId.setBounds(330,250,160,60);
		lbUserId.setFont(new Font("Times New Roman", Font.PLAIN, 22));

		lbPassword.setBounds(330,350,160,60);
		lbPassword.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		
		tfUserId.setBounds(570,260,160,40);
		tfUserId.setFont(new Font("Times New Roman", Font.PLAIN, 22));

		tfPassword.setBounds(570,360,160,40);
		tfPassword.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		
		lbVerify.setBounds(390,400,300,60);
		lbVerify.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbVerify.setForeground(Color.red);
		lbVerify.setVisible(false);
		
		startFrame.add(heading);
		startFrame.add(logIn);
		startFrame.add(lbUserId);
		startFrame.add(lbPassword);
		startFrame.add(tfUserId);
		startFrame.add(tfPassword);
		startFrame.add(lbVerify);
			
		startFrame.setIconImage(new ImageIcon("Pharmacy.png").getImage());

		startFrame.setSize(1080,720);
		startFrame.getRootPane().setDefaultButton(logIn);
		startFrame.getContentPane().setBackground(Color.decode("#ADD8E6"));
		startFrame.setLocationRelativeTo(null);
		startFrame.setLayout(null);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setVisible(true);
		startFrame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try 
		{
			if(jm.accountCheck(tfUserId.getText(), new String(tfPassword.getPassword())))
			{
				startFrame.dispose();
				new LoggedInPage(tfUserId.getText());
			}
			else
			{
				lbVerify.setVisible(true);
			}
		} 
		catch (Exception e1) { e1.printStackTrace();}
	}


}
