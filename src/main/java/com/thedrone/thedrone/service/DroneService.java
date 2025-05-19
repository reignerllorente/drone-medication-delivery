package com.thedrone.thedrone.service;

import com.thedrone.thedrone.entity.*;
import com.thedrone.thedrone.repository.DroneRepository;
import com.thedrone.thedrone.repository.MedicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DroneService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    public DroneService(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    public Drone registerDrone(Drone drone) {
        if (drone.getWeightLimit() > 1000) {
            throw new IllegalArgumentException("Weight limit cannot exceed 1000g");
        }
        return droneRepository.save(drone);
    }

    @Transactional
    public Drone loadMedications(String serialNumber, List<Medication> medicationsToLoad) {
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        if (drone == null) {
            throw new IllegalArgumentException("Drone not found");
        }

            if (drone.getBatteryCapacity() < 25) {
            throw new IllegalStateException("Drone battery too low to load (must be ≥ 25%)");
        }

        int totalWeight = medicationsToLoad.stream().mapToInt(Medication::getWeight).sum();
        int currentWeight = drone.getMedications() != null ?
                drone.getMedications().stream().mapToInt(Medication::getWeight).sum() : 0;

        if (totalWeight + currentWeight > drone.getWeightLimit()) {
            throw new IllegalStateException("Exceeds drone weight limit");
        }

        drone.setState(DroneState.LOADING);
        for (Medication med : medicationsToLoad) {
            med.setDrone(drone);
            medicationRepository.save(med);
        }

        drone.setState(DroneState.LOADED);
        return droneRepository.save(drone);
    }

    public List<Medication> getLoadedMedications(String serialNumber) {
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        if (drone == null) {
            throw new IllegalArgumentException("Drone not found");
        }
        return medicationRepository.findByDrone(drone);
    }

    public List<Drone> getAvailableDronesForLoading() {
        return droneRepository.findByState(DroneState.IDLE);
    }

    public int getDroneBatteryLevel(String serialNumber) {
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        if (drone == null) {
            throw new IllegalArgumentException("Drone not found");
        }
        return drone.getBatteryCapacity();
    }
}
