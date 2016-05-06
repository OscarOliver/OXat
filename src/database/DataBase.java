package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBase {

	private final String servername;
	private final String username;
	private final String password;
	private final String database;
	private final String url;
	
	private Connection conn;
	private Statement stmt;
	
	public DataBase(String servername, String username, String password, String databaseName) {
		this.servername = servername;
		this.username = username;
		this.password = password;
		this.database = databaseName;
		this.url = "jdbc:mysql://localhost/" + this.database;
	}
	
	// -- GET DATABASE INFO --
	public String getServername() {
		return servername;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getDataBaseName() {
		return database;
	}
	
	public String getUrl() {
		return url;
	}
	
	
	// -- OPEN & CLOSE DATABASE CONNECTION --
	public void openConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);
	}
	
	public void closeConnection() throws SQLException {
		stmt.close();
		conn.close();
	}
	
	
	// -- EXECUTE QUERIES --
	public ResultSet executeQuery(String sql) throws SQLException {
		ResultSet rs = null;
		stmt = conn.createStatement();
		stmt.closeOnCompletion();
		rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public int executeUpdate(String sql) throws SQLException {
		stmt = conn.createStatement();
		return stmt.executeUpdate(sql);
	}
	
	
	// -- TABLES --
	public void createTable(String sql) throws SQLException {
		stmt = conn.createStatement();
	}
}
