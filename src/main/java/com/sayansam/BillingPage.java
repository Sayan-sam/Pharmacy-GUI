package com.sayansam;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class BillingPage {

	static String username, customer;
	jdbcmethods jm = new jdbcmethods();
	JFrame billingPage = new JFrame("Billing Page");
	JLabel heading, sidePic, lbmedName, lbquantity, lTotal;
	JLabel enterMedName, enterQuantity, stockExceeded;
	JTextField tfquantity;
	JComboBox<?> tfmedName;
	String medName[];
	JPopupMenu pm;
	JMenuItem searchName[];
	JButton add, confirm;
	JScrollPane scrollableBill;
	JTable billTable;
	String billColumn[] = {"Sl. No.","Name","Type","Class", "Contains","Price","Quantity","Amount"};
	String billContent[][];
	int TotalCost = 0;
	
	BillingPage(String username, String customer) throws Exception
	{
		BillingPage.username = username;
		BillingPage.customer = customer;
		billingPage.getContentPane().setLayout(new FlowLayout());
		billContent = new String[0][8];
		heading = new JLabel(new ImageIcon("pharmacygui/resources/images/PharmacyM.png"));
		sidePic = new JLabel(new ImageIcon("pharmacygui/resources/images/Billing.png"));
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
		
		stockExceeded = new JLabel("Stock Exceeded!!!");
		stockExceeded.setFont(new Font("Times New Roman", Font.BOLD, 22));
		stockExceeded.setBounds(80,620,200,60);
		stockExceeded.setForeground(Color.RED);
		stockExceeded.setVisible(false);
		
		
		
		String medName[] = new String[jm.getMedlen()+1];
		medName[0] = "Medicine Name";
		for(int i = 1 ; i <= jm.getMedlen() ; i++)
		{
			medName[i] = jm.getMed(i);
		}
		Arrays.sort(medName, 1, jm.getMedlen());
		tfmedName = new JComboBox<Object>(medName);
		tfmedName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		final DefaultTableModel model = new DefaultTableModel(billContent, billColumn);
		
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
		
		lTotal.setBounds(320,620,200,60);
		confirm.setBounds(580, 630, 100, 40);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					int trancId = jm.initTranc(BillingPage.username, BillingPage.customer, "Sell");
					
					for(int i = 0; i<model.getRowCount();i++)
					{
						if (Integer.parseInt((String) billTable.getModel().getValueAt(i, 6)) != 0) {
							jm.addMed(trancId, (String) billTable.getModel().getValueAt(i, 1),
									Integer.parseInt((String) billTable.getModel().getValueAt(i, 6)));
						}
					}
					
					jm.updateTotalCost(trancId, TotalCost);
				} catch (Exception e1) {
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
						stockExceeded.setVisible(false);
						if(Integer.parseInt(tfquantity.getText()) > jm.getMedStock((String) tfmedName.getItemAt(tfmedName.getSelectedIndex())))
						{
							stockExceeded.setVisible(true);
						}
						else
						{
						model.addRow(new Object[]{model.getRowCount()+1,tfmedName.getItemAt(tfmedName.getSelectedIndex()) , jm.getMedType((String) tfmedName.getItemAt(tfmedName.getSelectedIndex())), jm.getMedClass((String) tfmedName.getItemAt(tfmedName.getSelectedIndex())),jm.getMedQuan((String) tfmedName.getItemAt(tfmedName.getSelectedIndex())), jm.getMedPrice((String) tfmedName.getItemAt(tfmedName.getSelectedIndex())), tfquantity.getText(), jm.getMedPrice((String) tfmedName.getItemAt(tfmedName.getSelectedIndex()))*Integer.parseInt(tfquantity.getText())});
					    TotalCost += jm.getMedPrice((String) tfmedName.getItemAt(tfmedName.getSelectedIndex()))*Integer.parseInt(tfquantity.getText());
						lTotal.setText("Total Amount: " + TotalCost);
						}
						
					}
					else if(flag != 0 && !(tfmedName.getItemAt(tfmedName.getSelectedIndex()).equals("Medicine Name") || tfquantity.getText().equals("Enter Quantity")))
					{
						enterMedName.setVisible(false);    
						enterQuantity.setVisible(false);
						stockExceeded.setVisible(false);	
						if(Integer.parseInt(tfquantity.getText()) > jm.getMedStock((String) tfmedName.getItemAt(tfmedName.getSelectedIndex())))
						{
							stockExceeded.setVisible(true);
						}
						else
						{
						TotalCost -= jm.getMedPrice((String) tfmedName.getItemAt(tfmedName.getSelectedIndex()))*Integer.parseInt((String) model.getValueAt(i, 6));
						model.setValueAt(tfquantity.getText(), i, 6);
						model.setValueAt(jm.getMedPrice((String) tfmedName.getItemAt(tfmedName.getSelectedIndex()))*Integer.parseInt(tfquantity.getText()),i,7);
						TotalCost += jm.getMedPrice((String) tfmedName.getItemAt(tfmedName.getSelectedIndex()))*Integer.parseInt(tfquantity.getText());
						lTotal.setText("Total Amount: " + TotalCost);
						}
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
		billingPage.add(stockExceeded);
		billingPage.getRootPane().setDefaultButton(add);

		JButton home, back;

		home = new JButton(new ImageIcon("pharmacygui/resources/images/home.png"));
		back = new JButton(new ImageIcon("pharmacygui/resources/images/back.png"));


		home.setBounds(10,90,30,30);
		home.setBorder(BorderFactory.createEmptyBorder());
		home.setContentAreaFilled(false);
		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				billingPage.dispose();
				new LoggedInPage(BillingPage.username);
			}});
		back.setBounds(50,90,30,30);
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setContentAreaFilled(false);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				billingPage.dispose();
				new LoggedInPage(BillingPage.username);
			}});

		billingPage.add(home);
		billingPage.add(back);
		billingPage.setIconImage(new ImageIcon("pharmacygui/resources/images/Pharmacy.png").getImage());

		billingPage.setSize(1080,720);
		billingPage.getContentPane().setBackground(Color.decode("#ADD8E6"));
		billingPage.setLocationRelativeTo(null);
		billingPage.setLayout(null);
		billingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		billingPage.setVisible(true);
		billingPage.setResizable(false);
	}
	
	
}
