package org.furmanski.service.impl;

import org.furmanski.config.JavaRandomConfigProperties;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class JavaRandomSourceServiceTest {

    @InjectMocks
    private JavaRandomSourceService javaRandomSourceService;

    @Mock
    private JavaRandomConfigProperties javaRandomConfigProperties;

    @ParameterizedTest
    @MethodSource("argumentsForGetValuesTest")
    void shouldReturnRandomValues(int count, int expectedNumberOfElements) {
        Mockito.when(javaRandomConfigProperties.getMax()).thenReturn(10);
        List<Integer> values = javaRandomSourceService.getValues(count);
        assertEquals(expectedNumberOfElements, values.size());
    }

    private static Stream<Arguments> argumentsForGetValuesTest() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(5, 5),
                Arguments.of(22, 22),
                Arguments.of(10, 10)
        );
    }
}
