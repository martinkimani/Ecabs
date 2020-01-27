/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.config;

import com.ecabs.challenge.util.AppConstants;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 *
 * @author martin
 */
@Configuration
@EnableRabbit
public class RabbitMQConfiguration implements RabbitListenerConfigurer {

    @Autowired
    ConnectionProperties connection;

    @Bean
    public TopicExchange messageExchange() {
        return new TopicExchange(AppConstants.MESSAGE_EXCHANGE);
    }

    @Bean
    public TopicExchange bookingExchange() {
        return new TopicExchange(AppConstants.BOOKING_EXCHANGE);
    }

    @Bean
    public Queue MessageAuditQueue() {
        return new Queue(AppConstants.MessageAuditQueue);
    }

    @Bean
    public Queue BookingAddQueue() {
        return new Queue(AppConstants.BookingAddQueue);
    }

    @Bean
    public Queue BookingEditQueue() {
        return new Queue(AppConstants.BookingEditQueue);
    }

    @Bean
    public Queue BookingDeleteQueue() {
        return new Queue(AppConstants.BookingDeleteQueue);
    }

    @Bean
    public Binding exchangeBinding() {
        return BindingBuilder.bind(bookingExchange()).to((messageExchange())).with(AppConstants.ROUTING_KEY_EXCHANGES);
    }

    @Bean
    public Binding messageBinding() {
        return BindingBuilder.bind(MessageAuditQueue()).to(messageExchange()).with(AppConstants.ROUTING_KEY_MESSAGES);
    }

    @Bean
    public Binding addBookingBinding() {
        return BindingBuilder.bind(BookingAddQueue()).to(bookingExchange()).with(AppConstants.ROUTING_KEY_ADDBOOKING);
    }

    @Bean
    public Binding deleteBookingBinding() {
        return BindingBuilder.bind(BookingDeleteQueue()).to(bookingExchange()).with(AppConstants.ROUTING_KEY_DELETEBOOKING);
    }

    @Bean
    public Binding editBookingBinding() {
        return BindingBuilder.bind(BookingEditQueue()).to(bookingExchange()).with(AppConstants.ROUTING_KEY_EDITBOOKING);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(connection.getRabbitMQHost());
        connectionFactory.setVirtualHost(connection.getEcabsVHost());
        connectionFactory.setUsername(connection.getRabbitMQUser());
        connectionFactory.setPassword(connection.getRabbitMQPass());

        return connectionFactory;
    }

    // You can comment all methods below and remove interface's implementation to use the default serialization / deserialization
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory myRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPrefetchCount(1);
        factory.setConcurrentConsumers(Integer.valueOf(connection.getMinConsumers()));
        factory.setMaxConcurrentConsumers(Integer.valueOf(connection.getMaxConsumers()));
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setContainerFactory(myRabbitListenerContainerFactory());
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public RabbitAdmin messageAdmin() {
        RabbitAdmin admin = new RabbitAdmin(this.connectionFactory());
        System.out.println("auto startup " + admin.getQueueProperties(AppConstants.BookingAddQueue));
        return admin;
    }

}
