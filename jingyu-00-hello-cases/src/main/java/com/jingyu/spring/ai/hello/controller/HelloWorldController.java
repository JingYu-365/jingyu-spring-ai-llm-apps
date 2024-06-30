package com.jingyu.spring.ai.hello.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * ollama hello world
 *
 * @author JingYu
 * @date 2024/06/29
 */
@AllArgsConstructor
@RequestMapping("/cases")
@RestController
public class HelloWorldController {

    private final OllamaChatModel ollamaChatModel;


    @GetMapping("/hello")
    String chat(@RequestParam String message) {
        return ollamaChatModel.call(message);
    }

    @GetMapping("/stream/hello")
    Flux<String> chatStream(@RequestParam String message) {
        return ollamaChatModel.stream(message);
    }
}
