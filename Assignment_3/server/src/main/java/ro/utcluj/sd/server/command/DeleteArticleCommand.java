package ro.utcluj.sd.server.command;

import ro.utcluj.sd.server.api.Article;
import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.mappers.Articles;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteArticleCommand implements CommandIF {
    @Override
    public Command execute(Command command) {

        Articles articles = new Articles();

        ArrayList<Article> aux = articles.getArticles();

        Article y = null;

        for(Article x: aux){
            if(x.getTitle().equals(command.getArticle().getTitle())){
                y = x;
                break;
            }
        }
        aux.remove(y);
        articles.setArticles(aux);
        try {
            articles.saveArticleLibrary();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Command("ok");
    }
}
