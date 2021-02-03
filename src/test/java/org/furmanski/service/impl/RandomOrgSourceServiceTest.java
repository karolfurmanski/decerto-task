package org.furmanski.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.furmanski.config.RandomOrgConfigProperties;
import org.furmanski.model.randomOrg.RandomOrgResponse;
import org.furmanski.model.randomOrg.RandomOrgResponseData;
import org.furmanski.model.randomOrg.RandomOrgResponseResult;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RandomOrgSourceServiceTest {

    private static MockWebServer mockWebServer;

    @InjectMocks
    private RandomOrgSourceService randomOrgSourceService;

    @Mock
    private RandomOrgConfigProperties randomOrgConfigProperties;

    @BeforeAll
    public static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(9090);
    }

    @AfterAll
    public static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void shouldReturnRandomValues() throws JsonProcessingException {
        RandomOrgResponse<Integer> randomOrgResponse = buildRandomOrgResponse();
        List<Integer> expectedResult = randomOrgResponse.getResult().getRandom().getData();

        Mockito.when(randomOrgConfigProperties.getUrl()).thenReturn("http://localhost:" + mockWebServer.getPort() + "/");

        mockWebServer.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(randomOrgResponse))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));

        List<Integer> result = randomOrgSourceService.getValues(5);
        assertEquals(expectedResult, result);
    }

    private RandomOrgResponse<Integer> buildRandomOrgResponse() {
        List<Integer> data = new ArrayList<>(Arrays.asList(1, 5, 7, 10, 4));
        RandomOrgResponseData<Integer> randomOrgResponseData = new RandomOrgResponseData<>();
        randomOrgResponseData.setData(data);
        RandomOrgResponseResult<Integer> randomOrgResponseResult = new RandomOrgResponseResult<>();
        randomOrgResponseResult.setRandom(randomOrgResponseData);
        RandomOrgResponse<Integer> randomOrgResponse = new RandomOrgResponse<>();
        randomOrgResponse.setResult(randomOrgResponseResult);
        return randomOrgResponse;
    }

}
