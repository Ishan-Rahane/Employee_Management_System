package com.htek.model;

import com.aerospike.mapper.annotations.*;

@AerospikeRecord(namespace = "test", set = "e")
//defines how the objects of the class should be stored in Aerospike.
public class Employee {
    @AerospikeKey
    @AerospikeBin
    private int id;
    @AerospikeBin
    private String name;

    @AerospikeBin
    private String password;
    @AerospikeBin
    private String email;
    @AerospikeBin
    private int sal;
    @AerospikeBin
    private String JoiningDate;

    @AerospikeBin
    private long accountNumber;

    @AerospikeEmbed
    @AerospikeBin
    private Department department;

    @AerospikeEnum
    private Role role;

    public Employee() {
    }

    public Employee(int id, String name,String password, String email, int sal, String joiningDate,long accountNumber, Department department, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.sal = sal;
        this.JoiningDate = joiningDate;
        this.accountNumber=accountNumber;
        this.department = department;
        this.role=role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public String getJoiningDate() {
        return JoiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        JoiningDate = joiningDate;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sal=" + sal +
                ", JoiningDate='" + JoiningDate + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", department=" + department + '\''+
                ", role=" + role +
                '}';
    }
}
