package com.project.expensemanager.controller;

import com.project.expensemanager.dto.CategoryExpenseDTO;
import com.project.expensemanager.entity.Expense;
import com.project.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    public ExpenseController() {
        System.out.println("✅ ExpenseController loaded!");
    }

    // Get all expenses
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    // Add expense
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.createExpense(expense);
    }

    // Get expense by ID
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    // Delete expense
    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "Expense deleted successfully!";
    }

    // ✅ Update Expense by ID
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseService.updateExpense(id, expense);
    }

    // ✅ Get Expenses by Category
    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(@PathVariable String category) {
        return expenseService.getExpensesByCategory(category);
    }

    // ✅ Get Expenses by Date
    @GetMapping("/date/{date}")
    public List<Expense> getExpensesByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return expenseService.getExpensesByDate(localDate);
    }

    // ✅ Get Expenses Between Dates
    @GetMapping("/daterange")
    public List<Expense> getExpensesBetweenDates(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return expenseService.getExpensesBetweenDates(startDate, endDate);
    }

    // ✅ Get Total Expense Amount
    @GetMapping("/total")
    public Map<String, Double> getTotalExpenseAmount() {
        double total = expenseService.getTotalExpenseAmount();
        return Map.of("totalAmount", total);
    }

    // ✅ Search Expenses by Title
    @GetMapping("/search")
    public List<Expense> searchExpensesByTitle(@RequestParam String keyword) {
        return expenseService.searchExpensesByTitle(keyword);
    }

    @GetMapping("/daily-percentage/{date}")
    public List<CategoryExpenseDTO> getDailyCategoryWisePercentage(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return expenseService.getDailyCategoryWisePercentage(localDate);
    }
}

