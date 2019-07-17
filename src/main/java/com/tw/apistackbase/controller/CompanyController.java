package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.CompanyRespository;
import com.tw.apistackbase.entity.Employee;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok(companyRespository.getCompanies().subList(((page-1)*pageSize), pageSize));

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

    @PostMapping("/companies")
    public ResponseEntity addCompany(@RequestBody Company company) {
        long maxId=companyRespository.getCompanies().stream().mapToLong(Company::getId).max().getAsLong();
        Company newCompany = new Company();
        newCompany.setCompanyName(company.getCompanyName());
        newCompany.setEmployees(company.getEmployees());
        newCompany.setId(maxId+1);
        companyRespository.add(newCompany);
        return ResponseEntity.ok(newCompany);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity updateCompany(@PathVariable long id,@RequestBody Company company) {
        Company updateCompany = companyRespository.getCompanies().stream()
                .filter(element->element.getId()==id)
                .findFirst()
                .orElse(null);
        if(updateCompany!=null){
            updateCompany.setEmployees(company.getEmployees());
            updateCompany.setCompanyName(company.getCompanyName());
            return ResponseEntity.ok(updateCompany);
        }
        return ResponseEntity.notFound().build();
    }

}
