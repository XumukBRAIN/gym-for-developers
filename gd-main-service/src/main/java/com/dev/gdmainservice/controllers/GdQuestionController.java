package com.dev.gdmainservice.controllers;

import com.dev.gdmainservice.converters.GdQuestionConverter;
import com.dev.gdmainservice.models.dto.GdQuestionDto;
import com.dev.gdmainservice.services.GdQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class GdQuestionController {
    private final GdQuestionService gdQuestionService;
    private final GdQuestionConverter gdQuestionConverter;

    @Autowired
    public GdQuestionController(GdQuestionService gdQuestionService, GdQuestionConverter gdQuestionConverter) {
        this.gdQuestionService = gdQuestionService;
        this.gdQuestionConverter = gdQuestionConverter;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdQuestionDto gdQuestionDTO) {
        gdQuestionService.save(gdQuestionConverter.convertToEntity(gdQuestionDTO));
        return ResponseEntity.ok("Вопрос успешно создан");
    }

}
