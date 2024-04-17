package id.ac.ui.cs.pustakaone.bookshop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;;

@RestController
@RequestMapping("/")
public class BookController {
    @GetMapping("books")
    public String getBooks() {
        return "Book List";
    }

    @GetMapping("book/{id}")
    public String getBookDetail(@PathVariable("id") int id, Model model) {
        return "Book 1";
    }
}
