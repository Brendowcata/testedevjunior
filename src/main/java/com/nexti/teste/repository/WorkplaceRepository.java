package com.nexti.teste.repository;

import com.nexti.teste.domain.Workplace;
import com.nexti.teste.repository.impl.WorkplaceRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkplaceRepository extends CrudRepository<Workplace, Long>, WorkplaceRepositoryCustom {
}
