package fr.fms.dao;

import java.beans.Statement;
import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.crypto.AEADBadTagException;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import fr.fms.entities.Article;

public class ArticleDao implements Dao<Article>{
	//test OK
	@Override
	public void create(Article obj) {
		String str = "INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES (?,?,?);";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getBrand());
			ps.setDouble(3, obj.getUnitPrice());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//test OK
	@Override
	public Article read(int id) {
		String str = "SELECT * FROM T_Articles WHERE IdArticle = ?;";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				Article article = new Article(result.getString("Description"), result.getString("Brand"), result.getDouble("UnitaryPrice"));
				return article;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Article obj, int id) {
		String str = "UPDATE T_Articles SET Description = ?, Brand = ?, UnitaryPrice = ? WHERE IdArticle = "+id+";";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getBrand());
			ps.setDouble(3, obj.getUnitPrice());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String str = "DELETE FROM T_Articles WHERE IdArticle ="+id+";";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Article> readAll() {
		String str = "SELECT * FROM T_Articles;";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				ArrayList<Article> articles = new ArrayList<Article>();
				while (result.next()) {
					articles.add(new Article(result.getString("Description"), result.getString("Brand"), result.getDouble("UnitaryPrice")));
				}
				return articles;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
