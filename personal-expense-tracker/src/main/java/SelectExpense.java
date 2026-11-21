import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectExpense {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/expense_tracker";
        String username = "root";
        String password = "root";

        String sql = "SELECT * FROM expense_transaction";

        try {
            Connection connection =
                DriverManager.getConnection(url, username, password);

            PreparedStatement statement =
                connection.prepareStatement(sql);

            ResultSet resultSet =
                statement.executeQuery();
        
            System.out.println("Expense Transactions:");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                int categoryId = resultSet.getInt("category_id");
                String transactionDate =
                    resultSet.getDate("transaction_date").toString();
                String paymentMethod =
                    resultSet.getString("payment_method");
                String type = resultSet.getString("type");

                System.out.println("--------------------");
                System.out.println("ID: " + id);
                System.out.println("Description: " + description);
                System.out.println("Amount: " + amount);
                System.out.println("Category ID: " + categoryId);
                System.out.println("Date: " + transactionDate);
                System.out.println("Payment Method: " + paymentMethod);
                System.out.println("Type: " + type);
            }
            
            resultSet.close();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}