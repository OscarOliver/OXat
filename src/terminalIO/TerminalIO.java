package terminalIO;

import java.util.Scanner;

/**
 * Created by Oscar Oliver on 01/05/16.
 */
public class TerminalIO {

	// --- INPUTS -------------------- //
	/**
	 * Read a single line.
	 * @return the input text line.
	 */
	public static String readLine() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	/**
	 * Read a single line with a message to the user.
	 * @param message the message to the user.
	 * @return the input text line.
	 */
	public static String readLine(String message) {
		print(message);
		return readLine();
	}


	/**
	 * Read an integer number.
	 * @return an integer number.
	 * @throws NumberFormatException if the input isn't an integer.
	 */
	public static int readInt() throws NumberFormatException {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		return Integer.parseInt(input);
	}

	/**
	 * Read an integer number with a message to the user.
	 * @param message the message to the user.
	 * @return an integer number.
	 * @throws NumberFormatException if the input isn't an integer.
	 */
	public static int readInt(String message) throws NumberFormatException {
		print(message);
		return readInt();
	}

	/**
	 * Read an integer number with a message to the user.<br>
	 * If the input isn't an integer, an error message will appear.
	 * The process will be repeat until the user type an integer.
	 * @param message the message to the user.
	 * @param errorMessage the error message to the user. If it's 'null' the
	 *                     error message will become the exception message.
	 * @return an integer number.
	 */
	public static int readInt(String message, String errorMessage) {
		boolean correct = false;
		int number = 0;
		while (!correct) {
			try {
				number = readInt(message);
				correct = true;
			} catch (NumberFormatException nfe) {
				if (errorMessage == null) errorMessage = nfe.getMessage();
				println(errorMessage);
			}
		}
		return number;
	}


	/**
	 * Read a floating point number.
	 * @return a floating point number.
	 * @throws NumberFormatException if the input isn't a floating point number.
	 */
	public static double readDouble() throws NumberFormatException {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		return Double.parseDouble(input);
	}

	/**
	 * Read a floating point number with a message to the user.
	 * @param message the message to the user.
	 * @return a floating point number.
	 * @throws NumberFormatException if the input isn't a floating point number.
	 */
	public static double readDouble(String message) throws NumberFormatException {
		print(message);
		return readDouble();
	}

	/**
	 * Read a floating point number with a message to the user.<br>
	 * If the input isn't a floating point number, an error message will appear.
	 * The process will be repeat until the user type a floating point number.
	 * @param message the message to the user.
	 * @param errorMessage the error message to the user. If it's 'null' the
	 *                     error message will become the exception message.
	 * @return a floating point number.
	 */
	public static double readDouble(String message, String errorMessage) {
		boolean correct = false;
		double number = 0;
		while (!correct) {
			try {
				number = readDouble(message);
				correct = true;
			} catch (NumberFormatException nfe) {
				if (errorMessage == null) errorMessage = nfe.getMessage();
				println(errorMessage);
			}
		}
		return number;
	}


	/**
	 * Read a boolean.<br>
	 * Valid strings ignoring case are:<br>
	 * - TRUE:  true, t, yes, y, si, s<br>
	 * - FALSE: false, f, no, n
	 * @return a boolean.
	 * @throws Exception if the input isn't a boolean string valid.
	 */
	public static boolean readBoolean() throws Exception{
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		input = input.toLowerCase().trim();

		if (input.equals("true")  || input.equals("t") ||
			input.equals("yes")   || input.equals("y") ||
			input.equals("si")    || input.equals("s"))
			return true;
		else if
		   (input.equals("false") || input.equals("f") ||
			input.equals("no")    || input.equals("n"))
			return false;

		throw new Exception("Invalid input string.");
	}

	/**
	 * Read a boolean with a message to the user.<br>
	 * Valid strings ignoring case are:<br>
	 * - TRUE:  true, t, yes, y, si, s<br>
	 * - FALSE: false, f, no, n
	 * @param message the message to the user.
	 * @return a boolean.
	 * @throws Exception if the input isn't a boolean string valid.
	 */
	public static boolean readBoolean(String message) throws Exception {
		print(message);
		return readBoolean();
	}

	/**
	 * Read a boolean with a message to the user.<br>
	 * If the input isn't a boolean valid string, an error message will appear.
	 * The process will be repeat until the user type a boolean valid string.<br>
	 * Valid strings ignoring case are:<br>
	 * - TRUE:  true, t, yes, y, si, s<br>
	 * - FALSE: false, f, no, n
	 * @param message the message to the user.
	 * @param errorMessage the error message to the user. If it's 'null' the
	 *                     error message will become the exception message.
	 * @return a boolean.
	 */
	public static boolean readBoolean(String message, String errorMessage) {
		boolean correct = false;
		boolean bool = false;
		while (!correct) {
			try {
				bool = readBoolean(message);
				correct = true;
			} catch (Exception e) {
				if (errorMessage == null) errorMessage = e.getMessage();
				println(errorMessage);
			}
		}
		return bool;
	}


