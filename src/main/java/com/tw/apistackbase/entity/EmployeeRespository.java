package com.tw.apistackbase.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class EmployeeRespository {
    private List<Employee> employees=new ArrayList<>();

    public EmployeeRespository() {
        this.employees.add(new Employee(1,"male","hali",20000,28));
        this.employees.add(new Employee(2,"female","sherry",10000,23));
    }

    public void add(Employee employee){
        this.employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
