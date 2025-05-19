package com.thedrone.thedrone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDTO {

    public @NotBlank @Pattern(regexp = "^[a-zA-Z0-9-_]+$") String getName() {
        return name;
    }

    public void setName(@NotBlank @Pattern(regexp = "^[a-zA-Z0-9-_]+$") String name) {
        this.name = name;
    }

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$")
    private String name;

    @Positive
    public int getWeight() {
        return weight;
    }

    public void setWeight(@Positive int weight) {
        this.weight = weight;
    }

    @Positive
    private int weight;

    public @NotBlank @Pattern(regexp = "^[A-Z0-9_]+$") String getCode() {
        return code;
    }

    public void setCode(@NotBlank @Pattern(regexp = "^[A-Z0-9_]+$") String code) {
        this.code = code;
    }

    @NotBlank
    @Pattern(regexp = "^[A-Z0-9_]+$")
    private String code;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    private byte[] image; // Base64 or URL string
}
