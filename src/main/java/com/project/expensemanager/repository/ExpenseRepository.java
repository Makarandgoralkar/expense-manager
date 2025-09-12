package com.project.expensemanager.repository;

import com.project.expensemanager.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategoryIgnoreCase(String category);
    List<Expense> findByDate(LocalDate date);
    List<Expense> findByDateBetween(LocalDate start, LocalDate end);
    List<Expense> findByTitleContainingIgnoreCase(String keyword);
}
