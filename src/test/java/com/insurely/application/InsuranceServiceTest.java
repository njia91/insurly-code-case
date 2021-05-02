package com.insurely.application;

import com.insurely.domain.insurance.*;
import com.insurely.repository.InsuranceRepository;
import com.insurely.repository.InsuranceRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InsuranceServiceTest {

    private InsuranceService insuranceService;

    @BeforeEach
    void setup() {
        InsuranceRepository insuranceRepository = new InsuranceRepositoryImpl(List.of(createCarInsuranceWithAllFields(),
                createCondoInsuranceWithAllFields(),
                createCottageInsuranceWithAllFields(),
                createMopedInsuranceWithAllFields()));

        this.insuranceService = new InsuranceService(insuranceRepository);
    }

    @Test
    void testCondoInsuranceFieldDiff() {
        // given
        CondoInsurance condoInsuranceFull = createCondoInsuranceWithAllFields();
        CondoInsurance condoInsuranceEmpty = new CondoInsurance();

        // when
        Set<String> fieldDiff = insuranceService.getFieldDiffFromTwoInsurancesOfSameType(condoInsuranceFull, condoInsuranceEmpty);

        // then
        assertThat(fieldDiff.size()).isEqualTo(11);
    }

    @Test
    void testMopedInsuranceFieldDiff() {
        // given
        MopedInsurance mopedInsuranceFull = createMopedInsuranceWithAllFields();
        MopedInsurance mopedInsuranceEmpty = new MopedInsurance();

        // when
        Set<String> fieldDiff = insuranceService.getFieldDiffFromTwoInsurancesOfSameType(mopedInsuranceFull, mopedInsuranceEmpty);

        // then
        assertThat(fieldDiff.size()).isEqualTo(9);
    }

    @Test
    void testCottageInsuranceFieldDiff() {
        // given
        CottageInsurance cottageInsuranceFull = createCottageInsuranceWithAllFields();
        CottageInsurance cottageInsuranceEmpty = new CottageInsurance();

        // when
        Set<String> fieldDiff = insuranceService.getFieldDiffFromTwoInsurancesOfSameType(cottageInsuranceFull, cottageInsuranceEmpty);

        // then
        assertThat(fieldDiff.size()).isEqualTo(10);
    }

    @Test
    void testCarInsuranceFieldDiff() {
        // given
        CarInsurance carInsuranceFull = createCarInsuranceWithAllFields();
        CarInsurance carInsuranceEmpty = new CarInsurance();

        // when
        Set<String> fieldDiff = insuranceService.getFieldDiffFromTwoInsurancesOfSameType(carInsuranceFull, carInsuranceEmpty);

        // then
        assertThat(fieldDiff.size()).isEqualTo(11);
    }

    @Test
    void shouldThrowExceptionIfInsurancesAreOfDifferentType() {
        // given
        CarInsurance carInsurance = new CarInsurance();
        CondoInsurance condoInsurance = new CondoInsurance();

        // then expect exception
        assertThrows(RuntimeException.class, () -> insuranceService.getFieldDiffFromTwoInsurancesOfSameType(carInsurance, condoInsurance));
    }

    @Test
    void testFindExistingCottageInsuranceAndGetFieldDiffOnEmptyCondoInsurance() {
        // given
        CondoInsurance condoInsurance = new CondoInsurance();

        // when
        Set<String> fieldDiff = insuranceService.getFieldDiffWithInsuranceInPersistenceStorage("cottage", condoInsurance);

        // then
        assertThat(fieldDiff.size()).isEqualTo(10);
    }

    @Test
    void testFindExistingCarInsuranceAndGetFieldDiffOnEmptyCondoInsurance() {
        // given
        CondoInsurance condoInsurance = new CondoInsurance();
        condoInsurance.setInsuredAmount(123);

        // when
        Set<String> fieldDiff = insuranceService.getFieldDiffWithInsuranceInPersistenceStorage("car", condoInsurance);

        // then
        assertThat(fieldDiff.size()).isEqualTo(11);
        assertThat(fieldDiff.contains("insuranceAmount")).isFalse();
    }

    @Test
    void testFindExistingCondoCarInsuranceAndGetFieldDiffOnEmptyCondoInsurance() {
        // given
        CondoInsurance condoInsurance = new CondoInsurance();
        condoInsurance.setInsuranceName("someName");

        // when
        Set<String> fieldDiff = insuranceService.getFieldDiffWithInsuranceInPersistenceStorage("condo", condoInsurance);

        // then
        assertThat(fieldDiff.size()).isEqualTo(10);
        assertThat(fieldDiff.contains("insuranceName")).isFalse();
    }

    @Test
    void testFindExistingMopedInsuranceAndGetFieldDiffOnEmptyCondoInsurance() {
        // given
        CondoInsurance condoInsurance = new CondoInsurance();

        // when
        Set<String> fieldDiff = insuranceService.getFieldDiffWithInsuranceInPersistenceStorage("moped", condoInsurance);

        // then
        assertThat(fieldDiff.size()).isEqualTo(9);
    }

    private MopedInsurance createMopedInsuranceWithAllFields() {
        MopedInsurance mopedInsurance = new MopedInsurance();

        mopedInsurance.setBrand("Push dakota");
        mopedInsurance.setModel("some model");
        mopedInsurance.setRenewalDate("some date");

        return (MopedInsurance) populateBaseInsuranceWithAllFields(mopedInsurance, "moped");
    }

    private CottageInsurance createCottageInsuranceWithAllFields() {
        CottageInsurance cottageInsurance = new CottageInsurance();

        cottageInsurance.setLivingArea(1234);
        cottageInsurance.setPostalCode("1234");
        cottageInsurance.setNumberOfResidents(123);
        cottageInsurance.setInsuredAmount(123);

        return (CottageInsurance) populateBaseInsuranceWithAllFields(cottageInsurance, "cottage");
    }

    private CarInsurance createCarInsuranceWithAllFields() {
        CarInsurance carInsurance = new CarInsurance();

        carInsurance.setRegistrationNo("ABC123");
        carInsurance.setBrand("Volvo");
        carInsurance.setModel("Some model");
        carInsurance.setRenewalDate("Some date");
        carInsurance.setCoverage("1234");

        return (CarInsurance) populateBaseInsuranceWithAllFields(carInsurance, "car");
    }

    private CondoInsurance createCondoInsuranceWithAllFields() {
        CondoInsurance condoInsurance = new CondoInsurance();

        condoInsurance.setCondo(true);
        condoInsurance.setLivingArea(5);
        condoInsurance.setLivingArea(5);
        condoInsurance.setPostalCode("123456");
        condoInsurance.setNumberOfResidents(13);
        condoInsurance.setInsuredAmount(12345);

        return (CondoInsurance) populateBaseInsuranceWithAllFields(condoInsurance, "condo");
    }

    private BaseInsurance populateBaseInsuranceWithAllFields(BaseInsurance baseInsurance, String insuranceNumber) {
        baseInsurance.setInsuranceType("baseInsurance");
        baseInsurance.setInsuranceSubType("subBaseInsurance");
        baseInsurance.setInsuranceName("some name");
        baseInsurance.setInsuranceNumber(insuranceNumber);
        baseInsurance.setPaymentMethod("Bitcoin");
        baseInsurance.setPremiumAmountYear(1234);
        return baseInsurance;

    }

}