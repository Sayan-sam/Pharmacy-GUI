package com.sayansam;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;


public class jdbcmethods {
	
	//This method class will have all the methods required to perform the jdbc applications.
	Connection conn = null;
	Statement stmt = null;
	ResultSet res = null;
	
	public static String URL = "jdbc:sqlite:pharmacygui/resources/database/pharmacy.db";
	// public static String USERNAME = "root";
	// public static String PASSWORD = "sam@mysql";
	
	String accountName;
	String accountDOB;
	String accountStatus;
	String accountType;
	String accountDOJ;
	
	Scanner jm = new Scanner(System.in);

	jdbcmethods() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	jdbcmethods(String url, String username, String password)
	{
		URL = url;
		// USERNAME = username;
		// PASSWORD = password;
	}
	
	
	//Here will go all the methods for the requirements.
	
	// Method for checking if the account with it's password exists!
	public boolean accountCheck(String username, String password) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		
		res = stmt.executeQuery("Select * from accounts where Username = '"+username+"' and Password = '"+password+"';");
		
		if(res.next())
		{
			stmt.close();
			res.close();
			return true;
		}
		else
		{
			stmt.close();
			res.close();
			return false;
		}
	}
	
	//Method for checking if an account exist from it's username!
	public boolean accountCheck(String username) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.accounts where Username = '"+username+"';");
		
		if(res.next())
		{
			stmt.close();
			res.close();
			return true;
		}
		else
		{
			stmt.close();
			res.close();
			return false;
		}
	}
	
	//Returning the type of account from it's username!
	public String getType(String username) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.accounts where Username = '"+username+"';");
		
		res.next();
		return res.getString("Type");
	}
	
	//Returning the status of account from it's username!
	public String getStatus(String username) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.accounts where Username = '"+username+"';");
		
		res.next();
		return res.getString("Status");
	}
	
	//Setting the details of an account from the username
		public void setAccount(String username) throws Exception
		{
			conn = DriverManager.getConnection(URL);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			res = stmt.executeQuery("Select * from pharmacy.accounts where Username = '"+username+"'");
			
			res.next();
			
			accountName = res.getString("Name");
			accountDOB = res.getString("DateOfBirth");
			accountType = res.getString("Type");
			accountStatus = res.getString("Status");
			accountDOJ = res.getString("DateOfJoin");
			
		}
		
	//Returns the username from a given index or returns null
		public String getUsername(int index) throws Exception
		{
			conn = DriverManager.getConnection(URL);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			res = stmt.executeQuery("Select Username from pharmacy.accounts");
			
			if(res.absolute(index))
			return res.getString("Username");
			else return null;
		}
 	
	//Changing status of an account from it's previous form!
	public void changeStatus(String username) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		if(getStatus(username).equals("Active"))
		{
			stmt.executeUpdate("Update pharmacy.accounts set Status = 'Deactive' where Username = '"+username+"'");
		}
		else if(getStatus(username).equals("Deactive"))
		{
			stmt.executeUpdate("Update pharmacy.accounts set Status = 'Active' where Username = '"+username+"'");
		}
	}
	
	//Change the password of an account from username and given new password
	public void changePassword(String username, String newPassword) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		stmt.executeUpdate("Update pharmacy.accounts set Password = '"+newPassword+"' where Username = '"+username+"';");
	}
	
	//Create a an account using the username, password and the type of the account!
	public void createAccount(String Name, String DOB, String username, String password, String Type) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		Date currentDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		
		
		stmt.executeUpdate("Insert into pharmacy.accounts(Username, Password, Type, Status, Name, DateOfBirth, DateOfJoin) values('"+username+"','"+password+"','"+Type+"','Active','"+Name+"', '"+DOB+"','"+ft.format(currentDate)+"'); ");
		stmt.close();
	}
	
	//Checking if the medicine exist from the username!
	public boolean checkMed(String medName) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.medicine where Name = '"+medName+"';");
		
		if(res.next())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Get Medicine Name from the first few letters, and the index of the position of the result or will return null!
	public String getMed(String iniMed, int index) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.medicine where Name like '"+iniMed+"%';");
		
		if(res.absolute(index))
		{
			return res.getString("Name");
		}
		else return null;
	}
	
	//Get Medicine Name from the index of the medicines!
	public String getMed(int index) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.medicine;");
		
		if(res.absolute(index))
		{
			return res.getString("Name");
		}
		else return null;
	}
	
	//Get the number of all the medicines in the Stock
	public int getMedlen() throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.medicine order by Name ASC;");
		int count = 1;
		
		while(res.absolute(count))
		{
			count++;
		}
		
		return --count;

	}
	
	
	// Get Medicine Price from name of the Medicine!
	public int getMedPrice(String medName) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.medicine where Name = '"+medName+"';");
		res.next();
		
		return res.getInt("Price");
	}
	
	//Get medicine Stock from name of the Medicine!
	public int getMedStock(String medName) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.medicine where Name = '"+medName+"';");
		res.next();
		
		return res.getInt("Stock");
	}
	
	//Get Medicine class from name of the Medicine!
	public String getMedClass(String medName) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.medicine where Name = '"+medName+"';");
		res.next();
		
		return res.getString("ClassName");
	}
	
	//Get Medicine type from name of the Medicine!
		public String getMedType(String medName) throws Exception
		{
			conn = DriverManager.getConnection(URL);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			res = stmt.executeQuery("Select * from pharmacy.medicine where Name = '"+medName+"';");
			res.next();
			
			return res.getString("TypeName");
		}
	//Get Medicine Quantity from name of the Medicine!
		public String getMedQuan(String medName) throws Exception
		{
			conn = DriverManager.getConnection(URL);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			res = stmt.executeQuery("Select * from pharmacy.medicine where Name = '"+medName+"';");
			res.next();
			
			return res.getString("Amount");
		}
		
	//Check if a customer is in the database from the phone number
	public boolean customerCheck(String number) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.customer where PhoneNumber = '"+number+"';");
		
		if(res.next())
		{
			stmt.close();
			res.close();
			return true;
		}
		else
		{
			stmt.close();
			res.close();
			return false;
		}
	} 
	
	//Check if the customer's details are submitted from their phone number 
	public boolean customerDetailCheck(String number) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.customer where PhoneNumber = '"+number+"';");
		
		res.next();
		
		if(res.getString("FirstName").equals(null) || res.getString("LastName").equals(null) || res.getString("DateOfBirth").equals(null))
		{
			return false;
		}
		else return true;
	}
	//Create a customer account with phone number
	public void createCustomer(String number) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		
		stmt.executeUpdate("Insert into pharmacy.customer(PhoneNumber, DateOfJoin) values('"+number+"', '"+ft.format(date)+"')");
	}
	
	//Update a customer account with the phone number and other Details
	public void updateCustomer(String number, String FirstName, String LastName, String DateOfBirth) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		stmt.executeUpdate("Update pharmacy.customer set FirstName = '"+FirstName+"', LastName = '"+LastName+"', DateOfBirth = '"+DateOfBirth+"' where PhoneNumber = '"+number+"';");
	}
	
	//Check if a company is in the database from the phone number
	public boolean companyCheck(String number) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.company where Username = '"+number+"';");
		
		if(res.next())
		{
			stmt.close();
			res.close();
			return true;
		}
		else
		{
			stmt.close();
			res.close();
			return false;
		}
	}
	
	//Get the type of the transaction from the id
	public String getTancType(int id) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		res = stmt.executeQuery("Select * from pharmacy.transaction where Id = "+id+";");
		
		res.next();
		
		return res.getString("Type");
	}
	
	
	//Create an instance in transaction table and return the transaction id
	public int initTranc(String username,String number,String type) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		Date datetimetranc = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		
		stmt.executeUpdate("Insert into pharmacy.transaction(Username,compcus,Type,Date) values ('" + username + "','"+ number + "','"+type+"','" + ft.format(datetimetranc) + "');");
		
		res = stmt.executeQuery("select Id from pharmacy.transaction");
		
		while(res.next())
		{}
		
		res.previous();
		return res.getInt("Id");
	}
	
	//Add or remove medicines from Transaction Details table with given amount
	public void addMed(int trancId, String medName, int quantity) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		Date datetimetranc = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		stmt.executeUpdate("insert into pharmacy.trancdetails(ItemName, Quantity, DateTime, TrancId) values('"+medName+"',"+quantity+",'"+ft.format(datetimetranc)+"',"+trancId+");");
		
		res = stmt.executeQuery("Select Type from pharmacy.transaction where Id = "+trancId+";");
		
		res.next();
		
		if(res.getString("Type").equals("Sell"))
		{
			stmt2.executeUpdate("Update pharmacy.medicine set Stock = Stock - "+quantity+" where Name = '"+medName+"'");
		}
		else
		{
			stmt2.executeUpdate("Update pharmacy.medicine set Stock = Stock + "+quantity+" where Name = '"+medName+"'");
		}
	}
	
	//Method to update the total cost of with the Transaction and total cost
	public void updateTotalCost(int trancId, int totalCost) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		stmt.executeUpdate("Update pharmacy.transaction set TotalCost = "+totalCost+" where Id = "+trancId+";");
	}
	
	//Method to update the rate of a medicine from name of the Medicine!
	public void updateMedRate(String medName, int rate) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		stmt.executeUpdate("Update pharmacy.medicine set Price = "+rate+" where Name = '"+medName+"';");

	}
	
	//Method to create a new medicine in the stock
	public void addNewMed(String Name, String Class, String Type, String Amount, int Price) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		stmt.executeUpdate("Insert into pharmacy.medicine (Name, ClassName, TypeName, Price, Amount) values('"+Name+"','"+Class+"','"+Type+"',"+Price+",'"+Amount+"');");
	}
	
	//Function to return the total price of the transaction from id and update the respective
	public int getPrice(int trancId) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		int totalCost=0;
		
		res = stmt.executeQuery("Select Price, Quantity from pharmacy.medicine, pharmacy.trancdetails where pharmacy.trancdetails.TrancId = "+trancId+";");
		
		while(res.next())
		{
			totalCost += res.getInt("Price")*res.getInt("Quantity");
		}
		
		stmt.executeUpdate("update pharmacy.transaction set TotalCost = "+totalCost+" where Id = '"+trancId+"';");
		return totalCost;
	}
	
	//Method to delete an account row from database with username
	public void deleteAccount(String username) throws Exception
	{
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		stmt.executeUpdate("Delete from pharmacy.accounts where Username = '"+username+"'");
	}
}
