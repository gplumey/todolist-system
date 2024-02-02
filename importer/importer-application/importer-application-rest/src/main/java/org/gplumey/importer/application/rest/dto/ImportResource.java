package org.gplumey.importer.application.rest.dto;

import org.gplumey.importer.application.rest.controller.ImporterController;
import org.gplumey.importer.domain.core.entity.Import;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ImportResource extends RepresentationModel<ImportResource> {
    public static ImportResource of(Import anImport) {
        var dto = new ImportResource();
        dto.add(linkTo(methodOn(ImporterController.class).start(anImport.getId().getValue().toString())).withRel("start"));
        return dto;
    }
}
