package com.technova.msvendor.config;

import com.technova.vendor.constants.RabbitVendorConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class RabbitConfiguration {
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Primary
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public Exchange vendorExchange() {
        return new DirectExchange(RabbitVendorConstants.VENDOR_EXCHANGE, true, false);
    }

    @Bean
    public Queue vendorSaveQueue() {
        return new Queue(RabbitVendorConstants.VENDOR_SAVE_REQUEST_QUEUE, true);
    }

    @Bean
    public Binding vendorSaveBinding(Queue vendorSaveQueue, Exchange vendorExchange) {
        return BindingBuilder.bind(vendorSaveQueue).to(vendorExchange).with(RabbitVendorConstants.VENDOR_SAVE_REQUEST_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue vendorLoginQueue() {
        return new Queue(RabbitVendorConstants.VENDOR_LOGIN_REQUEST_QUEUE, true);
    }

    @Bean
    public Binding vendorLoginBinding(Queue vendorLoginQueue, Exchange vendorExchange) {
        return BindingBuilder.bind(vendorLoginQueue).to(vendorExchange).with(RabbitVendorConstants.VENDOR_LOGIN_REQUEST_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue vendorFindByIdQueue() {
        return new Queue(RabbitVendorConstants.VENDOR_FIND_BY_ID_REQUEST_QUEUE, true);
    }

    @Bean
    public Binding vendorFindByIdBinding(Queue vendorFindByIdQueue, Exchange vendorExchange) {
        return BindingBuilder.bind(vendorFindByIdQueue).to(vendorExchange).with(RabbitVendorConstants.VENDOR_FIND_BY_ID_REQUEST_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue vendorUpdateQueue() {
        return new Queue(RabbitVendorConstants.VENDOR_UPDATE_REQUEST_QUEUE, true);
    }

    @Bean
    public Binding vendorUpdateBinding(Queue vendorUpdateQueue, Exchange vendorExchange) {
        return BindingBuilder.bind(vendorUpdateQueue).to(vendorExchange).with(RabbitVendorConstants.VENDOR_UPDATE_REQUEST_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue vendorDeleteQueue() {
        return new Queue(RabbitVendorConstants.VENDOR_DELETE_REQUEST_QUEUE, true);
    }

    @Bean
    public Binding vendorDeleteBinding(Queue vendorDeleteQueue, Exchange vendorExchange) {
        return BindingBuilder.bind(vendorDeleteQueue).to(vendorExchange).with(RabbitVendorConstants.VENDOR_DELETE_REQUEST_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue vendorSoftDeleteQueue() {
        return new Queue(RabbitVendorConstants.VENDOR_SOFT_DELETE_REQUEST_QUEUE, true);
    }

    @Bean
    public Binding vendorSoftDeleteBinding(Queue vendorSoftDeleteQueue, Exchange vendorExchange) {
        return BindingBuilder.bind(vendorSoftDeleteQueue).to(vendorExchange).with(RabbitVendorConstants.VENDOR_SOFT_DELETE_REQUEST_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue vendorActiveQueue() {
        return new Queue(RabbitVendorConstants.VENDOR_ACTIVE_REQUEST_QUEUE, true);
    }

    @Bean
    public Binding vendorActiveBinding(Queue vendorActiveQueue, Exchange vendorExchange) {
        return BindingBuilder.bind(vendorActiveQueue).to(vendorExchange).with(RabbitVendorConstants.VENDOR_ACTIVE_REQUEST_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue vendorGetProductsQueue() {
        return new Queue(RabbitVendorConstants.VENDOR_GET_PRODUCTS_REQUEST_QUEUE, true);
    }

    @Bean
    public Binding vendorGetProductsBinding(Queue vendorGetProductsQueue, Exchange vendorExchange) {
        return BindingBuilder.bind(vendorGetProductsQueue).to(vendorExchange).with(RabbitVendorConstants.VENDOR_GET_PRODUCTS_REQUEST_ROUTING_KEY).noargs();
    }
}
