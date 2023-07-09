package vwvm.gptweb;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

/**
 * <h3>store</h3>
 * <p>gpt测试</p>
 *
 * @author : BlackBox
 * @date : 2023-05-13 19:37
 **/
public class GptTest {
    public static void main(String[] args) {
        OpenAiService service = new OpenAiService("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");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Somebody once told me the world is gonna roll me")
                .model("ada")
                .echo(true)
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
    }
}
