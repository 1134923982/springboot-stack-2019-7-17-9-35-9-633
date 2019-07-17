package com.tw.apistackbase.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CompanyRespository {
    private List<Company> companies=new ArrayList<>();

    public CompanyRespository() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "male", "hali", 20000, 28));
        employees.add(new Employee(2, "female", "sherry", 10000, 23));

        this.companies.add(new Company(1, "alibaba", employees));
        this.companies.add(new Company(2, "huawei", employees));
    }

    public void add(Company company){
        this.companies.add(company);
    }

    public void delete(Company company){
        this.companies.remove(company);
    }

    public List<Company> getCompanies() {
        return companies;
    }
}
