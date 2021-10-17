package com.customerservice.domain.eventers;

import java.util.UUID;

public class CustomerCreateEv {

    //region PROPS
    private UUID customerId;
    private String userId;
    //endregion

    //region GETTERS AND SETTERS
    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    //endregion

    //region CONSTRUCTORS
    public CustomerCreateEv(UUID customerId, String userId) {
        this.customerId = customerId;
        this.userId = userId;
    }

    public CustomerCreateEv() {
    }
    //endregion
}
