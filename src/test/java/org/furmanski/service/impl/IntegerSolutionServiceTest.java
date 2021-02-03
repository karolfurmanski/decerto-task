package org.furmanski.service.impl;

import org.furmanski.config.ConfigProperties;
import org.furmanski.model.Solution;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class IntegerSolutionServiceTest {

    private static RandomOrgSourceService randomOrgSourceService;
    private static JavaRandomSourceService javaRandomSourceService;
    private static ConfigProperties configProperties;
    private static IntegerSolutionService integerSolutionService;

    @BeforeAll
    public static void setUp() {
        randomOrgSourceService = mock(RandomOrgSourceService.class);
        javaRandomSourceService = mock(JavaRandomSourceService.class);
        configProperties = mock(ConfigProperties.class);
        integerSolutionService = new IntegerSolutionService(javaRandomSourceService, randomOrgSourceService, configProperties);
    }

    @ParameterizedTest
    @MethodSource("argumentsForCalculateIntegersTest")
    void shouldCalculateIntegers(int count,
                                 List<Integer> randomOrgList,
                                 List<Integer> javaRandomList,
                                 int expectedResult) {
        randomOrgList = new ArrayList<>(randomOrgList);
        javaRandomList = new ArrayList<>(javaRandomList);
        Mockito.when(configProperties.getNumberOfElements()).thenReturn(count);
        Mockito.when(randomOrgSourceService.getValues(count)).thenReturn(randomOrgList);
        Mockito.when(javaRandomSourceService.getValues(count)).thenReturn(javaRandomList);
        List<Integer> expectedList = Stream.of(randomOrgList, javaRandomList)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        Solution<Integer> expectedSolution = new Solution<>(expectedResult, expectedList);
        Solution<Integer> solution = integerSolutionService.calculate();
        assertEquals(expectedSolution, solution);
    }

    private static Stream<Arguments> argumentsForCalculateIntegersTest() {
        return Stream.of(
                Arguments.of(5, Arrays.asList(1, 2, 5, 7, 10), Arrays.asList(2, 6, 4, 3, 20), 60),
                Arguments.of(2, Arrays.asList(4, 8), Arrays.asList(12, 33), 57),
                Arguments.of(3, Arrays.asList(11, 5, 3), Arrays.asList(7, 22, 2), 50),
                Arguments.of(1, Collections.singletonList(5), Collections.singletonList(1), 6)
        );
    }
}