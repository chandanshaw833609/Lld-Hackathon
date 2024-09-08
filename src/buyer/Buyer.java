package buyer;

import user.Role;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class Buyer extends User {
    public Buyer() {
        super(Role.BUYER);
    }
}
