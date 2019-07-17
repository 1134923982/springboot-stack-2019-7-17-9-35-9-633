package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.entity.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/employees")
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        long maxId=employeeRespository.getEmployees().stream().mapToLong(Employee::getId).max().getAsLong();
        employee.setId(maxId+1);
        employeeRespository.add(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity updateEmployee(@PathVariable long id,@RequestBody Employee employee) {
        Employee updateEmployee = employeeRespository.getEmployees().stream()
                .filter(element->element.getId()==id)
                .findFirst()
                .orElse(null);
        if(updateEmployee!=null){
            updateEmployee.setAge(employee.getAge());
            updateEmployee.setGender(employee.getGender());
            updateEmployee.setName(employee.getName());
            updateEmployee.setSalary(employee.getSalary());
            return ResponseEntity.ok(updateEmployee);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable long id) {
        Employee employee = employeeRespository.getEmployees().stream()
                .filter(element->element.getId()==id)
                .findFirst()
                .orElse(null);
        if(employee!=null){
            employeeRespository.delete(employee);
            List<Employee> employees = employeeRespository.getEmployees();
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

}
