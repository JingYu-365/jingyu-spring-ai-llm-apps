package com.jingyu.spring.ai.function.calling.func;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * 通过@Description描述函数的用途，这样ai在多个函数中可以根据描述进行选择。
 */
@Description("汽车价格检索")
@Service
public class MockSearchService implements Function<MockSearchService.SearchRequest, MockSearchService.SearchResponse> {

    @Data
    public static class SearchRequest {
        @JsonProperty(required = true, value = "path")
        @JsonPropertyDescription(value = "汽车型号")
        String carType;
    }

    public record SearchResponse(Integer price) {
    }

    public SearchResponse apply(SearchRequest request) {
        System.out.println(request.carType);
        // mock 数据
        return new SearchResponse(300000);
    }
}