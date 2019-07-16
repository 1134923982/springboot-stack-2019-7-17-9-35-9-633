package com.tw.apistackbase.entity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRespository {
    private List<Employee> employees=new ArrayList<>();

    public void add(Employee employee){
        this.employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
