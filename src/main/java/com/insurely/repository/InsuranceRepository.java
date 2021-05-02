package com.insurely.repository;

import com.insurely.domain.insurance.BaseInsurance;

import java.util.Optional;

public interface InsuranceRepository {

    Optional<BaseInsurance> getByInsuranceNumber(String insuranceNumber);
}
