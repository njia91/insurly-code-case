package com.insurely.repository.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class InsuranceJsonFileReaderTest {

    @Test
    void testConvertResourceAsString() {
        List<String> insuranceJson = InsuranceJsonFileReader.readInsurancesFromResources("classpath:insurances/*.json");

        assertThat(insuranceJson.size()).isEqualTo(52 * 4); // Same as resource dir
    }
}