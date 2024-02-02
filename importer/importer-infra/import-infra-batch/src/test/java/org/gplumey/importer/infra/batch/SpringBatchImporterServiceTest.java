package org.gplumey.importer.infra.batch;


import org.gplumey.importer.domain.core.entity.Import;
import org.gplumey.importer.domain.core.entity.ImportFile;
import org.gplumey.importer.domein.service.port.output.ImporterService;
import org.gplumey.importer.infra.batch.config.BatchConfig;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@SpringBatchTest
@SpringBootTest
@SpringJUnitConfig(BatchConfig.class)
@ComponentScan("org.gplumey.importer.infra.batch")
@EnableAutoConfiguration
@EnableConfigurationProperties(value = BatchSetup.class)
@ConfigurationPropertiesScan
@TestPropertySource("classpath:application.yml")
class SpringBatchImporterServiceTest {


    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
    private static final File workspace = new File(TEMP_DIRECTORY, "importer_tmp");

    private final Path INPUT_PATH = Path.of("src", "test", "resources", "batch", "input");
    private final Path OUTPUT_PATH = Path.of("src", "test", "resources", "batch", "output");
    Logger log = LoggerFactory.getLogger(SpringBatchImporterServiceTest.class);
    @Autowired
    ImporterService importerService;
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;
    @MockBean
    private BatchSetup batchSetup;

    @BeforeAll
    static void beforeAll() {
        if (!workspace.exists()) {
            workspace.mkdir();
        }
    }

    @BeforeEach
    void setup() {
        Mockito.when(batchSetup.getWorkspace()).thenReturn(workspace.getAbsolutePath());

        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    @DisplayName("""
            Given a basic CSV file 
            When import 
            Then new file is created.
            """)
    void testImportCSV() throws IOException {
        var source = INPUT_PATH.resolve("import.csv").toFile();
        if (!source.exists()) {
            throw new IllegalStateException("source " + source.getAbsoluteFile() + " does not exists");
        }

        var target = workspace.toPath().resolve(source.getName()).toFile();

        Files.copy(source.toPath(), target.toPath());

        var importFile = ImportFile.of(target);

        importerService.execute(Import.create(importFile));
    }

    @AfterEach
    void tearDown() throws IOException {
        Stream<Path> fileTrees = Files.walk(workspace.toPath());
        fileTrees.filter(Files::isRegularFile)
                 .map(Path::toFile)
                 .peek(file -> log.warn("deleting the file : " + file.getName()))
                 .forEach(file -> file.delete());
    }

    @TestConfiguration
    class Context {

        @Bean
        @Primary
        BatchSetup batchSetup() {
            var mock = Mockito.mock(BatchSetup.class);
            // Mockito.when(mock.inputDirectoryPath()).thenReturn(INPUT_PATH);
            //Mockito.when(mock.outputDirectoryPath()).thenReturn(OUTPUT_PATH);
            return mock;
        }
    }
}
