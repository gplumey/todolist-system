package org.gplumey.importer.application.rest.controller;


import org.gplumey.importer.application.rest.dto.ImportResource;
import org.gplumey.importer.domain.core.entity.Import;
import org.gplumey.importer.domain.core.entity.valueobject.ImportId;
import org.gplumey.importer.domein.service.port.input.Usecases;
import org.gplumey.importer.domein.service.port.input.command.StartImportCommand;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "/import")
@CrossOrigin
public class ImporterController {
    private final SimpMessagingTemplate messagingTemplate;

    private final Usecases.Commands.CreateImport createImport;

    private final Usecases.Commands.StartImport startImport;

    public ImporterController(SimpMessagingTemplate messagingTemplate, Usecases.Commands.CreateImport createImport,
                              Usecases.Commands.StartImport startImport) {
        this.messagingTemplate = messagingTemplate;
        this.createImport = createImport;
        this.startImport = startImport;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ImportResource upload(@RequestParam("file") MultipartFile file) {
        var root = Paths.get(".", "upload");
        var rootFolder = root.toFile();
        if (!rootFolder.exists()) {
            rootFolder.mkdir();
        }
        var tmpFile = root.resolve(Instant.now().toEpochMilli() + "_" + file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), tmpFile);
            System.out.println("Upload : " + file.getOriginalFilename());

            Import anImport = createImport.execute(() -> tmpFile.toFile());
            return ImportResource.of(anImport);
        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
    }


    @GetMapping(value = "import/{importId}/start", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ImportResource start(@PathVariable String importId) {
        startImport.execute(new StartImportCommand(ImportId.of(importId)));

        return ImportResource.of(new Import(ImportId.of(importId), null, null));
    }

    @GetMapping
    public String hello() throws InterruptedException {
        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(() ->

                {
                    try {
                        Thread.sleep(1000);
                        messagingTemplate.convertAndSend("/topic/message", "Hello world " + UUID.randomUUID());
                    } catch (InterruptedException e) {
                        messagingTemplate.convertAndSend("/topic/message", "Hello world FAILED " + UUID.randomUUID());

                        throw new RuntimeException(e);
                    }
                }
        );


        return "Hello Importer";
    }
}
