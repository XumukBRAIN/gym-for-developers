package com.dev.gdmainservice.services;

import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdQuestion;
import com.dev.gdmainservice.repositories.GdQuestionRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.List;

/**
 * Сервис для работы с вопросами
 *
 * @author Ildar
 */
@Slf4j
@Service
public class GdQuestionService {
    private final GdQuestionRepository questionRepository;

    @Autowired
    public GdQuestionService(GdQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Метод для создания вопроса
     *
     * @param question Данные вопроса
     */
    public void save(GdQuestion question) {
        if (question == null) {
            throw new GdRuntimeException("В качестве параметра был передан null");
        }

        questionRepository.save(question);
    }

    public List<GdQuestion> getRandomQuestions(Integer count) {
        if (count == null) {
            count = 10;
            log.info("Количество вопросов не указано. По умолчанию будет 10 вопросов");
        }

        return questionRepository.findRandomQuestions(count);
    }

    public void generatePdf(Integer count) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("random_questions.pdf"));
            document.open();
            List<String> randomQuestions = getRandomQuestions(count).stream()
                    .map(GdQuestion::getIssue)
                    .toList();
            int questionNumber = 1;
            BaseFont baseFont = BaseFont.createFont("ArialUnicodeMS.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 14);

            for (String question : randomQuestions) {
                Paragraph paragraph = new Paragraph(questionNumber + ". " + question, font);
                document.add(paragraph);
                questionNumber++;
            }

            document.close();
        } catch (Exception e) {
            log.error("Ошибка в ходе создания PDF-файла с вопросами: {}", e.getMessage());
            throw new RuntimeException();
        }
    }
}
