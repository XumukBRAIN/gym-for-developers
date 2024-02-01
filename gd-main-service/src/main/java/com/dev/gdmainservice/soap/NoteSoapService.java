package com.dev.gdmainservice.soap;

import com.dev.gdmainservice.models.entity.GdNote;
import com.dev.gdmainservice.repositories.GdNoteRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@WebService(name = "noteSoapService")
@RequiredArgsConstructor
public class NoteSoapService {

    private final GdNoteRepository repository;

    @WebMethod(operationName = "createNote")
    public String createNote(@WebParam CreateNoteRequest request) {
        LocalDateTime atTime = LocalDateTime.now();
        log.info("Пришел Soap-запрос на создание заметки в {}", atTime);
        return save(parseToEntity(request, atTime));
    }

    @WebMethod(operationName = "createMotes")
    public String createNotes(@WebParam List<CreateNoteRequest> requests) {
        LocalDateTime atTime = LocalDateTime.now();
        log.info("Пришел Soap-запрос на создание списка заметок в {}", atTime);
        return saveAll(parseToEntity(requests, atTime));
    }

    private GdNote parseToEntity(CreateNoteRequest request, LocalDateTime atTime) {
        if (request == null) {
            log.error("Отсутствует тело запроса, пришедшего в {}", atTime);
            throw new RuntimeException("Отсутствует тело запроса");
        }

        return GdNote.newBuilder()
                .advice(request.advice)
                .section(request.getSection())
                .build();
    }

    private List<GdNote> parseToEntity(List<CreateNoteRequest> requests, LocalDateTime atTime) {
        List<GdNote> notes = new ArrayList<>();
        int countNullRequests = 0;
        for (CreateNoteRequest request : requests) {
            if (request == null) {
                countNullRequests++;
                continue;
            }
            log.error("Отсутствует тела {} запрос(а|ов), {}", countNullRequests, atTime);
            notes.add(GdNote.newBuilder()
                            .section(request.getSection())
                            .advice(request.getAdvice())
                            .build());
        }

        return notes;
    }

    private String saveAll(List<GdNote> notes) {
        repository.saveAll(notes);
        return "Заметки успешно созданы";
    }

    private String save(GdNote note) {
        repository.save(note);
        return "Заметка успешно создана";
    }
}
