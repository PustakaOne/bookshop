package id.ac.ui.cs.pustakaone.bookshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HomeController {
    @GetMapping("/")
    public String getHello() {
        return "Hello bookshop!";
    }

}