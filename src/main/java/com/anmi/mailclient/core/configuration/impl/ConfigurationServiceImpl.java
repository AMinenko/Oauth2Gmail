package com.anmi.mailclient.core.configuration.impl;

import com.anmi.mailclient.core.configuration.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:config/application.properties")
public class ConfigurationServiceImpl implements ConfigurationService {
    @Autowired
    private Environment environment;

    @Override
    public String getProperty(String name) {
        return environment.getProperty(name);
    }
}
