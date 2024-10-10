package trustcore.tzw.jenkin_proj.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }
}
