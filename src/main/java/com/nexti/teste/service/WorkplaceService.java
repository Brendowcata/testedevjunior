package com.nexti.teste.service;

import com.nexti.teste.domain.Person;
import com.nexti.teste.domain.Workplace;
import com.nexti.teste.dto.WorkplaceDto;
import com.nexti.teste.dto.WorkplacePersonDto;
import com.nexti.teste.dto.WorkplacePostDto;
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
public class WorkplaceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkplaceService.class);

    @Autowired
    WorkplaceRepository repository;
    @Autowired
    PersonRepository personRepository;

    public ResponseEntity<WorkplacePersonDto> getWorkplaceById(Long id) {
        Optional<Workplace> optionalWorkplace = repository.getById(id);
        Date today = new Date();

        if (optionalWorkplace.isPresent()) {
            List<Person> personList = personRepository.getPersonWorkingByWorkplace(id);

            if (!optionalWorkplace.get().getFinishDate().after(today) || !optionalWorkplace.get().getStartDate().before(today)) {
                return ResponseEntity.ok(new WorkplacePersonDto(optionalWorkplace.get()));
            }

            return ResponseEntity.ok(new WorkplacePersonDto(optionalWorkplace.get(), personList));
        }

            return ResponseEntity.notFound().build();
    }

    public List<WorkplaceDto> getAll() {
        List<Workplace> workplaces = repository.getAllWorkplace();
        return WorkplaceDto.toConvert(workplaces);
    }

    public ResponseEntity<WorkplaceDto> save(WorkplacePostDto workplacePostDto, UriComponentsBuilder uriComponentsBuilder) {
        try {
            Workplace workplace = workplacePostDto.toConvert();
            repository.saveWorkplace(workplace);
            URI uri = uriComponentsBuilder.path("/workplace/{id}").buildAndExpand(workplace.getId()).toUri();

            return ResponseEntity.created(uri).build();

        } catch (Exception e) {
            LOGGER.error("Erro ao salvar posto: ", e);
        }

        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<WorkplaceDto> update(Long id, WorkplacePostDto workplacePostDto) {
        try {
            Optional<Workplace> optionalWorkplace = repository.getById(id);
            if (optionalWorkplace.isPresent()) {
                repository.updateWorkplace(id, workplacePostDto.toConvert());

                return ResponseEntity.ok().build();
            }

        } catch (Exception e){
            LOGGER.error("Erro ao atualizar posto: ", e);
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity delete(Long id) {
        try {
            Optional<Workplace> optionalWorkplace = repository.findById(id);
            if (optionalWorkplace.isPresent()) {
                List<Person> persons = personRepository.getPersonByWorkplace(id);

                if (!persons.isEmpty()){
                    return new ResponseEntity<>("Não é possível deletar postos com usuários!", HttpStatus.BAD_REQUEST);
                }

                repository.deleteWorkplace(id);

                return ResponseEntity.ok().build();
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao deletar posto: ", e);
        }

        return ResponseEntity.notFound().build();
    }

}
