package keyboardIO;

import java.util.Scanner;

/**
 * Created by alumne on 01/05/16.
 */
public class KeyboardIO {

	public static String readLine() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	public static String readLine(String message) {
		print(message);
		return readLine();
	}


	public static int readInt(String message) {
		return readInt(message, null);
	}

	public static int readInt(String message, String errorMessage) {
		boolean correcte = false;
		Scanner sc = new Scanner(System.in);
		String input;
		int number = 0;
		while (!correcte) {
			try {
				print(message);
				input = sc.nextLine();
				number = Integer.parseInt(input);
				correcte = true;
			} catch (NumberFormatException nfe) {
				println(errorMessage);
			}
		}
		return number;
	}


	public static void print(String text) {
		System.out.print(text);
	}

	public static void print(int number) {
		System.out.print(number);
	}

	public static void print(double number) {
		System.out.print(number);
	}

	public static void println() {
		System.out.println();
	}

	public static void println(String text) {
		System.out.println(text);
	}

	public static void println(int number) {
		System.out.println(number);
	}

	public static void println(double number) {
		System.out.println(number);
	}


	public static void printTitle(String title) {
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
