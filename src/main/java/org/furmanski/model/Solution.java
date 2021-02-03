package org.furmanski.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Solution<T> implements Serializable {
    private final T result;
    private final List<T> values;
}
