package ro.utcluj.sd.server.command;

import ro.utcluj.sd.server.api.Article;
import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.mappers.Articles;

public class GetArticleCommand implements CommandIF {
    @Override
    public Command execute(Command command) {

        Articles articles = new Articles();

        for(Article x : articles.getArticles()) {
            if (x.getTitle().equals(command.getArticle().getTitle())) {
                return new Command("articleResponse", x);
            }
        }
        return new Command("error");
    }
}
