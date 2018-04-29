package ro.utcluj.sd;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ro.utcluj.sd.dal.impl.DaoFactory;
import ro.utcluj.sd.dal.impl.PlayerDao;
import ro.utcluj.sd.model.Player;

public class AccountView {
    @FXML
    public Label label_money;
    @FXML
    public Button btn_add;
    @FXML
    public Button btn_withdraw;
    @FXML
    public TextField tf_money;

    private String mail;
    private Player p;
    private AdminOps a;
    PlayerDao pp;


    public void add() {
        int x = Integer.parseInt(tf_money.getText());

        if (a.modifyCash(x, p) == true) {
            p = pp.findByEmail(this.getMail());
            label_money.setText("Balance: " + p.getAccount().toString() + " $");
        }

        tf_money.setText("");
    }

    public void withdraw() {
        int x = Integer.parseInt(tf_money.getText());

        if(a.modifyCash((0 - x), p)==true){
            p = pp.findByEmail(this.getMail());
            label_money.setText("Balance: " + p.getAccount().toString() + " $");
        }
        tf_money.setText("");
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void init() {
        pp = DaoFactory.getInstance(App.DAO_TYPE).getPlayerDao();
        p = pp.findByEmail(this.getMail());
        a = new AdminOps(App.DAO_TYPE);

        label_money.setText("Balance: " + p.getAccount().toString() + " $");
    }
}
