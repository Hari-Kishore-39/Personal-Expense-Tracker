package exampleclasses;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTransactionExample {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/expense_tracker";
        String username = "root";
        String password = "root";

        String sql =
            "UPDATE expense_transaction SET amount = ? WHERE id = ?";

        try (Connection connection =
                 DriverManager.getConnection(url, username, password)) {

            connection.setAutoCommit(false);

            try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

                // Update the first transaction
                statement.setBigDecimal(1, new BigDecimal("300.00"));
                statement.setInt(2, 1);
                statement.executeUpdate();

                // Update the second transaction
                statement.setBigDecimal(1, new BigDecimal("50000.00"));
                statement.setInt(2, 2);
                statement.executeUpdate();

                // Save both updates
                connection.commit();

                System.out.println("Both updates committed.");

            } catch (SQLException e) {

                // Undo changes made in this transaction
                connection.rollback();

                System.out.println("Transaction rolled back.");
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
