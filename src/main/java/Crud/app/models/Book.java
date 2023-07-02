package Crud.app.models;

import javax.validation.constraints.*;

public class Book {
    private int id;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min=2, max = 50, message = "name should be between 2 and 50 characters")
    private String name;
    @NotEmpty(message = "author should exist")
    @Size(min=2, max=40, message = "Authors name should be between 2 and 40 characters")
    private String author;
    @NotNull (message = "this field cannot be empty")
    @Min(value = 1900, message = "Smallest value is 1900")
    @Digits(integer = 4, message = "Year of birth has to be 4 digits long", fraction = 0)
    private int year;

    private int loaner;

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

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }
}

