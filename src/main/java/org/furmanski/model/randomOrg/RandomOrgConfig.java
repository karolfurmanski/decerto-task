package org.furmanski.model.randomOrg;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RandomOrgConfig implements Serializable {
    private final String jsonrpc;
    private final String method;
    private final RandomOrgConfigParams params;
    private final Integer id;
}
