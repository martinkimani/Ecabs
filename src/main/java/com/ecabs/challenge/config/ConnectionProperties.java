/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author martin
 */
@Component
@ConfigurationProperties("vars")
public class ConnectionProperties {

    private String rabbitMQHost;
    private String rabbitMQUser;
    private String rabbitMQPass;
    private String ecabsVHost;
    private String maxConsumers;
    private String minConsumers;

    /**
     * @return the rabbitMQHost
     */
    public String getRabbitMQHost() {
        return rabbitMQHost;
    }

    /**
     * @param rabbitMQHost the rabbitMQHost to set
     */
    public void setRabbitMQHost(String rabbitMQHost) {
        this.rabbitMQHost = rabbitMQHost;
    }

    /**
     * @return the rabbitMQUser
     */
    public String getRabbitMQUser() {
        return rabbitMQUser;
    }

    /**
     * @param rabbitMQUser the rabbitMQUser to set
     */
    public void setRabbitMQUser(String rabbitMQUser) {
        this.rabbitMQUser = rabbitMQUser;
    }

    /**
     * @return the rabbitMQPass
     */
    public String getRabbitMQPass() {
        return rabbitMQPass;
    }

    /**
     * @param rabbitMQPass the rabbitMQPass to set
     */
    public void setRabbitMQPass(String rabbitMQPass) {
        this.rabbitMQPass = rabbitMQPass;
    }

    /**
     * @return the ecabsVHost
     */
    public String getEcabsVHost() {
        return ecabsVHost;
    }

    /**
     * @param ecabsVHost the ecabsVHost to set
     */
    public void setEcabsVHost(String ecabsVHost) {
        this.ecabsVHost = ecabsVHost;
    }

    /**
     * @return the maxConsumers
     */
    public String getMaxConsumers() {
        return maxConsumers;
    }

    /**
     * @param maxConsumers the maxConsumers to set
     */
    public void setMaxConsumers(String maxConsumers) {
        this.maxConsumers = maxConsumers;
    }

    /**
     * @return the minConsumers
     */
    public String getMinConsumers() {
        return minConsumers;
    }

    /**
     * @param minConsumers the minConsumers to set
     */
    public void setMinConsumers(String minConsumers) {
        this.minConsumers = minConsumers;
    }

}
