package com.insurely.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurely.repository.InsuranceRepositoryImpl;
import com.insurely.repository.utils.BaseInsuranceFactory;
import com.insurely.repository.utils.InsuranceJsonFileReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InsuranceRepositoryConfiguration {

    @Bean
    public BaseInsuranceFactory baseInsuranceFactory(ObjectMapper objectMapper) {
        return new BaseInsuranceFactory(objectMapper);
    }

    @Bean
    public InsuranceRepositoryImpl insuranceRepository(BaseInsuranceFactory baseInsuranceFactory) {
        List<String> carInsuranceAsStrings = InsuranceJsonFileReader.readInsurancesFromResources("classpath:insurances/*");
        return new InsuranceRepositoryImpl(baseInsuranceFactory.createPOJOsFromJson(carInsuranceAsStrings));
    }
}
