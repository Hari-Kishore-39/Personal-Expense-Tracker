import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateExpense {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/expense_tracker";
        String username = "root";
        String password = "root";

        String sql =
            "UPDATE expense_transaction SET amount = ? WHERE id = ?";

        try {
            Connection connection =
                DriverManager.getConnection(url, username, password);

            PreparedStatement statement =
                connection.prepareStatement(sql);
        
            statement.setBigDecimal(1, new java.math.BigDecimal("250.00"));
            statement.setInt(2, 1);

            int rowsUpdated = statement.executeUpdate();

            System.out.println("Rows updated: " + rowsUpdated);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}