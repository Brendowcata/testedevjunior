package com.nexti.teste.dto;

import com.nexti.teste.domain.Person;
import com.nexti.teste.domain.Workplace;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PersonWorkplaceDto {

    private Long id;
    private String name;
    private Date admissionDate;
    private Date demissionDate;
    private Workplace workplace;

    public PersonWorkplaceDto(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.admissionDate = person.getAdmissionDate();
        this.demissionDate = person.getDemissionDate();
        this.workplace = person.getWorkplace();
    }

    public static List<PersonWorkplaceDto> toConvert(List<Person> persons) {
        return persons.stream().map(PersonWorkplaceDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public Date getDemissionDate() {
        return demissionDate;
    }

    public Workplace getWorkplace() {
        return workplace;
    }
}
