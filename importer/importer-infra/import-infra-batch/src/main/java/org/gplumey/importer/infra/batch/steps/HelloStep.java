package org.gplumey.importer.infra.batch.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class HelloStep implements Tasklet {
    Logger log = LoggerFactory.getLogger(HelloStep.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        var path = chunkContext.getStepContext().getJobParameters().get("path");
        log.info("Hello from spring Batch: " + path);

        return RepeatStatus.FINISHED;
    }
}
