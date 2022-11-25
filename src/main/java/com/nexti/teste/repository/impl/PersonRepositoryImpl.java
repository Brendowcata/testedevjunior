package com.nexti.teste.repository.impl;

import com.nexti.teste.domain.Person;
import com.nexti.teste.domain.ServiceType;
import com.nexti.teste.domain.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepositoryCustom {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static final String SELECT_ALL_PERSON = "SELECT p.id AS personId, " +
            "p.name AS personName, " +
            "p.admission_date AS admissionDate, " +
            "p.demission_date AS demissionDate, " +
            "wp.name AS workplaceName," +
            "wp.id as workplaceId," +
            "wp.start_date AS startDate, " +
            "wp.finish_date AS finishDate, " +
            "st.id AS serviceId, " +
            "st.name AS serviceName " +
            "FROM person p " +
            "INNER JOIN workplace wp ON p.workplace_id = wp.id " +
            "INNER JOIN service_type st ON wp.service_type_id = st.id ";

    //TODO deve retornar na consulta, todas as informações referentes ao Posto('Workplace') desta pessoa.
    @Override
    public List<Person> getAllPerson() {

        return jdbcTemplate.query(SELECT_ALL_PERSON, (rs, row)->{

            Person person = new Person();
            Workplace workplace = new Workplace();
            ServiceType serviceType = new ServiceType();

            person.setId(rs.getLong("personId"));
            person.setName(rs.getString("personName"));
            person.setAdmissionDate(rs.getTimestamp("admissionDate"));
            person.setDemissionDate(rs.getTimestamp("demissionDate"));
            workplace.setId(rs.getLong("workplaceId"));
            workplace.setName(rs.getString("workplaceName"));
            workplace.setStartDate(rs.getTimestamp("startDate"));
            workplace.setFinishDate(rs.getTimestamp("finishDate"));
            serviceType.setId(rs.getLong("serviceId"));
            serviceType.setName(rs.getString("serviceName"));
            workplace.setServiceType(serviceType);
            person.setWorkplace(workplace);

            return  person;
        });
    }


    @Override
    public void savePerson(Person person) {
       jdbcTemplate.update("INSERT INTO person(admission_date, demission_date, name, workplace_id) VALUES (?, ?, ?, ?)",
                person.getAdmissionDate(), person.getDemissionDate(), person.getName(), person.getWorkplace().getId());
    }

    @Override
    public void updatePerson(Long id, Person person) {
        jdbcTemplate.update("UPDATE person SET " +
                "admission_date = ?, demission_date = ?, name = ? WHERE id = ? ",
                person.getAdmissionDate(), person.getDemissionDate(), person.getName(), id);
    }

    @Override
    public void deletePerson(Long id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    @Override
    public List<Person> getAllPersonActive() {

        StringBuilder builder = new StringBuilder();

        return jdbcTemplate.query(builder.append(SELECT_ALL_PERSON)
                        .append("AND p.demission_date > NOW() OR p.demission_date IS NULL " +
                                "AND p.admission_date IS NOT NULL " +
                                "GROUP BY p.id").toString(), (rs, row)->{

            Person person = new Person();
            Workplace workplace = new Workplace();
            ServiceType serviceType = new ServiceType();

            person.setId(rs.getLong("personId"));
            person.setName(rs.getString("personName"));
            person.setAdmissionDate(rs.getTimestamp("admissionDate"));
            person.setDemissionDate(rs.getTimestamp("demissionDate"));
            workplace.setId(rs.getLong("workplaceId"));
            workplace.setName(rs.getString("workplaceName"));
            workplace.setStartDate(rs.getTimestamp("startDate"));
            workplace.setFinishDate(rs.getTimestamp("finishDate"));
            serviceType.setId(rs.getLong("serviceId"));
            serviceType.setName(rs.getString("serviceName"));
            workplace.setServiceType(serviceType);
            person.setWorkplace(workplace);

            return  person;
        });
    }

    @Override
    public List<Person> getAllPersonInactive() {

        StringBuilder builder = new StringBuilder();

        return jdbcTemplate.query(builder.append(SELECT_ALL_PERSON)
                .append("WHERE p.demission_date < NOW() OR p.admission_date IS NULL").toString(), (rs, row)->{

            Person person = new Person();
            Workplace workplace = new Workplace();
            ServiceType serviceType = new ServiceType();

            person.setId(rs.getLong("personId"));
            person.setName(rs.getString("personName"));
            person.setAdmissionDate(rs.getTimestamp("admissionDate"));
            person.setDemissionDate(rs.getTimestamp("demissionDate"));
            workplace.setId(rs.getLong("workplaceId"));
            workplace.setName(rs.getString("workplaceName"));
            workplace.setStartDate(rs.getTimestamp("startDate"));
            workplace.setFinishDate(rs.getTimestamp("finishDate"));
            serviceType.setId(rs.getLong("serviceId"));
            serviceType.setName(rs.getString("serviceName"));
            workplace.setServiceType(serviceType);
            person.setWorkplace(workplace);

            return  person;
        });
    }

    @Override
    public void workplaceTransfer(Long id, Long workplaceId) {
        jdbcTemplate.update("UPDATE person SET " +
                        "workplace_id = ? WHERE id = ?  ", workplaceId, id);
    }

    @Override
    public List<Person> getPersonWorkingByWorkplace(Long id) {
        StringBuilder builder = new StringBuilder();

        return jdbcTemplate.query(builder.append(SELECT_ALL_PERSON)
                .append("AND p.demission_date > NOW() OR p.demission_date IS NULL " +
                        "AND p.admission_date < NOW() " +
                        "WHERE p.workplace_id = ? " +
                        "GROUP BY p.id").toString(), (rs, row)->{

            Person person = new Person();

            person.setId(rs.getLong("personId"));
            person.setName(rs.getString("personName"));
            person.setAdmissionDate(rs.getTimestamp("admissionDate"));
            person.setDemissionDate(rs.getTimestamp("demissionDate"));

            return  person;
        }, id);
    }

    @Override
    public List<Person> getPersonByWorkplace(Long id) {
        StringBuilder builder = new StringBuilder();

        return jdbcTemplate.query(builder.append(SELECT_ALL_PERSON)
                .append("WHERE p.workplace_id = ?").toString(), (rs, row)->{

            Person person = new Person();

            person.setId(rs.getLong("personId"));
            person.setName(rs.getString("personName"));
            person.setAdmissionDate(rs.getTimestamp("admissionDate"));
            person.setDemissionDate(rs.getTimestamp("demissionDate"));

            return  person;
        }, id);
    }

    @Override
    public Optional<Person> getById(Long id) {
        try {
            StringBuilder builder = new StringBuilder();
            return jdbcTemplate.queryForObject(builder.append(SELECT_ALL_PERSON)
                    .append("WHERE p.id = ?").toString(), (rs, row)->{

                Person person = new Person();
                Workplace workplace = new Workplace();
                ServiceType serviceType = new ServiceType();

                person.setId(rs.getLong("personId"));
                person.setName(rs.getString("personName"));
                person.setAdmissionDate(rs.getTimestamp("admissionDate"));
                person.setDemissionDate(rs.getTimestamp("demissionDate"));
                workplace.setId(rs.getLong("workplaceId"));
                workplace.setName(rs.getString("workplaceName"));
                workplace.setStartDate(rs.getTimestamp("startDate"));
                workplace.setFinishDate(rs.getTimestamp("finishDate"));
                serviceType.setId(rs.getLong("serviceId"));
                serviceType.setName(rs.getString("serviceName"));
                workplace.setServiceType(serviceType);
                person.setWorkplace(workplace);

                return Optional.of(person);
        }, id);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }


}
