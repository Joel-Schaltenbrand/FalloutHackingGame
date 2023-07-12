package ch.schaltenbrandlabs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public final class Logic {
	public static final int WAIT_TIME = 2500;
	private static final String SPECIAL_CHARS = "!#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
	private static final String HEXA_CHARS = "123456789ABCDEF";

	private Logic() {
		//Private Constructor
	}

	static ArrayList<String> getWordlist(int difficulty) throws FileNotFoundException {
		String filename;
		switch (difficulty) {
			case 1 -> filename = "1-EASY.txt";
			case 3 -> filename = "3-HARD.txt";
			default -> filename = "2-MEDIUM.txt";
		}
		ClassLoader classLoader = Logic.class.getClassLoader();
		Scanner scn = new Scanner(new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile()));
		ArrayList<String> wordList = new ArrayList<>();
		while (scn.hasNext()) {
			wordList.add(scn.next().toUpperCase());
		}
		scn.close();
		Collections.shuffle(wordList);
		return wordList;
	}

	static String chooseWord(ArrayList<String> wordlistRaw) {
		return wordlistRaw.get((int) (Math.random() * wordlistRaw.size()));
	}

	static ArrayList<String> generateList(ArrayList<String> wordListRaw) {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < wordListRaw.size(); i += 2) {
			list.add(generateLine(wordListRaw.get(i)) + "  " + generateLine(wordListRaw.get(i + 1)));
		}
		return list;
	}

	private static String generateLine(String word) {
		StringBuilder line = new StringBuilder();
		int max = 20;
		int random = (int) (Math.random() * max + 1);
		int randomRest = max - random;
		Random r = new Random();
		line.append("\033[0;32m");
		line.append("0x0");
		for (int i = 0; i < 4; i++) {
			line.append(HEXA_CHARS.charAt(r.nextInt(HEXA_CHARS.length())));
		}
		line.append(" ");
		for (int i = 0; i < random; i++) {
			line.append(SPECIAL_CHARS.charAt(r.nextInt(SPECIAL_CHARS.length())));
		}
		line.append("\033[0;92m");
		line.append(word);
		line.append("\033[0;32m");
		for (int i = 0; i < randomRest; i++) {
			line.append(SPECIAL_CHARS.charAt(r.nextInt(SPECIAL_CHARS.length())));
		}
		line.append("\033[0m");
		return line.toString();
	}

	protected static void cleanConsole() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
		printTitle();
	}

	protected static void errorMessage(Exception exception, String message) {
		cleanConsole();
		System.err.println(message);
		exception.printStackTrace();
		System.exit(99);
	}

	protected static void printTitle() {
		ClassLoader classLoader = Logic.class.getClassLoader();
		Scanner scn;
		try {
			scn = new Scanner(new File(Objects.requireNonNull(classLoader.getResource("title.txt")).getFile()));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		while (scn.hasNextLine()) {
			System.out.println(scn.nextLine());
		}
		scn.close();
		System.out.println("\n\n\n");
	}
}
