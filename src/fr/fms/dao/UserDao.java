package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.User;
import fr.fms.entities.User;

class UserDao implements Dao<User>{
	@Override
	public void create(User obj) {
		String str = "INSERT INTO T_Users (login, password) VALUES (?,?);";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPass());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public User read(int id) {
		String str = "SELECT * FROM T_Users WHERE idUser = ?;";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				User user = new User(result.getString("login"), result.getString("password"));
				return user ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(User obj, int id) {
		String str = "UPDATE T_Users SET login = ?, password = ? WHERE idUser = "+id+";";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPass());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String str = "DELETE FROM T_Users WHERE idUser ="+id+";";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<User> readAll() {
		String str = "SELECT * FROM T_Users;";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				ArrayList<User> user = new ArrayList<User>();
				while (result.next()) {
					user.add(new User(result.getString("login"), result.getString("password")));
				}
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
