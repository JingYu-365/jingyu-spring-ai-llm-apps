package com.jingyu.spring.ai.embeding.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Model Embedding cases
 *
 * @author JingYu
 * @date 2024/06/30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/embedding")
public class EmbeddingController {

    private final OllamaEmbeddingModel ollamaEmbeddingModel;

    @GetMapping("/msg")
    public Map embed(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        EmbeddingResponse embeddingResponse = ollamaEmbeddingModel.embedForResponse(List.of(message));
        return Map.of("embedding", embeddingResponse);
    }

    /**
     * 将向量化数据存储到向量数据库
     *
     * @param message 信息
     * @return
     */
    @GetMapping("/msg/vector")
    public Map embedAndOptions(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        EmbeddingResponse embeddingResponse = ollamaEmbeddingModel.embedForResponse(List.of(message));

        return Map.of("embedding", embeddingResponse);
    }

}
