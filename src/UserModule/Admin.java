package UserModule;

import BookSearch.BookCategory;
import BookSearch.BookCategoryManager;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Admin extends User{
    public Admin() {
        super(Role.ADMIN);
    }

}
