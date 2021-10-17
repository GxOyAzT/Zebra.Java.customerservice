package com.customerservice.application.customer.validate;

import com.customerservice.domain.entities.CustomerEntity;
import com.customerservice.domain.enums.GenderEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidateCustomerCommandTests {

    private ValidateCustomerCommand _underTests;

    @BeforeEach
    public void initTest() {
        _underTests = new ValidateCustomerCommand();
    }

    @Test
    public void correctData() {
        //given
        var customer = new CustomerEntity();
        customer.setUserId("123abc");
        customer.setFullName("correct_name");
        customer.setGender(GenderEnum.Female);
        customer.setDob(Date.valueOf("2000-03-01"));

        //when
        var result = _underTests.validateCustomer(customer);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void userId_NotPassed() {
        //given
        var customer = new CustomerEntity();
        customer.setFullName("correct_name");
        customer.setGender(GenderEnum.Female);
        customer.setDob(Date.valueOf("2000-03-01"));

        //when
        var result = _underTests.validateCustomer(customer);

        //then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessages().size()).isEqualTo(1);
        assertThat(result.getMessages().get(0).getPropertyName()).isEqualTo("userId");
        assertThat(result.getMessages().get(0).getValidationInfo()).isEqualTo("User ID has to be passed.");
    }

    @Test
    public void fullName_notPassed() {
        //given
        var customer = new CustomerEntity();
        customer.setUserId("123abc");
        customer.setFullName("");
        customer.setGender(GenderEnum.Female);
        customer.setDob(Date.valueOf("2000-03-01"));

        //when
        var result = _underTests.validateCustomer(customer);

        //then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessages().size()).isEqualTo(1);
        assertThat(result.getMessages().get(0).getPropertyName()).isEqualTo("fullName");
        assertThat(result.getMessages().get(0).getValidationInfo()).isEqualTo("Full name cannot be empty.");
    }

    @Test
    public void fullName_tooLong() {
        //given
        var customer = new CustomerEntity();
        customer.setUserId("123abc");
        customer.setFullName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        customer.setGender(GenderEnum.Female);
        customer.setDob(Date.valueOf("2000-03-01"));

        //when
        var result = _underTests.validateCustomer(customer);

        //then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessages().size()).isEqualTo(1);
        assertThat(result.getMessages().get(0).getPropertyName()).isEqualTo("fullName");
        assertThat(result.getMessages().get(0).getValidationInfo()).isEqualTo("Full name cannot be longer then 100 characters.");
    }

    @Test
    public void fullName_null() {
        //given
        var customer = new CustomerEntity();
        customer.setUserId("123abc");
        customer.setGender(GenderEnum.Female);
        customer.setDob(Date.valueOf("2000-03-01"));

        //when
        var result = _underTests.validateCustomer(customer);

        //then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessages().size()).isEqualTo(1);
        assertThat(result.getMessages().get(0).getPropertyName()).isEqualTo("fullName");
        assertThat(result.getMessages().get(0).getValidationInfo()).isEqualTo("Full name cannot be longer then 100 characters.");
    }

    @Test
    public void gender_undefined() {
        //given
        var customer = new CustomerEntity();
        customer.setUserId("123abc");
        customer.setFullName("valid_value");
        customer.setGender(GenderEnum.Undefined);
        customer.setDob(Date.valueOf("2000-03-01"));

        //when
        var result = _underTests.validateCustomer(customer);

        //then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessages().size()).isEqualTo(1);
        assertThat(result.getMessages().get(0).getPropertyName()).isEqualTo("gender");
        assertThat(result.getMessages().get(0).getValidationInfo()).isEqualTo("Gender has to be specified.");
    }

    @Test
    public void dob_afterToday() {
        //given
        var customer = new CustomerEntity();
        customer.setUserId("123abc");
        customer.setFullName("valid_value");
        customer.setGender(GenderEnum.Male);
        customer.setDob(Date.valueOf("3000-03-01"));

        //when
        var result = _underTests.validateCustomer(customer);

        //then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessages().size()).isEqualTo(1);
        assertThat(result.getMessages().get(0).getPropertyName()).isEqualTo("dob");
        assertThat(result.getMessages().get(0).getValidationInfo()).isEqualTo("Date of birth cannot be after today date.");
    }

    @Test
    public void all_values_incorrect() {
        //given
        var customer = new CustomerEntity();
        customer.setFullName("");
        customer.setGender(GenderEnum.Undefined);
        customer.setDob(Date.valueOf("3000-03-01"));

        //when
        var result = _underTests.validateCustomer(customer);

        //then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessages().size()).isEqualTo(4);
    }
}
