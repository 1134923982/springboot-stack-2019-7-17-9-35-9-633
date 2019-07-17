package com.tw.apistackbase.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Company {
    @Id
    @GeneratedValue
    @Column(name = "id",unique = true,nullable = false,insertable = true,updatable = false,length = 20)
    private long id;
    private String companyName;
    //忽略表中数据
    @OneToMany(cascade = CascadeType.ALL)
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
