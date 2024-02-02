package org.gplumey.importer.infra.batch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@EnableConfigurationProperties
@ContextConfiguration(classes = BatchSetup.class)
@TestPropertySource(locations = "/application.yml")
public class BatchConfigTest {

    @Autowired
    BatchSetup batchSetup;


    @Test
    void test() {
        Assertions.assertNotNull(batchSetup.getWorkspace());
    }
}
