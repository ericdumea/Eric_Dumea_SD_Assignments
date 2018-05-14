package ro.utcluj.sd.server.command;

import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.api.User;
import ro.utcluj.sd.server.mappers.Users;

import java.util.ArrayList;

public class AddUserCommand implements CommandIF {
    @Override
    public Command execute(Command command) {

        Users users = new Users();
        ArrayList<User> aux = users.getUsers();

        aux.add(command.getUser());

        users.setUsers(aux);
        users.saveUserLibrary();

        return new Command("ok");
    }
}
