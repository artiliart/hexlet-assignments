package exercise.controller;

import exercise.daytime.Daytime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@RequiredArgsConstructor
public class WelcomeController {

    @Qualifier("getDaytime")
    private final Daytime daytime;

    @GetMapping("/welcome")
    private String kal() {
        return "It is" + daytime.getName() + " now! Welcome to Spring!";
    }
    
}
