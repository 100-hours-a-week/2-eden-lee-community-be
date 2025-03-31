package com.example.community.test;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Annotation");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Annotation");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("BeforeEach Annotation");
    }

    @AfterEach
    void afterEach() {
        System.out.println("AfterEach Annotation");
    }

    @Test
    void test1() {
        System.out.println("test1");
    }

    @Test
    @DisplayName("test2 !!")    // 테스트의 이름을 새로 작성
    void test2() {
        System.out.println("test2");
    }

    @Test
    @Disabled   // 실행하지 않는다는 의미
    void test3() {
        System.out.println("test3");
    }
}
