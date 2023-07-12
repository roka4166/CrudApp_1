package Crud.app.repositories;

import Crud.app.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    Book findBookByNameStartingWith(String startingWith);
}
