package com.thedrone.thedrone.repository;


import com.thedrone.thedrone.entity.Drone;
import com.thedrone.thedrone.entity.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    // Find all drones by state (e.g., IDLE or LOADING)
    List<Drone> findByState(DroneState state);

    // Find a drone by serial number
    Drone findBySerialNumber(String serialNumber);
}