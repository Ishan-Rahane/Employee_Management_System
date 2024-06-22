package com.htek.service;

import com.aerospike.client.query.Filter;
import com.aerospike.client.query.Statement;
import com.htek.configuration.AeromapperConfiguration;
import com.htek.email.EmailDetails;
import com.htek.model.Employee;
import com.htek.repository.EmployeeRepository;
import com.htek.repository.IEmployeeRepository;

import com.htek.security.BCryptPasswordEncoderService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class EmployeeService {
    @Inject
    IEmployeeRepository repository;

    @Inject
    BCryptPasswordEncoderService bCryptPasswordEncoderService;


    public Object addEmployee(Employee employee) {
        String encodedPassword = bCryptPasswordEncoderService.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        return repository.addEmployee(employee);
    }

    public Employee findByEmail(String empEmail) {
        EmailDetails emailDetails = repository.findByEmpEmail(empEmail);
        return this.getEmployeeById(emailDetails.getId());
    }
    public List<Employee> getAllEmployee() {
        return repository.getAllEmployee();
    }

    public Employee getEmployeeById(int id) {
        return repository.getEmployeeById(id);
    }

    public Object deleteEmployeeById(int id) {
        return repository.deleteEmployeeById(id);
    }

    public Object updateEmployeeById(Employee employee, int id) {
        return repository.updateEmployeeById(employee,id);
    }



   // Ishan method

//    @Override
//    public String addEmployee(Employee employee) {
//        aeromapperConfiguration.getMapper().save(employee);
//        if(aeromapperConfiguration != null){
//            EmailService.sendEmail(new EmailDetails("Employee Alert !!!", "Congratulations "+employee.getName()+", Your Employee Id is "+employee.getId(), employee.getEmail()));
//        }
//        return "Employee added successfully!!!!";
//    }
//
//    @Override
//    public List<Employee> getAllEmployee() {
//       return aeromapperConfiguration.getMapper().scan(Employee.class);
//    }
//
//    @Override
//    public Employee getEmployeeById(int id) {
//        return aeromapperConfiguration.getMapper().read(Employee.class,id);
//    }
//
//    @Override
//    public String deleteEmployeeById(int id) {
//        aeromapperConfiguration.getMapper().delete(Employee.class,id);
//        return "Employee deleted successfully!!!";
//    }
//
//    @Override
//    public String updateEmployeeById(Employee employee, int id) {
//        Employee emp = aeromapperConfiguration.getMapper().read(Employee.class,id);
//
//        emp.setName(employee.getName());
//        emp.setEmail(employee.getEmail());
//        emp.setSal(employee.getSal());
//        emp.setJoiningDate(employee.getJoiningDate());
//        emp.setDepartment(employee.getDepartment());
//
//        aeromapperConfiguration.getMapper().save(emp);
//        return "Employee updated successfully!!!";
//    }
//
//    @Override
//    public void findByEmail(String email) {
//        aeromapperConfiguration.getMapper().read(EmailDetails.class,email);
//    }

}
