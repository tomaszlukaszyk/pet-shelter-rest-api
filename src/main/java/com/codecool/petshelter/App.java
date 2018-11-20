package com.codecool.petshelter;

import org.glassfish.jersey.server.ResourceConfig;

public class App extends ResourceConfig {

    public App() {
        register(new SimpleBinder());
        packages("com.codecool.petshelter.endpoints");
    }
}
