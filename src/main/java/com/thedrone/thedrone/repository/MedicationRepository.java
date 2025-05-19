package com.thedrone.thedrone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.thedrone.thedrone.entity.Drone;
import com.thedrone.thedrone.entity.Medication;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    // Find all medications loaded on a given drone
    List<Medication> findByDrone(Drone drone);
}
