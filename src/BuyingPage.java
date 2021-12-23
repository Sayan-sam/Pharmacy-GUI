import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PrinterException;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class BuyingPage {

	jdbcmethods jm = new jdbcmethods();
	JFrame billingPage = new JFrame();
	JLabel heading, sidePic, lbmedName, lbquantity, lTotal;
	JLabel enterMedName, enterQuantity, stockExceeded;
	JTextField tfquantity;
	JComboBox<?> tfmedName;
	String medName[];
	JPopupMenu pm;
	JMenuItem searchName[];
	JButton add, confirm, addMed, updateMed;
	JScrollPane scrollableBill;
	JTable billTable;
	String billColumn[] = {"Sl. No.","Name","Type","Class", "Contains","Price","Quantity","Amount"};
	String billContent[][];
	int TotalCost = 0;
	
	//BuyingPage(){}
	
	
	BuyingPage(String username) throws Exception
	{
		billingPage.getContentPane().setLayout(new FlowLayout());
		billContent = new String[0][8];
		heading = new JLabel(new ImageIcon("PharmacyM.png"));
		sidePic = new JLabel("Here will go the demonstration pic", SwingConstants.CENTER);
		pm = new  JPopupMenu("Search Popup");
		lbmedName = new JLabel("Medicine Name");
		lbmedName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbquantity = new JLabel("Quantity");
		lbquantity.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfquantity = new JTextField("Enter Quantity");
		tfquantity.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lTotal = new JLabel("Total Amount: 0");
		lTotal.setFont(new Font("Times New Roman", Font.BOLD, 22));
		add = new JButton("ADD");
		add.setFont(new Font("Times New Roman", Font.BOLD, 14));
		confirm = new JButton("Confirm");
		confirm.setFont(new Font("Times New Roman", Font.BOLD, 14));
		addMed = new JButton("Add Medicine");
		updateMed = new JButton("Update Rate");
		
		enterMedName = new JLabel("Enter Medicine Name!!");
		enterMedName.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		enterMedName.setBounds(80,155,150,30);
		enterMedName.setForeground(Color.RED);
		enterMedName.setVisible(false);
		
		enterQuantity = new JLabel("Enter Stock Name!!");
		enterQuantity.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		enterQuantity.setBounds(330,155,150,30);
		enterQuantity.setForeground(Color.RED);
		enterQuantity.setVisible(false);
		
		addMed.setFont(new Font("Times New Roman", Font.BOLD, 14));
		addMed.setBounds(20,630,140,40);
		addMed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addNewMed(username);
			}
			
		});
		
		updateMed.setFont(new Font("Times New Roman", Font.BOLD, 14));
		updateMed.setBounds(180,630,140,40);
		updateMed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateMedicineRate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		
		String medName[] = new String[jm.getMedlen()+1];
		medName[0] = "Medicine Name";
		for(int i = 1 ; i <= jm.getMedlen() ; i++)
		{
			medName[i] = jm.getMed(i);
		}
		Arrays.sort(medName, 1, jm.getMedlen());
		tfmedName = new JComboBox<Object>(medName);
		tfmedName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		DefaultTableModel model = new DefaultTableModel(billContent, billColumn);
		
		billTable = new JTable(model) {
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }};
		    
		billTable.setBackground(Color.PINK);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		billTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		billTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		billTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		billTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		billTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		billTable.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		billTable.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		billTable.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
		billTable.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
		
		
		scrollableBill = new JScrollPane(billTable);
		scrollableBill.setBounds(10,190,700,420);
		scrollableBill.setBackground(Color.PINK);
		scrollableBill.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		scrollableBill.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
		heading.setBounds(0,0,1080,80);
		
		sidePic.setBackground(Color.red);
		sidePic.setOpaque(true);
		sidePic.setBounds(720,80,360,645);
		
		add.setBounds(550,130,80,30);
		
		lbmedName.setBounds(82,110,100,20);
		lbmedName.setVisible(false);
		
		lbquantity.setBounds(332,110,100,20);
		lbquantity.setVisible(false);
		
		tfmedName.setBounds(80,130,150,30);
		tfmedName.addItemListener((ItemListener) new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if(tfmedName.getItemAt(tfmedName.getSelectedIndex()).equals("Medicine Name"))
				{
				lbmedName.setVisible(false);
				}
				else
				{
				lbmedName.setVisible(true);
				}
				
			}
		});;
		
		
		
		tfquantity.setBounds(330,130,150,30);
		tfquantity.addFocusListener((FocusListener) new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
				if(tfquantity.getText().equals("Enter Quantity"))
				{
				tfquantity.setText("");
				lbquantity.setVisible(true);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				if(tfquantity.getText().equals(""))
				{
				tfquantity.setText("Enter Quantity");
				lbquantity.setVisible(false);
				}
			}
		});
		
		lTotal.setBounds(360,620,200,60);
		confirm.setBounds(580, 630, 100, 40);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					int trancId = jm.initTranc(username, "Company", "Buy");
					
					for(int i = 0; i<model.getRowCount();i++)
					{
						if (Integer.parseInt((String) billTable.getModel().getValueAt(i, 6)) != 0) {
							jm.addMed(trancId, (String) billTable.getModel().getValueAt(i, 1),
									Integer.parseInt((String) billTable.getModel().getValueAt(i, 6)));
						}
					}
					
					jm.updateTotalCost(trancId, TotalCost);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					boolean print = billTable.print();
					if(!print)
					{
						JOptionPane.showMessageDialog(null, "Unable to print!!");
					}
				} catch (PrinterException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
;			}
			
		});
		
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int flag = 0;
				int i;
				for(i = 0 ; i < model.getRowCount(); i++)
				{
					if(tfmedName.getItemAt(tfmedName.getSelectedIndex()).equals(model.getValueAt(i, 1)))
					{
						flag++;
						break;
					}
				}

			try {
					if(!(tfmedName.getItemAt(tfmedName.getSelectedIndex()).equals("Medicine Name") || tfquantity.getText().equals("Enter Quantity") || flag != 0))
					{
						enterMedName.setVisible(false);    
						enterQuantity.setVisible(false);
						
						model.addRow(new Object[]{model.getRowCount()+1,tfmedName.getItemAt(tfmedName.getSelectedIndex()) , jm.getMedType((String) tfmedName.getItemAt(tfmedName.getSelectedIndex())), jm.getMedClass((String) tfmedName.getItemAt(tfmedName.getSelectedIndex())),jm.getMedQuan((String) tfmedName.getItemAt(tfmedName.getSelectedIndex())), jm.getMedPrice((String) tfmedName.getItemAt(tfmedName.getSelectedIndex())), tfquantity.getText(), jm.getMedPrice((String) tfmedName.getItemAt(tfmedName.getSelectedIndex()))*Integer.parseInt(tfquantity.getText())});
					    TotalCost += jm.getMedPrice((String) tfmedName.getItemAt(tfmedName.getSelectedIndex()))*Integer.parseInt(tfquantity.getText());
						lTotal.setText("Total Amount: " + TotalCost);
						
					}
					else if(flag != 0 && !(tfmedName.getItemAt(tfmedName.getSelectedIndex()).equals("Medicine Name") || tfquantity.getText().equals("Enter Quantity")))
					{
						enterMedName.setVisible(false);    
						enterQuantity.setVisible(false);
						
						TotalCost -= jm.getMedPrice((String) tfmedName.getItemAt(tfmedName.getSelectedIndex()))*Integer.parseInt((String) model.getValueAt(i, 6));
						model.setValueAt(tfquantity.getText(), i, 6);
						model.setValueAt(jm.getMedPrice((String) tfmedName.getItemAt(tfmedName.getSelectedIndex()))*Integer.parseInt(tfquantity.getText()),i,7);
						TotalCost += jm.getMedPrice((String) tfmedName.getItemAt(tfmedName.getSelectedIndex()))*Integer.parseInt(tfquantity.getText());
						lTotal.setText("Total Amount: " + TotalCost);
					}
					else 
					{
						if(tfmedName.getItemAt(tfmedName.getSelectedIndex()).equals("Medicine Name"))
						{
							enterMedName.setVisible(true);
						}
						if(tfquantity.getText().equals("Enter Quantity"))
						{
							enterQuantity.setVisible(true);
						}
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		
		AutoCompleteDecorator.decorate(tfmedName);
		
		billingPage.add(heading);
		billingPage.add(sidePic);
		billingPage.add(lbmedName);
		billingPage.add(lbquantity);
		billingPage.add(tfmedName);
		billingPage.add(tfquantity);
		billingPage.add(add);
		billingPage.getContentPane().add(scrollableBill);
		billingPage.add(lTotal);
		billingPage.add(confirm);
		billingPage.add(enterMedName);
		billingPage.add(enterQuantity);
		billingPage.add(addMed);
		billingPage.add(updateMed);
		billingPage.getRootPane().setDefaultButton(add);

		billingPage.setIconImage(new ImageIcon("Pharmacy.png").getImage());

		JButton home, back;

		home = new JButton(new ImageIcon("home.png"));
		back = new JButton(new ImageIcon("back.png"));


		home.setBounds(10,90,30,30);
		home.setBorder(BorderFactory.createEmptyBorder());
		home.setContentAreaFilled(false);
		back.setBounds(50,90,30,30);
		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				billingPage.dispose();
				new LoggedInPage(username);
			}});
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setContentAreaFilled(false);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				billingPage.dispose();
				new LoggedInPage(username);
			}});

		billingPage.add(home);
		billingPage.add(back);
		
		billingPage.setSize(1080,720);
		billingPage.getContentPane().setBackground(Color.decode("#ADD8E6"));
		billingPage.setLocationRelativeTo(null);
		billingPage.setLayout(null);
		billingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		billingPage.setVisible(true);
		billingPage.setResizable(false);
	}
	
	public void updateMedicineRate() throws Exception
	{
		JFrame frame = new JFrame();
		JLabel rate = new JLabel("Rate: 0");
		JTextField newRate = new JTextField("Enter New Rate");
		String medName[] = new String[jm.getMedlen()+1];
		JButton next = new JButton("Continue");
		medName[0] = "Medicine Name";
		for(int i = 1 ; i <= jm.getMedlen() ; i++)
		{
			medName[i] = jm.getMed(i);
		}
		Arrays.sort(medName, 1, jm.getMedlen());
		JComboBox<?> medicines = new JComboBox<Object>(medName);	
		medicines.setBounds(30,30,160,30);
		medicines.addItemListener((ItemListener) new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if(medicines.getItemAt(medicines.getSelectedIndex()).equals("Medicine Name"))
				{
					rate.setText("Rate: 0");
				}
				else
				{
					try {
						rate.setText("Rate: "+jm.getMedPrice((String) medicines.getItemAt(medicines.getSelectedIndex())));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});;
		
		
		rate.setBounds(30,100,160,30);
		rate.setFont(new Font("Times New Roman", Font.BOLD, 22));
		
		
		newRate.setBounds(200,100,160,30);
		newRate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		newRate.addFocusListener((FocusListener) new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
				if(newRate.getText().equals("Enter New Rate") || newRate.getText().equals("Insert INTEGER!"))
				{
					newRate.setText("");

				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				if(newRate.getText().equals(""))
				{
					newRate.setText("Enter New Rate");
				}
			}
		});
		
		next.setBounds(250,30,100,30);
		next.setFont(new Font("Times New Roman", Font.BOLD, 12));
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!medicines.getItemAt(medicines.getSelectedIndex()).equals("Medicine Name")) {
					try {
						jm.updateMedRate((String) medicines.getItemAt(medicines.getSelectedIndex()),
								Integer.parseInt(newRate.getText()));
						frame.dispose();
					} catch (NumberFormatException e1) {

						newRate.setText("Insert INTEGER!");
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				}
			}
			
		});
		
		frame.add(medicines);
		frame.add(rate);
		frame.add(newRate);
		frame.add(next);
		
		frame.setIconImage(new ImageIcon("Pharmacy.png").getImage());

		frame.setSize(400,200);
		frame.getContentPane().setBackground(Color.pink);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		
	}

	public void addNewMed(String username)
	{
		JButton proceed = new JButton("Continue");
		JButton cancel = new JButton ("Cancel");
		
		String typeName[] = {"Select Type Name","Capsule","Drops","Injection","Liquid","Tablet","Topical"};
		String className[] = {"Select Class Name","Antacids","Antifertility","Antihistamines","Antimicrobals","Covid-19 Vacc.","Neurological"};
		JFrame frame = new JFrame("Add New Med");
		JLabel lname = new JLabel("Enter Name:");
		JLabel lprice = new JLabel("Enter Price: ");
		JLabel lamount = new JLabel("Enter Amount: ");
		JLabel ltype = new JLabel("Select Type:");
		JLabel lclass = new JLabel("Select Class: ");
		JLabel present = new JLabel("Medicine Already Present!!");
		
		JTextField tfname = new JTextField("Enter Name");

		JTextField tfprice = new JTextField("Enter Price");
		
		JTextField tfamount = new JTextField("Enter Amount");
		
		JComboBox<?> cbtype = new JComboBox<Object>(typeName);
		
		JComboBox<?> cbclass = new JComboBox<Object>(className);
		
		
		JLabel lnamee = new JLabel("Field cannot be empty");
		JLabel lpricee = new JLabel("Field cannot be empty");
		JLabel lamounte = new JLabel("Field cannot be empty");
		JLabel ltypee = new JLabel("Field cannot be empty");
		JLabel lclasse = new JLabel("Field cannot be empty");
		
		present.setBounds(200, 0, 160, 60);
		present.setFont(new Font("Times New Roman", Font.BOLD, 12));
		present.setForeground(Color.RED);
		present.setVisible(false);
		
		
		lname.setBounds(130,30,160,60);
		lname.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		lprice.setBounds(130,80,160,60);
		lprice.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		lamount.setBounds(130,130,160,60);
		lamount.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		ltype.setBounds(130,180,160,60);
		ltype.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		lclass.setBounds(130,230,160,60);
		ltype.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		tfname.setBounds(260,45,160,30);
		tfname.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfname.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(tfname.getText().equals("Enter Name"))
				{
					tfname.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(tfname.getText().equals(""))
				{
					tfname.setText("Enter Name");
				}				
			}
			
		});
		
		tfprice.setBounds(260,95,160,30);
		tfprice.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfprice.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(tfprice.getText().equals("Enter Price") || tfprice.getText().equals("Integer Value only"))
				{
					tfprice.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(tfprice.getText().equals(""))
				{
					tfprice.setText("Enter Price");
				}				
			}
			
		});
		
		tfamount.setBounds(260,145,160,30);
		tfamount.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfamount.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(tfamount.getText().equals("Enter Amount"))
				{
					tfamount.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(tfamount.getText().equals(""))
				{
					tfamount.setText("Enter Amount");
				}				
			}
			
		});
		
		cbtype.setBounds(260,195,160,30);
		cbtype.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		cbclass.setBounds(260,245,160,30);
		cbclass.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		lnamee.setForeground(Color.RED);
		lnamee.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		lnamee.setBounds(260,65,150,30);
		lnamee.setVisible(false);		
		
		lpricee.setForeground(Color.RED);
		lpricee.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		lpricee.setBounds(260,115,150,30);
		lpricee.setVisible(false);		
		
		lamounte.setForeground(Color.RED);
		lamounte.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		lamounte.setBounds(260,165,150,30);
		lamounte.setVisible(false);
		
		ltypee.setForeground(Color.RED);
		ltypee.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		ltypee.setBounds(260,215,150,30);
		ltypee.setVisible(false);
		
		lclasse.setForeground(Color.RED);
		lclasse.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		lclasse.setBounds(260,265,150,30);
		lclasse.setVisible(false);

		proceed = new JButton("Continue");
		proceed.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		proceed.setBounds(280,320,100,30);
		proceed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean flag = tfname.getText().equals("Enter Name") || tfname.getText().equals("Enter Price") || tfname.getText().equals("Integer Value only") || tfamount.getText().equals("Enter Amount") || ((String)cbclass.getItemAt(cbclass.getSelectedIndex())).equals("Select Class Name") || ((String)cbtype.getItemAt(cbtype.getSelectedIndex())).equals("Select Type Name");
				lnamee.setVisible(false);
				lpricee.setVisible(false);
				lamounte.setVisible(false);
				lclasse.setVisible(false);
				ltypee.setVisible(false);
					
				boolean medcheck = false;
				try {
					medcheck = jm.checkMed(tfname.getText());
				} catch (Exception e2) {
					e2.printStackTrace();
				} 
				
				if(medcheck)
				{
					present.setVisible(true);
				}
				else if(!flag)
				{
					System.out.println(username);
					try {
						jm.addNewMed(tfname.getText(), ((String)cbclass.getItemAt(cbclass.getSelectedIndex())), ((String)cbtype.getItemAt(cbtype.getSelectedIndex())), tfamount.getText(), Integer.parseInt(tfprice.getText()));
						
						billingPage.dispose();
						frame.dispose();
						new BuyingPage(username);
						} catch (NumberFormatException e1) {
						e1.printStackTrace();
						tfprice.setText("Integer Value only");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else
				{
					if(tfname.getText().equals("Enter Name"))
						lnamee.setVisible(true);
					if(tfprice.getText().equals("Enter Price") || tfprice.getText().equals("Integer Value only"))
						lpricee.setVisible(true);
					if(tfamount.getText().equals("Enter Amount"))
						lamounte.setVisible(true);
					if(((String)cbclass.getItemAt(cbclass.getSelectedIndex())).equals("Select Class Name"))
						lclasse.setVisible(true);
					if(((String)cbtype.getItemAt(cbtype.getSelectedIndex())).equals("Select Type Name"))
						ltypee.setVisible(true);
					
				}
				
			}});
		
		cancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cancel.setBounds(150,320,100,30);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}});
		
		frame.add(lname);
		frame.add(lprice);
		frame.add(lamount);
		frame.add(ltype);
		frame.add(lclass);
		
		frame.add(lnamee);
		frame.add(lpricee);
		frame.add(lamounte);
		frame.add(ltypee);
		frame.add(lclasse);
		
		frame.add(tfname);
		frame.add(tfprice);
		frame.add(tfamount);
		frame.add(cbclass);
		frame.add(cbtype);
		frame.add(present);
		
		frame.add(proceed);
		frame.add(cancel);
		
		frame.setIconImage(new ImageIcon("Pharmacy.png").getImage());

		
		frame.setSize(580,410);
		frame.getContentPane().setBackground(Color.pink);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
