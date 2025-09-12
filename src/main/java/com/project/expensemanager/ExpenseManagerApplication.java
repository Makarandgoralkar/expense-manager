package com.project.expensemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.project.expensemanager")
public class ExpenseManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExpenseManagerApplication.class, args);
    }
}
