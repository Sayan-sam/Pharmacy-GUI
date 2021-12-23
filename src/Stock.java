import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class Stock {
	

	jdbcmethods jm = new jdbcmethods();
	JFrame stock = new JFrame("Medicine Stock");
	JLabel heading, sidePic, stockList;
	JScrollPane scrollableStock;
	JTable stockTable;
	String stockColumn[] = {"Sl. No.","Name","Type","Class", "Contains","Price","Stock"};
	String stockContent[][];
	
	Stock(String username) throws Exception
	{
		stock.getContentPane().setLayout(new FlowLayout());
		stockContent = new String[0][7];
		heading = new JLabel(new ImageIcon("PharmacyM.png"));
		sidePic = new JLabel(new ImageIcon("Stock.png"));
		stockList = new JLabel("MEDICINE STOCK", SwingConstants.CENTER);
		
		DefaultTableModel model = new DefaultTableModel(stockContent, stockColumn)
				{
			 
					private static final long serialVersionUID = 1L;

					@Override
					public Class<?> getColumnClass(int colNum) {
						switch (colNum) {
							case 0:
								return Integer.class;
							case 5:
								return Integer.class;
							case 6:
								return Integer.class;
							default:
								return String.class;
			        }
			    }
			
				};
		
		   
		
		stockTable = new JTable(model) {
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }};
		    
		stockTable.setBackground(Color.PINK);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		stockTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		stockTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		stockTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		stockTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		stockTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		stockTable.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		stockTable.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		stockTable.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
		
		for(int i = 1; i <= jm.getMedlen() ; i++)
		{
		model.addRow(new Object[]{model.getRowCount()+1, jm.getMed(i), jm.getMedType(jm.getMed(i)), jm.getMedClass(jm.getMed(i)), jm.getMedQuan(jm.getMed(i)), jm.getMedPrice(jm.getMed(i)), jm.getMedStock(jm.getMed(i)) });
		}
		
	//	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(stockTable.getModel());
	//	stockTable.setRowSorter(sorter);
		
		stockTable.setAutoCreateRowSorter(true);
		
		

		
		
		scrollableStock = new JScrollPane(stockTable);
		scrollableStock.setBounds(10,140,700,480);
		scrollableStock.setBackground(Color.PINK);
		scrollableStock.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		scrollableStock.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		stockList.setBounds(180,80,300,60);
		stockList.setFont(new Font("Times New Roman", Font.PLAIN,22));
		
		
		heading.setBounds(0,0,1080,80);
		
		sidePic.setBounds(720,80,360,645);
		
		
		stock.add(heading);
		stock.add(sidePic);
		stock.getContentPane().add(scrollableStock);
		stock.add(stockList);

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
				new LoggedInPage(username);
			}});
		back.setBounds(50,90,30,30);
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setContentAreaFilled(false);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stock.dispose();
				new Accounts(username);
			}});

		stock.add(home);
		stock.add(back);
		
		stock.setSize(1080,720);
		stock.getContentPane().setBackground(Color.decode("#ADD8E6"));
		stock.setLocationRelativeTo(null);
		stock.setLayout(null);
		stock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stock.setVisible(true);
		stock.setResizable(false);
	}
}
