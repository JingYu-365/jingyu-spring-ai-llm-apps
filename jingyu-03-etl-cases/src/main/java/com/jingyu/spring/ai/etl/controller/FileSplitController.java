package com.jingyu.spring.ai.etl.controller;

import cn.hutool.core.collection.CollectionUtil;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * 文件transform
 *
 * @author JingYu
 * @date 2024/07/27
 */
@RestController
@RequestMapping("/transform")
public class FileSplitController {

    /**
     * 1. 加载PDF内容
     * 2. 文本进行切分
     *
     * @return
     */
    @GetMapping("/file")
    List<?> fileContentProcess() {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(new ClassPathResource("汽车销售话术.pdf"));
        List<Document> documentList = tikaDocumentReader.read();
        if (CollectionUtil.isEmpty(documentList)) {
            return Collections.EMPTY_LIST;
        }
        return new TokenTextSplitter().apply(documentList);
    }

}
