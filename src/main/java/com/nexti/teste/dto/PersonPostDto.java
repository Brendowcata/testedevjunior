package com.nexti.teste.dto;

import com.nexti.teste.domain.Person;
import com.nexti.teste.domain.Workplace;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PersonPostDto {

    @NotNull
    private String name;
    private Date admissionDate;
    private Date demissionDate;
    @NotNull
    private Workplace workplace;

    public Person toConvert() {
        return new Person(name, admissionDate, demissionDate, workplace);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setDemissionDate(Date demissionDate) {
        this.demissionDate = demissionDate;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }
}
