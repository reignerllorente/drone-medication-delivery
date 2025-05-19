package com.thedrone.thedrone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Invalid medication name") String getName() {
        return name;
    }

    public void setName(@Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Invalid medication name") String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public @Pattern(regexp = "^[A-Z0-9_]+$", message = "Invalid medication code") String getCode() {
        return code;
    }

    public void setCode(@Pattern(regexp = "^[A-Z0-9_]+$", message = "Invalid medication code") String code) {
        this.code = code;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Invalid medication name")
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int weight; // in grams

    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Invalid medication code")
    @Column(nullable = false, unique = true)
    private String code;

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;
}