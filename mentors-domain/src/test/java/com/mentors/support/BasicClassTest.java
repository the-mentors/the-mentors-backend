package com.mentors.support;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.context.annotation.Profile;

@Profile("test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BasicClassTest {
}
