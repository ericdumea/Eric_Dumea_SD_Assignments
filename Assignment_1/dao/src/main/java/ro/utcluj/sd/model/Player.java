package ro.utcluj.sd.model;


public class Player {
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean admin;


    public Player(){}

    public Player(String email, String password){
        this.email = email;
        this.password = password;

    }

    public Player(int id, String name,String email, String password, boolean admin){
        this.password=password;
        this.email=email;
        this.admin=admin;
        this.name=name;
        this.id = id;
    }

    public Player(String email, String pass, String name) {
        this.password=pass;
        this.email=email;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
