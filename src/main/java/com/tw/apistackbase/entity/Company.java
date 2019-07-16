package com.tw.apistackbase.entity;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private long id;
    private String companyNumber;
    private List<Employee> employees= new ArrayList<>();

    public Company(long id, String companyNumber, List<Employee> employees) {
        this.id = id;
        this.companyNumber = companyNumber;
        this.employees = employees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
