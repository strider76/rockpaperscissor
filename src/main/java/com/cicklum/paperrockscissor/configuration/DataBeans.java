package com.cicklum.paperrockscissor.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.cicklum.paperrockscissor.model.Round;
import com.cicklum.paperrockscissor.model.User;

/**
 * GameUsers - Register all users and their rounds
 *
 * @author manuel.millan
 *
 */

@Configuration
public class DataBeans {

    @Bean(name = "roundsPlayed")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public List<Round> rounds() {
        return new ArrayList<>();
    }

    @Bean(name="listUsers")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public List<User> users() {
	return new ArrayList<>();
    }

}
