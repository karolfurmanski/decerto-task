package org.furmanski.model.randomOrg;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.furmanski.model.deserialization.InstantDeserializer;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class RandomOrgResponseData<T> {
    private List<T> data;
    @JsonDeserialize(using = InstantDeserializer.class)
    private Instant completionTime;
}
