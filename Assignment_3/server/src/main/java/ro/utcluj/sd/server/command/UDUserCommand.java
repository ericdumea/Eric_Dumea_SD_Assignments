package ro.utcluj.sd.server.command;

import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.api.User;
import ro.utcluj.sd.server.mappers.Users;

import java.util.ArrayList;

public class UDUserCommand implements CommandIF{
    @Override
    public Command execute(Command command) {

        if(command.getCommand().equals("updateUser")){
            Users users = new Users();
            ArrayList<User> aux = users.getUsers();
            for(User x : aux){
                if(x.getUsername().equals(command.getUser().getUsername())){
                    x.setPassword(command.getUser().getPassword());
                    x.setName(command.getUser().getName());
                    return new Command("ok");
                }
            }
            return new Command("UserNF");
        }
        else{
            Users users = new Users();
            ArrayList<User> aux = users.getUsers();
            User y = null;
            for(User x : aux){
                if(x.getUsername().equals(command.getUser().getUsername())){
                    y =x;
                }

            }

            if(y!=null){
                aux.remove(y);
                users.setUsers(aux);
                users.saveUserLibrary();
                return new Command("ok");
            }

            return new Command("UserNF");
        }
    }
}
