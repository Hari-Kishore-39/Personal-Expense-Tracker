import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCategories {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/expense_tracker";
        String username = "root";
        String password = "root";

        String sql =
            "UPDATE categories SET name = ?, type = ? WHERE id = ?";

        try {
            Connection connection =
                DriverManager.getConnection(url, username, password);

            PreparedStatement statement =
                connection.prepareStatement(sql);
        
            statement.setString(1, "Insurance");
            statement.setString(2, "EXPENSE");
            statement.setInt(3, 10);

            int rowsUpdated = statement.executeUpdate();

            System.out.println("Rows updated: " + rowsUpdated);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}