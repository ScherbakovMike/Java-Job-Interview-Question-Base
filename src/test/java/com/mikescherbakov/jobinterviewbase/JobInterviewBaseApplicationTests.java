package com.mikescherbakov.jobinterviewbase;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test-minimal")
class JobInterviewBaseApplicationTests {

  @Test
  void contextLoads() {
    // Tests that the application context can be loaded without datasources
  }
}
