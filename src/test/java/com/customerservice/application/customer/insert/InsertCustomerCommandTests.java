package com.customerservice.application.customer.insert;

import com.customerservice.application.customer.validate.ValidateCustomerCommand;
import com.customerservice.domain.entities.CustomerEntity;
import com.customerservice.domain.enums.GenderEnum;
import com.customerservice.domain.exceptions.bussineslogic.InvalidModelException;
import com.customerservice.domain.models.validation.ValidationResultModel;
import com.customerservice.repository.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InsertCustomerCommandTests {

    @Mock
    private CustomerRepo customerRepo;
    @Mock
    private ValidateCustomerCommand validateCustomerCommand;
    private InsertCustomerCommand underTests;

    @BeforeEach
    private void init() {
        underTests = new InsertCustomerCommand(validateCustomerCommand, customerRepo);
    }

    @Test
    public void correctData() throws InvalidModelException {
        //given
        var customer = new CustomerEntity();

        given(validateCustomerCommand.validateCustomer(customer)).willReturn(new ValidationResultModel());

        //when
        underTests.Insert(customer);

        verify(customerRepo, times(1)).save(any());
    }

    @Test
    public void incorrectData() throws InvalidModelException {
        //given
        var customer = new CustomerEntity();

        var validationResultModel = new ValidationResultModel();
        validationResultModel.addValidateMessage("X", "Y");
        given(validateCustomerCommand.validateCustomer(customer)).willReturn(validationResultModel);

        //when
        assertThatThrownBy(() -> underTests.Insert(customer))
                .isInstanceOf(InvalidModelException.class)
                .hasMessageContaining("Y");

        verify(customerRepo, never()).save(any());
    }
}
