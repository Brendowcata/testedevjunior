package com.nexti.teste.service;

import com.nexti.teste.domain.Person;
import com.nexti.teste.domain.Workplace;
import com.nexti.teste.dto.PersonPostDto;
import com.nexti.teste.dto.PersonWorkplaceDto;
import com.nexti.teste.enums.ServiceTypeEnum;
import com.nexti.teste.repository.PersonRepository;
import com.nexti.teste.repository.WorkplaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    PersonRepository personRepository;
    @Autowired
    WorkplaceRepository workplaceRepository;

    public List<PersonWorkplaceDto> getAll(){
        List<Person> persons = personRepository.getAllPerson();
        return PersonWorkplaceDto.toConvert(persons);
    }

    public ResponseEntity<PersonWorkplaceDto> save(PersonPostDto personPostDto, UriComponentsBuilder uriComponentsBuilder) {
        try {
            Person person = personPostDto.toConvert();
            personRepository.savePerson(person);
            URI uri = uriComponentsBuilder.path("/person/byid/{id}").buildAndExpand(person.getId()).toUri();

            return ResponseEntity.created(uri).build();

        } catch (Exception e) {
            LOGGER.error("Erro ao salvar usuário: ", e);
        }

        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<PersonWorkplaceDto> update(Long id, PersonPostDto personPostDto) {
        try {
        Optional<Person> optionalPerson = personRepository.getById(id);
        if (optionalPerson.isPresent()) {
            personRepository.updatePerson(id, personPostDto.toConvert());

            return ResponseEntity.ok().build();
        }

        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar usuário: ", e);
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity delete(Long id) {
        try {
        Optional<Person> optionalPerson = personRepository.getById(id);
        if (optionalPerson.isPresent()) {
            personRepository.deletePerson(id);

            return ResponseEntity.ok().build();
        }

        } catch (Exception e) {
            LOGGER.error("Erro ao deleter usuário: ", e);
        }

        return ResponseEntity.notFound().build();
    }

    public List<PersonWorkplaceDto> getAllPersonsActives() {
        List<Person> persons = personRepository.getAllPersonActive();
        return PersonWorkplaceDto.toConvert(persons);
    }

    public List<PersonWorkplaceDto> getAllPersonsInactives() {
        List<Person> persons = personRepository.getAllPersonInactive();
        return PersonWorkplaceDto.toConvert(persons);
    }

    public ResponseEntity<PersonWorkplaceDto> findById(Long id) {
        Optional<Person> optionalPerson = personRepository.getById(id);
        if (optionalPerson.isPresent()) {
            return ResponseEntity.ok(new PersonWorkplaceDto(optionalPerson.get()));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity workplaceTransfer(Long id, Long workplaceId) {
        try {
            Optional<Person> optionalPerson = personRepository.getById(id);
            Optional<Workplace> optionalWorkplace = workplaceRepository.getById(workplaceId);
            if (optionalPerson.isPresent() && optionalWorkplace.isPresent()) {
                Person person = optionalPerson.get();
                Workplace workplace = optionalWorkplace.get();
                Date today = new Date();

                if (person.getDemissionDate() != null) {
                    if (!person.getDemissionDate().after(today)) {
                        return new ResponseEntity<>("Esse Usuário está demitido!", HttpStatus.BAD_REQUEST);
                    }
                }

                if (person.getAdmissionDate() == null || !person.getAdmissionDate().before(today)) {
                    return new ResponseEntity<>("Esse Usuário ainda não foi admitido!", HttpStatus.BAD_REQUEST);
                }

                if (!workplace.getFinishDate().after(today)){
                    return new ResponseEntity<>("O Posto está desativado!", HttpStatus.BAD_REQUEST);
                }

                if (!workplace.getStartDate().before(today)) {
                    return new ResponseEntity<>("O Posto ainda não está ativo!", HttpStatus.BAD_REQUEST);
                }

                if (workplace.getServiceType().getId().equals(ServiceTypeEnum.SERVICE_C.getId())) {
                    return new ResponseEntity<>("Não é possivel transferir para esse posto!", HttpStatus.BAD_REQUEST);
                }

                personRepository.workplaceTransfer(id, workplaceId);
                Optional<Person> newPerson = personRepository.getById(id);

                return ResponseEntity.ok(new PersonWorkplaceDto(newPerson.get()));
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao trocar usuário de posto: ", e);
        }

        return new ResponseEntity<>("Usuário ou Posto não encontrado!", HttpStatus.NOT_FOUND);
    }
}
