package com.example.counter;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Counter {
    private int value;
    private LocalDateTime timestamp;
    
    // A convenience static method to create a counter with current timestamp
    public static Counter of(int value) {
        return Counter.builder()
                .value(value)
                .timestamp(LocalDateTime.now())
                .build();
    }
} 