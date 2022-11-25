package com.nexti.teste.dto;

import com.nexti.teste.domain.Person;
import com.nexti.teste.domain.ServiceType;
import com.nexti.teste.domain.Workplace;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class WorkplacePersonDto {

    private Long id;
    private String name;
    private Date startDate;
    private Date finishDate;
    private ServiceType serviceType;
    private List<PersonDto> persons;

    public WorkplacePersonDto(Workplace workplace) {
        this.id = workplace.getId();
        this.name = workplace.getName();
        this.startDate = workplace.getStartDate();
        this.finishDate = workplace.getFinishDate();
        this.serviceType = workplace.getServiceType();
    }

    public WorkplacePersonDto(Workplace workplace, List<Person> persons) {
        this.id = workplace.getId();
        this.name = workplace.getName();
        this.startDate = workplace.getStartDate();
        this.finishDate = workplace.getFinishDate();
        this.serviceType = workplace.getServiceType();
        this.persons = new ArrayList<>();
        this.persons.addAll(persons.stream().map(PersonDto::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public List<PersonDto> getPersons() {
        return persons;
    }
}
