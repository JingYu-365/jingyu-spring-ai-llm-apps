package com.jingyu.spring.ai.function.calling.controller;

import com.jingyu.spring.ai.function.calling.func.MockSearchService;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * function calling controller
 *
 * @author JingYu
 * @date 2024/07/29
 */
@RestController
@RequestMapping("/function/calling")
@AllArgsConstructor
public class FunctionCallingController {


    private final OllamaChatModel ollamaChatModel;

    /**
     * 指定自定义函数回答用户的提问
     *
     * @param prompt       用户的提问
     * @return SSE流式响应
     */
    @GetMapping(value = "/chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ChatResponse chatStreamWithFunction(@RequestParam String prompt) {
        return ChatClient.create(ollamaChatModel).prompt().messages(new UserMessage(prompt))
                // spring ai会从已注册为bean的function中查找函数，
                // 将它添加到请求中。如果成功触发就会调用函数
                .functions("mockSearchService")
                .call().chatResponse();
    }

    /**
     * 指定自定义函数回答用户的提问
     *
     * @param prompt       用户的提问
     * @return SSE流式响应
     */
    @GetMapping(value = "/prompt/chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ChatResponse chatStreamWithPromptFunction(@RequestParam String prompt) {


        UserMessage userMessage = new UserMessage(prompt);

        OllamaOptions options = OllamaOptions.builder()
                .withFunctionCallbacks(List.of(FunctionCallbackWrapper.builder(new MockSearchService())
                        .withName("mockSearchService")
                        .withDescription("汽车价格检索")
                        .withResponseConverter((response) -> "" + response.price())
                        .build()))
                .build();

        return ollamaChatModel.call(new Prompt(List.of(userMessage), options));

    }
}
