package ro.utcluj.sd.server.command;

import ro.utcluj.sd.server.api.Command;

public class CommandFactory {

    public static CommandIF getCommand(Command command){

        switch ( command.getCommand()){
            case "login" : return new LoginCommand();
            case "getArticlesList" : return new GetAllArticlesCommand();
            case "getArticle" : return new GetArticleCommand();
            case "addArticle" : return new AddArticleCommand();
            case "updateArticle" : return new UpdateArticleCommand();
            case "deleteArticle" : return new DeleteArticleCommand();
            case "addUser" : return new AddUserCommand();
            case "deleteUser" : return new UDUserCommand();
            case "updateUser" : return new UDUserCommand();
            default:
                System.out.println("Nu e ok mai  " + command.getCommand());
        }

        return null;
    }

}
