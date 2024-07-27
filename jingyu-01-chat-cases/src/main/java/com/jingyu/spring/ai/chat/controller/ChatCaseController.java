package com.jingyu.spring.ai.chat.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用模型问答
 *
 * @author JingYu
 * @date 2024/06/30
 */
@AllArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatCaseController {

    private final OllamaChatModel ollamaChatModel;

    @GetMapping
    String chat(@RequestParam String message) {
        return ollamaChatModel.call(message);
    }
}
