package org.furmanski.service.impl;

import lombok.RequiredArgsConstructor;
import org.furmanski.config.RandomOrgConfigProperties;
import org.furmanski.model.randomOrg.RandomOrgConfig;
import org.furmanski.model.randomOrg.RandomOrgConfigParams;
import org.furmanski.model.randomOrg.RandomOrgResponse;
import org.furmanski.service.SourceService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RandomOrgSourceService implements SourceService<Integer> {

    private final RandomOrgConfigProperties randomOrgConfigProperties;
    private final WebClient webClient = WebClient.create();

    @Override
    public List<Integer> getValues(int count) {
        RandomOrgConfigParams randomOrgConfigParams = buildRandomOrgConfigParams(count);
        RandomOrgConfig randomOrgConfig = buildRandomOrgConfig(randomOrgConfigParams);
        RandomOrgResponse<Integer> randomOrgResponse = getRandomOrgData(randomOrgConfig);
        return randomOrgResponse.getResult().getRandom().getData();
    }

    private RandomOrgConfigParams buildRandomOrgConfigParams(int count) {
        return RandomOrgConfigParams.builder()
                .apiKey(randomOrgConfigProperties.getKey())
                .n(count)
                .min(randomOrgConfigProperties.getMin())
                .max(randomOrgConfigProperties.getMax())
                .build();
    }

    private RandomOrgConfig buildRandomOrgConfig(RandomOrgConfigParams randomOrgConfigParams) {
        return RandomOrgConfig.builder()
                .jsonrpc(randomOrgConfigProperties.getJsonrpc())
                .method(randomOrgConfigProperties.getMethod())
                .params(randomOrgConfigParams)
                .id(randomOrgConfigProperties.getId())
                .build();
    }

    private RandomOrgResponse<Integer> getRandomOrgData(RandomOrgConfig randomOrgConfig) {
        Mono<RandomOrgResponse<Integer>> monoResponse = webClient.post()
                .uri(randomOrgConfigProperties.getUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(randomOrgConfig))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<RandomOrgResponse<Integer>>(){});
        return monoResponse.block();
    }
}
