package com.customerservice.domain.models.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResultModel {

    private List<ValidationMessageModel> messages;

    public ValidationResultModel() {
        this.messages = new ArrayList<>();
    }

    public boolean isValid() {
        return messages.isEmpty();
    }

    public List<ValidationMessageModel> getMessages() {
        return messages;
    }

    public void addValidateMessage(
            String fieldName,
            String validationInfo) {
        var message = new ValidationMessageModel(fieldName, validationInfo);

        messages.add(message);
    }
}
