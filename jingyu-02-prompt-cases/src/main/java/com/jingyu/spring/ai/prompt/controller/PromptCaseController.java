package com.jingyu.spring.ai.prompt.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * 验证Prompt使用方式
 *
 * @author JingYu
 * @date 2024/07/27
 */
@RestController
@RequestMapping("/prompt")
@AllArgsConstructor
public class PromptCaseController {

    private final OllamaChatModel ollamaChatModel;

    @GetMapping
    String chatOptions(@RequestParam String carName, @RequestParam Integer price) {

        PromptTemplate promptTemplate = new PromptTemplate("您是一位专业的汽车销售文案撰写员。" +
                "对于售价为 {price} 元的 {car_name} 轿车，您能提供一个吸引人的50字简短销售口号吗？");

        Prompt prompt = promptTemplate.create(Map.of("price", price, "car_name", carName));

        ChatResponse call = ollamaChatModel.call(prompt);
        return call.toString();
    }

    @GetMapping("/role")
    String chatRolePrompt(@RequestParam("name") String name, @RequestParam("product") String product) {

        String userText = """
                帮我设计一个小米14Pro手机的宣传文案，要求20字以内，手机特点如下：
                1. 手机屏幕分辨率高；
                2. 照片支持AI能力；
                等等
                """;

        Message userMessage = new UserMessage(userText);

        String systemText = """
                你的名字是 {name}，是一个优秀的 {product} 宣传文案设计，能够从用户的痛点出发，结合产品的特点进行宣传文案设计。
                """;

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", name, "product", product));

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        return ollamaChatModel.call(prompt).toString();

    }

}
