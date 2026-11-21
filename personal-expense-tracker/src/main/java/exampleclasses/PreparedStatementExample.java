package exampleclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementExample {
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/expense_tracker";
		String userName = "root";
		String password = "root";
		
		String categoryName = "insurance";
		String categoryType = "Expense";
		
		try {
			
			Connection con = DriverManager.getConnection(url,userName,password);
			
			String sql = "INSERT INTO categories (name,type) VALUES(?,?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, categoryName);
			ps.setString(2, categoryType);
			
			int rows = ps.executeUpdate();
			
			System.out.println("Rows Inserted : "+ rows);
			
			ps.close();
			con.close();
			
			
			
		}catch(SQLException e) {
			System.out.println("Database Connection Failed");
			e.printStackTrace();
		}
		
		
		
	}

}
