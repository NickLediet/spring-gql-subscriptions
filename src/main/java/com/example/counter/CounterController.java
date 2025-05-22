package com.example.counter;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CounterController {
    
    private final CounterService counterService;
    
    @PostMapping("/api/counter/test-sequence")
    public String triggerTestSequence() {
        log.info("Test sequence requested via REST endpoint");
        counterService.emitTestSequence();
        return "Test sequence started. Check logs for details.";
    }
} 