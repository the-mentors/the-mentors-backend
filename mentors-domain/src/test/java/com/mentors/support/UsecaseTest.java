package com.mentors.support;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

@ExtendWith(MockitoExtension.class)
@Profile("test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UsecaseTest {
}
