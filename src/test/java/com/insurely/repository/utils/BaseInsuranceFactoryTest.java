package com.insurely.repository.utils;

import com.insurely.configuration.JacksonConfiguration;
import com.insurely.domain.insurance.BaseInsurance;
import com.insurely.domain.insurance.CarInsurance;
import com.insurely.domain.insurance.CondoInsurance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BaseInsuranceFactoryTest {


    private BaseInsuranceFactory baseInsuranceFactory;

    @BeforeEach
    void setup() {
        this.baseInsuranceFactory = new BaseInsuranceFactory(new JacksonConfiguration().objectMapper());
    }

    @Test
    void testCreatingCarInsurancePojo() {
        // given
        String carInsuranceAsString = "{\"insuranceType\":\"vehicleInsurance\",\"insuranceSubType\":\"carInsurance\",\"insuranceNumber\":\"0346dea4a74b081c12526f96252ee63a\",\"paymentMethod\":\"44e755197c17f0577788acb8608c5666\",\"brand\":\"cf8845f187fc0ce57f631fe292217177\",\"coverage\":\"1bdcf6253aa1e64c2f7323fb1b3664a2\"}";

        // when
        List<BaseInsurance> insuranceList = baseInsuranceFactory.createPOJOsFromJson(List.of(carInsuranceAsString));

        // then
        assertThat(insuranceList.size()).isEqualTo(1);
        assertThat(insuranceList.get(0).getInsuranceType()).isEqualTo("vehicleInsurance");
        assertThat(insuranceList.get(0).getInsuranceNumber()).isEqualTo("0346dea4a74b081c12526f96252ee63a");
    }

    @Test
    void testCreatingCondoInsurancePOJO() {
        // given
        String condoInsuranceAsString = "{\"insuranceType\":\"houseContentInsurance\",\"insuranceSubType\":\"condoInsurance\",\"livingArea\":51,\"numberOfResidents\":76,\"insuredAmount\":18}";

        // when
        List<BaseInsurance> insuranceList = baseInsuranceFactory.createPOJOsFromJson(List.of(condoInsuranceAsString));

        // then
        assertThat(insuranceList.size()).isEqualTo(1);
        CondoInsurance condoInsurance = (CondoInsurance) insuranceList.get(0);
        assertThat(condoInsurance.getInsuranceType()).isEqualTo("houseContentInsurance");
        assertThat(condoInsurance.getNumberOfResidents()).isEqualTo(76);
    }

    @Test
    void testCreatingMultipleCarInsurancePojo() {
        // given
        String carInsuranceAsString = "{\"insuranceType\":\"vehicleInsurance\",\"insuranceSubType\":\"carInsurance\",\"insuranceNumber\":\"0346dea4a74b081c12526f96252ee63a\",\"paymentMethod\":\"44e755197c17f0577788acb8608c5666\",\"brand\":\"cf8845f187fc0ce57f631fe292217177\",\"coverage\":\"1bdcf6253aa1e64c2f7323fb1b3664a2\"}";
        String carInsuranceAsString2 = "{\"insuranceType\":\"vehicleInsurance\",\"insuranceSubType\":\"carInsurance\",\"insuranceName\":\"2b93aee990184884a8c18ffa5faf0489\",\"paymentMethod\":\"09d29faea52bf0843156378c5eba8f2f\",\"premiumAmountYear\":93,\"brand\":\"76ba1d194335e94e4ac493f1f910fd25\",\"model\":\"0ca49bf9a0711f8d23a295c284a752c5\",\"renewalDate\":\"d43a0b1d6985d2618a4ebcc0aeb3facc\"}";

        // when
        List<BaseInsurance> insuranceList = baseInsuranceFactory.createPOJOsFromJson(List.of(carInsuranceAsString, carInsuranceAsString2));

        // then
        assertThat(insuranceList.size()).isEqualTo(2);
        assertThat(insuranceList.get(0).getInsuranceType()).isEqualTo("vehicleInsurance");
        assertThat(insuranceList.get(0).getInsuranceNumber()).isEqualTo("0346dea4a74b081c12526f96252ee63a");

        assertThat(insuranceList.get(1).getInsuranceType()).isEqualTo("vehicleInsurance");
        assertThat(insuranceList.get(1).getInsuranceName()).isEqualTo("2b93aee990184884a8c18ffa5faf0489");
    }

    @Test
    void testWithDifferentInsuranceTypes() {
        // given
        String carInsuranceAsString = "{\"insuranceType\":\"vehicleInsurance\",\"insuranceSubType\":\"carInsurance\",\"insuranceNumber\":\"0346dea4a74b081c12526f96252ee63a\",\"paymentMethod\":\"44e755197c17f0577788acb8608c5666\",\"brand\":\"cf8845f187fc0ce57f631fe292217177\",\"coverage\":\"1bdcf6253aa1e64c2f7323fb1b3664a2\"}";
        String condoInsuranceAsString = "{\"insuranceType\":\"houseContentInsurance\",\"insuranceSubType\":\"condoInsurance\",\"livingArea\":51,\"numberOfResidents\":76,\"insuredAmount\":18}";

        // when
        List<BaseInsurance> insuranceList = baseInsuranceFactory.createPOJOsFromJson(List.of(carInsuranceAsString, condoInsuranceAsString));

        // then
        assertThat(insuranceList.size()).isEqualTo(2);
        CarInsurance carInsurance = (CarInsurance) insuranceList.get(0);
        assertThat(carInsurance.getInsuranceType()).isEqualTo("vehicleInsurance");
        assertThat(carInsurance.getInsuranceNumber()).isEqualTo("0346dea4a74b081c12526f96252ee63a");

        CondoInsurance condoInsurance = (CondoInsurance) insuranceList.get(1);
        assertThat(condoInsurance.getInsuranceType()).isEqualTo("houseContentInsurance");
        assertThat(condoInsurance.getNumberOfResidents()).isEqualTo(76);
    }
}