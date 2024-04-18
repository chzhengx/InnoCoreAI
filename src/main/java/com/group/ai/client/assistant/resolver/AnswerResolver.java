package com.group.ai.client.assistant.resolver;


import com.group.ai.client.assistant.model.Answer;
import com.group.ai.client.assistant.repository.AnswerRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

/**
 * Created by zcl on 2024/4/18.
 */
@Component
public class AnswerResolver implements GraphQLQueryResolver {
    private final AnswerRepository answerRepository;

    public AnswerResolver(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer getAnswer(String question) {
        return answerRepository.findAll().stream()
                .filter(answer -> answer.getQuestion().equalsIgnoreCase(question))
                .findFirst()
                .orElse(null);
    }
}
