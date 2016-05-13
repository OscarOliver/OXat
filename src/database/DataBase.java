package database;
import java.sql.*;


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
		//this.url = "jdbc:mysql://localhost/" + this.database;
		this.url = "jdbc:mysql://192.168.222.111:3306/" + this.database;
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

	public String executeFunction(String sql) throws SQLException {
		CallableStatement sentencia=conn.prepareCall("{?=call getName(?)}");	//Declarar la sentencia.
		sentencia.registerOutParameter(1, Types.INTEGER);						//Registrar el parámetro de entrada
		sentencia.setInt(2, 1);													//Registrar el parámetro de salida
		sentencia.executeQuery();												//Realizar la llamada
		return sentencia.getString(1);											//Recoger el parámetro de salida
	}
	
	
	// -- TABLES --
	public void createTable(String sql) throws SQLException {
		stmt = conn.createStatement();
	}
}