	/**
	 * Read an email address.<br>
	 * Format accepted: *@*.*<br>
	 * where * means at least one character.
	 * @return an email address.
	 * @throws Exception if the input isn't a valid email.
	 */
	public static String readEmail() throws Exception {
		String email = readLine();
		int length = email.length();
		int arrovaPos = email.indexOf('@');
		int dotPos = email.lastIndexOf('.');
		if (arrovaPos == -1 || dotPos == -1 ||		// Not exists @ and .
			arrovaPos >= dotPos-1 ||				// No spaces between them or are inverted
			arrovaPos == 0 || dotPos == length)		// Stars with @ or ends with .
			throw new Exception("Invalid email format. Format: *@*.*");

		return email;
	}

	/**
	 * Read an email address.<br>
	 * Format accepted: *@*.*<br>
	 * where * means at least one character.
	 * @param message the message to the user.
	 * @return an email address.
	 * @throws Exception if the input isn't a valid email.
	 */
	public static String readEmail(String message) throws Exception {
		print(message);
		return readEmail();
	}

	/**
	 * Read an email address.<br>
	 * Format accepted: *@*.*<br>
	 * where * means at least one character.
	 * If the input isn't an email valid string, an error message will appear.
	 * The process will be repeat until the user type a floating point number.
	 * @param message the message to the user.
	 * @param errorMessage the error message to the user. If it's 'null' the
	 *                     error message will become the exception message.
	 * @return an email address.
	 * @throws Exception if the input isn't a valid email.
	 */
	public static String readEmail(String message, String errorMessage) {
		boolean correct = false;
		String email = "";
		while (!correct) {
			try {
				email = readEmail(message);
				correct = true;
			} catch (Exception e) {
				if (errorMessage == null) errorMessage = e.getMessage();
				println(errorMessage);
			}
		}
		return email;
	}



	// --- OUTPUTS -------------------- //

	/**
	 * Print a text.
	 * @param text the text to print.
	 */
	public static void print(String text) {
		System.out.print(text);
	}

	/**
	 * Print a character.
	 * @param c the character to print.
	 */
	public static void print(char c) {
		System.out.print(c);
	}

	/**
	 * Print a number.
	 * @param number the integer number to print.
	 */
	public static void print(int number) {
		System.out.print(number);
	}

	/**
	 * Print a number.
	 * @param number the double type number to print.
	 */
	public static void print(double number) {
		System.out.print(number);
	}

	/**
	 * Print a boolean.
	 * @param b the boolean to print.
	 */
	public static void print(boolean b) {
		System.out.print(b);
	}

	/**
	 * Print a object
	 * @param o the object to print.
	 */
	public static void print(Object o) {
		System.out.print(o.toString());
	}

	/**
	 * Print a new line character: '\n'
	 */
	public static void println() {
		System.out.println();
	}

	/**
	 * Print a text adding new line character at the end.
	 * @param text the text to print.
	 */
	public static void println(String text) {
		System.out.println(text);
	}

	/**
	 * Print a character adding new line character at the end.
	 * @param c the character to print.
	 */
	public static void println(char c) {
		System.out.println(c);
	}

	/**
	 * Print a number adding new line character at the end.
	 * @param number the integer number to print.
	 */
	public static void println(int number) {
		System.out.println(number);
	}

	/**
	 * Print a number adding new line character at the end.
	 * @param number the double type number to print.
	 */
	public static void println(double number) {
		System.out.println(number);
	}

	/**
	 * Print a boolean adding new line character at the end.
	 * @param b the boolean to print.
	 */
	public static void println(boolean b) {
		System.out.println(b);
	}

	/**
	 * Print a object adding new line character at the end.
	 * @param o the object to print.
	 */
	public static void println(Object o) {
		System.out.println(o.toString());
	}

	/**
	 * Print text with left padding.
	 * @param text the text to print.
	 * @param length the total length.
	 */
	public static void printLPad(String text, int length) {
		if (text == null) text = "null";
		length -= text.length();
		for (int i = 0; i < length; i++) {
			print(" ");
		}
		print(text);
	}

	/**
	 * Print text with right padding.
	 * @param text the text to print.
	 * @param length the total length.
	 */
	public static void printRPad(String text, int length) {
		if (text == null) text = "null";
		length -= text.length();
		print(text);
		for (int i = 0; i < length; i++) {
			print(" ");
		}
	}

