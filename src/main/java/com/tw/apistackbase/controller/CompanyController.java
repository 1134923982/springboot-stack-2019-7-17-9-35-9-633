package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.CompanyRespository;
import com.tw.apistackbase.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
public class CompanyController {
    private CompanyRespository companyRespository = new CompanyRespository();


    public CompanyController() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "male", "hali", 20000, 28));
        employees.add(new Employee(2, "female", "sherry", 10000, 23));

        this.companyRespository.add(new Company(1, "alibaba", employees));
        this.companyRespository.add(new Company(2, "huawei", employees));
    }

    @GetMapping("/companies")
    public ResponseEntity getCompanies(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "0") Integer pageSize) {
        if (pageSize == 0 || page == 0)
            return ResponseEntity.ok(companyRespository.getCompanies());

        if(page*pageSize>companyRespository.getCompanies().size())
            pageSize = companyRespository.getCompanies().size();
        return ResponseEntity.ok(companyRespository.getCompanies().subList((page*pageSize - 1), pageSize));

    }

    @GetMapping("/companies/{id}")
    public ResponseEntity getCompany(@PathVariable long id) {
        Company company = companyRespository.getCompanies().stream()
                .filter(element -> element.getId() == id)
                .findFirst().orElse(null);
        if (company != null) {
            return ResponseEntity.ok(company);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/companies/{id}/employees")
    public ResponseEntity getEmployeesByCompanyId(@PathVariable long id) {
        Company company = companyRespository.getCompanies().stream()
                .filter(element -> element.getId() == id)
                .findFirst().orElse(null);
        if (company != null) {
            return ResponseEntity.ok(company.getEmployees());
        }
        return ResponseEntity.notFound().build();
    }

}
