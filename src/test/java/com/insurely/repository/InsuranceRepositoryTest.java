package com.insurely.repository;

import com.insurely.domain.insurance.BaseInsurance;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class InsuranceRepositoryTest {


    @Test
    void testShouldFindingInsuranceByNumber() {
        // given
        InsuranceRepositoryImpl insuranceRepository = new InsuranceRepositoryImpl(createBaseInsuranceList());

        // when
        String insuranceNumber = "1234";
        Optional<BaseInsurance> insurance = insuranceRepository.getByInsuranceNumber(insuranceNumber);

        // then
        assertThat(insurance.isPresent()).isEqualTo(true);
        assertThat(insurance.get().getInsuranceNumber()).isEqualTo(insuranceNumber);
    }

    @Test
    void testShouldNotFindInsurance() {
        // given
        InsuranceRepositoryImpl insuranceRepository = new InsuranceRepositoryImpl(createBaseInsuranceList());

        // when
        String insuranceNumber = "I do not exist";
        Optional<BaseInsurance> insurance = insuranceRepository.getByInsuranceNumber(insuranceNumber);

        // then
        assertThat(insurance.isPresent()).isEqualTo(false);
    }

    private List<BaseInsurance> createBaseInsuranceList() {
        return List.of(
                createBaseInsuranceWithNumber("1234"),
                createBaseInsuranceWithNumber("4321")
                );
    }

    private BaseInsurance createBaseInsuranceWithNumber(String insuranceNumber) {
        BaseInsurance baseInsurance = new BaseInsurance();
        baseInsurance.setInsuranceNumber(insuranceNumber);
        return baseInsurance;
    }

}