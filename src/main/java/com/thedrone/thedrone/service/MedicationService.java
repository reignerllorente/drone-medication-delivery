package com.thedrone.thedrone.service;

import com.thedrone.thedrone.entity.Medication;
import com.thedrone.thedrone.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public Medication save(Medication medication) {
        return medicationRepository.save(medication);
    }

    public List<Medication> getAll() {
        return medicationRepository.findAll();
    }
}
