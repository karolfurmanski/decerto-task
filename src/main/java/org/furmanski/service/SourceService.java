package org.furmanski.service;

import java.util.List;

public interface SourceService<T> {

    List<T> getValues(int count);
}
