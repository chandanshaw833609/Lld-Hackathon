package login;

<<<<<<< HEAD
import user.User;
import user.BuyerManager;
import user.UserManager;
import user.UserMetaData;
=======
import UserModule.User;
import UserModule.UserManager;
import UserModule.UserMetaData;
>>>>>>> 6d6c03d9c2281f58743b4efa43a229a751c1f9cc

public class PasswordLoginStrategy implements LoginStrategy {

    UserManager userManager;

    public PasswordLoginStrategy()
    {
        this.userManager = UserManager.getInstance();
    }

    @Override
    public User processLogin(UserMetaData userMetaData) {
        User user = userManager.getUserByName(userMetaData.getName());
        return user != null && user.getPassword().equals(userMetaData.getPassword()) ? user : null;
    }
}
