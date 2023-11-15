package com.dev.gdmainservice.controllers;

import com.dev.gdmainservice.converters.GdNoteConverter;
import com.dev.gdmainservice.models.dto.GdNoteCommonDto;
import com.dev.gdmainservice.models.entity.GdNote;
import com.dev.gdmainservice.responses.OperationResponse;
import com.dev.gdmainservice.services.GdNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class GdNoteController {

    private final GdNoteService noteService;

    @Autowired
    public GdNoteController(GdNoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdNoteCommonDto noteDto) {
        noteService.save(GdNoteConverter.convertToEntity(noteDto));
        return ResponseEntity.ok("Заметка успешно создана");
    }

    @GetMapping("/{id}")
    public GdNote getNote(@PathVariable("id") Integer id) {
        return noteService.findOne(id);
    }

    @GetMapping("/reviewNotes/{status}")
    public OperationResponse<List<GdNoteCommonDto>> getAllReviewingNotes(@PathVariable("status") String status) {
        return new OperationResponse<>(
                HttpStatus.OK.value(),
                noteService.getAllReviewingNotes(status).stream()
                        .map(GdNoteConverter::convertToDTO)
                        .toList()
        );
    }

    @PostMapping("/changeStatus/{id}/{status}")
    public OperationResponse<String> changeStatus(@PathVariable("id") Integer id, @PathVariable("status") String status) {
        noteService.changeStatus(id, status);
        return new OperationResponse<>(HttpStatus.OK.value(), "Статус заметки успешно изменен");
    }
}
