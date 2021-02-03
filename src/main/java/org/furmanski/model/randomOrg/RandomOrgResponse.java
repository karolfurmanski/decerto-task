package org.furmanski.model.randomOrg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RandomOrgResponse<T> {
    private String jsonrpc;
    private RandomOrgResponseResult<T> result;
    private Integer id;
}
