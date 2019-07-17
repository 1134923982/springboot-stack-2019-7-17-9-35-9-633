package com.tw.apistackbase.entity;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private long id;
    private String companyName;
    private List<Employee> employees= new ArrayList<>();

    public Company() {
    }

    public Company(long id, String companyName, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employees = employees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
