package com.customerservice.domain.dtos.customer;

import com.customerservice.domain.enums.GenderEnum;

import java.sql.Date;

public class CustomerCreateDto {

    //region PROPS
    private String userId;
    private String fullName;
    private Date dob;
    private GenderEnum gender;
    //endregion

    //region GETTERS SETTERS
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    //endregion

    //region CONSTRUCTORS
    public CustomerCreateDto(String userId,String fullName, Date dob, GenderEnum gender) {
        this.userId = userId;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
    }

    public CustomerCreateDto() {
    }
    //endregion
}
