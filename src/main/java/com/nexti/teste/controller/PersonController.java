package com.nexti.teste.controller;

import com.nexti.teste.dto.PersonPostDto;
import com.nexti.teste.dto.PersonWorkplaceDto;
import com.nexti.teste.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/all")
    ResponseEntity<List<PersonWorkplaceDto>> getAllPersons(){
        return ResponseEntity.ok(personService.getAll());
    }

    @PostMapping("/create")
    @Transactional
    ResponseEntity<PersonWorkplaceDto> save(@RequestBody @Valid PersonPostDto personPostDto, UriComponentsBuilder uriComponentsBuilder) {
        return personService.save(personPostDto, uriComponentsBuilder);
    }

    @PutMapping("/update/{id}")
    @Transactional
    ResponseEntity<PersonWorkplaceDto> update(@PathVariable Long id, @RequestBody PersonPostDto personPostDto) {
        return personService.update(id, personPostDto);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    ResponseEntity delete(@PathVariable Long id) {
        return personService.delete(id);
    }

    @GetMapping("/actives")
    ResponseEntity<List<PersonWorkplaceDto>> getAllPersonsActives() {
        return ResponseEntity.ok(personService.getAllPersonsActives());
    }

    @GetMapping("/inactives")
    ResponseEntity<List<PersonWorkplaceDto>> getAllPersonsInactives() {
        return ResponseEntity.ok(personService.getAllPersonsInactives());
    }

    @GetMapping("byid/{id}")
    ResponseEntity<PersonWorkplaceDto> findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping("/{id}/workplacetransfer/{workplaceId}")
    ResponseEntity workplaceTransfer(@PathVariable Long id, @PathVariable Long workplaceId) {
        return personService.workplaceTransfer(id, workplaceId);
    }

}
