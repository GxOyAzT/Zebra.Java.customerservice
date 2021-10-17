package com.customerservice.api.configuration;

import com.customerservice.application.customer.event.insert.IInsertCustomerEventer;
import com.customerservice.application.customer.event.insert.InsertCustomerEventer;
import com.customerservice.application.customer.insert.IInsertCustomerCommand;
import com.customerservice.application.customer.insert.InsertCustomerCommand;
import com.customerservice.application.customer.validate.IValidateCustomerCommand;
import com.customerservice.application.customer.validate.ValidateCustomerCommand;
import com.customerservice.application.shared.eventer.EventerBase;
import com.customerservice.repository.CustomerRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
public class ApiConfig {

    @Bean
    @Scope("singleton")
    public EventerBase eventerBase() throws IOException, TimeoutException {
        return new EventerBase();
    }

    @Bean
    public IInsertCustomerCommand insertCustomerCommand(
            IValidateCustomerCommand validateCustomerCommand,
            CustomerRepo customerRepo,
            IInsertCustomerEventer insertCustomerEventer) {
        return new InsertCustomerCommand(
                validateCustomerCommand,
                customerRepo,
                insertCustomerEventer
        );
    }

    @Bean
    public IValidateCustomerCommand validateCustomerCommand() {
        return new ValidateCustomerCommand();
    }

    @Bean
    public IInsertCustomerEventer insertCustomerEventer(EventerBase eventerBase) {
        return new InsertCustomerEventer(
                eventerBase
        );
    }
}
