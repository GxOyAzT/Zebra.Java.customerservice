package com.customerservice.application.shared.eventer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EventerBase {

    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public EventerBase() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        var connection = factory.newConnection();

        channel = connection.createChannel();
    }
}
