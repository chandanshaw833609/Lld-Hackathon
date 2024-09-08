package seller;

import user.Role;
import user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seller extends User {
    public Seller() {
        super(Role.SELLER);
    }
}
