import database.DBGestion;
import terminalIO.TerminalIO;

import java.sql.ResultSet;
import java.util.Scanner;


/**
 * Created by Oscar Oliver on 01/05/16.
 */
public class OXatCopy {
	DBGestion db;
	int userId;

	/*public static void main(String[] args) {
		String IP;
		if (args.length == 0) IP = "127.0.0.1"; //IP = "192.168.222.111";
		else				  IP = args[0];
		new OXatCopy().init(IP);
	}*/

	public void init(String ip) {
		Debug.log("Conexió amb el servidor: " + ip);
		db = new DBGestion(ip, "root", "alumne", "OXat");

		logIn();
		TerminalIO.println("\n\n");
		getUser();
		TerminalIO.println("\n\n");
		start();
	}

	private void logIn() {
		String title = "Menú d'accés";
		TerminalIO.printTitle(title);

		String [] menu = {"Accedir", "Registrar-se", "Sortir"};

		int option = TerminalIO.menu(menu,  "Introdueix opció: ", "Opció invàlida.");
		switch (option) {
			case 1:
				// Do nothing --> Go to getUser();
				break;
			case 2:
				TerminalIO.println("\n\n");
				crearUsuari();
				break;
			case 3:
				exit();
				break;
		}
	}

	private void crearUsuari() {
		String title = "Crear usuari";
		TerminalIO.printTitle(title);

		String username;
		boolean nameOK = false;
		do {
			username = TerminalIO.readLine("Nom d'usuari: ");
			try {
				db.insert("USERS", "Name, Password", "'" + username + "', 'abc123'");
				nameOK = true;
			} catch (Exception e) {
				Debug.log(e.getMessage());
				TerminalIO.println("Aquest nom d'usuari ja existeix.");
				nameOK = false;
			}
		} while (!nameOK);

		String password, pw;
		do {
			password = TerminalIO.readLine("Contrasenya: ");
			pw = TerminalIO.readLine("Repeteix contrasenya: ");
		} while (!password.equals(pw));
		try {
			db.update("USERS", "Password = '" + pw + "'", "Name = '" + username + "'");
		} catch (Exception e) {
			Debug.log(e.getMessage());
		}
	}

	private void getUser() {
		String title = "Accés";
		TerminalIO.printTitle(title);

		String username;
		String password;

		boolean accessOK = false;
		int attempts = 3;
		do {
			username = TerminalIO.readLine("Nom d'usuari: ");
			password = TerminalIO.readLine("Contrasenya: ");
			try {
				ResultSet rs;
				rs = db.select("Id", "USERS", "Name='"+username+"' AND Password='"+password+"'", "Name");
				rs.next();
				userId = rs.getInt("Id");
				Debug.log("Id: " + userId);
				accessOK = true;
			} catch (Exception e) {
				TerminalIO.println(e.getMessage());
				attempts--;
				if (attempts == 0) {
					TerminalIO.println("Has realitzat massa intents.");
					exit();
				}
			} finally {
				db.closeConnection();
			}
		} while (!accessOK);
	}

	private void start() {
		String title = "OXat";
		TerminalIO.printTitle(title);

		String [] menu = {"Enviar missatge", "Veure missatges", "Sortir"};

		while (true) {
			int option = TerminalIO.menu(menu, "Introdueix opció: ", "Opció invàlida.");
			switch (option) {
				case 1:
					sendMessage();
					break;
				case 2:
					showMessages();
					break;
				case 3:
					exit();
					break;
			}

			pause("\n\nPrèmer intro <-' per continuar...");
			TerminalIO.println("\n");
		}
	}

	private void sendMessage() {
		String toUserName;
		int toUserId = userId;
		String message;

		boolean accessOK = false;
		do {
			toUserName = TerminalIO.readLine("Usuari: ");
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
		message = TerminalIO.readLine("Missatge:\n");
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
		ResultSet rs, rs2;
		String username;
		try {
			rs = db.select("Text, From_User", "Messages", "To_User="+userId, "Time");

			while(rs.next()) {
				String id = rs.getString("From_User");
				rs2 = db.select("Name", "USERS", "Id="+id, null);
				rs2.next();
				username = rs2.getString("Name");
				TerminalIO.println(username + "\t" + rs.getString("Text"));
			}

		} catch (Exception e) {
			Debug.log(e.getMessage());
		} finally {
			db.closeConnection();
		}
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

