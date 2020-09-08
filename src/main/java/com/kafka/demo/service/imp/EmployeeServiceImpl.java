package com.kafka.demo.service.imp;

import com.google.gson.Gson;
import com.kafka.demo.model.Employee;
import com.kafka.demo.repository.EmployeeRepository;
import com.kafka.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void insertToDb(String message) {
        Employee employee = new Gson().fromJson(message, Employee.class);
        employeeRepository.save(employee);
    }
}
