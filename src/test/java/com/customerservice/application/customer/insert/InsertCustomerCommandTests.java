package com.customerservice.application.customer.insert;

import com.customerservice.application.customer.event.insert.InsertCustomerEventer;
import com.customerservice.application.customer.validate.ValidateCustomerCommand;
import com.customerservice.domain.entities.CustomerEntity;
import com.customerservice.domain.eventers.CustomerCreateEv;
import com.customerservice.domain.exceptions.bussineslogic.InvalidModelException;
import com.customerservice.domain.models.validation.ValidationResultModel;
import com.customerservice.repository.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InsertCustomerCommandTests {

    @Mock
    private CustomerRepo customerRepo;
    @Mock
    private ValidateCustomerCommand validateCustomerCommand;
    @Mock
    private InsertCustomerEventer insertCustomerEventer;
    private InsertCustomerCommand underTests;

    @BeforeEach
    private void init() {
        underTests = new InsertCustomerCommand(validateCustomerCommand, customerRepo, insertCustomerEventer);
    }

    @Test
    public void correctData() throws InvalidModelException, IOException {
        //given
        var customer = new CustomerEntity();
        customer.setUserId("1");
        customer.setId(UUID.randomUUID());

        given(validateCustomerCommand.validateCustomer(customer)).willReturn(new ValidationResultModel());
        given(customerRepo.save(any())).willReturn(customer);


        //when
        underTests.insert(customer);

        verify(customerRepo, times(1)).save(any());

        ArgumentCaptor<CustomerCreateEv> customerCreateEvArgumentCaptor = ArgumentCaptor.forClass(CustomerCreateEv.class);
        verify(insertCustomerEventer, times(1)).send(customerCreateEvArgumentCaptor.capture());
        assertEquals("1", customerCreateEvArgumentCaptor.getValue().getUserId());
        assertEquals(customer.getId(), customerCreateEvArgumentCaptor.getValue().getCustomerId());
    }

    @Test
    public void incorrectData() {
        //given
        var customer = new CustomerEntity();

        var validationResultModel = new ValidationResultModel();
        validationResultModel.addValidateMessage("X", "Y");
        given(validateCustomerCommand.validateCustomer(customer)).willReturn(validationResultModel);

        //when
        assertThatThrownBy(() -> underTests.insert(customer))
                .isInstanceOf(InvalidModelException.class)
                .hasMessageContaining("Y");

        verify(customerRepo, never()).save(any());
        verify(insertCustomerEventer, never()).send(any());
    }
}
