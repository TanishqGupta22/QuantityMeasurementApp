package com.app.controller;

import com.app.model.QuantityMeasurementEntity;
import com.app.service.QuantityMeasurementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quantity")
public class QuantityMeasurementController {

    private final QuantityMeasurementService service;

    public QuantityMeasurementController(QuantityMeasurementService service) {
        this.service = service;// declare service object
    }

    @PostMapping
    public QuantityMeasurementEntity add(@RequestBody QuantityMeasurementEntity entity) {
        return service.save(entity);
    }

    @GetMapping
    public List<QuantityMeasurementEntity> getAll() {
        return service.getAll();
    }
}