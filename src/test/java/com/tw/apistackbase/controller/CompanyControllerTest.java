package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.CompanyRespository;
import com.tw.apistackbase.entity.Employee;
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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

   // @Autowired
//    private CompanyController companyController;
    @MockBean
    private CompanyRespository mockCompanyRespository;

    @Before
    public void setUp() throws Exception{

    }


    @Test
    public void should_return_companies_when_request_all_companies_api() throws Exception {
//        mockCompanyRespository = Mockito.mock(CompanyRespository.class);
        List<Company> mockList = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"male","hali",20000,28));
        employees.add(new Employee(2,"female","sherry",10000,23));
        mockList.add(new Company(1,"alibaba",employees));
        Mockito.when(mockCompanyRespository.getCompanies()).thenReturn(mockList);

        mockMvc.perform(get("/companies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\n" +
                        "        \"id\": 1,\n" +
                        "        \"companyName\": \"alibaba\",\n" +
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
                        "    }]"));
    }

    @Test
    public void should_return_company_by_id_when_request_company_id_api() throws Exception {
//        mockCompanyRespository = Mockito.mock(CompanyRespository.class);
        List<Company> mockList = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"male","hali",20000,28));
        employees.add(new Employee(2,"female","sherry",10000,23));
        mockList.add(new Company(1,"alibaba",employees));
        Mockito.when(mockCompanyRespository.getCompanies()).thenReturn(mockList);

        mockMvc.perform(get("/companies/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"companyName\": \"alibaba\",\n" +
                        "    \"employees\": [\n" +
                        "        {\n" +
                        "            \"id\": 1,\n" +
                        "            \"gender\": \"male\",\n" +
                        "            \"name\": \"hali\",\n" +
                        "            \"salary\": 20000,\n" +
                        "            \"age\": 28\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 2,\n" +
                        "            \"gender\": \"female\",\n" +
                        "            \"name\": \"sherry\",\n" +
                        "            \"salary\": 10000,\n" +
                        "            \"age\": 23\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}"));
    }

    @Test
    public void should_return_employees_by_company_id_when_request_company_id_employees_api() throws Exception {
//        mockCompanyRespository = Mockito.mock(CompanyRespository.class);
        List<Company> mockList = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"male","hali",20000,28));
        employees.add(new Employee(2,"female","sherry",10000,23));
        mockList.add(new Company(1,"alibaba",employees));
        Mockito.when(mockCompanyRespository.getCompanies()).thenReturn(mockList);

        mockMvc.perform(get("/companies/1/employees"))
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
    public void should_return_employees_by_page_when_request_page_and_page_size_api() throws Exception {
//        mockCompanyRespository = Mockito.mock(CompanyRespository.class);
        List<Company> mockList = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"male","hali",20000,28));
        employees.add(new Employee(2,"female","sherry",10000,23));
        mockList.add(new Company(1,"alibaba",employees));
        mockList.add(new Company(2,"huawei",employees));
        Mockito.when(mockCompanyRespository.getCompanies()).thenReturn(mockList);

        mockMvc.perform(get("/companies?page=1&pageSize=3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"companyName\": \"alibaba\",\n" +
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
                        "        \"companyName\": \"huawei\",\n" +
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

    @Test
    public void should_return_new_company_when_request_add_company_api() throws Exception {
//        mockCompanyRespository = Mockito.mock(CompanyRespository.class);
        List<Company> mockList = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"male","hali",20000,28));
        employees.add(new Employee(2,"female","sherry",10000,23));
        mockList.add(new Company(1,"alibaba",employees));
        Mockito.when(mockCompanyRespository.getCompanies()).thenReturn(mockList);

        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "        \"companyName\": \"arraba\",\n" +
                        "        \"employees\": [\n" +
                        "            \n" +
                        "        ]\n" +
                        "}"))


                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 2,\n" +
                        "    \"companyName\": \"arraba\",\n" +
                        "    \"employees\": []\n" +
                        "}"));
    }

    @Test
    public void should_return_update_company_when_request_update_company_api() throws Exception {
//        mockCompanyRespository = Mockito.mock(CompanyRespository.class);
        List<Company> mockList = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"male","hali",20000,28));
        employees.add(new Employee(2,"female","sherry",10000,23));
        mockList.add(new Company(1,"alibaba",employees));
        Mockito.when(mockCompanyRespository.getCompanies()).thenReturn(mockList);

        mockMvc.perform(put("/companies/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "        \"companyName\": \"arraba\",\n" +
                        "        \"employees\": [\n" +
                        "            \n" +
                        "        ]\n" +
                        "}"))


                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"companyName\": \"arraba\",\n" +
                        "    \"employees\": []\n" +
                        "}"));
    }

    @Test
    public void should_return_other_companies_when_delete_a_company_api() throws Exception {
//        mockCompanyRespository = Mockito.mock(CompanyRespository.class);
        List<Company> mockList = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"male","hali",20000,28));
        employees.add(new Employee(2,"female","sherry",10000,23));
        mockList.add(new Company(1,"alibaba",employees));
        Mockito.when(mockCompanyRespository.getCompanies()).thenReturn(mockList);

        mockMvc.perform(delete("/companies/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"companyName\":\"alibaba\",\"employees\":[{\"id\":1,\"gender\":\"male\",\"name\":\"hali\",\"salary\":20000.0,\"age\":28},{\"id\":2,\"gender\":\"female\",\"name\":\"sherry\",\"salary\":10000.0,\"age\":23}]}]"));
    }
}