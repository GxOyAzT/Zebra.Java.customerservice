package com.customerservice.application.customer.insert;

import com.customerservice.domain.entities.CustomerEntity;
import com.customerservice.domain.exceptions.bussineslogic.InvalidModelException;

public interface IInsertCustomerCommand {
    void Insert(CustomerEntity customerModel) throws InvalidModelException;
}
