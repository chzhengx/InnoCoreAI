package com.group.ai.client.user.repository;

import com.group.ai.client.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zcl on 2024/4/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
