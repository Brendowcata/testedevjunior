package com.nexti.teste.dto;

import com.nexti.teste.domain.Person;

import java.util.Date;

public class PersonDto {

    private Long id;
    private String name;
    private Date admissionDate;
    private Date demissionDate;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.admissionDate = person.getAdmissionDate();
        this.demissionDate = person.getDemissionDate();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDemissionDate() {
        return demissionDate;
    }

    public void setDemissionDate(Date demissionDate) {
        this.demissionDate = demissionDate;
    }
}
