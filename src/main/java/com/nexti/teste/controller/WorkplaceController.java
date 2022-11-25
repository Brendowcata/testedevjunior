package com.nexti.teste.controller;

import com.nexti.teste.domain.Workplace;
import com.nexti.teste.dto.WorkplaceDto;
import com.nexti.teste.dto.WorkplacePersonDto;
import com.nexti.teste.dto.WorkplacePostDto;
import com.nexti.teste.service.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/workplace")
public class WorkplaceController {


    @Autowired
    WorkplaceService workplaceService;

    @GetMapping("/{id}")
    ResponseEntity<WorkplacePersonDto> getWorkplace(@PathVariable Long id){
        return workplaceService.getWorkplaceById(id);
    }

    @GetMapping("/all")
    ResponseEntity<List<WorkplaceDto>> getAllWorkplaces() {
        return ResponseEntity.ok(workplaceService.getAll());
    }

    @PostMapping("/create")
    ResponseEntity<WorkplaceDto> save(@RequestBody @Valid WorkplacePostDto workplacePostDto, UriComponentsBuilder uriComponentsBuilder) {
        return workplaceService.save(workplacePostDto, uriComponentsBuilder);
    }

    @PutMapping("/update/{id}")
    @Transactional
    ResponseEntity<WorkplaceDto> update(@PathVariable Long id, @RequestBody @Valid WorkplacePostDto workplacePostDto) {
        return workplaceService.update(id, workplacePostDto);
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    ResponseEntity delete(@PathVariable Long id) {
        return workplaceService.delete(id);
    }
}
