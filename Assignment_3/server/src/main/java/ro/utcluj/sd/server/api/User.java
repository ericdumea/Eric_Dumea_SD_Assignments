package ro.utcluj.sd.server.api;

public class User {

    private String name;
    private String username;

    public User() {
    }

    private String password;

    public User(String name, String username, String password, boolean admin) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    private boolean admin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}
