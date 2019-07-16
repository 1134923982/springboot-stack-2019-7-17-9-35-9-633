package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.CompanyRespository;
import com.tw.apistackbase.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
public class CompanyController {
    private CompanyRespository companyRespository = new CompanyRespository();


    public CompanyController() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"male","hali",20000,28));
        employees.add(new Employee(2,"female","sherry",10000,23));

        this.companyRespository.add(new Company(1,"alibaba",employees));
        this.companyRespository.add(new Company(2,"huawei",employees));
    }

    @GetMapping("/companies")
    public ResponseEntity getCompanies(){
        return ResponseEntity.ok(companyRespository.getCompanies());
    }

}
