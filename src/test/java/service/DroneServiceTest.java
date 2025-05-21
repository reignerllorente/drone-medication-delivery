package service;


import com.thedrone.thedrone.entity.Drone;
import com.thedrone.thedrone.entity.DroneModel;
import com.thedrone.thedrone.entity.DroneState;
import com.thedrone.thedrone.repository.DroneRepository;
import com.thedrone.thedrone.service.DroneService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class DroneServiceTest {

    private final DroneRepository droneRepository = mock(DroneRepository.class);
    private final DroneService droneService = new DroneService(droneRepository, null);

    @Test
    void testRegisterDrone() {
        Drone drone = new Drone();
        drone.setSerialNumber("DRONE-001");
        drone.setModel(DroneModel.LIGHTWEIGHT);
        drone.setBatteryCapacity(100);
        drone.setWeightLimit(500);
        drone.setState(DroneState.IDLE);

        when(droneRepository.save(any(Drone.class))).thenReturn(drone);

        Drone saved = droneService.registerDrone(drone);

        assertNotNull(saved);
        assertEquals("DRONE-001", saved.getSerialNumber());
        verify(droneRepository, times(1)).save(drone);
    }
}
