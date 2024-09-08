package book;

public enum BookCategory {
    LITERATURE_FICTION("Literature & Fiction"),
    CRIME_THRILLER_MYSTERY("Crime, Thriller & Mystery"),
    ACTION_ADVENTURE("Action & Adventure"),
    SCIENCE_FICTION("Science Fiction"),
    BIOGRAPHY("Biography"),
    FANTASY("Fantasy");

    private final String name;

    BookCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
