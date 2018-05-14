package ro.utcluj.sd.server.command;

import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.api.User;
import ro.utcluj.sd.server.mappers.Users;

import java.util.ArrayList;

public class LoginCommand implements CommandIF {
    @Override
    public Command execute(Command command) {

        User user = command.getUser();
        Users userLibrary = new Users();
        userLibrary.getUserLibrary();
        ArrayList<User> users = userLibrary.getUsers();
        Command toReturn = null;
        for (User x :users){
            if(x.getUsername().equals(user.getUsername())){
                if(x.getPassword().equals(user.getPassword())){
                    if(x.getUsername().equals("admin")){
                        return new Command("admin", x);
                    } else {
                        toReturn = new Command("loginok", x);
                        return toReturn;
                    }
                }
            }
        }

        if(toReturn == null){
            toReturn = new Command("loginerror",user);
        }
        return toReturn;
    }
}
