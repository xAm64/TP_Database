package fr.fms.dao;

import java.util.ArrayList;

import fr.fms.entities.Article;
import fr.fms.entities.User;

public class TestDao {

	public static void main(String[] args) {
		//ArticleDao articleDao = new ArticleDao();
		// create
		/*
		Article article = new Article("enceinte bluetooth", "JBL", 199.90);
		articleDao.create(article);
		*/
		//OK
		
		//read
		/*
		Article article = articleDao.read(19);
		System.out.println("Description: "+article.getDescription()+". Marque :"+article.getBrand()+". Prix unitaire: "+article.getStringPrice()+".");
		*/
		//OK
		
		//update
		/*
		Article article = new Article("Ordinateur Apple x30000", "Apple", 32999.90);
		articleDao.update(article, 18);
		*/
		//Ok
		
		//delete
		/*
		articleDao.delete(18);
		*/
		//OK
		
		//readAll
		/*
		ArrayList<Article> articles = articleDao.readAll();
		for (Article a: articles) {
			Article aTemp = new Article(a.getDescription(), a.getBrand(), a.getUnitPrice());
			String description = aTemp.getDescription();
			String brand = aTemp.getBrand();
			double prix = aTemp.getUnitPrice();
			System.out.println("Description: "+description+". Marque: "+brand+". Prix unitaire: "+prix+".");
		}
		*/
		//OK
		
		// ############# USER #############
		UserDao userDao = new UserDao();
		
		//create
		/*
		User isnaa = new User("Isnaa", "tapé!");
		userDao.create(isnaa);
		*/
		//OK
		
		//read
		/*
		User user = userDao.read(4);
		System.out.println("identifian: "+user.getLogin()+". Pass :"+user.getPass()+".");
		*/
		//OK
		
		//update
		/*
		User user = new User("Exa", "exactement0");
		userDao.update(user, 3);
		*/
		//OK
		//delete
		/*
		userDao.delete(3);
		*/
		//OK
		
		//readAll
		ArrayList<User> users = userDao.readAll();
		for (User u: users) {
			User uTemp = new User(u.getLogin(), u.getPass());
			String loggin = uTemp.getLogin();
			String pass = uTemp.getPass();
			System.out.println("Login: "+loggin+". Mot de passe: "+pass+".");
		}
		
	}

}
