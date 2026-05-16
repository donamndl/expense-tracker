package com.expensetracker.service;

import com.expensetracker.dao.CategoryDAO;
import com.expensetracker.dao.ExpenseDAO;
import com.expensetracker.model.Category;
import com.expensetracker.model.Expense;

import java.math.BigDecimal;
import java.util.List;

public class ExpenseService {

    private final ExpenseDAO expenseDAO = new ExpenseDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();

    public boolean addExpense(Expense expense) {
        if (expense.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("❌ Amount must be greater than zero.");
            return false;
        }
        if (expense.getTitle() == null || expense.getTitle().trim().isEmpty()) {
            System.out.println("❌ Title cannot be empty.");
            return false;
        }
        return expenseDAO.addExpense(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseDAO.getAllExpenses();
    }

    public List<String> getMonthlySummary(int year, int month) {
        if (month < 1 || month > 12) {
            System.out.println("❌ Invalid month. Enter 1–12.");
            return List.of();
        }
        return expenseDAO.getMonthlySummary(year, month);
    }

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
}