package com.nexti.teste.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "service_type")
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column
    private String name;

    public ServiceType() {
    }

    public ServiceType(Long id) {
        this.id = id;
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
}
