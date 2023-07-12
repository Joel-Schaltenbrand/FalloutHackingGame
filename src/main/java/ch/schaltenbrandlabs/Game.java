package ch.schaltenbrandlabs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public final class Game {
	private static final Scanner INPUT = new Scanner(System.in);
	private static int maxTries = 10;
	private static ArrayList<String> wordList;
	private static String chosenWord;
	private static ResourceBundle resourceBundle;

	private Game() {
		//Private Constructor
	}

	public static void startGameMenu(Locale locale) throws InterruptedException {
		resourceBundle = ResourceBundle.getBundle("bundle", locale);
		startMenu();
	}

	private static void startMenu() throws InterruptedException {
		Logic.cleanConsole();
		System.out.println("\n\n" + resourceBundle.getString("menu1") + ": " + maxTries + " " + resourceBundle.getString("menu2"));
		System.out.print("\n\n" + resourceBundle.getString("chooseOption") + ": ");
		switch (INPUT.nextInt()) {
			case 1 -> startGame();
			case 2 -> startSettings();
			case 3 -> {
			}
			default -> {
				System.out.println(resourceBundle.getString("invalidInput"));
				Thread.sleep(Logic.WAIT_TIME);
				startMenu();
			}
		}
	}

	private static void startSettings() throws InterruptedException {
		Logic.cleanConsole();
		System.out.print("(" + resourceBundle.getString("start1") + ": " + maxTries + ")\n\n" + resourceBundle.getString("start2") + ": ");
		maxTries = INPUT.nextInt();
		Logic.cleanConsole();
		System.out.println(resourceBundle.getString("start3") + " " + maxTries + " " + resourceBundle.getString("start4"));
		Thread.sleep(Logic.WAIT_TIME);
		startMenu();
	}

	private static void startGame() {
		Logic.cleanConsole();
		try {
			ArrayList<String> wordlistRaw = Logic.getWordlist(chooseDifficulty());
			chosenWord = Logic.chooseWord(wordlistRaw);
			wordList = Logic.generateList(wordlistRaw);
			guessWord();
		} catch (FileNotFoundException e) {
			Logic.errorMessage(e, resourceBundle.getString("failOnLoad"));
		} catch (InterruptedException e) {
			Logic.errorMessage(e, resourceBundle.getString("fail"));
		}
	}

	private static int chooseDifficulty() throws InterruptedException {
		Scanner input = new Scanner(System.in);
		System.out.print(resourceBundle.getString("difficulty") + ": ");
		int difficulty = input.nextInt();
		if (difficulty != 1 && difficulty != 2 && difficulty != 3) {
			System.out.println("\n\n" + resourceBundle.getString("invalidDifficulty"));
			Thread.sleep(Logic.WAIT_TIME);
			Logic.cleanConsole();
			chooseDifficulty();
		}
		return difficulty;
	}

	private static void guessWord() throws InterruptedException {
		int tries = 1;
		int correctLetters;
		boolean won = false;
		do {
			correctLetters = 0;
			Logic.cleanConsole();
			printWordList();
			System.out.println("\n" + resourceBundle.getString("currentAttempt") + ": " + tries + "/" + maxTries);
			System.out.print("\n" + resourceBundle.getString("inputWord") + ": ");
			String guess = INPUT.next().toUpperCase();
			if (guess.length() != chosenWord.length()) {
				System.out.println("\n" + resourceBundle.getString("invalidLength1") + " " + chosenWord.length() + " "
						+ resourceBundle.getString("invalidLength2"));
				Thread.sleep(Logic.WAIT_TIME);
				continue;
			}
			for (int i = 0; i < chosenWord.length(); i++) {
				if (chosenWord.charAt(i) == guess.charAt(i)) {
					correctLetters++;
				}
			}
			if (correctLetters == chosenWord.length()) {
				won = true;
			} else {
				System.out.println("\n" + resourceBundle.getString("rightLetter") + ": " + correctLetters + "/" + chosenWord.length());
				tries++;
				Thread.sleep(Logic.WAIT_TIME);
			}
		} while (tries <= maxTries && !won);
		Logic.cleanConsole();
		if (won) {
			System.out.println(resourceBundle.getString("win") + ": " + chosenWord + "\n"
					+ resourceBundle.getString("attempts") + ": " + tries + "/" + maxTries);
		} else {
			System.out.println(resourceBundle.getString("lose") + ": " + chosenWord);
		}
		Thread.sleep(Logic.WAIT_TIME * 2);
		startMenu();
	}

	private static void printWordList() {
		System.out.println(resourceBundle.getString("wordlist") + ":\n-------------------------------------------------------------------------");
		for (String word : wordList) {
			System.out.println(word);
		}
	}
}
