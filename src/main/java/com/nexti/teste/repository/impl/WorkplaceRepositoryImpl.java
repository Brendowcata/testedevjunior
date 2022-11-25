package com.nexti.teste.repository.impl;

import com.nexti.teste.domain.ServiceType;
import com.nexti.teste.domain.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class WorkplaceRepositoryImpl implements WorkplaceRepositoryCustom{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Workplace> getAllWorkplace() {

        return jdbcTemplate.query("SELECT wp.id as workplaceId, " +
                "wp.name AS workplaceName, " +
                "wp.start_date AS startDate, " +
                "wp.finish_date AS finishDate, " +
                "st.id AS serviceId, " +
                "st.name AS serviceName " +
                "FROM workplace wp " +
                "INNER JOIN service_type st ON wp.service_type_id = st.id", (rs, row )->{

            Workplace workplace = new Workplace();
            ServiceType serviceType = new ServiceType();

            workplace.setId(rs.getLong("workplaceId"));
            workplace.setName(rs.getString("workplaceName"));
            workplace.setStartDate(rs.getTimestamp("startDate"));
            workplace.setFinishDate(rs.getTimestamp("finishDate"));
            serviceType.setId(rs.getLong("serviceId"));
            serviceType.setName(rs.getString("serviceName"));
            workplace.setServiceType(serviceType);

            return  workplace;
        });
    }

    @Override
    public void saveWorkplace(Workplace workplace) {
        jdbcTemplate.update("INSERT INTO workplace(finish_date, name, start_date, service_type_id) VALUES (?, ?, ?, ?)",
                workplace.getFinishDate(), workplace.getName(), workplace.getStartDate(), workplace.getServiceType().getId());
    }

    @Override
    public void updateWorkplace(Long id, Workplace workplace) {
        jdbcTemplate.update("UPDATE workplace SET finish_date = ?, start_date = ?, name = ?, service_type_id = ? WHERE id = ?",
                workplace.getFinishDate(), workplace.getStartDate(), workplace.getName(), workplace.getServiceType().getId(), id);
    }

    @Override
    public void deleteWorkplace(Long id) {
        jdbcTemplate.update("DELETE FROM workplace WHERE id = ? ", id);
    }

    @Override
    public Optional<Workplace> getById(Long id) {

        try {
            return jdbcTemplate.queryForObject("SELECT wp.id as workplaceId, " +
                "wp.name AS workplaceName, " +
                "wp.start_date AS startDate, " +
                "wp.finish_date AS finishDate, " +
                "st.id AS serviceId, " +
                "st.name AS serviceName " +
                "FROM workplace wp " +
                "INNER JOIN service_type st ON wp.service_type_id = st.id " +
                "WHERE wp.id = ?", (rs, row )->{

            Workplace workplace = new Workplace();
            ServiceType serviceType = new ServiceType();

            workplace.setId(rs.getLong("workplaceId"));
            workplace.setName(rs.getString("workplaceName"));
            workplace.setStartDate(rs.getTimestamp("startDate"));
            workplace.setFinishDate(rs.getTimestamp("finishDate"));
            serviceType.setId(rs.getLong("serviceId"));
            serviceType.setName(rs.getString("serviceName"));
            workplace.setServiceType(serviceType);

            return Optional.of(workplace);

        }, id);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

}
