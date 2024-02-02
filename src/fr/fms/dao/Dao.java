package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface Dao<T> {
	public Connection connection = BddConnection.getConnexion();
	public void create(T obj);
	public T read(int id);
	public void update(T obj, int id);
	public void delete(int id);
	public ArrayList<T> readAll();
}
