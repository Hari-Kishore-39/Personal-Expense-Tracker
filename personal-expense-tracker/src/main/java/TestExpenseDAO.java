import java.math.BigDecimal;
import java.sql.Date;

public class TestExpenseDAO {

    public static void main(String[] args) {

        ExpenseDAO dao = new ExpenseDAO();

        // CREATE
//        dao.addExpense(
//                "Lunch",
//                new BigDecimal("250.00"),
//                1,
//                Date.valueOf("2026-11-21"),
//                "Cash",
//                "EXPENSE"
//        );

        // READ all
        for (String expense : dao.getAllExpenses()) {
            System.out.println(expense);
        }

        // READ one
//        System.out.println(dao.getExpenseById(1));

        // UPDATE
//        dao.updateExpense(
//                1,
//                "Dinner",
//                new BigDecimal("350.00"),
//                1,
//                Date.valueOf("2026-11-21"),
//                "Card",
//                "EXPENSE"
//        );

        
//        dao.deleteExpense(6);
    }
}