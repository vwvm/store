package vwvm.gptweb;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import java.util.Arrays;

public class TestB {
    public static void main(String[] args) {
        // 配置api keys
        OpenAiClient openAiClient = new OpenAiClient("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");
        CompletionResponse completions = openAiClient.completions("三体人是什么？");
        Arrays.stream(completions.getChoices()).forEach(System.out::println);
    }
}
