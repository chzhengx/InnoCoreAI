package com.group.ai.client.user.repository;

import com.group.ai.client.user.model.LogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zcl on 2024/4/17.
 */
public interface LogEntryRepository extends MongoRepository<LogEntry, String> {
}
