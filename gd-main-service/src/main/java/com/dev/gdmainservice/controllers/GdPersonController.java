package com.dev.gdmainservice.controllers;

import com.dev.gdmainservice.converters.GdPersonConverter;
import com.dev.gdmainservice.models.dto.GdPersonDto;
import com.dev.gdmainservice.models.entity.GdPerson;
import com.dev.gdmainservice.services.GdPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class GdPersonController {
    private final GdPersonService personService;

    @Autowired
    public GdPersonController(GdPersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdPersonDto personDto) {
        personService.save(GdPersonConverter.convertToEntity(personDto));

        return ResponseEntity.ok("Пользователь успешно создан");
    }

    @GetMapping("/{id}")
    public GdPerson getPerson(@PathVariable("id") Long id) {
        return personService.findOne(id);
    }

    @GetMapping("/extraInfo/{id}")
    public GdPerson.ExtraInfo getExtraInfo(@PathVariable("id") Long id) {
        return GdPersonConverter.convertToExtraInfoDTO(personService.getExtraInfo(id));
    }

    @PostMapping("/extraInfo/create/{id}")
    public ResponseEntity<String> saveExtraInfo(@PathVariable("id") Long id, @RequestBody GdPerson.ExtraInfo extraInfo) {
        personService.saveExtraInfo(id, extraInfo);
        return ResponseEntity.ok("Дополнительная информация создана/обновлена успешно!");
    }
}
