package com.project.expensemanager.controller;

import com.project.expensemanager.dto.CategoryExpenseDTO;
import com.project.expensemanager.service.ExpenseService;
import jakarta.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/charts")
public class ChartController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/daily/{date}")
    public void getDailyExpenseChart(@PathVariable String date, HttpServletResponse response) throws IOException {
        LocalDate localDate = LocalDate.parse(date);

        // fetch daily percentages
        List<CategoryExpenseDTO> percentages = expenseService.getDailyCategoryWisePercentage(localDate);

        if (percentages.isEmpty()) {
            response.setContentType("text/plain");
            response.getWriter().write("No data found for " + date);
            return;
        }

        // dataset
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        for (CategoryExpenseDTO dto : percentages) {
            dataset.setValue(dto.getCategory(), dto.getPercentage());
        }

        // create chart
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Daily Expenses (" + date + ")",
                dataset,
                true,  // include legend
                true,
                false
        );

        // Configure labels to show "Category: XX.XX%"
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}: {2}", new DecimalFormat("0.00"), new DecimalFormat("0.00%")
        ));

        // return as PNG
        response.setContentType("image/png");
        ChartUtils.writeChartAsPNG(response.getOutputStream(), pieChart, 600, 400);
    }
}
