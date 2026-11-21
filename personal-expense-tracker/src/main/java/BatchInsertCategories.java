import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchInsertCategories {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/expense_tracker";
        String username = "root";
        String password = "root";

        String sql =
            "INSERT INTO categories (name, type) VALUES (?, ?)";

        try (
            Connection connection =
                DriverManager.getConnection(url, username, password)){
        	
        	connection.setAutoCommit(false);

        	try(PreparedStatement statement =
                    connection.prepareStatement(sql)){
            
        		// Add the first category
        		statement.setString(1, "Shopping");
        		statement.setString(2, "EXPENSE");
        		statement.addBatch();

        		// Add the second category
        		statement.setString(1, "Investment");
        		statement.setString(2, "INCOME");
        		statement.addBatch();

        		// Add the third category
        		statement.setString(1, "Entertainment");
        		statement.setString(2, "EXPENSE");
        		statement.addBatch();

        		// Execute all queued inserts
        		int[] results = statement.executeBatch();
            
        		connection.commit();

        		System.out.println("Batch completed.");
        		System.out.println("Number of results: " + results.length);

            } catch (SQLException e) {
        	
        	connection.rollback();
            System.out.println("Batch insert failed.");
            System.out.println("Reason: " + e.getMessage());
            }
        }catch(SQLException e) {
        	e.printStackTrace();
        }
    }
}