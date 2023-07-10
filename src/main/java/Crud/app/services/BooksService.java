package Crud.app.services;

import Crud.app.models.Book;
import Crud.app.models.Person;
import Crud.app.repositories.BooksRepository;
import Crud.app.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Book> index(){
        return booksRepository.findAll();
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
        Book book =(Book) booksRepository.findById(bookId).orElse(null);
        book.setLoaner(person);
        booksRepository.save(book);
    }
    @Transactional
    public void releaseBook(int id) {

    }
}
