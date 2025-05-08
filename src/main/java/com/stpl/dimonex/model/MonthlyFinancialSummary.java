package com.stpl.dimonex.model;

public class MonthlyFinancialSummary {
    private int month;
    private int year;
    private double totalExpense;
    private double profit;

    public MonthlyFinancialSummary(int month, int year, double totalExpense, double profit) {
        this.month = month;
        this.year = year;
        this.totalExpense = totalExpense;
        this.profit = profit;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public double getProfit() {
        return profit;
    }

    public String getMonthYearLabel() {
        return String.format("%02d/%d", month, year);
    }
}
