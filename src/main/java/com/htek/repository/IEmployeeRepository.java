package com.htek.repository;


import com.htek.email.EmailDetails;
import com.htek.model.Employee;

import java.util.List;


public interface IEmployeeRepository {
    Object addEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Employee getEmployeeById(int id);

    Object deleteEmployeeById(int id);

    Object updateEmployeeById(Employee employee, int id);

    EmailDetails findByEmpEmail(String empEmail);


    //ISHAN Method
    
//    public String addEmployee(Employee employee);
//
//    public List<Employee> getAllEmployee();
//
//    public Employee getEmployeeById(int id);
//
//    public String deleteEmployeeById(int id);
//
//    public String updateEmployeeById(Employee employee, int id);
//
//    void findByEmail(String email);
}