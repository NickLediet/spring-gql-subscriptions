package com.example.counter;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.graphql.api.IncrementCountMutationResolverInterface;
import com.example.graphql.api.SetCountMutationResolverInterface;
import com.example.graphql.types.CounterDTO;

import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CountMutationResolver implements IncrementCountMutationResolverInterface, SetCountMutationResolverInterface{
    private final CounterService counterService;

    @Override
    @MutationMapping
    public Mono<CounterDTO> incrementCount(DataFetchingEnvironment env) {
        log.info("GraphQL mutation: incrementCount");
        counterService.incrementCount();
        return Mono.just(counterService.getCount()).map(CounterDTO::new);
    }

    @Override
    @MutationMapping
    public Mono<CounterDTO> setCount(@Argument int count, DataFetchingEnvironment env) {
        log.info("GraphQL mutation: setCount to {}", count);
        counterService.setCount(count);
        return Mono.just(counterService.getCount()).map(CounterDTO::new);
    }
} 