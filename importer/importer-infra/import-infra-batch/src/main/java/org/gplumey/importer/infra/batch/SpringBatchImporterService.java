package org.gplumey.importer.infra.batch;

import org.gplumey.importer.domain.core.entity.Import;
import org.gplumey.importer.domain.core.entity.valueobject.JobId;
import org.gplumey.importer.domein.service.port.output.ImporterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Component
public class SpringBatchImporterService implements ImporterService {

    private final JobLauncher jobLauncher;
    private final Job job;


    @Value("batch.temp.folder")
    String tempFolder;
    Logger logger = LoggerFactory.getLogger(SpringBatchImporterService.class);

    public SpringBatchImporterService(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Override
    public JobId execute(Import imp) {
        logger.info("Start import for " + imp.getId());
        Resource inputFile = new PathResource(imp.getImportFile().getPath().value());
        var jobParameters = new JobParametersBuilder()
                .addJobParameter("inputFile", inputFile, Resource.class)
                //.addJobParameter("import", imp, Import.class)
                .toJobParameters();
        try {
            var jobExecution = jobLauncher.run(job, jobParameters);
            logger.info("Job id: " + jobExecution.getId());
        } catch (JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
        return JobId.create();
    }
}
