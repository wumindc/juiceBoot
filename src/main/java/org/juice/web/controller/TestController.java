package org.juice.web.controller;

import org.juice.annotions.JuiceController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@JuiceController
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/a.do")
    public String test() {
        return null;
    }

    @GetMapping("/b.do")
    public Map<String, Object> testMap() {
        return Map.of(
            "message", "Hello, Juice!",
            "author", "Juice",
            "version", "1.0.0"
        );
    }

    @GetMapping("/c.do")
    public void testVoid() {
        log.info("Hello, Juice!");
    }
}
