package com.app.service;

import com.app.model.QuantityMeasurementEntity;
import com.app.repository.QuantityMeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementService {

    private final QuantityMeasurementRepository repository;

    public QuantityMeasurementService(QuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    public QuantityMeasurementEntity save(QuantityMeasurementEntity entity) {
        return repository.save(entity);
    }

    public List<QuantityMeasurementEntity> getAll() {
        return repository.findAll();
    }
}