package com.dev.gdmainservice.controllers;

import com.dev.gdmainservice.converters.GdQuestionConverter;
import com.dev.gdmainservice.models.dto.GdQuestionDto;
import com.dev.gdmainservice.models.entity.GdQuestion;
import com.dev.gdmainservice.services.GdQuestionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/question")
public class GdQuestionController {
    private final GdQuestionService gdQuestionService;

    @Autowired
    public GdQuestionController(GdQuestionService gdQuestionService) {
        this.gdQuestionService = gdQuestionService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdQuestionDto questionDto) {
        gdQuestionService.save(GdQuestionConverter.convertToEntity(questionDto));
        return ResponseEntity.ok("Вопрос успешно создан");
    }

    @GetMapping("/randomQuestions/{count}")
    public ResponseEntity<List<GdQuestion>> randomQuestions(@PathVariable Integer count) {
        return ResponseEntity.ok(gdQuestionService.getRandomQuestions(count));
    }

    @GetMapping("/generate-pdf/{count}")
    public void generateAndDownloadPdf(@PathVariable Integer count, HttpServletResponse response) throws IOException {
        File file = new File("random_questions.pdf");
        gdQuestionService.generatePdf(count);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=random_questions.pdf");
        response.setHeader("Content-Length", String.valueOf(file.length()));

        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error("Ошибка в ходе создания PDF-файла с вопросами: {} в {}", e.getMessage(), LocalDateTime.now());
            response.sendError(0, "Ошибка в ходе создания PDF-файла с вопросами");
            throw e;
        }
    }
}
