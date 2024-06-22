package com.htek.repository;

import com.htek.configuration.AeromapperConfiguration;
import com.htek.email.EmailDetails;
import com.htek.model.Employee;
import com.htek.service.EmailService;
import io.micronaut.context.annotation.Bean;
import jakarta.inject.Inject;

import java.util.List;

@Bean
public class EmployeeRepository implements IEmployeeRepository{

    @Inject
    private AeromapperConfiguration aeromapperConfiguration;

    @Override
    public Object addEmployee(Employee employee) {
        aeromapperConfiguration.getMapper().save(employee);
        EmailDetails emailDetails = new EmailDetails(employee.getId(), employee.getEmail(), employee.getPassword());
        aeromapperConfiguration.getMapper().save(emailDetails);
        if(aeromapperConfiguration != null){
            EmailService.sendEmail(new EmailDetails("Employee Alert !!!", "Congratulations "+employee.getName()+", Your Employee Id is "+employee.getId(), employee.getEmail()));
        }
        return "Employee added successfully!!!!";
    }


    @Override
    public List<Employee> getAllEmployee() {
        return aeromapperConfiguration.getMapper().scan(Employee.class);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return aeromapperConfiguration.getMapper().read(Employee.class,id);
    }

    @Override
    public Object deleteEmployeeById(int id) {
        return aeromapperConfiguration.getMapper().delete(Employee.class,id);
    }

    @Override
    public Object updateEmployeeById(Employee employee, int id) {
        Employee emp = aeromapperConfiguration.getMapper().read(Employee.class,id);
        emp.setName(employee.getName());
        emp.setEmail(employee.getEmail());
        emp.setSal(employee.getSal());
        emp.setJoiningDate(employee.getJoiningDate());
        emp.setDepartment(employee.getDepartment());

        aeromapperConfiguration.getMapper().save(emp);
        return "Employee updated successfully!!!";
    }

    @Override
    public EmailDetails findByEmpEmail(String empEmail) {
        return aeromapperConfiguration.getMapper().read(EmailDetails.class,empEmail);
    }
}
