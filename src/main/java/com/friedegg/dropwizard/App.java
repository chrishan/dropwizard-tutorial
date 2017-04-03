package com.friedegg.dropwizard;

import com.codahale.metrics.health.HealthCheck;
import com.friedegg.dropwizard.controller.EmployeeRESTController;
import com.friedegg.dropwizard.health.EmployeeHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

/**
 * Created by chris_000 on 03/04/2017.
 */
//http://howtodoinjava.com/dropwizard/tutorial-and-hello-world-example/
//Application class is entry point for any dropwizard application.
//It nees to extend the io.dropwizard.Application class and implement methods.
//They prepare the runtime environment of the application.
public class App extends Application<Configuration>{

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        LOGGER.info("Registering health check");
        final EmployeeHealthCheck healthCheck = new EmployeeHealthCheck();
        environment.healthChecks().register("dao connectivity check", healthCheck);

        LOGGER.info("Registering REST resources");
        //Inject Environment.getValidator() in REST resource from Application
        environment.jersey().register(new EmployeeRESTController(environment.getValidator()));
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
