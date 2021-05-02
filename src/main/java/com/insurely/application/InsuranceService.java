package com.insurely.application;

import com.insurely.domain.insurance.BaseInsurance;
import com.insurely.repository.InsuranceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;

@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = requireNonNull(insuranceRepository);
    }

    public Set<String> getFieldDiffFromTwoInsurancesOfSameType(BaseInsurance firstInsurance, BaseInsurance secondInsurance) {

        if (firstInsurance.getClass().equals(secondInsurance.getClass())) {
            return firstInsurance.getFieldDiff(secondInsurance);
        }

        throw new RuntimeException("insurances are not of the same type");
    }

    public Set<String> getFieldDiffWithInsuranceInPersistenceStorage(String insuranceNumber, BaseInsurance givenInsurance) {
        Optional<BaseInsurance> existingInsurance = insuranceRepository.getByInsuranceNumber(insuranceNumber);

        return existingInsurance.map(insurance -> insurance.getFieldDiff(givenInsurance))
                .orElseThrow(() -> new RuntimeException("unable to find given insurance number in repository: " + insuranceNumber));
    }
}
