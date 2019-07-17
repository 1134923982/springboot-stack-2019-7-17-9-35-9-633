package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.entity.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRespository employeeRespository;


//    public EmployeeController() {
//        this.employeeRespository.add(new Employee(1,"male","hali",20000,28));
//        this.employeeRespository.add(new Employee(2,"male","guci",20000,28));
////        this.companyRespository.add(new Company(2,"huawei"));
//    }
//
    @GetMapping("/employees")
    public ResponseEntity getEmployees(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "0") int pageSize, @RequestParam(defaultValue = "") String gender){
        if(!gender.equals("")){
            return ResponseEntity.ok(employeeRespository.getEmployees().stream().filter(element->element.getGender().equals(gender)));
        }
        if ((pageSize == 0 || page == 0))
            return ResponseEntity.ok(employeeRespository.getEmployees());

        if(page*pageSize>employeeRespository.getEmployees().size())
            pageSize = employeeRespository.getEmployees().size();
        return ResponseEntity.ok(employeeRespository.getEmployees().subList(((page-1)*pageSize), page*pageSize));
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployeeById(@PathVariable long id){
        Employee employee = employeeRespository.getEmployees().stream()
                .filter(element -> element.getId() == id)
                .findFirst().orElse(null);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

}
