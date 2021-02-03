package org.furmanski.service.impl;

import lombok.RequiredArgsConstructor;
import org.furmanski.config.ConfigProperties;
import org.furmanski.model.Solution;
import org.furmanski.service.SolutionService;
import org.furmanski.service.SourceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BinaryOperator;

@Service
@RequiredArgsConstructor
public class IntegerSolutionService implements SolutionService<Integer> {

    private final SourceService<Integer> javaRandomSourceService;
    private final SourceService<Integer> randomOrgSourceService;
    private final ConfigProperties configProperties;
    private final BinaryOperator<Integer> addIntegers = Integer::sum;

    @Override
    public Solution<Integer> calculate() {
        Integer count = configProperties.getNumberOfElements();
        List<Integer> randomOrgValues = randomOrgSourceService.getValues(count);
        List<Integer> javaRandomValues = javaRandomSourceService.getValues(count);
        Integer result = randomOrgValues.stream().reduce(0, addIntegers);
        result = javaRandomValues.stream().reduce(result, addIntegers);
        randomOrgValues.addAll(javaRandomValues);
        return new Solution<>(result, randomOrgValues);
    }
}
