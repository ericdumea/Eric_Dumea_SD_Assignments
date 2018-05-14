package ro.utcluj.sd.server.command;

import ro.utcluj.sd.server.api.Article;
import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.mappers.Articles;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateArticleCommand implements CommandIF {
    @Override
    public Command execute(Command command) {
        Articles articles = new Articles();

        ArrayList<Article> art = articles.getArticles();

        for (Article x : art) {
            if(x.getTitle().equals(command.getArticle().getTitle())){
                x.setContent(command.getArticle().getContent());
                x.setAbstractt(command.getArticle().getAbstractt());
                x.setImage(command.getArticle().getImage());
                x.setRelated_titles(command.getArticle().getRelated_titles());
                break;
            }
        }

        articles.setArticles(art);

        try {
            articles.saveArticleLibrary();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Command("ok");
    }
}
