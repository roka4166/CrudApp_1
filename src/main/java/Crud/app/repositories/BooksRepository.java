package Crud.app.repositories;

import Crud.app.models.Book;
import Crud.app.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByLoanerId(int loanerId);

    Book findBookByNameStartingWith(String startingWith);
}
