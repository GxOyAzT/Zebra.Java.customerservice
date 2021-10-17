package com.customerservice.api.controller;

import com.customerservice.application.customer.insert.IInsertCustomerCommand;
import com.customerservice.domain.dtos.customer.CustomerCreateDto;
import com.customerservice.domain.entities.CustomerEntity;
import com.customerservice.domain.exceptions.bussineslogic.InvalidModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "customer")
public class CustomerController {

    private final IInsertCustomerCommand insertCustomerCommand;

    @Autowired
    public CustomerController(IInsertCustomerCommand insertCustomerCommand) {
        this.insertCustomerCommand = insertCustomerCommand;
    }

    @PostMapping
    public void insertCustomer(@RequestBody CustomerCreateDto customerCreateDto) throws InvalidModelException, IOException {
        var customer = new CustomerEntity();
        customer.setUserId(customerCreateDto.getUserId());
        customer.setFullName(customerCreateDto.getFullName());
        customer.setGender(customerCreateDto.getGender());
        customer.setDob(customerCreateDto.getDob());

        insertCustomerCommand.insert(customer);
    }
}
