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
    private final GdAnswerService answerService;

    @Autowired
    public GdAnswerController(GdAnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdAnswerDto answerDto) {
        answerService.save(answerDto.getQuestionId(), GdAnswerConverter.convertToEntity(answerDto));
        return ResponseEntity.ok("Ваш ответ успешно добавлен");
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Long> like(@PathVariable("id") Long id) {
        Long actualLikes = answerService.like(id);
        return ResponseEntity.ok(actualLikes);
    }
}
