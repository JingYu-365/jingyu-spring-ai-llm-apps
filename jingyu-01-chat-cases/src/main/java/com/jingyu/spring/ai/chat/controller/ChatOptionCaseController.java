package com.jingyu.spring.ai.chat.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * 使用模型问答
 *
 * @author JingYu
 * @date 2024/06/30
 */
@AllArgsConstructor
@RequestMapping("/chat-option")
@RestController
public class ChatOptionCaseController {

    private final OllamaChatModel ollamaChatModel;

    @GetMapping
    Flux<?> chatOptions(@RequestParam String message) {
        return ollamaChatModel.stream(new Prompt(
                message,
                new OllamaOptions()
                        .withTemperature(0.2f)      // 设置回话温度，温度越高回答越发散
                        .withModel("llama3.1")        // 设置本次会话使用的模型，会覆盖application中配置的。
        ));
    }
}
