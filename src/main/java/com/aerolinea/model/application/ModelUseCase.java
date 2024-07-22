package com.aerolinea.model.application;

import java.util.List;

import com.aerolinea.model.domain.entity.Model;
import com.aerolinea.model.domain.service.ModelService;

public class ModelUseCase {
    private final ModelService modelService;

    public ModelUseCase(ModelService modelService) {
        this.modelService = modelService;
    }

    public List<Model> getAllModels(){
        return modelService.getAllModels();
    }
    
}
