import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteExpense {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/expense_tracker";
        String username = "root";
        String password = "root";

        String sql =
            "DELETE FROM expense_transaction WHERE id = ?";

        try {
            Connection connection =
                DriverManager.getConnection(url, username, password);

            PreparedStatement statement =
                connection.prepareStatement(sql);
        
            statement.setInt(1, 5);

            int rowsDeleted = statement.executeUpdate();

            System.out.println("Rows deleted: " + rowsDeleted);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}