	/**
	 * Print centered text with the same padding on both sides.
	 * @param text the text to print.
	 * @param length the total length.
	 */
	public static void printCPad(String text, int length) {
		if (text == null) text = "null";
		length -= text.length();
		int lPad = length/2;
		int rPad = length - lPad;
		for (int i = 0; i < lPad; i++) {
			print(" ");
		}
		print(text);
		for (int i = 0; i < rPad; i++) {
			print(" ");
		}
	}

	/**
	 * Print a text in a rectangle made of asterisks '*'.
	 * @param title the title text. It have to be a single line.
	 */
	public static void printHeadTitle(String title) {
		int n = title.length() +4;
		for (int i = 0; i < n; i++) {
			print("*");
		}
		println();
		println("* " + title + " *");
		for (int i = 0; i < n; i++) {
			print("*");
		}
		println();
	}

	/**
	 * Print a text underlined with '-'.
	 * @param title the title text. It have to be a single line.
	 */
	public static void printSectionTitle(String title) {
		int n = title.length() +2;
		println(" " + title);
		for (int i = 0; i < n; i++) {
			print("-");
		}
		println();
	}

	/**
	 * Print a table.<br>
	 * By default, columns are spaced 3 white spaces and null values are
	 * changed to an empty string.
	 * @param titles the title of each column.
	 * @param content the table content.
	 * @throws NullPointerException if titles or content arrays are null.
	 * @throws Exception if titles and content have different number of columns.
	 */
	public static void printTable(String[] titles, String[][] content) throws Exception {
		printTable(titles, content, "", 3);
	}

	/**
	 * Print a table.
	 * @param titles the title of each column.
	 * @param content the table content.
	 * @param nullValue a string to replace nulls in titles or content.
	 * @param spaceBetweenColums number of white spaces between each column.
	 * @throws NullPointerException if titles or content arrays are null.
	 * @throws Exception if titles and content have different number of columns.
	 */
	public static void printTable(String[] titles, String[][] content, String nullValue, int spaceBetweenColums) throws NullPointerException, Exception {
		if (titles == null || content == null) {
			throw new NullPointerException("Titles and content can't be null.");
		}
		if (titles.length != content[0].length) {
			throw new Exception("Titles and content have different number of columns.");
		}
		if (nullValue == null) nullValue = "";
		if (spaceBetweenColums < 1) spaceBetweenColums = 1;
		
		// Calculate width columns vector
		int[] colMaxs = new int[titles.length];
		for (int i = 0; i < titles.length; i++) {
			if (titles[i] == null) titles[i] = nullValue;
			colMaxs[i] = titles[i].length();
		}
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[0].length; j++) {
				if (content[i][j] == null) content[i][j] = nullValue;
				if (content[i][j].length() > colMaxs[j]) {
					colMaxs[j] = content[i][j].length();
				}
			}
		}
		for (int i = 0; i < colMaxs.length - 1; i++) {
			colMaxs[i] += spaceBetweenColums;
		}
		colMaxs[colMaxs.length - 1] += 1;

		// Print titles
		int spaces;
		print(" ");
		for (int i = 0; i < titles.length; i++) {
			printRPad(titles[i], colMaxs[i]);
		}
		println();

		// Print -'s
		for (int i = 0; i < colMaxs.length; i++) {
			for (int j = 0; j < colMaxs[i]; j++) {
				print("-");
			}
		}
		println("-");

		// Print content
		for (int i = 0; i < content.length; i++) {
			print(" ");
			for (int j = 0; j < content[0].length; j++) {
				printRPad(content[i][j], colMaxs[j]);
			}
			println();
		}
	}




	// --- MENU ---------------------------//

	/**
	 * Print a menu and read the option that user has chosen.
	 * @param options the different options of the menu.
	 * @param chooseOptionMessage a message to user.
	 * @return the option chosen.
	 */
	public static int menu(String[] options, String chooseOptionMessage) {
		for (int i = 0; i < options.length; i++) {
			println((i+1) + ". " + options[i]);
		}
		int option;
		do {
			option = readInt(chooseOptionMessage);
		} while (option < 1 || option > options.length);
		return option;
	}

	/**
	 * Print a menu and read the option that user has chosen.
	 * @param options the different options of the menu.
	 * @param chooseOptionMessage a message to user.
	 * @param errorMessage an error message to user.
	 * @return the option chosen.
	 */
	public static int menu(String[] options, String chooseOptionMessage, String errorMessage) {
		for (int i = 0; i < options.length; i++) {
			println((i+1) + ". " + options[i]);
		}
		int option;
		do {
			option = readInt(chooseOptionMessage, errorMessage);
		} while (option < 1 || option > options.length);
		return option;
	}
}
