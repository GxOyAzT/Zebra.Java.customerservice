package com.customerservice.application.customer.eventer.insert;

import com.customerservice.application.customer.event.insert.InsertCustomerEventer;
import com.customerservice.application.shared.eventer.EventerBase;
import com.customerservice.domain.eventers.CustomerCreateEv;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class InsertCustomerEventerTests {

    @Test
    public void sendRabbitMqCreateCustomerMessage() throws IOException, TimeoutException {
        var eventerBase = new EventerBase();

        var insertCustomerEventer = new InsertCustomerEventer(eventerBase);

        insertCustomerEventer.send(new CustomerCreateEv(UUID.randomUUID(), "2b2e700a-5248-44be-af90-0ebdc9e54c00"));
    }
}
