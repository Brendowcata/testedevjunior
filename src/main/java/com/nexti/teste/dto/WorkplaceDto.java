package com.nexti.teste.dto;

import com.nexti.teste.domain.ServiceType;
import com.nexti.teste.domain.Workplace;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class WorkplaceDto {

    private Long id;
    private String name;
    private Date startDate;
    private Date finishDate;
    private ServiceType serviceType;

    public WorkplaceDto(Workplace workplace) {
        this.id = workplace.getId();
        this.name = workplace.getName();
        this.startDate = workplace.getStartDate();
        this.finishDate = workplace.getFinishDate();
        this.serviceType = workplace.getServiceType();
    }

    public static List<WorkplaceDto> toConvert(List<Workplace> workplaces) {
        return workplaces.stream().map(WorkplaceDto::new).collect(Collectors.toList());
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

}
