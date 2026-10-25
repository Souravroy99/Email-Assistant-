package com.email.email_writer.service;

import com.email.email_writer.model.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailGeneratorService {
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public String generateEmailReply(EmailRequest emailRequest) {
        String prompt = buildPrompt(emailRequest) ;

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        return null;
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following email content. Please don't generate a subject line.") ;

        if(emailRequest.getTone() != null && !emailRequest.getTone().equals("")) {
            prompt.append(" Use a ")
                    .append(emailRequest.getTone())
                    .append(" tone.");
        }

        prompt.append("\nOriginal email: \n")
                .append(emailRequest.getEmailContent()) ;

        return prompt.toString() ;
    }
}