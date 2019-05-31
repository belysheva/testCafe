package ru.cafe.model;

public class Cafe extends AbstractBaseEntity {

    private String name;

    public Cafe(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}
