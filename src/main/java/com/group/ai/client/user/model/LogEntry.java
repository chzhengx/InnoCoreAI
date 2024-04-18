package com.group.ai.client.user.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by zcl on 2024/4/17.
 */
@Getter
@RequiredArgsConstructor
@Document
public class LogEntry {
    @Id
    private String id;
    private String message;
}
