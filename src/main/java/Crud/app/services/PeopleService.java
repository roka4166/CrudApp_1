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
public class PeopleService {

    private final PeopleRepository peopleRepository;

    private final BooksRepository booksRepository;
    @Autowired

    public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> index(){
        return peopleRepository.findAll();
    }

    public Person show(int id){
        return peopleRepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }
    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id){
        return booksRepository.findBooksByLoanerId(id);
    }

    public Optional<Person> findByFullName(String name){
        return peopleRepository.findByNameLastname(name);
    }
}
