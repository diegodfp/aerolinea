package com.aerolinea.documenttype.domain.service;

import java.util.List;

import com.aerolinea.documenttype.domain.entity.Documenttype;

public interface DocumenttypeService {
    void createDocumenttype(Documenttype documenttype);
    Documenttype findDocumenttypeById(int id);
    void updateDocumenttype(Documenttype documenttype);
    void deleteDocumenttype(int id);
    List<Documenttype> getAllDocumenttypes();
}
