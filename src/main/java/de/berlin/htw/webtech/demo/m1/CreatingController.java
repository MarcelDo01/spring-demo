package de.berlin.htw.webtech.demo.m1;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatingController {


    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name",required = false, defaultValue = "World" ) String name) {
        return  "Hello " + name;
    }
}
