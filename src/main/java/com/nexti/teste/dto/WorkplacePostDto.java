package com.nexti.teste.dto;

import com.nexti.teste.domain.ServiceType;
import com.nexti.teste.domain.Workplace;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class WorkplacePostDto {

    @NotNull
    private String name;
    @NotNull
    private Date startDate;
    @NotNull
    private ServiceType serviceType;
    @NotNull
    private Date finishDate;

    public Workplace toConvert() {
        return new Workplace(name, startDate, finishDate, serviceType);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
