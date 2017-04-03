package com.friedegg.dropwizard.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by chris_000 on 03/04/2017.
 */

//This is representation which holds data and serialized to JSON. It's the model for RESTful application.
//When using Jersey with Jackson, all you need to build a resource representation is - a simple POJO following java bean standards
//Jackson constructs the JSON string recursively according to the getter methods of each class and their return type.

//If required in some cases, you can prevent a property from being a part of the JSON representation by adding the @JsonIgnore annotation to its getter.
public class Employee {

    //When accepting PUT and POST requests, you will need to validate user submitted entity content in request body.
    //Dropwizard uses hibernate validator for this purpose. Adding validation requires following steps.
    @NotNull
    private Integer id;
    @NotBlank @Length(min=2, max=255)
    private String firstName;
    @NotBlank @Length(min=2, max=255)
    private String lastName;
    @Pattern(regexp=".+@.+\\.[a-z]+")
    private String email;

    public Employee(){
    }

    public Employee(Integer id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Emplyee [id=" + id + ", firstName=" + firstName + ", lastName="
                + lastName + ", email=" + email + "]";
    }
}
