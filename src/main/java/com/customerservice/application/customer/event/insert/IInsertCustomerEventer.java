package com.customerservice.application.customer.event.insert;

import com.customerservice.domain.eventers.CustomerCreateEv;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface IInsertCustomerEventer {

    void send(CustomerCreateEv customerCreateEv) throws IOException;
}
