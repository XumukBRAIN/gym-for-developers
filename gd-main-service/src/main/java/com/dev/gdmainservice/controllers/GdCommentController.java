package com.dev.gdmainservice.controllers;

import com.dev.gdmainservice.converters.GdCommentConverter;
import com.dev.gdmainservice.models.dto.GdCommentDto;
import com.dev.gdmainservice.models.entity.GdComment;
import com.dev.gdmainservice.services.GdCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class GdCommentController {

    private final GdCommentService gdCommentService;
    private final GdCommentConverter gdCommentConverter;

    @Autowired
    public GdCommentController(GdCommentService gdCommentService, GdCommentConverter gdCommentConverter) {
        this.gdCommentService = gdCommentService;
        this.gdCommentConverter = gdCommentConverter;
    }

    @PostMapping("/create")
    public GdComment create(@RequestBody GdCommentDto gdCommentDTO) {
        return gdCommentService.create(gdCommentDTO.getNoteId(), gdCommentConverter.convertToEntity(gdCommentDTO));
    }

}
