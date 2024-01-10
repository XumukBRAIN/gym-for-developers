package com.dev.gdmainservice.utils;

import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.repositories.GdAnswerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class AppStopListener implements ApplicationListener<ContextClosedEvent> {
    private final GdAnswerRepository gdAnswerRepository;

    @Autowired
    public AppStopListener(GdAnswerRepository gdAnswerRepository) {
        this.gdAnswerRepository = gdAnswerRepository;
    }

    @Override
    public void onApplicationEvent(@Nullable ContextClosedEvent event) {
        AtomicInteger countUpdatedAnswer = new AtomicInteger();
        try {
            AppStartedListener.mapLikes.forEach((id, likes) ->
                    countUpdatedAnswer.set(gdAnswerRepository.saveLikes(id, likes))
            );
            log.info("Количество ответов у которых были обновлены лайки: {}", countUpdatedAnswer);
        } catch (Exception e) {
            log.error("Ошибка при сохранении кэша ответов");
            throw new GdRuntimeException("Ошибка при сохранении кэша ответов");
        }
    }
}
