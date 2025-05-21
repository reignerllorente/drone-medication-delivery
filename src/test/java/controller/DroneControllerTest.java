import com.fasterxml.jackson.databind.ObjectMapper;
import com.thedrone.thedrone.controller.DroneController;
import com.thedrone.thedrone.dto.DroneRegistrationRequest;
import com.thedrone.thedrone.entity.Drone;
import com.thedrone.thedrone.entity.DroneModel;
import com.thedrone.thedrone.service.DroneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DroneController.class)
public class DroneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DroneService droneService;

    @Test
    public void testRegisterDrone() throws Exception {
        DroneRegistrationRequest request = new DroneRegistrationRequest(
                "DRN-1234",
                DroneModel.LIGHTWEIGHT,
                500,
                75
        );

        Drone registeredDrone = new Drone();
        registeredDrone.setSerialNumber(request.getSerialNumber());
        registeredDrone.setModel(request.getModel());
        registeredDrone.setWeightLimit(request.getWeightLimit());
        registeredDrone.setBatteryCapacity(request.getBatteryCapacity());

        when(droneService.registerDrone(org.mockito.ArgumentMatchers.any(Drone.class)))
                .thenReturn(registeredDrone);

        mockMvc.perform(post("/api/drones/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value("DRN-1234"));
    }
}