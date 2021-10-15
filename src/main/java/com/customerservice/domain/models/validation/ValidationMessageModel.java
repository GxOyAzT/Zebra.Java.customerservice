package com.customerservice.domain.models.validation;

public class ValidationMessageModel {

    //region FIELDS
    private String propertyName;
    private String validationInfo;
    //endregion

    //region GETTERS SETTERS
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getValidationInfo() {
        return validationInfo;
    }

    public void setValidationInfo(String validationInfo) {
        this.validationInfo = validationInfo;
    }
    //endregion

    //region CONSTRUCTORS
    public ValidationMessageModel(String propertyName, String validationInfo) {
        this.propertyName = propertyName;
        this.validationInfo = validationInfo;
    }
    //endregion
}
