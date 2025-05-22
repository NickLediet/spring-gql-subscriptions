package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ViewController {
    @GetMapping("/")
    public String home() {
        log.info("Home page requested");
        return "index";
    }

    @GetMapping("/wsexample")
    public String wsexample() {
        log.info("WebSocket example page requested");
        return "wsexample";
    }

    @GetMapping("/counter")
    public String graphqlCounter() {
        log.info("GraphQL counter page requested");
        return "graphql-counter";
    }
    
    @GetMapping("/counter-simple")
    public String simpleCounter() {
        log.info("Simple counter test page requested");
        return "counter-simple";
    }
}
