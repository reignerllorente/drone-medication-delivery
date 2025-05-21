package com.thedrone.thedrone.dto;

import com.thedrone.thedrone.entity.DroneModel;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneRegistrationRequest {

    public @NotBlank @Size(max = 100) String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(@NotBlank @Size(max = 100) String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @NotBlank
    @Size(max = 100)
    private String serialNumber;

    public @NotNull DroneModel getModel() {
        return model;
    }

    @NotNull
    private DroneModel model;

    @Max(1000)
    @Positive
    public int getWeightLimit() {
        return weightLimit;
    }

    @Max(1000)
    @Positive
    private int weightLimit;

    @Min(0)
    @Max(100)
    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    @Min(0)
    @Max(100)
    private int batteryCapacity;

    public DroneRegistrationRequest(String serialNumber, DroneModel model, int weightLimit, int batteryCapacity) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
    }

    public DroneRegistrationRequest() {
    }
}
