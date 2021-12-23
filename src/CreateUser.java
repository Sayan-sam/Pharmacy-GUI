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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class CreateUser {

	jdbcmethods jm = new jdbcmethods();
	JFrame customerSignup = new JFrame();
	JLabel lName, doB, lusername, lpassword;
	JLabel Namempt, dobempt, usernamempt, passempt, typempt;
	JTextField tfName, tdoB, tfusername, tfpass;
	JButton proceed, cancel;
	UtilDateModel model = new UtilDateModel();
	JComboBox<?> Type;
	
	
	CreateUser(String username)
	{
		String type[] = {"Select Type","Admin","User"};
		Type = new JComboBox<Object>(type);
		Type.setBounds(180,250,160,30);
		Type.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		lName = new JLabel("Enter Name:");
		lName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lName.setBounds(130,30,160,60);
		
		doB = new JLabel("Date of Birth: ");
		doB.setFont(new Font("Times New Roman", Font.BOLD, 14));
		doB.setBounds(130,80,160,60);
		
		lusername = new JLabel("Username: ");
		lusername.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lusername.setBounds(130,130,160,60);
		
		lpassword = new JLabel("Pasword: ");
		lpassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lpassword.setBounds(130,180,160,60);
		
		Namempt= new JLabel("Field cannot be empty!");
		Namempt.setForeground(Color.RED);
		Namempt.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		Namempt.setBounds(260,65,150,30);
		Namempt.setVisible(false);		
		
		dobempt = new JLabel("Field cannot be empty!");
		dobempt.setForeground(Color.RED);
		dobempt.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		dobempt.setBounds(260,115,150,30);
		dobempt.setVisible(false);		
		
		usernamempt = new JLabel("Field cannot be empty!");
		usernamempt.setForeground(Color.RED);
		usernamempt.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		usernamempt.setBounds(260,165,150,30);
		usernamempt.setVisible(false);
		
		passempt = new JLabel("Field cannot be empty!");
		passempt.setForeground(Color.RED);
		passempt.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		passempt.setBounds(260,215,150,30);
		passempt.setVisible(false);
		
		typempt = new JLabel("Field cannot be empty!");
		typempt.setForeground(Color.RED);
		typempt.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		typempt.setBounds(180,270,160,30);
		typempt.setVisible(false);

		tfName = new JTextField("Enter Name");
		tfName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tfName.setBounds(260,45,150,30);
		tfName.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(tfName.getText().equals("Enter Name"))
				{
					tfName.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(tfName.getText().equals(""))
				{
					tfName.setText("Enter Name");
				}				
			}
			
		});
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(260,95,150,30);
		
		
		tfusername = new JTextField("Enter Username");
		tfusername.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tfusername.setBounds(260,145,150,30);
		tfusername.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(tfusername.getText().equals("Enter Username"))
				{
					tfusername.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(tfusername.getText().equals(""))
				{
					tfusername.setText("Enter Username");
				}				
			}
			
		});
		
		tfpass= new JTextField("Enter Password");
		tfpass.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tfpass.setBounds(260,195,150,30);
		tfpass.setVisible(true);
		tfpass.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(tfpass.getText().equals("Enter Password"))
				{
					tfpass.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(tfpass.getText().equals(""))
				{
					tfpass.setText("Enter Password");
				}				
			}
			
		});

		
		proceed = new JButton("Continue");
		proceed.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		proceed.setBounds(280,320,100,30);
		proceed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try 
				{
					Namempt.setVisible(false);
					dobempt.setVisible(false);
					usernamempt.setVisible(false);
					passempt.setVisible(false);
					typempt.setVisible(false);
					
					if (!(tfName.getText().equals("Enter Name") ||  tfusername.getText().equals("Enter Username") || tfpass.getText().equals("Enter Password") || ((String)Type.getItemAt(Type.getSelectedIndex())).equals("Select Type")   ||  datePicker.getModel().getValue() == null)) {
				
						
						if (!jm.accountCheck(tfusername.getText())) {
							Date selectedDate = (Date) datePicker.getModel().getValue();
							SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
							jm.createAccount(tfName.getText(), ft.format(selectedDate), tfusername.getText(),
									tfpass.getText(), ((String)Type.getItemAt(Type.getSelectedIndex())));
							new ManageUsers(username);
							customerSignup.dispose();
						}
						else
							usernameMatch();
					}
					else
					{
						if(tfName.getText().equals("Enter Name")) 
						{
							Namempt.setVisible(true);
						}
						if(datePicker.getModel().getValue() == null) 
						{
							dobempt.setVisible(true);
						}
						if(tfusername.getText().equals("Enter Username")) 
						{
							usernamempt.setVisible(true);
						}
						if(tfpass.getText().equals("Enter Password")) 
						{
							passempt.setVisible(true);
						}
						if(((String)Type.getItemAt(Type.getSelectedIndex())).equals("Select Type"))
						{
							typempt.setVisible(true);
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
		cancel.setBounds(150,320,100,30);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				customerSignup.dispose();
				try {
					new ManageUsers(username);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		customerSignup.add(lName);
		customerSignup.add(doB);
		customerSignup.add(Namempt);
		customerSignup.add(dobempt);
		customerSignup.add(proceed);
		customerSignup.add(cancel);
		customerSignup.add(tfName);
		customerSignup.add(lusername);
		customerSignup.add(lpassword);
		customerSignup.add(usernamempt);
		customerSignup.add(passempt);
		customerSignup.add(tfusername);
		customerSignup.add(tfpass);
		customerSignup.add(Type);
		customerSignup.add(typempt);
		customerSignup.add(datePicker);
		
		

		
		customerSignup.setIconImage(new ImageIcon("Pharmacy.png").getImage());
		customerSignup.getRootPane().setDefaultButton(proceed);
		customerSignup.setSize(580,410);
		customerSignup.getContentPane().setBackground(Color.pink);
		customerSignup.setLocationRelativeTo(null);
		customerSignup.setLayout(null);
		customerSignup.setVisible(true);
		customerSignup.setResizable(false);
	}
	
	//Dialouge Box method for same username concurrency
	public void usernameMatch()
	{
		JFrame frame = new JFrame();
		JLabel error = new JLabel("Username Already Exist!!!");
		error.setBounds(20,30,150,30);
		JButton ok = new JButton("Ok");
		ok.setBounds(60,60,60,20);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}});
		
		frame.setIconImage(new ImageIcon("Pharmacy.png").getImage());

		frame.add(error);
		frame.add(ok);
		frame.setSize(200,150);
		frame.getContentPane().setBackground(Color.pink);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
