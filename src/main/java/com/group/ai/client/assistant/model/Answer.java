package com.group.ai.client.assistant.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by zcl on 2024/4/18.
 */
@Getter
@Setter
@Document
public class Answer {
    @Id
    private String id;
    private String question;
    private String answer;
}
