package ro.utcluj.sd.server.mappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.beans.Observable;
import ro.utcluj.sd.server.api.Article;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Articles {



    private ArrayList<Article> articles = new ArrayList<>();
    public Type articleListType = new TypeToken<ArrayList<Article>>(){}.getType();


    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public void saveArticleLibrary() throws IOException {

        Gson gson = new GsonBuilder().create();

        FileWriter userWriter = null;

        //System.out.println(getClass().getResource("/users.json").toString().substring(6));

        File file = new File(getClass().getResource("/articles.json").toString().substring(6));
        try {
            //temp.createNewFile();
            userWriter = new FileWriter(file, false);//if file exists append to file. Works fine.
        } catch (IOException e) {
            e.printStackTrace();
        }

        gson.toJson(articles, userWriter);
        try {
            userWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void getArticleLibrary() {
        Gson gson = new Gson();
        File file = new File(getClass().getResource("/articles.json").toString().substring(6));
        try {
            articles = gson.fromJson(new FileReader(file),articleListType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Articles() {
        this.getArticleLibrary();
    }

//    public static void main(String[] args) throws IOException, FileNotFoundException, URISyntaxException {
//        Articles x = new Articles();
//
//        BufferedImage img = null;
//
//        System.out.println(x.getClass().getResource("/image.jpg").toURI().toString());
//
//        try {
//            img = ImageIO.read(new File(x.getClass().getResource("/image.jpg").toURI().toString().substring(6)));
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(img, "jpg", baos);
//        baos.flush();
//        BASE64Encoder base = new BASE64Encoder();
//        String encodedImage = base.encode(baos.toByteArray());
//        baos.close();
//        Article l = new Article("Pantis Vlad", "First Image test");
//        System.out.println(encodedImage);
//        l.setImage(encodedImage);
//
//        x.articles.add(new Article("Pantis Vlad","Doing Aviation"));
//        x.articles.add(new Article("Pantis Vlad","Doing UTCLUJ"));
//        x.articles.add(new Article("Pantis Vlad","Doing SD"));
//        x.articles.add(l);
//
//        x.saveArticleLibrary();
//
//        x.getArticleLibrary();
//        System.out.println(x.articles);
//    }

}
