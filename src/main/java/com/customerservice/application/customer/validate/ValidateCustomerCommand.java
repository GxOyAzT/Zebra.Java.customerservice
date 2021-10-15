package com.customerservice.application.customer.validate;

import com.customerservice.domain.entities.CustomerEntity;
import com.customerservice.domain.enums.GenderEnum;
import com.customerservice.domain.models.validation.ValidationResultModel;

import java.sql.Date;

public class ValidateCustomerCommand implements IValidateCustomerCommand{

    @Override
    public ValidationResultModel validateCustomer(CustomerEntity customer) {
        var validationResultModel = new ValidationResultModel();

        if (customer.getFullName().isEmpty()) {
            validationResultModel.addValidateMessage(
                    "fullName",
                    "Full name cannot be empty."
            );
        }

        if (customer.getFullName().length() > 100) {
            validationResultModel.addValidateMessage(
                    "fullName",
                    "Full name cannot be longer then 100 characters."
            );
        }

        if (customer.getGender() == GenderEnum.Undefined) {
            validationResultModel.addValidateMessage(
                    "gender",
                    "Gender has to be specified."
            );
        }

        if (customer.getDob().after(new Date(System.currentTimeMillis()))) {
            validationResultModel.addValidateMessage(
                    "dob",
                    "Date of birth cannot be after today date."
            );
        }

        return validationResultModel;
    }
}
