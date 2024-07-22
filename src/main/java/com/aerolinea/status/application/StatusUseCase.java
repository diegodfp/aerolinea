package com.aerolinea.status.application;

import java.util.List;

import com.aerolinea.status.domain.entity.Status;
import com.aerolinea.status.domain.service.StatusService;

public class StatusUseCase {
     private final StatusService statusService;

    public StatusUseCase(StatusService statusService) {
        this.statusService = statusService;
    }

    public List<Status> getAllStatuses(){
        return statusService.getAllStatuses();
    }
}
