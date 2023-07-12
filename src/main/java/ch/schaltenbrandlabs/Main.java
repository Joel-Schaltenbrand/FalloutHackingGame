package ch.schaltenbrandlabs;

import java.util.Locale;
import java.util.Scanner;

public final class Main {
	private Main() {
		//Private Constructor
	}

	public static void main(String[] args) {
		try {
			chooseLanguage();
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

	private static void wrongInput() throws InterruptedException {
		System.out.println("Wrong Input! / Falsche Eingabe!");
		Thread.sleep(Logic.WAIT_TIME);
		chooseLanguage();
	}

	private static void chooseLanguage() throws InterruptedException {
		Scanner input = new Scanner(System.in);
		Logic.cleanConsole();
		System.out.printf("%s%40s", "Choose your language", "Wähle deine Sprache");
		System.out.println();
		System.out.printf("%s%41s", "1 - EN", "2 - DE");
		System.out.println("\n\n9 - Exit\n");
		Locale locale = Locale.getDefault();
		if (input.hasNextInt()) {
			switch (input.nextInt()) {
				case 1 -> locale = Locale.ENGLISH;
				case 2 -> locale = Locale.GERMAN;
				case 9 -> System.exit(0);
				default -> wrongInput();
			}
			Game.startGameMenu(locale);
		} else {
			wrongInput();
		}
	}
}
