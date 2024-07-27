package com.jingyu.spring.ai.etl.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * 文件加载控制器
 *
 * @author JingYu
 * @date 2024/07/27
 */
@RestController
@RequestMapping("loader")
@AllArgsConstructor
public class FileLoaderController {

    /**
     * 加载Json文档
     *
     * @return 文档内容
     */
    @GetMapping("/json")
    List<Document> loadJsonAsDocuments() {
        // 加载文件
        Resource resource = new ClassPathResource("etl.json");
        // 创建reader对象
        JsonReader jsonReader = new JsonReader(resource);
        // 获取内容
        return jsonReader.get();
    }


    /**
     * 加兹文本文件内容
     *
     * @return 文本内容
     */
    @GetMapping("/text")
    List<Document> loadText() {
        // 创建加载
        TextReader textReader = new TextReader(new ClassPathResource("etl.txt"));
        // 获取内容
        return textReader.read();
    }


    /**
     * 使用tika加载PDF, DOC/DOCX, PPT/PPTX, and HTML等文件
     * @return 文件内容
     */
    @GetMapping("/file")
    List<Document> loadFile() {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(new ClassPathResource("汽车销售话术.pdf"));
        return tikaDocumentReader.read();
    }

}
