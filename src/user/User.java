package user;

import java.util.UUID;

public class User {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private String username;
    private final Role role;
    private String password;
    private String email;

    public User(Role role) {
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
