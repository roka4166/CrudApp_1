package Crud.app.models;

import javax.validation.Valid;
import javax.validation.constraints.*;


public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String nameLastname;
    @NotNull (message = "this field cannot be empty")
    @Min(value = 1900, message = "Smallest value is 1900")
    @Digits(integer = 4, message = "Year of birth has to be 4 digits long", fraction = 0)
    private int yearOfBirth;


    public Person() {

    }

    public Person(int id, String nameLastname, int yearOfBirth) {
        this.id = id;
        this.yearOfBirth = yearOfBirth;
        this.nameLastname = nameLastname;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameLastname() {
        return nameLastname;
    }

    public void setNameLastname(String name) {
        this.nameLastname = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
