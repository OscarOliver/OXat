import database.DBGestion;
import keyboardIO.KeyboardIO;

import java.sql.ResultSet;


/**
 * Created by alumne on 01/05/16.
 */
public class OXat {
	DBGestion db;
	int userId;

	public static void main(String[] args) {
		String IP;
		if (args.length == 0) IP = "127.0.0.1";
		else				  IP = args[0];
		new OXat().init(IP);
	}

	public void init(String ip) {
		db = new DBGestion(ip, "root", "alumne", "OXat");
		getUser();
		start();
	}

	private void getUser() {
		String username;
		String password;

		boolean accessOK = false;
		int attempts = 3;
		do {
			username = KeyboardIO.readLine("Nom d'usuari: ");
			password = KeyboardIO.readLine("Password: ");
			try {
				ResultSet rs;
				rs = db.select("Id", "USERS", "Name='"+username+"' AND Password='"+password+"'", "Name");
				rs.next();
				userId = rs.getInt("Id");
				Debug.log("Id: " + userId);
				accessOK = true;
			} catch (Exception e) {
				KeyboardIO.println(e.getMessage());
				attempts--;
				if (attempts == 0) {
					KeyboardIO.println("Has realitzat massa intents.");
					System.exit(0);
				}
			} finally {
				db.closeConnection();
			}
		} while (!accessOK);
	}

	private void start() {
		String [] menu = {"Enviar missatge", "Veure missatges", "Sortir"};
		while (true) {
			int option = KeyboardIO.menu(menu, "Introdueix opció: ", "Opció invàlida.");
			switch (option) {
				case 1:
					sendMessage();
					break;
				case 2:
					showMessages();
					break;
				case 3:
					System.exit(0);
					break;
			}
		}
	}

	private void sendMessage() {
		String toUserName;
		int toUserId = userId;
		String message;

		boolean accessOK = false;
		do {
			toUserName = KeyboardIO.readLine("Usuari: ");
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
		message = KeyboardIO.readLine("Missatge:\n");
		try {
			Debug.log(	"From: " + userId   + "\n" +
						"To:   " + toUserId + "\n" +
						message);
			//Debug.log( db.function("getName", toUserId+"") );
			db.insert("Messages", "Text, From_User, To_User", "'"+message+"', "+userId+", "+toUserId);
		} catch (Exception e) {
			Debug.log(e.getMessage());
		}
	}

	private void showMessages() {
		ResultSet rs;
		try {
			rs = db.select("Text, From_User", "Messages", "To_User="+userId, "Time");
			while(rs.next()) {
				KeyboardIO.println(rs.getString("From_User") + "\t" + rs.getString("Text"));
			}
		} catch (Exception e) {
			Debug.log(e.getMessage());
		} finally {
			db.closeConnection();
		}
	}
}
