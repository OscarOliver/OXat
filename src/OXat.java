import database.DBGestion;
import keyboardIO.KeyboardIO;

import java.sql.ResultSet;


/**
 * Created by alumne on 01/05/16.
 */
public class OXat {
	DBGestion db = new DBGestion("127.0.0.1", "root", "alumne", "OXat");
	int userId;

	public static void main(String[] args) {
		new OXat().init();
	}

	public void init() {
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
		int option = KeyboardIO.menu(menu, "Introdueix opció: ", "Opció invàlida.");
		while (true) {
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
				rs = db.select("Id", "Users", toUserName+"=name", "Name");
				toUserId = rs.getInt("Id");
				accessOK = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (!accessOK);
		message = KeyboardIO.readLine("Missatge:\n");
		try {
			db.insert("Messages", "Text, From_User, To_User", message+", "+userId+", "+toUserId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showMessages() {
		ResultSet rs;
		try {
			rs = db.select("Text, To_User", "Messages", userId+"= From_User", "Time");
			while(rs.next()) {
				KeyboardIO.println(rs.getString("From_User") + "\t" + rs.getString("Text"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
