package com.example.nutribalance.security;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.nutribalance.security.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WebConfig.class})
@ExtendWith(SpringExtension.class)
class WebConfigTest {
    @Autowired
    private WebConfig webConfig;

    /**
     * Method under test: {@link WebConfig#passwordEncoder()}
     */
    @Test
    void testPasswordEncoder() {
        assertTrue(webConfig.passwordEncoder() instanceof BCryptPasswordEncoder);
    }
}

