package com.customerservice.application.customer.insert;

import com.customerservice.application.customer.event.insert.IInsertCustomerEventer;
import com.customerservice.application.customer.validate.IValidateCustomerCommand;
import com.customerservice.domain.entities.CustomerEntity;
import com.customerservice.domain.eventers.CustomerCreateEv;
import com.customerservice.domain.exceptions.bussineslogic.InvalidModelException;
import com.customerservice.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class InsertCustomerCommand implements IInsertCustomerCommand {

    private final IValidateCustomerCommand validateCustomerCommand;
    private final CustomerRepo customerRepo;
    private final IInsertCustomerEventer insertCustomerEventer;

    @Autowired
    public InsertCustomerCommand(
            IValidateCustomerCommand validateCustomerCommand,
            CustomerRepo customerRepo, IInsertCustomerEventer insertCustomerEventer) {
        this.validateCustomerCommand = validateCustomerCommand;
        this.customerRepo = customerRepo;
        this.insertCustomerEventer = insertCustomerEventer;
    }

    @Override
    public void insert(CustomerEntity customerModel) throws InvalidModelException, IOException {
        var validationResult = validateCustomerCommand.validateCustomer(customerModel);

        if (!validationResult.isValid()) {
            throw new InvalidModelException(validationResult.getMessages().get(0).getValidationInfo());
        }

        var insertedCustomer = customerRepo.save(customerModel);

        var customerCreateEv = new CustomerCreateEv(
                insertedCustomer.getId(),
                insertedCustomer.getUserId()
        );

        insertCustomerEventer.send(customerCreateEv);
    }
}
