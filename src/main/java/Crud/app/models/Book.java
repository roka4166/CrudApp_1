package Crud.app.models;

import javax.persistence.*;
import javax.validation.constraints.*;
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min=2, max = 50, message = "name should be between 2 and 50 characters")
    private String name;
    @Column(name = "author")
    @NotEmpty(message = "author should exist")
    @Size(min=2, max=40, message = "Authors name should be between 2 and 40 characters")
    private String author;
    @Column(name = "year")
    @NotNull (message = "this field cannot be empty")
    @Digits(integer = 4, message = "Year has to be 4 digits long", fraction = 0)
    private int year;
    @ManyToOne
    @JoinColumn(name = "loaner", referencedColumnName = "id")
    private Person loaner;

    public Person getLoaner() {
        return loaner;
    }

    public void setLoaner(Person loaner) {
        this.loaner = loaner;
    }



    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public Book(){
    }

    public Book(int id, String name, String author, int year, Person loaner) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.loaner = loaner;
    }
}

