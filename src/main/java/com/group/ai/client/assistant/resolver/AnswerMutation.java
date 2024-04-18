package com.group.ai.client.assistant.resolver;

import com.group.ai.client.assistant.model.Answer;
import com.group.ai.client.assistant.repository.AnswerRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

/**
 * Created by zcl on 2024/4/18.
 */
@Component
public class AnswerMutation implements GraphQLMutationResolver {
    private final AnswerRepository answerRepository;

    public AnswerMutation(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer addAnswer(String question, String answer) {
        Answer newAnswer = new Answer();
        newAnswer.setQuestion(question);
        newAnswer.setAnswer(answer);
        return answerRepository.save(newAnswer);
    }
}
