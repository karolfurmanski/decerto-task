package org.furmanski.service;

import org.furmanski.model.Solution;

public interface SolutionService<T> {

    Solution<T> calculate();
}
