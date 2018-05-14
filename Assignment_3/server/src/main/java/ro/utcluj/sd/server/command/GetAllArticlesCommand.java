package ro.utcluj.sd.server.command;

import ro.utcluj.sd.server.api.Article;
import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.mappers.Articles;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GetAllArticlesCommand implements CommandIF {
    @Override
    public Command execute(Command command) {
        Articles articles = new Articles();
        articles.getArticleLibrary();
        Command toReturn = new Command("allarticles");
        ArrayList<String> al = new ArrayList<>();
        String[] strings =null;
        for(Article x : articles.getArticles()){
            al.add(x.getTitle());
        }
        if(!al.isEmpty()){
            strings = al.toArray(new String[al.size()]);
            toReturn.setStringField(strings);
            return toReturn;
        }
        toReturn.setCommand("error");
        return toReturn;

    }
}
