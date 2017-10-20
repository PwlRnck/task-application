package com.crud.tasks.config;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public abstract class PropertiesConfiguration {

    protected static PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer(String... propertiesLocations) {
        final Resource[] resources = new Resource[propertiesLocations.length];
        for (int i = 0; i < propertiesLocations.length; i++) {
            String propertiesLocation = propertiesLocations[i];
            resources[i] = new ClassPathResource(propertiesLocation);
        }
        return createPropertySourcesPlaceholderConfigurer(resources);
    }

    protected static PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer(Resource... resources) {
        final PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
        ppc.setIgnoreUnresolvablePlaceholders(true);
        ppc.setIgnoreResourceNotFound(false);
        ppc.setLocations(resources);
        return ppc;
    }

}