package Crud.app.services;

import Crud.app.models.Book;
import Crud.app.models.Person;
import Crud.app.repositories.BooksRepository;
import Crud.app.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@Transactional(readOnly = true)
public class BooksService {
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Book> index(Integer page, Integer books_per_page, String sort_by_year){
        if (page != null && books_per_page != null){
            if(sort_by_year.equals("true")){
                return booksRepository.findAll(PageRequest.of(page,books_per_page, Sort.by("year"))).getContent();
            }
            return booksRepository.findAll(PageRequest.of(page, books_per_page)).getContent();
        }
        else if(sort_by_year.equals("true")){
            return booksRepository.findAll(Sort.by("year"));
        }
        return booksRepository.findAll();
    }

    public Book index(String bookSearch){
        if(!bookSearch.equals("")){
            return booksRepository.findBookByNameStartingWith(bookSearch);
        }
        return null;
    }

    public Book show(int id){
        return booksRepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(Book person){
        booksRepository.save(person);
    }
    @Transactional
    public void update(int id, Book updatedPerson){
        updatedPerson.setId(id);
        booksRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    public Optional<Person> getLoaner(int id){
        return peopleRepository.findById(id);
    }
    @Transactional
    public void loanBook(Person person, int bookId) {
        Book book =booksRepository.findById(bookId).orElse(null);
        if(book != null){
            book.setLoaner(person);
            book.setTaken_at(new Date());
            booksRepository.save(book);
        }
    }
    @Transactional
    public void releaseBook(int id) {
        Book book = booksRepository.findById(id).orElse(null);
        if(book != null){
            book.setLoaner(null);
            booksRepository.save(book);
        }
    }
}
