import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.*;

public class ChangePassword {
	
	jdbcmethods jm = new jdbcmethods();
	JFrame changePasswordFrame = new JFrame("PHARMACY GUI");
	JLabel lbUsername, lbCurrentPassword, lbNewPassword, lbConfirmNewPassword, matchPasswords, wrongDetails; 
	JTextField tfUserName;
	JPasswordField tfCurrentPassword, tfNewPassword, tfConfirmNewPassword;
	JButton confirm, cancel;
	
	ChangePassword()
	{
		lbUsername = new JLabel("Username: ", SwingConstants.LEFT);
		lbCurrentPassword = new JLabel("Current Password: ", SwingConstants.LEFT);
		lbNewPassword = new JLabel("New Password: ", SwingConstants.LEFT);
		lbConfirmNewPassword = new JLabel("Confirm New Password: ", SwingConstants.LEFT);
		matchPasswords = new JLabel();
		wrongDetails = new JLabel();
		tfUserName = new JTextField();
		tfCurrentPassword = new JPasswordField();
		tfNewPassword = new JPasswordField();
		tfConfirmNewPassword = new JPasswordField();
		confirm = new JButton("CONFIRM");
		cancel = new JButton("CANCEL");
		
		wrongDetails.setBounds(265,20,280,20);
		wrongDetails.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		wrongDetails.setForeground(Color.red);
		wrongDetails.setBackground(Color.pink);
		
		
		matchPasswords.setBounds(425,330,200,20);
		matchPasswords.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		matchPasswords.setForeground(Color.red);
		matchPasswords.setBackground(Color.pink);
		
		lbUsername.setBounds(125,45,160,60);
		lbUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		lbCurrentPassword.setBounds(125,125,160,60);
		lbCurrentPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		lbNewPassword.setBounds(125,205,160,60);
		lbNewPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		lbConfirmNewPassword.setBounds(125,285,250,60);
		lbConfirmNewPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		
		
		tfUserName.setBounds(425,50,200,40);
		tfUserName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		tfCurrentPassword.setBounds(425,130,200,40);
		tfCurrentPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		tfNewPassword.setBounds(425,210,200,40);
		tfNewPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		tfConfirmNewPassword.setBounds(425,290,200,40);
		tfConfirmNewPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfConfirmNewPassword.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				String cnfnewpw = new String(tfConfirmNewPassword.getPassword());
				String newpw = new String(tfNewPassword.getPassword());
				
				if(cnfnewpw.equals(newpw))
				{
					matchPasswords.setText("Passwords Matched!");
					matchPasswords.setVisible(true);
				}
				else
				{
					matchPasswords.setText("Passwords DONOT Match!");
					matchPasswords.setVisible(true);
				}
			}
			public void keyTyped(KeyEvent e) {}
		});
		
		confirm.setBounds(410,370,100,40);
		confirm.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					if( jm.accountCheck (tfUserName.getText(), new String (tfCurrentPassword.getPassword())) && Arrays.equals(tfNewPassword.getPassword(), tfConfirmNewPassword.getPassword()) )
					{
						//jm.changePassword(tfUserName.getText(), new String (tfNewPassword.getPassword()));
						
						wrongDetails.setText("Password changed!!!");
						wrongDetails.setVisible(true);
						
						cancel.setText("GO BACK");
					}
					else
					{
						wrongDetails.setText("Incorrect Credentials!");
						wrongDetails.setVisible(true);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		cancel.setBounds(240,370,100,40);
		cancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				changePasswordFrame.dispose();
			}
		});
		
		
		changePasswordFrame.add(lbUsername);
		changePasswordFrame.add(lbCurrentPassword);
		changePasswordFrame.add(lbNewPassword);
		changePasswordFrame.add(lbConfirmNewPassword);
		changePasswordFrame.add(tfUserName);
		changePasswordFrame.add(tfCurrentPassword);
		changePasswordFrame.add(tfNewPassword);
		changePasswordFrame.add(tfConfirmNewPassword);
		changePasswordFrame.add(matchPasswords);
		changePasswordFrame.add(wrongDetails);
		changePasswordFrame.add(confirm);
		changePasswordFrame.add(cancel);

		changePasswordFrame.setIconImage(new ImageIcon("Pharmacy.png").getImage());

		changePasswordFrame.getRootPane().setDefaultButton(confirm);
		changePasswordFrame.setSize(720,480);
		changePasswordFrame.getContentPane().setBackground(Color.pink);
		changePasswordFrame.setLocationRelativeTo(null);
		changePasswordFrame.setLayout(null);
		changePasswordFrame.setVisible(true);
		changePasswordFrame.setResizable(false);
		
	}
	

}
