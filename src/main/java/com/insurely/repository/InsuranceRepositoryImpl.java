package com.insurely.repository;

import com.insurely.domain.insurance.BaseInsurance;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;


public class InsuranceRepositoryImpl implements InsuranceRepository {

    private final List<BaseInsurance> insurances;

    public InsuranceRepositoryImpl(List<BaseInsurance> insurances) {
        this.insurances = requireNonNull(insurances);
    }

    public Optional<BaseInsurance> getByInsuranceNumber(String insuranceNumber) {

        return insurances.stream()
                .filter(insurance ->
                        // I that an insurance is dirty if it does not have an identifier.
                        insurance.getInsuranceNumber() != null && insurance.getInsuranceNumber().equals(insuranceNumber))
                .findFirst();
    }

}
