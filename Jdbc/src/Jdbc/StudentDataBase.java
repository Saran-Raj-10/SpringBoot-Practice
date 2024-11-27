package Jdbc;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDataBase {
	private static Connection connection=null;
	private static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) throws SQLException
	{
		StudentDataBase studentdatabase=new StudentDataBase();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dburl="jdbc:mysql://localhost:3306/dummy";
			String username="root";
			String password="Saranrajece10@";
			connection=DriverManager.getConnection(dburl,username,password);
			System.out.println("HIII");
		}
		catch(Exception e) {
			System.out.println("Connection failed"+e.getMessage());
		}
//		finally 
//		{
//			try 
//			{
//        		if(connection!=null&& !connection.isClosed()) 
//        		{
//        			connection.close();
//        			System.out.println("connection closed");
//        		}
//        	}
//			catch(Exception e) {
//        		System.out.println("Error closing connection");
//        		}
//		}

		
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
				studentdatabase.insertRecord();
				break;
			case 2:
				studentdatabase.selectRecord();
				break;
			case 3:
				studentdatabase.updateRecord();
				break;
			case 4:
				studentdatabase.deleteRecord();
				break;
			case 5:
				studentdatabase.transcation();
				break;
			case 6:
				studentdatabase.batchProcessing();
				break;
			default:
				break;
		}
	}
	private void insertRecord() throws SQLException
	{
		System.out.println("Insert name");
		System.out.println("Enter the name");
		sc.nextLine();
		String name=sc.nextLine();
		System.out.println("Enter the percentage");
		int percentage=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the address");
		String address=sc.nextLine();
		String sql="insert into student(student_name,percentage,address) values(?,?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, percentage);
		preparedStatement.setString(3, address);
		int rows=preparedStatement.executeUpdate();
		if(rows>0) {
			System.out.println("Record inserted Successfully");
		}
	}
	
	public void selectRecord() throws SQLException{
			System.out.println("Enter rollno to display");
			int number=sc.nextInt();
			String sql="select * from student";
			Statement statement=connection.createStatement();
			ResultSet result=statement.executeQuery(sql);
			if(!result.next()) {
				System.out.println("No row");
			}
			else {
				do
				{
					System.out.println("The rollno is "+result.getString("rollno"));
					System.out.println("The name is "+result.getString("student_name"));
					System.out.println("The percentage is "+result.getInt("percentage"));
					System.out.println("The address is "+result.getString("address"));
				}while(result.next());
			}
//			else
//			{
//				System.out.println("The record  not found");
//			}
	}
	
	public void updateRecord() throws SQLException{
		System.out.println("Enter roll no to update database");
		int roll=sc.nextInt();
		String sql="select * from student where rollno="+roll;
		Statement statement=connection.createStatement();
		ResultSet result=statement.executeQuery(sql);
		if(result.next())
		{
			System.out.println("The rollno is "+result.getString("rollno"));
			System.out.println("The name is "+result.getString("student_name"));
			System.out.println("The percentage is "+result.getInt("percentage"));
			System.out.println("The address is "+result.getString("address"));
			
			System.out.println("What to be updated");
			System.out.println("1.student_name");
			System.out.println("2.percentage");
			System.out.println("3.address");
			
			int choice=sc.nextInt();
			String sqlQuery="update student set ";
			switch(choice)
			{
				case 1:
					System.out.println("enter name to be updated");
					System.out.println("enter name");
					sc.nextLine();
					String name=sc.nextLine();
					sqlQuery=sqlQuery+"student_name=? where rollno="+roll;
					PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
					preparedStatement.setString(1, name);
					int rows=preparedStatement.executeUpdate();
					if(rows>0) {
						System.out.println("Record updated Successfully");
					}
					break;
				case 2:
					System.out.println("enter percentage to be updated");
					System.out.println("enter percentage");
					int per=sc.nextInt();
					sqlQuery=sqlQuery+"percentage=? where rollno="+roll;
					PreparedStatement preparedStatement1=connection.prepareStatement(sqlQuery);
					preparedStatement1.setInt(1, per);
					int rows1=preparedStatement1.executeUpdate();
					if(rows1>0) {
						System.out.println("Record updated Successfully");
					}
					break;
				case 3:
					System.out.println("enter address to be updated");
					System.out.println("enter address");
					sc.nextLine();
					String address=sc.nextLine();
					sqlQuery=sqlQuery+"student_name=? where rollno="+roll;
					PreparedStatement preparedStatement2=connection.prepareStatement(sqlQuery);
					preparedStatement2.setString(1, address);
					int rows2=preparedStatement2.executeUpdate();
					if(rows2>0) {
						System.out.println("Record updated Successfully");
					}
					break;
				default:
					System.out.println("Enter correct choice");
					
			}
			System.out.println("Updated");
		}
	}
	
	public void deleteRecord() throws SQLException{
		System.out.println("Going to delete the rows in table");
		System.out.println("Enter rollno to delete the table");
		int roll=sc.nextInt();
		String sql="delete from student where rollno="+roll;
		Statement statement=connection.createStatement();
		int rows=statement.executeUpdate(sql);
		if(rows>0) {
			System.out.println("Record deleted Successfully");
		}
		System.out.println("Row deleted successfully");
	}
	
	public void transcation() throws SQLException {
		//connection.setAutoCommit(false);
		String sql1="insert into student(student_name,percentage,address) values('Gggg',101,'CIT')";
		String sql2="insert into student(student_name,percentage,address) values(200,'258','REC')";
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
		PreparedStatement preparedStatement1=connection.prepareStatement(sql2);
		int rows1=preparedStatement.executeUpdate();
		int rows2=preparedStatement1.executeUpdate();
		if(rows1>0 && rows2>0) {
			//connection.commit();  ---> run when the autocommit is true(it become true when the given query are true)
			System.out.println("Transcation executed");
		}
		else {
			//connection.rollback(); ---> run when the autocommit is false
		}
	}
	
	public void batchProcessing() throws SQLException{
		connection.setAutoCommit(false);
		
		String sql1="insert into student(student_name,percentage,address) values('charan',90,'CIT')";
		String sql2="insert into student(student_name,percentage,address) values('vishva',80,'AIT')";
		String sql3="insert into student(student_name,percentage,address) values('nigga',99,'BIT')";
		String sql4="insert into student(student_name,percentage,address) values('raj',50,'DIT')";
		String sql5="insert into student(student_name,percentage,address) values('shiva',100,'EIT')";
		
		Statement statement=connection.createStatement();
		statement.addBatch(sql1);
		statement.addBatch(sql2);
		statement.addBatch(sql3);
		statement.addBatch(sql4);
		statement.addBatch(sql5);
		
		int rows[]=statement.executeBatch();
		for(int i:rows)
		{
			if(i>0)
				continue;
			else {
				connection.rollback();
				connection.commit();
			}
		}
	}
	
	
}
