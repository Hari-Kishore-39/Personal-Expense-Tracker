import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    private static final String URL =
            "jdbc:mysql://localhost:3306/expense_tracker";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // CREATE: Insert a new expense
    public void addExpense(
            String description,
            BigDecimal amount,
            int categoryId,
            Date transactionDate,
            String paymentMethod,
            String type) {

        String sql = "INSERT INTO expense_transaction "
                + "(description, amount, category_id, transaction_date, "
                + "payment_method, type) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {
            statement.setString(1, description);
            statement.setBigDecimal(2, amount);
            statement.setInt(3, categoryId);
            statement.setDate(4, transactionDate);
            statement.setString(5, paymentMethod);
            statement.setString(6, type);

            int rowsInserted = statement.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);

        } catch (SQLException e) {
            System.out.println("Could not insert expense.");
            e.printStackTrace();
        }
    }

    // READ: Retrieve all expenses
    public List<String> getAllExpenses() {

        List<String> expenses = new ArrayList<>();

        String sql = "SELECT id, description, amount, category_id, "
                + "transaction_date, payment_method, type "
                + "FROM expense_transaction";

        try (
            Connection connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                String expense =
                        resultSet.getInt("id") + " | "
                        + resultSet.getString("description") + " | "
                        + resultSet.getBigDecimal("amount") + " | "
                        + resultSet.getInt("category_id") + " | "
                        + resultSet.getDate("transaction_date") + " | "
                        + resultSet.getString("payment_method") + " | "
                        + resultSet.getString("type");

                expenses.add(expense);
            }

        } catch (SQLException e) {
            System.out.println("Could not retrieve expenses.");
            e.printStackTrace();
        }

        return expenses;
    }

    // READ: Retrieve one expense by ID
    public String getExpenseById(int id) {

        String sql = "SELECT id, description, amount, category_id, "
                + "transaction_date, payment_method, type "
                + "FROM expense_transaction WHERE id = ?";

        try (
            Connection connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id") + " | "
                            + resultSet.getString("description") + " | "
                            + resultSet.getBigDecimal("amount") + " | "
                            + resultSet.getInt("category_id") + " | "
                            + resultSet.getDate("transaction_date") + " | "
                            + resultSet.getString("payment_method") + " | "
                            + resultSet.getString("type");
                }
            }

        } catch (SQLException e) {
            System.out.println("Could not retrieve expense.");
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE: Update an existing expense
    public void updateExpense(
            int id,
            String description,
            BigDecimal amount,
            int categoryId,
            Date transactionDate,
            String paymentMethod,
            String type) {

        String sql = "UPDATE expense_transaction SET "
                + "description = ?, amount = ?, category_id = ?, "
                + "transaction_date = ?, payment_method = ?, type = ? "
                + "WHERE id = ?";

        try (
            Connection connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {
            statement.setString(1, description);
            statement.setBigDecimal(2, amount);
            statement.setInt(3, categoryId);
            statement.setDate(4, transactionDate);
            statement.setString(5, paymentMethod);
            statement.setString(6, type);
            statement.setInt(7, id);

            int rowsUpdated = statement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

        } catch (SQLException e) {
            System.out.println("Could not update expense.");
            e.printStackTrace();
        }
    }

    // DELETE: Delete an expense by ID
    public void deleteExpense(int id) {

        String sql = "DELETE FROM expense_transaction WHERE id = ?";

        try (
            Connection connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);

        } catch (SQLException e) {
            System.out.println("Could not delete expense.");
            e.printStackTrace();
        }
    }
}