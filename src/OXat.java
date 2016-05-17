import database.DBGestion;
import terminalIO.TerminalIO;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Oscar Oliver on 01/05/16.
 */
public class OXat {
	DBGestion db;
	int userId;

	public OXat (String databaseServerIP) {
		Debug.log("Conexió amb el servidor: " + databaseServerIP);
		db = new DBGestion(databaseServerIP, "root", "alumne", "OXat");
	}


	public boolean createUser(String username, String password) {
		try {
			db.insert("USERS", "Name, Password", "'" + username + "', " + password);
			return true;
		} catch (Exception e) {
			Debug.log(e.getMessage());
			return false;
		}
	}

	public boolean logIn(String username, String password) {
		boolean accessOK = false;

		try {
			ResultSet rs;
			rs = db.select("Id", "USERS", "Name='"+username+"' AND Password='"+password+"'", "Name");
			rs.next();
			userId = rs.getInt("Id");
			Debug.log("Id: " + userId);
			accessOK = true;
		} catch (Exception e) {
			Debug.log(e.getMessage());
		} finally {
			db.closeConnection();
		}
		return accessOK;
	}

	public boolean sendMessage(String toUserName, String message) {
		int toUserId = userId;

		boolean accessOK = false;
		do {
			try {
				ResultSet rs;
				rs = db.select("Id", "USERS", "Name='"+ toUserName+"'", "Name");
				rs.next();
				toUserId = rs.getInt("Id");
				accessOK = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.closeConnection();
			}
		} while (!accessOK);
		try {
			Debug.log(	"From: " + userId   + "\n" +
						"To:   " + toUserId + "\n" +
						message);
			db.insert("Messages", "Text, From_User, To_User", "'"+message+"', "+userId+", "+toUserId);
		} catch (Exception e) {
			Debug.log(e.getMessage());
			return false;
		}
		return true;
	}

	public ArrayList<String[]> getMessages() {
		ArrayList<String[]> messages = new ArrayList<>();
		ResultSet rs, rs2;
		String username, time, text;
		try {
			rs = db.select("Text, From_User, Time", "Messages", "To_User="+userId, "Time DESC");

			while(rs.next()) {
				String id = rs.getString("From_User");
				rs2 = db.select("Name", "USERS", "Id="+id, null);
				rs2.next();
				username = rs2.getString("Name");
				Timestamp timestamp = rs.getTimestamp("Time");
				time = timestamp.toString();
				text = rs.getString("Text");
				messages.add(new String[] {username, time, text});
			}
		} catch (Exception e) {
			Debug.log(e.getMessage());
		} finally {
			db.closeConnection();
		}
		return messages;
	}

	private void pause(String message) {
		Scanner sc = new Scanner(System.in);
		TerminalIO.print(message);
		sc.nextLine();
	}

	private void exit() {
		TerminalIO.println("\nFins aviat!");
		System.exit(0);
	}
}
