package org.furmanski.controller;

import lombok.RequiredArgsConstructor;
import org.furmanski.model.Solution;
import org.furmanski.service.SolutionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("solutions")
@RequiredArgsConstructor
public class SolutionController {

    private final SolutionService<Integer> integerSolutionService;

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<Solution<Integer>> getSolution() {
        return new ResponseEntity<>(integerSolutionService.calculate(), HttpStatus.OK);
    }
}
