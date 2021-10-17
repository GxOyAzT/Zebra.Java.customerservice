package com.customerservice.application.customer.event.insert;

import com.customerservice.application.shared.eventer.EventerBase;
import com.customerservice.domain.eventers.CustomerCreateEv;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class InsertCustomerEventer implements IInsertCustomerEventer{

    private final EventerBase eventerBase;
    private final ObjectWriter objectWriter;

    public InsertCustomerEventer(EventerBase eventerBase) {
        this.eventerBase = eventerBase;
        objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    @Override
    public void send(CustomerCreateEv customerCreateEv) {
        try
        {
            String json = objectWriter.writeValueAsString(customerCreateEv);
            eventerBase.getChannel().basicPublish("CustomerCreate", "", false, null, json.getBytes(StandardCharsets.UTF_8));
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
