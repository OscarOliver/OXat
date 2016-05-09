package database;
import java.sql.ResultSet;


public class DBGestion {
	
	private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
	private static final String DROP_TABLE = "DROP TABLE ";
	private static final String EMPTY_TABLE = "TRUNCATE ";

	private static final String INSERT = "INSERT INTO ";
	private static final String SELECT = "SELECT ";
	private static final String UPDATE = "UPDATE ";
	private static final String DELETE = "DELETE ";
	
	private static final String VALUES = " VALUES ";
	private static final String FROM = " FROM ";
	private static final String WHERE = " WHERE ";
	private static final String ORDER_BY = " ORDER BY ";
	private static final String SET = " SET ";
	
	private DataBase db;
	
	/**
	 * Creates a new data base with a manager facility to work with it.
	 * @param db the data base
	 */
	public DBGestion(DataBase db) {
		this.db = db;
	}
	
	/**
	 * Creates a new data base with a manager facility to work with it.
	 * @param servername the server direction like 'localhost' or an IP
	 * @param username the username
	 * @param password the user password
	 * @param databaseName the name of the data base
	 */
	public DBGestion(String servername, String username, String password, String databaseName) {
		db = new DataBase(servername, username, password, databaseName);
	}

	/**
	 * Create a new table.
	 * @param tableName the table name
	 * @param columnsDefinition the columns definition: column name, type, etc. in sql format
	 * @throws Exception 
	 */
	public void createTable(String tableName, String columnsDefinition) throws Exception {
		String sql = CREATE_TABLE + tableName + "(" + columnsDefinition + ") COLLATE latin1_spanish_ci";
		db.openConnection();
		db.executeUpdate(sql);
		db.closeConnection();
	}
	
	/**
	 * Remove the table from the data base.
	 * @param table
	 * @throws Exception
	 */
	public void removeTable(String table) throws Exception {
		String sql = DROP_TABLE + table;
		db.openConnection();
		db.executeUpdate(sql);
		db.closeConnection();
	}
	
	/**
	 * Empty the table content.
	 * @param table the table name
	 * @throws Exception
	 */
	public void emptyTable(String table) throws Exception {
		String sql = EMPTY_TABLE + table;
		db.openConnection();
		db.executeUpdate(sql);
		db.closeConnection();
	}
	
	// -- INSERT --
	/**
	 * Insert data to a table.
	 * @param table the table name
	 * @param columns the columns name. Format: col1, col2, ...
	 * @param values the values for the columns. Format: value1, value2, ...
	 * @throws Exception
	 */
	public void insert(String table, String columns, String values) throws Exception {
		String sql = INSERT + table + "(" + columns + ")" + VALUES + "(" + values + ")";
		db.openConnection();
		db.executeUpdate(sql);
		db.closeConnection();
	}
	
	// -- SELECT --
	/**
	 * Select all content from the table.<br>
	 * <i>Remember:</i> call '<tt>closeConnection()</tt>' after use this method.
	 * @param table the table name
	 * @return
	 * @throws Exception
	 */
	public ResultSet selectAll(String table) throws Exception {
		return select("*", table, null, null);
	}
	
	/**
	 * Select all content from the table where the condition is true.<br>
	 * <i>Remember:</i> call '<tt>closeConnection()</tt>' after use this method.
	 * @param table the table name
	 * @param where the condition to select the data. In sql format
	 * @return
	 * @throws Exception
	 */
	public ResultSet selectAll(String table, String where) throws Exception {
		return select("*", table, where, null);
	}
	
	/**
	 * Select all content from the table where the condition is true with the order defined.<br>
	 * <i>Remember:</i> call '<tt>closeConnection()</tt>' after use this method.
	 * @param table the table name
	 * @param where the condition to select the data. In sql format
	 * @param orderBy the order of data. In sql format
	 * @return
	 * @throws Exception
	 */
	public ResultSet selectAll(String table, String where, String orderBy) throws Exception {
		return select("*", table, where, orderBy);
	}
	
	/**
	 * Full select option.<br>
	 * <i>Remember:</i> call '<tt>closeConnection()</tt>' after use this method.
	 * @param column the columns we want to select
	 * @param table the table name
	 * @param where the condition to select the data. In sql format
	 * @param orderBy the order of data. In sql format
	 * @return
	 * @throws Exception
	 */
	public ResultSet select(String column, String table, String where, String orderBy) throws Exception {
		ResultSet rs = null;
		String sql = SELECT + column + FROM + table;
		if (where != null && !where.equals("")) {
			sql += WHERE + where;
		}
		if (orderBy != null && !orderBy.equals("")) {
			sql += ORDER_BY + orderBy;
		}
		sql += ";";
		db.openConnection();
		rs = db.executeQuery(sql);
		return rs;
	}
	
	// -- UPDATE --
	/**
	 * Update content from the table.
	 * @param table the table name
	 * @param set the new value to set
	 * @param where the condition to find what to update. In sql format
	 * @throws Exception
	 */
	public void update(String table, String set, String where) throws Exception {
		String sql = UPDATE + table + SET + set + WHERE + where;
		db.openConnection();
		db.executeUpdate(sql);
		db.closeConnection();
	}
	
	// -- DELETE --
	/**
	 * Delete data from the table (deletes a row).
	 * @param table the table name
	 * @param where the condition. In sql format
	 * @throws Exception
	 */
	public void delete(String table, String where) throws Exception {
		String sql = DELETE + FROM + table + WHERE + where;
		db.openConnection();
		db.executeUpdate(sql);
		db.closeConnection();
	}


	// -- FUNCION --
	/**
	 * Call a function
	 * @param function_name
	 * @param args the function arguments. Format: arg1, arg2, ...
	 * @return
	 */
	public String function(String function_name, String args) throws Exception {
		String sql = "exec " + function_name + "(" + args + ");";
		String result;
		db.openConnection();
		result = db.executeFunction(sql);
		db.closeConnection();
		return result;
	}
	
	/**
	 * Close the data base connection.
	 */
	public void closeConnection() {
		try {
			db.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
