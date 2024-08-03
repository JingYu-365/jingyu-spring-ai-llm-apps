package com.jingyu.spring.ai.embeding.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * Embedding结果向量化存储
 *
 * @author JingYu
 * @date 2024/07/28
 */
@RestController
@RequestMapping("/vector")
@AllArgsConstructor
public class VectorController {

    private final VectorStore vectorStore;

    /**
     * 将向量化数据存储到向量数据库
     *
     * @param message 文本内容
     * @return 向量化存储结果
     */
    @GetMapping("/text")
    public Boolean embedAndVector(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {

        // 将文本内容向量化到chromaDB中
        vectorStore.add(List.of(new Document(message)));

        return Boolean.TRUE;
    }


    @GetMapping("/retrieval")
    public Object queryVector(@RequestParam("query") String query) {
        SearchRequest searchRequest = SearchRequest.defaults()
                .withQuery(query)
                .withTopK(5)
                .withSimilarityThreshold(SearchRequest.SIMILARITY_THRESHOLD_ACCEPT_ALL);
//                .withFilterExpression();

        return vectorStore.similaritySearch(searchRequest);
    }
}
