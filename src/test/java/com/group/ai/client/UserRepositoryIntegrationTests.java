package com.group.ai.client;

import com.group.ai.client.user.model.User;
import com.group.ai.client.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.*;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;
import static org.springframework.data.domain.ExampleMatcher.matching;

import java.util.Optional;

/**
 * Created by zcl on 2024/4/18.
 */
@Transactional
@SpringBootTest
public class UserRepositoryIntegrationTests {
    @Autowired
    UserRepository repository;

    User skyler, walter, flynn, marie, hank;

    @BeforeEach
    public void setUp() {

        repository.deleteAll();

        this.skyler = repository.save(new User(1L, "Skyler", "White", "45@email.com"));
        this.walter = repository.save(new User(2L, "Walter", "White", "50@email.com"));
        this.flynn = repository.save(new User(3L, "Walter Jr. (Flynn)", "White", "17@email.com"));
        this.marie = repository.save(new User(4L, "Marie", "Schrader", "38@email.com"));
        this.hank = repository.save(new User(5L, "Hank", "Schrader", "43@email.com"));
    }

    @Test
    public void countBySimpleExample() {

        var example = Example.of(new User(null, null, "White", null));

        assertThat(repository.count(example)).isEqualTo(3L);
    }

    @Test
    public void ignorePropertiesAndMatchByAge() {

        var example = Example.of(flynn, matching(). //
                withIgnorePaths("firstname", "lastname"));

        assertThat(repository.findOne(example)).contains(flynn);
    }

    @Test
    public void substringMatching() {

        var example = Example.of(new User(null, "er", null, null), matching(). //
                withStringMatcher(StringMatcher.ENDING));

        assertThat(repository.findAll(example)).containsExactly(skyler, walter);
    }

    @Test
    public void matchStartingStringsIgnoreCase() {

        var example = Example.of(new User(null, "Walter", "WHITE", null), matching(). //
                withIgnorePaths("age"). //
                withMatcher("firstname", startsWith()). //
                withMatcher("lastname", ignoreCase()));

        assertThat(repository.findAll(example)).containsExactlyInAnyOrder(flynn, walter);
    }

    @Test
    public void configuringMatchersUsingLambdas() {

        var example = Example.of(new User(null, "Walter", "WHITE", null), matching(). //
                withIgnorePaths("age"). //
                withMatcher("firstname", ExampleMatcher.GenericPropertyMatcher::startsWith). //
                withMatcher("lastname", ExampleMatcher.GenericPropertyMatcher::ignoreCase));

        assertThat(repository.findAll(example)).containsExactlyInAnyOrder(flynn, walter);
    }

    @Test
    public void valueTransformer() {

        var example = Example.of(new User(null, null, "White", "99@email.com"), matching(). //
                withMatcher("age", matcher -> matcher.transform(value -> Optional.of(50))));

        assertThat(repository.findAll(example)).containsExactly(walter);
    }
}
