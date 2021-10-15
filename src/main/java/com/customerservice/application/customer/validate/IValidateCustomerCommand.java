package com.customerservice.application.customer.validate;

import com.customerservice.domain.entities.CustomerEntity;
import com.customerservice.domain.models.validation.ValidationResultModel;

public interface IValidateCustomerCommand {
    ValidationResultModel validateCustomer(CustomerEntity customer);
}
