package com.thedrone.thedrone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadMedicationRequest {

    public @NotBlank String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(@NotBlank String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @NotBlank
    private String serialNumber;

    public @NotEmpty List<MedicationDTO> getMedications() {
        return medications;
    }

    public void setMedications(@NotEmpty List<MedicationDTO> medications) {
        this.medications = medications;
    }

    @NotEmpty
    private List<MedicationDTO> medications;
}
