package com.example.demo.pick;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class PickEventHandler {

    private final PickRepository pickRepository;

    @Async
    @EventListener
    @Transactional
    public void handle(PickEvent event) {
        pickRepository.save(new Pick(event.getArticle(), event.getPicker(), event.getItem()));
    }
}
