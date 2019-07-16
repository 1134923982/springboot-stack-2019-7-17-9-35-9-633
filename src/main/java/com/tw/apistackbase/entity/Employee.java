package com.tw.apistackbase.entity;

public class Employee {
    private long id;
    private String gender;
    private String name;
    private double salary;
    private int age;

    public Employee(long id, String gender, String name, double salary, int age) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
