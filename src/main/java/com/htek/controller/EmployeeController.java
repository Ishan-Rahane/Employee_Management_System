package com.htek.controller;

import com.htek.dto.CustomResponse;
import com.htek.model.Employee;
import com.htek.service.EmployeeService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;


import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;

import javax.annotation.security.PermitAll;
import java.util.List;

@Controller("/emp")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class EmployeeController {
    @Inject
    EmployeeService employeeService;

    @Post("/add")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<CustomResponse> addEmployee(@Body Employee employee) {
        return HttpResponse.ok(new CustomResponse(employeeService.addEmployee(employee)));
    }

    @Get
    @Secured({"ROLE_MANAGER"})
    public Employee findByEmail(@QueryValue String email)
    {
        return employeeService.findByEmail(email);
    }

    @Get("/show")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<Employee>> getEmployee() {
        List<Employee> emp = employeeService.getAllEmployee();

        if (emp.size() >= 0) {
            return HttpResponse.ok().body(emp);
        }
        return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Get("/show/{id}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Employee> getEmployeeById(@PathVariable int id){
        Employee emp = employeeService.getEmployeeById(id);

        if(emp != null){
            return HttpResponse.ok().body(emp);
        }
        else
            return HttpResponse.status(HttpStatus.NOT_FOUND);
    }

    @Get("/show/{id}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Employee> getEmployeeByAccNumber(@PathVariable int accNumber){
        Employee emp = employeeService.getEmployeeById(accNumber);

        if(emp != null){
            return HttpResponse.ok().body(emp);
        }
        else
            return HttpResponse.status(HttpStatus.NOT_FOUND);
    }


    @Delete("/delete/{id}")
    @Secured({"ROLE_MANAGER"})
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<CustomResponse> deleteEmployeeById(@PathVariable int id){
        return HttpResponse.ok(new CustomResponse(employeeService.deleteEmployeeById(id)));
    }


    @Put("/update/{id}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<CustomResponse> updateEmployeeById(@Body Employee employee,@PathVariable int id){
        return HttpResponse.ok(new CustomResponse(employeeService.updateEmployeeById(employee,id)));
    }
}
