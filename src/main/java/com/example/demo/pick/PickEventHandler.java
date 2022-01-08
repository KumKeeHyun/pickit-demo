package com.example.demo.pick;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class PickEventHandler {

    private final PickerPickRepository pickerPickRepository;

    @Async
    @EventListener
    @Transactional
    public void handle(PickEvent event) {
        pickerPickRepository.save(new PickerPick(event.getPicker(), event.getPick()));
    }
}
