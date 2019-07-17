package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.CompanyRespository;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.entity.EmployeeRespository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRespository mockEmployeeRespository;

    @Before
    public void setUp() throws Exception{

    }


    @Test
    public void should_return_employees_when_request_all_employees_api() throws Exception {
        List<Employee> mockList = new ArrayList<>();
        mockList.add(new Employee(1,"male","hali",20000,28));
        mockList.add(new Employee(2,"female","sherry",10000,23));
        Mockito.when(mockEmployeeRespository.getEmployees()).thenReturn(mockList);

        mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"name\": \"hali\",\n" +
                        "        \"salary\": 20000,\n" +
                        "        \"age\": 28\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 2,\n" +
                        "        \"gender\": \"female\",\n" +
                        "        \"name\": \"sherry\",\n" +
                        "        \"salary\": 10000,\n" +
                        "        \"age\": 23\n" +
                        "    }\n" +
                        "]"));
    }
    @Test
    public void should_return_a_employee_when_request_a_employee_id_api() throws Exception {
        List<Employee> mockList = new ArrayList<>();
        mockList.add(new Employee(1,"male","hali",20000,28));
        mockList.add(new Employee(2,"female","sherry",10000,23));
        Mockito.when(mockEmployeeRespository.getEmployees()).thenReturn(mockList);

        mockMvc.perform(get("/employees/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"name\": \"hali\",\n" +
                        "    \"salary\": 20000,\n" +
                        "    \"age\": 28\n" +
                        "}"));
    }

    @Test
    public void should_return_employeeS_when_request_employees_by_page_api() throws Exception {
        List<Employee> mockList = new ArrayList<>();
        mockList.add(new Employee(1,"male","hali",20000,28));
        mockList.add(new Employee(2,"female","sherry",10000,23));
        Mockito.when(mockEmployeeRespository.getEmployees()).thenReturn(mockList);

        mockMvc.perform(get("/employees?page=2&pageSize=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 2,\n" +
                        "        \"gender\": \"female\",\n" +
                        "        \"name\": \"sherry\",\n" +
                        "        \"salary\": 10000,\n" +
                        "        \"age\": 23\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    public void should_return_employeeS_when_request_employees_by_gender_api() throws Exception {
        List<Employee> mockList = new ArrayList<>();
        mockList.add(new Employee(1,"male","hali",20000,28));
        mockList.add(new Employee(2,"female","sherry",10000,23));
        Mockito.when(mockEmployeeRespository.getEmployees()).thenReturn(mockList);

        mockMvc.perform(get("/employees?gender=male"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"name\": \"hali\",\n" +
                        "        \"salary\": 20000,\n" +
                        "        \"age\": 28\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    public void should_return_new_company_when_request_add_company_api() throws Exception {
        List<Employee> mockList = new ArrayList<>();
        mockList.add(new Employee(1,"male","hali",20000,28));
        mockList.add(new Employee(2,"female","sherry",10000,23));
        Mockito.when(mockEmployeeRespository.getEmployees()).thenReturn(mockList);

        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"name\": \"halibote\",\n" +
                        "        \"salary\": 10000,\n" +
                        "        \"age\": 22\n" +
                        "}"))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 3,\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"name\": \"halibote\",\n" +
                        "    \"salary\": 10000,\n" +
                        "    \"age\": 22\n" +
                        "}"));
    }

    @Test
    public void should_return_updated_company_when_request_update_a_company_api() throws Exception {
        List<Employee> mockList = new ArrayList<>();
        mockList.add(new Employee(1,"male","hali",20000,28));
        mockList.add(new Employee(2,"female","sherry",10000,23));
        Mockito.when(mockEmployeeRespository.getEmployees()).thenReturn(mockList);

        mockMvc.perform(put("/employees/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"name\": \"halibote\",\n" +
                        "        \"salary\": 10000,\n" +
                        "        \"age\": 22\n" +
                        "}"))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"name\": \"halibote\",\n" +
                        "    \"salary\": 10000,\n" +
                        "    \"age\": 22\n" +
                        "}"));
    }


}