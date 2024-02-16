package com.dev.gdmainservice.controllers;

import com.dev.gdmainservice.models.dto.GdBookDto;
import com.dev.gdmainservice.responses.OperationResponse;
import com.dev.gdmainservice.services.GdBookServiceGrpc;
import com.dev.gdmainservice.services.GdBookServiceHttp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class GdBookController {

    private final GdBookServiceGrpc serviceGrpc;
    private final GdBookServiceHttp serviceHttp;

    @GetMapping(value = "/all")
    public OperationResponse<List<GdBookDto>> all() {
        return new OperationResponse<>(
                HttpStatus.OK.value(),
                serviceGrpc.getAllBooks()
        );
    }

    @GetMapping(value = "/byTopic/{topic}")
    public OperationResponse<List<GdBookDto>> byTopic(@PathVariable("topic") String topic) {
        return new OperationResponse<>(
                HttpStatus.OK.value(),
                serviceGrpc.getAllBooksByTopic(topic)
        );
    }

    @PostMapping(value = "/save")
    public OperationResponse<String> save(@RequestBody GdBookDto book) {
        return new OperationResponse<>(
                HttpStatus.OK.value(),
                serviceHttp.save(book)
        );
    }
}
