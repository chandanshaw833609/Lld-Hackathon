package UserModule;

import java.util.UUID;

public class UserMetaData {
    String name;
    Role role;
    String password;
    String email;

    public UserMetaData(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public UserMetaData() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
