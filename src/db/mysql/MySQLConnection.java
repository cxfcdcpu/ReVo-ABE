/**
 * 
 */
package db.mysql;
import db.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import entity.user;

/**
 * @author XiaofeiCao
 *
 */
public class MySQLConnection implements DBConnection{

	
	private Connection conn;

	public MySQLConnection() {
	// Step 1 Connect to MySQL.
		try {
			System.out.println("Connecting to " + MySQLDBUtil.URL);
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			conn = DriverManager.getConnection(MySQLDBUtil.URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public boolean verifyLogin(String username, String password) {
		// TODO Auto-generated method stub
		
		user curUser= searchUser(username);
		if(curUser!=null && curUser.getHashedPassword().equals(password)) {
			return true;
		}
		
		return false;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public user searchUser(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM user WHERE username = '"+username+"';";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				return new user(rs.getString("username"),rs.getString("password")
						,rs.getString("attributes"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public boolean insertUser(String username, String password, String attributes) {
		

		String sql = "INSERT INTO user (username,password,attributes)"
				+ "VALUES ('"+username+"','"+password+"','"+attributes+"');";
		try {
			Statement stmt = conn.createStatement();
			user curUser = searchUser(username);
			if(curUser == null) {
				stmt.executeUpdate(sql);
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return false;
	}

}
