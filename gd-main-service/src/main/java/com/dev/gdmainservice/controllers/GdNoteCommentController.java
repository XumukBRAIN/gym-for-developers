package com.dev.gdmainservice.controllers;

import com.dev.gdmainservice.converters.GdCommentConverter;
import com.dev.gdmainservice.models.dto.GdNoteCommentDto;
import com.dev.gdmainservice.models.entity.GdNoteComment;
import com.dev.gdmainservice.services.GdNoteCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/note/comment")
public class GdNoteCommentController {

    private final GdNoteCommentService commentService;

    @Autowired
    public GdNoteCommentController(GdNoteCommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public GdNoteComment create(@RequestBody GdNoteCommentDto commentDto) {
        return commentService.create(commentDto.getNoteId(), GdCommentConverter.convertToEntity(commentDto));
    }
}
