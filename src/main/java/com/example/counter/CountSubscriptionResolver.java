package com.example.counter;

import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import com.example.graphql.api.CounterSubscriptionResolverInterface;
import com.example.graphql.types.CounterDTO;

import graphql.schema.DataFetchingEnvironment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CountSubscriptionResolver implements CounterSubscriptionResolverInterface {
    private final CounterService counterService;
    
    @PostConstruct
    public void init() {
        log.info("CountSubscriptionResolver initialized");
    }

    @SubscriptionMapping("counter")
    public Flux<CounterDTO> counter(DataFetchingEnvironment env) {
        log.info("GraphQL subscription requested for counter");
        // Emit an initial value to ensure subscribers get something right away
        counterService.emitTestSequence();
       return counterService.getCounter().map(CounterDTO::new); 
        // return counterService.getCounter()
        //     .doOnSubscribe(s -> log.info("Client subscribed to counter"))
        //     .doOnNext(value -> log.info("Emitting counter value: {}", value))
        //     .doOnError(e -> log.error("Error in counter subscription: {}", e.getMessage()))
        //     .doOnCancel(() -> log.info("Client cancelled counter subscription"));
    }
} 