package org.vwvm.paperManagement.api.openAi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/test")
public class OpenAIController {

    @GetMapping("/openai")
    public String complete() throws IOException, InterruptedException {
        String prompt = "The quick brown fox";
        String url = "https://api.openai.com/v1/engines/davinci-codex/completions";
        String requestBody = String.format("{\"prompt\": \"%s\", \"max_tokens\": 50}", prompt);

        HttpClient client = HttpClient.newHttpClient();
        String apiKey = "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";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", String.format("Bearer %s", apiKey))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            return String.format("Error: %d %s", response.statusCode(), response.body());
        }
    }
}
