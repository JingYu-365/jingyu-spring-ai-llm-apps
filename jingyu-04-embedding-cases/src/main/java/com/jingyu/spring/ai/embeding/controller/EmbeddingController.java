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

    /**
     * 将文本内容向量化
     *
     * @param message 文本
     * @return 向量化数据
     */
    @GetMapping("/text")
    public Map<String, ?> embed(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        EmbeddingResponse embeddingResponse = ollamaEmbeddingModel.embedForResponse(List.of(message));
        return Map.of("embedding", embeddingResponse);
    }

}
