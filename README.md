# 💰 Expense Tracker
A console-based **Expense Tracker** application built with **Java**, **JDBC**, and **MySQL**. Track your daily spending, organize expenses by category, and view monthly summaries — all from the terminal.

---

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Running the Application](#running-the-application)
- [Usage Guide](#usage-guide)
- [Architecture](#architecture)

---

## ✨ Features

- ➕ **Add Expenses** — Log expenses with a title, amount, category, date, and optional notes
- 🗂️ **Categorize Spending** — Organize expenses into categories (Food, Transport, Housing, etc.)
- 📊 **Monthly Summary** — View a breakdown of total spending per category for any month
- 📋 **View All Expenses** — List all recorded expenses in a clean tabular format

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 25 |
| Database Connectivity | JDBC |
| Database | MySQL |
| IDE | Eclipse IDE |
| Driver | MySQL Connector/J |

---

## ✅ Prerequisites

Make sure the following are installed before getting started:

- [JDK 11+](https://www.oracle.com/java/technologies/downloads/)
- [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/)
- [MySQL Server 8.x](https://dev.mysql.com/downloads/mysql/)
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) (JDBC Driver JAR)

---

## ▶️ Running the Application

1. Right-click `MainApp.java`
2. Select `Run As` → `Java Application`
3. The app will launch in the Eclipse **Console** tab

---

## 📖 Usage Guide

Once the application starts, you'll see the main menu:

```
╔══════════════════════════════╗
║     💰 EXPENSE TRACKER       ║
╚══════════════════════════════╝

===== MAIN MENU =====
1. Add Expense
2. View All Expenses
3. Monthly Summary
4. Exit
```

**Adding an Expense**
- Enter a title, amount, pick a category from the list, enter the date (or press Enter for today), and optionally add notes.

**Viewing All Expenses**
- Displays all expenses in a table with ID, title, amount, category, and date.

**Monthly Summary**
- Enter a year and month (e.g. `2025` and `5`) to see spending totals broken down by category.

---

## 🏗️ Architecture

This project follows a **3-layer architecture**:

```
┌─────────────────────────────────┐
│        UI Layer (MainApp)        │  ← Handles user input/output only
├─────────────────────────────────┤
│     Service Layer (Service)      │  ← Business logic & validation
├─────────────────────────────────┤
│       DAO Layer (DAO)            │  ← All database operations
├─────────────────────────────────┤
│         MySQL Database           │
└─────────────────────────────────┘
```

| Layer | Responsibility |
|-------|---------------|
| UI | Console menus, reading user input, displaying results |
| Service | Input validation, business rules, coordinating DAOs |
| DAO | SQL queries, JDBC connections, result set mapping |
| Model | Plain Java objects (POJO) representing data |

---

## 🚀 Future Enhancements

- [ ] Delete / Edit expenses
- [ ] Export monthly summary to CSV
- [ ] Filter expenses by date range or category
- [ ] Swing-based GUI
- [ ] Budget limit alerts per category
