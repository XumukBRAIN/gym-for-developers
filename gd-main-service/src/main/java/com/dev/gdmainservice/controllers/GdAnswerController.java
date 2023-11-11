package com.dev.gdmainservice.controllers;

import com.dev.gdmainservice.converters.GdAnswerConverter;
import com.dev.gdmainservice.models.dto.GdAnswerDto;
import com.dev.gdmainservice.services.GdAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class GdAnswerController {
    private final GdAnswerService gdAnswerService;
    private final GdAnswerConverter gdConvertAnswer;

    @Autowired
    public GdAnswerController(GdAnswerService gdAnswerService, GdAnswerConverter gdConvertAnswer) {
        this.gdAnswerService = gdAnswerService;
        this.gdConvertAnswer = gdConvertAnswer;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdAnswerDto gdAnswerDTO) {
        gdAnswerService.save(gdAnswerDTO.getQuestionId(), gdConvertAnswer.convertToEntity(gdAnswerDTO));
        return ResponseEntity.ok("Ваш ответ успешно добавлен");
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Long> like(@PathVariable("id") Long id) {
        Long actualLikes = gdAnswerService.like(id);
        return ResponseEntity.ok(actualLikes);
    }

}
