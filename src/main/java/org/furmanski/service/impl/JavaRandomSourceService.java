package org.furmanski.service.impl;

import lombok.RequiredArgsConstructor;
import org.furmanski.config.JavaRandomConfigProperties;
import org.furmanski.service.SourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class JavaRandomSourceService implements SourceService<Integer> {

    private final JavaRandomConfigProperties randomOrgConfigProperties;

    @Override
    public List<Integer> getValues(int count) {
        Random random = new Random();
        Integer max = randomOrgConfigProperties.getMax();
        List<Integer> values = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            values.add(random.nextInt(max));
        }
        return values;
    }
}
