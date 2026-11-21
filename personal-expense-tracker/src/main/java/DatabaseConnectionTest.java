import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnectionTest {
	
	public static void main(String[] args) {
		
		
		String url = "jdbc:mysql://localhost:3306/expense_tracker";
		String name = "root";
		String password = "root";
		
		String description = "Medical checkup";
        BigDecimal amount = new BigDecimal("500.00");
        int categoryId = 8; 
        Date transactionDate = Date.valueOf("2026-11-20");
        String paymentMethod = "UPI";
        String type = "EXPENSE";
        
        String sql = "INSERT INTO expense_transaction"
                + "(description, amount, category_id, transaction_date, payment_method, type) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

		
		
		try {
			Connection connect = DriverManager.getConnection(url,name,password);
			System.out.println("Database Connected Successfully");
			
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, description);
			ps.setBigDecimal(2, amount);
            ps.setInt(3, categoryId);
            ps.setDate(4, transactionDate);
            ps.setString(5, paymentMethod);
            ps.setString(6, type);
            
            int rowsInserted = ps.executeUpdate();

            System.out.println("Rows inserted: " + rowsInserted);

            ps.close();
			connect.close();
			
		}catch(SQLException e) {
			System.out.println("Database Connetion Failed");
			e.printStackTrace();	
		}
		
		
		
	}

}
