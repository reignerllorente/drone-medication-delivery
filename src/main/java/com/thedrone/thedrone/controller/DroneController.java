package com.thedrone.thedrone.controller;

import com.thedrone.thedrone.dto.*;
import com.thedrone.thedrone.entity.Drone;
import com.thedrone.thedrone.entity.Medication;
import com.thedrone.thedrone.service.DroneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping("/register")
    public ResponseEntity<Drone> registerDrone(@RequestBody @Valid DroneRegistrationRequest request) {

        Drone drone = new Drone();
        drone.setSerialNumber(request.getSerialNumber());
        drone.setModel(request.getModel());
        drone.setWeightLimit(request.getWeightLimit());
        drone.setBatteryCapacity(request.getBatteryCapacity());
        drone.setState(com.thedrone.thedrone.entity.DroneState.IDLE);

        return ResponseEntity.ok(droneService.registerDrone(drone));
    }

    @PostMapping("/load")
    public ResponseEntity<Drone> loadDrone(@RequestBody @Valid LoadMedicationRequest request) {

        List<Medication> meds = request.getMedications().stream()
                .map(dto -> {
                    Medication med = new Medication();
                    med.setName(dto.getName());
                    med.setWeight(dto.getWeight());
                    med.setCode(dto.getCode());
                    med.setImage(dto.getImage());
                    return med;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(droneService.loadMedications(request.getSerialNumber(), meds));
    }

    @GetMapping("/{serialNumber}/medications")
    public ResponseEntity<List<Medication>> getLoadedMedications(@PathVariable String serialNumber) {
        return ResponseEntity.ok(droneService.getLoadedMedications(serialNumber));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Drone>> getAvailableDrones() {
        return ResponseEntity.ok(droneService.getAvailableDronesForLoading());
    }

    @GetMapping("/{serialNumber}/battery")
    public ResponseEntity<Integer> getBatteryLevel(@PathVariable String serialNumber) {
        return ResponseEntity.ok(droneService.getDroneBatteryLevel(serialNumber));
    }
}