package com.customerservice.domain.models.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidationResultModelTests {

    @Test
    public void validresultReturnsTrue() {
        //given
        var validationResultModel = new ValidationResultModel();

        //that
        assertThat(validationResultModel.isValid()).isTrue();
    }

    @Test
    public void validresultReturnsFalse() {
        //given
        var validationResultModel = new ValidationResultModel();

        validationResultModel.addValidateMessage("X", "Y");

        //that
        assertThat(validationResultModel.isValid()).isFalse();
    }
}
