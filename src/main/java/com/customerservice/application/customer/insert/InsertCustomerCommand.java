package com.customerservice.application.customer.insert;

import com.customerservice.application.customer.validate.IValidateCustomerCommand;
import com.customerservice.domain.entities.CustomerEntity;
import com.customerservice.domain.exceptions.bussineslogic.InvalidModelException;
import com.customerservice.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class InsertCustomerCommand implements IInsertCustomerCommand {

    private final IValidateCustomerCommand validateCustomerCommand;
    private final CustomerRepo customerRepo;

    @Autowired
    public InsertCustomerCommand(
            IValidateCustomerCommand validateCustomerCommand,
            CustomerRepo customerRepo) {
        this.validateCustomerCommand = validateCustomerCommand;
        this.customerRepo = customerRepo;
    }

    @Override
    public void Insert(CustomerEntity customerModel) throws InvalidModelException {
        var validationResult = validateCustomerCommand.validateCustomer(customerModel);

        if (!validationResult.isValid()) {
            throw new InvalidModelException(validationResult.getMessages().get(0).getValidationInfo());
        }

        customerRepo.save(customerModel);
    }
}
