package com.project.expensemanager.dto;

public class CategoryExpenseDTO {
    private String category;
    private double percentage;

    public CategoryExpenseDTO(String category, double percentage) {
        this.category = category;
        this.percentage = percentage;
    }

    public String getCategory() {
        return category;
    }

    public double getPercentage() {
        return percentage;
    }
}
