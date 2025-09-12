package com.project.expensemanager.service;

import com.project.expensemanager.entity.Expense;
import com.project.expensemanager.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Create expense
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // Get expense by ID
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        return expense.orElse(null);
    }

    // Delete expense
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    // ✅ Update Expense
    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id " + id));

        expense.setTitle(expenseDetails.getTitle());
        expense.setDescription(expenseDetails.getDescription());
        expense.setAmount(expenseDetails.getAmount());
        expense.setCategory(expenseDetails.getCategory());
        expense.setDate(expenseDetails.getDate());

        return expenseRepository.save(expense);
    }

    // ✅ Get by Category
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategoryIgnoreCase(category);
    }

    // ✅ Get by Date
    public List<Expense> getExpensesByDate(LocalDate date) {
        return expenseRepository.findByDate(date);
    }

    // ✅ Get between dates
    public List<Expense> getExpensesBetweenDates(LocalDate start, LocalDate end) {
        return expenseRepository.findByDateBetween(start, end);
    }

    // ✅ Get total amount
    public double getTotalExpenseAmount() {
        return expenseRepository.findAll()
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // ✅ Search by Title
    public List<Expense> searchExpensesByTitle(String keyword) {
        return expenseRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
