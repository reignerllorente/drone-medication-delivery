package com.thedrone.thedrone.scheduler;

import com.thedrone.thedrone.entity.Drone;
import com.thedrone.thedrone.entity.DroneState;
import com.thedrone.thedrone.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DroneStateScheduler {

    public DroneStateScheduler(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    private final DroneRepository droneRepository;

    @Scheduled(fixedRate = 10000) // every 10 seconds
    public void updateDroneStates() {
        List<Drone> drones = droneRepository.findAll();

        for (Drone drone : drones) {
            switch (drone.getState()) {
                case LOADING:
                    // If already loaded, move to LOADED
                    drone.setState(DroneState.LOADED);
                    break;

                case LOADED:
                    // Simulate delivery
                    drone.setState(DroneState.DELIVERING);
                    break;

                case DELIVERING:
                    // Simulate reaching delivery destination
                    drone.setState(DroneState.DELIVERED);
                    drone.setBatteryCapacity(Math.max(0, drone.getBatteryCapacity() - 10)); // reduce battery
                    break;

                case DELIVERED:
                    // Returning after delivery
                    drone.setState(DroneState.RETURNING);
                    break;

                case RETURNING:
                    // After returning, set to IDLE
                    drone.setState(DroneState.IDLE);
                    break;

                default:
                    break;
            }
        }

        droneRepository.saveAll(drones);
    }
}
