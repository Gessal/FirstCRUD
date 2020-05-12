package model;

public class User {
    private Long id;
    private String name;
    private String surname;
    private byte age;

    public User (String name, String surname, byte age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public User (Long id, String name, String surname, byte age) {
        this(name, surname, age);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public byte getAge() {
        return age;
    }
}
