package com.friedegg.dropwizard.health;

import com.codahale.metrics.health.HealthCheck;
import com.friedegg.dropwizard.model.Employee;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Created by chris_000 on 03/04/2017.
 */
public class EmployeeHealthCheck extends HealthCheck{
    @Override
    protected Result check() throws Exception {

        Client client = ClientBuilder.newClient();
        Employee employee = client.target("http://localhost:8080/employees/")
                            .path("1")
                            .request(MediaType.APPLICATION_JSON)
                            .get(Employee.class);

        if(employee.getId() == 1) {
            return Result.healthy();
        }
        return Result.unhealthy("Cannot connect to database");
    }
}
