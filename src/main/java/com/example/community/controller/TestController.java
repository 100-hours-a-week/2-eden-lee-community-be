package com.example.community.controller;

import com.example.community.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestRepository testRepository;

    @GetMapping("")
    public List<Map<String, Object>> getAllUsers() {
        return testRepository.findAllUsers();
    }

}
