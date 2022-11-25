package com.nexti.teste.repository.impl;

import com.nexti.teste.domain.Workplace;

import java.util.List;
import java.util.Optional;

public interface WorkplaceRepositoryCustom {

    List<Workplace> getAllWorkplace();

    void saveWorkplace(Workplace workplace);

    void updateWorkplace(Long id, Workplace workplace);

    void deleteWorkplace(Long id);

    Optional<Workplace> getById(Long id);


}
