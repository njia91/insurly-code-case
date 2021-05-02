package com.insurely.repository.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class InsuranceJsonFileReader {

    private InsuranceJsonFileReader() {}

    public static List<String> readInsurancesFromResources(String path) {
        return Arrays.stream(getResources(path))
                .map(InsuranceJsonFileReader::asString)
                .collect(Collectors.toList());
    }

    private static Resource[] getResources(String path) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            return resolver.getResources(path);
        } catch (IOException e) {
            throw new RuntimeException("unable to read resource with prefix; " + path);
        }
    }


    private static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new RuntimeException("unable to convert resource to string", e);
        }
    }

}
