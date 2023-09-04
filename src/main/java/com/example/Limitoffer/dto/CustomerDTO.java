package com.example.Limitoffer.dto;

import jakarta.validation.constraints.NotNull;

public class CustomerDTO {


    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private Integer age;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
