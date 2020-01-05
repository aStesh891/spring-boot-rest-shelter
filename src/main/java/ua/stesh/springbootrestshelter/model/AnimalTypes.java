package ua.stesh.springbootrestshelter.model;

public enum AnimalTypes {
    CAT("CAT"),
    PARROT("PARROT"),
    DOG("DOG"),
    SNAKE("SNAKE"),
    HAMSTER("HAMSTER"),
    MOUSE("MOUSE"),
    FISH("FISH"),
    MONKEY("MONKEY"),
    PIG("PIG"),
    HORSE("HORSE");

    private String type;

    AnimalTypes(String type){this.type = type;}

    public String getType() {
        return type;
    }
}
