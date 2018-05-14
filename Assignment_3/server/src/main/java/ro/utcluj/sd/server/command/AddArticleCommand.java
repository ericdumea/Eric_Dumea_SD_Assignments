package ro.utcluj.sd.server.command;

import ro.utcluj.sd.server.api.Article;
import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.mappers.Articles;

import java.io.IOException;
import java.util.ArrayList;

public class AddArticleCommand implements CommandIF{
    @Override
    public Command execute(Command command) {

        Articles articles = new Articles();

        ArrayList<Article> art = articles.getArticles();

        art.add(command.getArticle());
        articles.setArticles(art);

        try {
            articles.saveArticleLibrary();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Command("ok");

    }
}
