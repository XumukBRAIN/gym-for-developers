package com.dev.gdmainservice.utils;

import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.repositories.GdAnswerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AppStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    public static Map<Long, Long> mapLikes = new HashMap<>();

    private final GdAnswerRepository gdAnswerRepository;

    @Autowired
    public AppStartedListener(GdAnswerRepository gdAnswerRepository) {
        this.gdAnswerRepository = gdAnswerRepository;
    }

    @Override
    public void onApplicationEvent(@Nullable ApplicationStartedEvent event) {
        try {
            List<Long[]> list = gdAnswerRepository.loadLikes();
            for (Long[] x : list) {
                mapLikes.put(x[0], x[1]);
            }
            log.info("Кэш ответов загружен успешно");
        } catch (Exception e) {
            log.error("Ошибка при загрузке кэша ответов: {}", e.getMessage());
            throw new GdRuntimeException("Ошибка при загрузке кэша ответов", "answer.cache.load.failed");
        }
    }
}
