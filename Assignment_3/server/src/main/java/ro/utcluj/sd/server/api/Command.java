package ro.utcluj.sd.server.api;

import java.util.ArrayList;
import java.util.Arrays;

public class Command {

    private String command;
    private User user;
    private Article article;

    private String[] stringField = null;

    public Command(String command, User object) {
        this.command = command;
        this.user = object;
        article = null;
    }

    public Command(String command, Article article) {
        this.command = command;
        this.article = article;
        user = null;
    }

    public Command(String command) {
        this.command = command;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Command() {
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }


    public String[] getStringField() {
        return stringField;
    }

    public void setStringField(String[] stringField) {
        this.stringField = stringField;
    }

    @Override
    public String toString() {
        return "Command{" +
                "command='" + command + '\'' +
                ", user=" + user +
                ", article=" + article +
                ", stringField=" + Arrays.toString(stringField) +
                '}';
    }
}
