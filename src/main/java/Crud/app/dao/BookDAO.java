package Crud.app.dao;

import Crud.app.models.Book;
import Crud.app.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book(name, author, year) values(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE book set name=?, author=?, year=? WHERE id=?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE from book where id=?", id);
    }

    public void loanBook(Person person, int bookId){
        jdbcTemplate.update("update book set loaner=? where id=?", person.getId(), bookId);
    }

    public void releaseBook(int bookId){
        jdbcTemplate.update("update book set loaner=? where id=?", null, bookId);
    }
}
