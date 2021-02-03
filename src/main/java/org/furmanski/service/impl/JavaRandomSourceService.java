package org.furmanski.service.impl;

import org.furmanski.service.SourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class JavaRandomSourceService implements SourceService<Integer> {

    @Override
    public List<Integer> getValues(int count) {
        Random random = new Random();
        List<Integer> values = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            values.add(random.nextInt(count));
        }
        return values;
    }
}
