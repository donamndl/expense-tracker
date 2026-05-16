package com.expensetracker.dao;

import com.expensetracker.db.DBConnection;
import com.expensetracker.model.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    // Add a new expense
    public boolean addExpense(Expense expense) {
        String sql = "INSERT INTO expenses (title, amount, category_id, expense_date, notes) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, expense.getTitle());
            pst.setBigDecimal(2, expense.getAmount());
            pst.setInt(3, expense.getCategoryId());
            pst.setDate(4, Date.valueOf(expense.getExpenseDate()));
            pst.setString(5, expense.getNotes());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding expense: " + e.getMessage());
            return false;
        }
    }

    // Get all expenses
    public List<Expense> getAllExpenses() {
        List<Expense> list = new ArrayList<>();
        String sql = "SELECT e.*, c.name AS category_name FROM expenses e " +
                     "JOIN categories c ON e.category_id = c.id ORDER BY e.expense_date DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Expense(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getBigDecimal("amount"),
                    rs.getInt("category_id"),
                    rs.getString("category_name"),
                    rs.getDate("expense_date").toLocalDate(),
                    rs.getString("notes")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching expenses: " + e.getMessage());
        }
        return list;
    }

    // Monthly summary by category
    public List<String> getMonthlySummary(int year, int month) {
        List<String> summary = new ArrayList<>();
        String sql = "SELECT c.name, SUM(e.amount) AS total " +
                     "FROM expenses e JOIN categories c ON e.category_id = c.id " +
                     "WHERE YEAR(e.expense_date) = ? AND MONTH(e.expense_date) = ? " +
                     "GROUP BY c.name ORDER BY total DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, year);
            pst.setInt(2, month);

            ResultSet rs = pst.executeQuery();
            double grandTotal = 0;

            while (rs.next()) {
                String line = String.format("%-20s ₹ %.2f", rs.getString("name"), rs.getDouble("total"));
                summary.add(line);
                grandTotal += rs.getDouble("total");
            }
            summary.add("─".repeat(35));
            summary.add(String.format("%-20s ₹ %.2f", "GRAND TOTAL", grandTotal));

        } catch (SQLException e) {
            System.err.println("Error generating summary: " + e.getMessage());
        }
        return summary;
    }
}