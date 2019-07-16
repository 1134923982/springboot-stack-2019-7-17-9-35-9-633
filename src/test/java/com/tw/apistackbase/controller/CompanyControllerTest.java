package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.CompanyRespository;
import com.tw.apistackbase.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
//    private CompanyController companyController;
    @MockBean
    private CompanyRespository mockCompanyRespository;


    @Test
    public void should_return_companies_when_request_all_companies_api() throws Exception {
        mockCompanyRespository = Mockito.mock(CompanyRespository.class);
        List<Company> mockList = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"male","hali",20000,28));
        employees.add(new Employee(2,"female","sherry",10000,23));
        mockList.add(new Company(1,"alibaba",employees));
        Mockito.when(mockCompanyRespository.getCompanies()).thenReturn(mockList);

        mockMvc.perform(get("/companies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"companyNumber\": \"alibaba\",\n" +
                        "        \"employees\": [\n" +
                        "            {\n" +
                        "                \"id\": 1,\n" +
                        "                \"gender\": \"male\",\n" +
                        "                \"name\": \"hali\",\n" +
                        "                \"salary\": 20000,\n" +
                        "                \"age\": 28\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"id\": 2,\n" +
                        "                \"gender\": \"female\",\n" +
                        "                \"name\": \"sherry\",\n" +
                        "                \"salary\": 10000,\n" +
                        "                \"age\": 23\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 2,\n" +
                        "        \"companyNumber\": \"huawei\",\n" +
                        "        \"employees\": [\n" +
                        "            {\n" +
                        "                \"id\": 1,\n" +
                        "                \"gender\": \"male\",\n" +
                        "                \"name\": \"hali\",\n" +
                        "                \"salary\": 20000,\n" +
                        "                \"age\": 28\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"id\": 2,\n" +
                        "                \"gender\": \"female\",\n" +
                        "                \"name\": \"sherry\",\n" +
                        "                \"salary\": 10000,\n" +
                        "                \"age\": 23\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }\n" +
                        "]"));
    }
}