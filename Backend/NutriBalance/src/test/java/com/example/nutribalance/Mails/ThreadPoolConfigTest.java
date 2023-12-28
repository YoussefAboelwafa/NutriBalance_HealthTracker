package com.example.nutribalance.Mails;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ThreadPoolConfig.class})
@ExtendWith(SpringExtension.class)
class ThreadPoolConfigTest {
    @Autowired
    private ThreadPoolConfig threadPoolConfig;

    /**
     * Method under test: {@link ThreadPoolConfig#emailThreadPool()}
     */
    @Test
    void testEmailThreadPool() {
        assertTrue(threadPoolConfig.emailThreadPool() instanceof ThreadPoolTaskExecutor);
    }
}
