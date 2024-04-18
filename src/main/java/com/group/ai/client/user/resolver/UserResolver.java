package com.group.ai.client.user.resolver;

import com.group.ai.client.user.model.User;
import com.group.ai.client.user.repository.UserRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

/**
 * Created by zcl on 2024/4/17.
 */
@Component
public class UserResolver implements GraphQLQueryResolver {

    private final UserRepository userRepository;

    public UserResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
