package com.insurely.application;

import com.insurely.domain.insurance.BaseInsurance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class InsuranceServiceIntegrationTest {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    void testFieldDiffWithMaxDataBaseInsuranceAgainstMaxDataCarInsurance() {
        // given
        BaseInsurance baseInsurance = new BaseInsurance();
        baseInsurance.setInsuranceType("baseInsurance");
        baseInsurance.setInsuranceSubType("subBaseInsurance");
        baseInsurance.setInsuranceName("some name");
        baseInsurance.setInsuranceNumber("some number");
        baseInsurance.setPaymentMethod("Bitcoin");
        baseInsurance.setPremiumAmountYear(1234);

        // when
        Set<String> fieldDiff = insuranceService
                .getFieldDiffWithInsuranceInPersistenceStorage("6c11070c4daab18af9353fbe3c75177b", baseInsurance);

        // then
        assertThat(fieldDiff.size()).isEqualTo(5);
    }
}
