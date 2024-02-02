package org.gplumey.importer.infra.batch.steps;

import org.gplumey.importer.infra.batch.model.TodolistRow;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

public class CVSItemReader extends FlatFileItemReader<TodolistRow> {

    public CVSItemReader(Resource resource) {
        setResource(resource);
        setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("name");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(TodolistRow.class);
            }});
        }});
    }
}
