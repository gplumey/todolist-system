package org.gplumey.importer.infra.batch.config.job;

import org.gplumey.importer.infra.batch.BatchSetup;
import org.gplumey.importer.infra.batch.model.TodolistRow;
import org.gplumey.importer.infra.batch.steps.CVSItemReader;
import org.gplumey.importer.infra.batch.steps.HelloStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import java.nio.file.Path;

@Configuration
public class ImportConfig {


    private final JobRepository jobRepository;
    private final ResourcelessTransactionManager transactionManager;

    public ImportConfig(JobRepository jobRepository, ResourcelessTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }


    @Bean
    @Qualifier("step1")
    public Step step() {
        return new StepBuilder("myStep", jobRepository).tasklet(new HelloStep(), transactionManager).build();
    }

    @Bean
    @Qualifier("inmportCSVStep")
    public Step importCSVStep(FlatFileItemReader<TodolistRow> reader,
                              ItemWriter writer,
                              ItemProcessor processor) {
        var builder = new StepBuilder("importCSV", jobRepository);
        return builder.chunk(2, transactionManager)
                      .reader(reader)
                      .processor(processor)
                      .writer(writer)
                      .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader reader(@Value("#{jobParameters['inputFile']}") Resource inputFile) {
        FlatFileItemReader reader = new CVSItemReader(inputFile);

        return reader;
    }

    @Bean
    @StepScope
    public ItemProcessor processor() {
        ItemProcessor<TodolistRow, TodolistRow> processor = item -> new TodolistRow(item.getName().toUpperCase());

        return processor;
    }

    @Bean
    @StepScope
    public FlatFileItemWriter writer(BatchSetup batchSetup) {
        Path workspace = Path.of(batchSetup.getWorkspace());

        Path output = workspace.resolve("outputData.csv");
        WritableResource resource = new FileSystemResource(output);

        var writer = new FlatFileItemWriter<TodolistRow>();
        writer.setResource(resource);
        writer.setLineAggregator(new DelimitedLineAggregator<>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<>() {
                    {
                        setNames(new String[]{"name"});
                    }
                });
            }
        });
        return writer;
    }

    @Bean
    public Job job(@Qualifier("inmportCSVStep") Step step2) {
        return new JobBuilder("importerJob", jobRepository).start(step()).next(step2).build();
    }
}
