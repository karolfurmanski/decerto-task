package org.furmanski.model.randomOrg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RandomOrgResponseResult<T> {
    private RandomOrgResponseData<T> random;
    private Integer bitsUsed;
    private Integer bitsLeft;
    private Integer requestsLeft;
    private Integer advisoryDelay;
}
