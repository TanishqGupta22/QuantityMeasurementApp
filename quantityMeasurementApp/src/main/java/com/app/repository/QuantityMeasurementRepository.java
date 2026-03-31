package com.app.repository;

import com.app.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Integer> {


    List<QuantityMeasurementEntity> findByName(String name);

}