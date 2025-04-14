package com.microServices.microService.controller;

import com.microServices.microService.configuration.LimitConfiguration;
import com.microServices.microService.configuration.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {
    private final Configuration configuration;
    public LimitsConfigurationController(Configuration configuration){
        this.configuration=configuration;
    }
    @GetMapping("/limits")
    public LimitConfiguration retriveLimitsFromConfigurations()
    {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
}
