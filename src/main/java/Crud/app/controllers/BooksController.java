package Crud.app.controllers;
import Crud.app.models.Book;
import Crud.app.models.Person;
import Crud.app.services.BooksService;
import Crud.app.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;
    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model, @ModelAttribute("person") Person person, @RequestParam(required = false, value = "page") Integer page,
                        @RequestParam(required = false, value = "books_per_page") Integer books_per_page,
                        @RequestParam(required = false, defaultValue = "false", value = "sort_by_year") String sort_by_year){
        model.addAttribute("books", booksService.index(page, books_per_page, sort_by_year));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksService.show(id));
        model.addAttribute("people", peopleService.index());
        model.addAttribute("person1", new Person());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", booksService.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/give")
    public String give(@PathVariable("id") int id, @ModelAttribute Person person){
        booksService.loanBook(person, id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        booksService.releaseBook(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "", value = "bookSearch") String bookSearch, Model model){
        model.addAttribute("book", booksService.index(bookSearch));
        return "books/search";
    }




}
