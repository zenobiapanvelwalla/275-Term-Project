package com.backend.netflix.beans;

import java.math.BigDecimal;
import java.util.List;

public class MonthlyIncome {

    private List<String> month;
    private List<BigDecimal> income;

    public List<BigDecimal> getIncome() {
        return income;
    }

    public void setIncome(List<BigDecimal> income) {
        this.income = income;
    }

    public List<String> getMonth() {
        return month;
    }

    public void setMonth(List<String> month) {
        this.month = month;
    }



}
