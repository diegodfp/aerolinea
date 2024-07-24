package com.aerolinea.documenttype.application;

import java.util.List;

import com.aerolinea.documenttype.domain.entity.Documenttype;
import com.aerolinea.documenttype.domain.service.DocumenttypeService;

public class DocumenttypeUseCase {
    private final DocumenttypeService documenttypeService;

    public DocumenttypeUseCase(DocumenttypeService documenttypeService) {
        this.documenttypeService = documenttypeService;
    }

    public List<Documenttype> getAllDocumenttypes(){
        return documenttypeService.getAllDocumenttypes();
    }
}
