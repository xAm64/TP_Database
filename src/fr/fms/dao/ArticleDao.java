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
	String table = "T_Articles";

	@Override
	public void create(Article obj) {
		String str = "INSERT INTO "+table+" (Description, Brand, UnitaryPrice) VALUES (?,?,?);";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getBrand());
			ps.setDouble(3, obj.getUnitPrice());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Article read(int id) {
		String str = "SELECT * FROM "+table+" WHERE IdArticle = ?;";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				return (Article) result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Article obj) {
		String str = "UPDATE "+table+" SET Description = ?, Brand = ?, UnitaryPrice = ? WHERE IdArticle = ?";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getBrand());
			ps.setDouble(3, obj.getUnitPrice());
			ps.setInt(4, obj.getIdArticle());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Article obj) {
		String str = "DELETE FROM "+table+" WHERE IdArticle ="+obj.getIdArticle()+";";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ResultSet result = ps.executeQuery();
			if (result.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Article> readAll() {
		String str = "SELECT * FROM "+table+";";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				ArrayList<Article> x = new ArrayList<Article>();
				while (result.next()) {
					int id = x.getInt(1);
					String description = x.getString(2);
					String brand = x.getString(3);
					double price = x.getDouble(4);
					x.add(new Article(rsIdUser, rsDescription, rsBrand, rsPrice));
				}
				return x;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
