import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class Purchase {
	

	jdbcmethods jm = new jdbcmethods();
	JFrame stock = new JFrame();
	JLabel heading, sidePic, stockList;
	JScrollPane scrollableBill;
	JTable billTable;
	String billColumn[] = {"Sl. No.","Total Cost","Date"};
	String billContent[][];
	
	Connection conn;
	Statement stmt;
	ResultSet res;
	
	
	Purchase(String username) throws Exception
	{
		stock.getContentPane().setLayout(new FlowLayout());
		billContent = new String[0][3];
		heading = new JLabel("HERE WILL GO THE HEADING", SwingConstants.CENTER);
		sidePic = new JLabel("Here will go the demonstration pic", SwingConstants.CENTER);
		stockList = new JLabel("Purchase of the account "+username, SwingConstants.CENTER);
		
		DefaultTableModel model = new DefaultTableModel(billContent, billColumn)
				{
			 
					private static final long serialVersionUID = 1L;

					@Override
					public Class<?> getColumnClass(int colNum) {
						switch (colNum) {
							case 0:
								return Integer.class;
							case 1:
								return Integer.class;
							default:
								return Date.class;
			        }
			    }
			
				};
		
		   
		
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
		billTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		billTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "Sb@405984");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			res = stmt.executeQuery("Select * from pharmacy.transaction where type='Buy' and Username = '"+username+"';");
			
			while(res.next())
			{
				model.addRow(new Object[]{res.getInt("Id"),res.getInt("TotalCost"),res.getString("Date")});
			}
		
		billTable.setAutoCreateRowSorter(true);
		billTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				JTable table =(JTable) e.getSource();
		        Point point = e.getPoint();
		        int row = table.rowAtPoint(point);
		        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	
		        	try {
						details((int) table.getModel().getValueAt(row, 0));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		        }
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}});
		

		
		
		scrollableBill = new JScrollPane(billTable);
		scrollableBill.setBounds(10,140,700,480);
		scrollableBill.setBackground(Color.PINK);
		scrollableBill.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		scrollableBill.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		stockList.setBounds(180,80,300,60);
		stockList.setFont(new Font("Times New Roman", Font.PLAIN,22));
		
		
		heading.setBackground(Color.blue);
		heading.setOpaque(true);
		heading.setBounds(0,0,1080,80);
		
		sidePic.setBackground(Color.red);
		sidePic.setOpaque(true);
		sidePic.setBounds(720,80,360,645);
		
		
		stock.add(heading);
		stock.add(sidePic);
		stock.getContentPane().add(scrollableBill);
		stock.add(stockList);

		
		stock.setSize(1080,720);
		stock.getContentPane().setBackground(Color.pink);
		stock.setLocationRelativeTo(null);
		stock.setLayout(null);
		stock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stock.setVisible(true);
		stock.setResizable(false);
	}
	
	public void details(int trancId) throws Exception
	{
		JFrame frame = new JFrame();
		JScrollPane trancBill;
		JTable trancTable;
		String TrancColumn[] = {"Sl. No.","Name","Quantity", "Amount"};
		String TrancContent[][];
		jdbcmethods jm = new jdbcmethods();
		
		TrancContent = new String[0][3]; 
		DefaultTableModel model = new DefaultTableModel(TrancContent, TrancColumn)
		{
	 
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int colNum) {
				switch (colNum) {
					case 1:
						return Integer.class;
					default:
						return Integer.class;
	        }
	    }
	
		};
		
		trancTable = new JTable(model) {
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }};
		    
		    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			trancTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
			trancTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
			trancTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
			trancTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "Sb@405984");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			res = stmt.executeQuery("Select * from pharmacy.trancdetails where TrancId = "+trancId+";");
			
			while(res.next())
			{
				model.addRow(new Object[]{model.getRowCount()+1,res.getString("ItemName"),res.getInt("Quantity"),jm.getMedPrice(res.getString("ItemName"))*res.getInt("Quantity") });
			}
			trancTable.setAutoCreateRowSorter(true);
			
			trancBill = new JScrollPane(trancTable);
			trancBill.setBounds(10,140,700,480);
			trancBill.setBackground(Color.PINK);
			trancBill.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
			trancBill.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			trancBill.setBounds(0,0,480,480);
			
		frame.add(trancBill);
			
		frame.setSize(480,480);
		frame.getContentPane().setBackground(Color.pink);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
	}
}