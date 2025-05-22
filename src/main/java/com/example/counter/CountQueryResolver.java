package com.example.counter;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.graphql.api.CurrentCountQueryResolverInterface;
import com.example.graphql.types.CounterDTO;

import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CountQueryResolver implements CurrentCountQueryResolverInterface {

    private final CounterService counterService;

    @QueryMapping
    @Override
    public Mono<CounterDTO> currentCount(DataFetchingEnvironment env) {
        log.info("GraphQL query: currentCount");
        return Mono.just(counterService.getCount()).map(CounterDTO::new);
    }
}
