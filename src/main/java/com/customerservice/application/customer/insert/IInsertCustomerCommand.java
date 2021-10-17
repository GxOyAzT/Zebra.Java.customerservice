package com.customerservice.application.customer.insert;

import com.customerservice.domain.entities.CustomerEntity;
import com.customerservice.domain.exceptions.bussineslogic.InvalidModelException;

import java.io.IOException;

public interface IInsertCustomerCommand {
    void insert(CustomerEntity customerModel) throws InvalidModelException, IOException;
}
