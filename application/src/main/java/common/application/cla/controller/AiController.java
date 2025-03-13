package common.application.cla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/class") 
public record AiController() {

    @GetMapping("/ai")
    public String ai() {
        String url = "/cla/ai/ai";
        return url;
    }

}
