package com.group.ai.client.assistant.repository;

import com.group.ai.client.assistant.model.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zcl on 2024/4/18.
 */
public interface AnswerRepository extends MongoRepository<Answer, String> {
}
