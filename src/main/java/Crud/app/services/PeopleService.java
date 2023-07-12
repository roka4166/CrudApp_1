package Crud.app.services;

import Crud.app.models.Book;
import Crud.app.models.Person;
import Crud.app.repositories.BooksRepository;
import Crud.app.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    @Autowired

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
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
        Optional<Person> person = peopleRepository.findById(id);

        if(person.isPresent()){
            Hibernate.initialize(person.get().getBooks());
            List<Book> books = person.get().getBooks();
            for(Book book : books){
                Date takenAt = book.getTaken_at();
                Date today = new Date();
                long difference_In_Time = today.getTime() - takenAt.getTime();
                if(difference_In_Time > 864000000){
                    book.setOverDue(true);
                }
            }
        }
        return Collections.emptyList();
    }

    public Optional<Person> findByFullName(String name){
        return peopleRepository.findByNameLastname(name);
    }
}
