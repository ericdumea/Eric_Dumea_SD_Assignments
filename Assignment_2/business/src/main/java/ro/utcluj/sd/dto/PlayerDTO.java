package ro.utcluj.sd.dto;

import ro.utcluj.sd.model.Player;

public class PlayerDTO {

    private String name;
    private String pass;
    private String email;
    private int account;
    private int id;

    public PlayerDTO(Player p){
        this.name = p.getName();
        this.email = p.getEmail();
        this.pass = p.getPassword();
        this.id = p.getId();
        this.account = p.getAccount();
    }

    public PlayerDTO() {
    }

    public PlayerDTO(String name, String pass, String email, int account, int id) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.account = account;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
