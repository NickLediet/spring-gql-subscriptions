package com.example.counter;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
@Slf4j
public class CounterService {
    private static Integer count = 0;
    private static final Sinks.Many<Integer> counterSink = Sinks.many().replay().all();
    
    // Initialize the sink with the starting value
    static {
        log.info("Initializing CounterService static block");
        Sinks.EmitResult result = counterSink.tryEmitNext(count);
        log.info("Initial emit result: {}", result);
    }
    
    @PostConstruct
    public void init() {
        log.info("CounterService initialized, current count = {}", count);
        log.info("Emitting initial value to sink");
        Sinks.EmitResult result = counterSink.tryEmitNext(count);
        log.info("PostConstruct emit result: {}", result);
    }

    public int getCount() {
        log.info("Getting current count: {}", count);
        return count;
    }

    public void setCount(Integer count) {
        log.info("Setting count to: {}", count);
        this.count = count;
        Sinks.EmitResult result = counterSink.tryEmitNext(count);
        log.info("setCount emit result: {}", result);
    }

    public void incrementCount() {
        count++;
        log.info("Incremented count to: {}", count);
        Sinks.EmitResult result = counterSink.tryEmitNext(count);
        log.info("incrementCount emit result: {}", result);
    }

    public Flux<Integer> getCounter() {
        log.info("Subscribing to counter Flux");
        return counterSink.asFlux();
    }
    
    /**
     * Manually emit multiple values for testing purposes.
     * This can be used to verify subscriptions are working.
     */
    public void emitTestSequence() {
        log.info("Emitting test sequence...");
        // Start with current value
        counterSink.tryEmitNext(count);
        
        // Emit a sequence of test values with delays between them
        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    Thread.sleep(1000);
                    int testValue = count + i;
                    log.info("Emitting test value: {}", testValue);
                    counterSink.tryEmitNext(testValue);
                }
            } catch (InterruptedException e) {
                log.error("Test sequence interrupted", e);
            }
        }).start();
    }
}
