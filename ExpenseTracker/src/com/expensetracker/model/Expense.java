package com.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    private int id;
    private String title;
    private BigDecimal amount;
    private int categoryId;
    private String categoryName;
    private LocalDate expenseDate;
    private String notes;

    // Constructor for adding new expense
    public Expense(String title, BigDecimal amount, int categoryId, LocalDate expenseDate, String notes) {
        this.title = title;
        this.amount = amount;
        this.categoryId = categoryId;
        this.expenseDate = expenseDate;
        this.notes = notes;
    }

    // Full constructor (from DB)
    public Expense(int id, String title, BigDecimal amount, int categoryId,
                   String categoryName, LocalDate expenseDate, String notes) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.expenseDate = expenseDate;
        this.notes = notes;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public BigDecimal getAmount() { return amount; }
    public int getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public LocalDate getExpenseDate() { return expenseDate; }
    public String getNotes() { return notes; }
}