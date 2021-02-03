package org.furmanski.model.randomOrg;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RandomOrgConfigParams implements Serializable {
    private final String apiKey;
    private final Integer n;
    private final Integer min;
    private final Integer max;
}
