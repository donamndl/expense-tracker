package com.expensetracker.ui;

import com.expensetracker.model.Category;
import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    static Scanner sc = new Scanner(System.in);
    static ExpenseService expenseService = new ExpenseService(); // ✅ Only service, no DAOs here

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     💰 EXPENSE TRACKER       ║");
        System.out.println("╚══════════════════════════════╝");

        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Monthly Summary");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> viewExpenses();
                case 3 -> monthlySummary();
                case 4 -> { System.out.println("Goodbye! 👋"); return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void addExpense() {
        System.out.println("\n--- Add New Expense ---");

        System.out.print("Title: ");
        String title = sc.nextLine();

        System.out.print("Amount (₹): ");
        BigDecimal amount = new BigDecimal(sc.nextLine().trim());

        // ✅ Fetched via service layer
        List<Category> categories = expenseService.getAllCategories();
        System.out.println("Categories:");
        for (Category c : categories) {
            System.out.println("  " + c.getId() + ". " + c.getName());
        }
        System.out.print("Choose category number: ");
        int catId = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Date (yyyy-MM-dd) [blank = today]: ");
        String dateInput = sc.nextLine().trim();
        LocalDate date = dateInput.isEmpty() ? LocalDate.now()
                : LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.print("Notes (optional): ");
        String notes = sc.nextLine();

        Expense expense = new Expense(title, amount, catId, date, notes);

        // ✅ Validation + DB insert handled by service
        boolean success = expenseService.addExpense(expense);
        System.out.println(success ? "✅ Expense added!" : "❌ Failed to add expense.");
    }

    static void viewExpenses() {
        System.out.println("\n--- All Expenses ---");

        // ✅ Fetched via service layer
        List<Expense> expenses = expenseService.getAllExpenses();

        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.printf("%-4s %-20s %-12s %-15s %-12s%n", "ID", "Title", "Amount", "Category", "Date");
        System.out.println("─".repeat(65));
        for (Expense e : expenses) {
            System.out.printf("%-4d %-20s ₹%-11.2f %-15s %-12s%n",
                e.getId(), e.getTitle(), e.getAmount(),
                e.getCategoryName(), e.getExpenseDate());
        }
    }

    static void monthlySummary() {
        System.out.println("\n--- Monthly Summary ---");

        System.out.print("Enter year (e.g. 2025): ");
        int year = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Enter month (1-12): ");
        int month = Integer.parseInt(sc.nextLine().trim());

        // ✅ Validation + DB query handled by service
        List<String> summary = expenseService.getMonthlySummary(year, month);

        if (summary.isEmpty()) {
            System.out.println("No data for this period.");
        } else {
            System.out.println("\nCategory             Amount");
            System.out.println("─".repeat(35));
            summary.forEach(System.out::println);
        }
    }
}