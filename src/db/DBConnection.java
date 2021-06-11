package db;
import java.util.List;
import java.util.Set;

import java.sql.*;
import entity.user;
public interface DBConnection {

	public void close();
	
	public user searchUser(String username);
	
	public boolean verifyLogin(String username, String password);
	
	public boolean insertUser(String username,String password,String attributes);

	
}
