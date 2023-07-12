# Fallout Hacking Game

[![CodeQL](https://github.com/Joel-Schaltenbrand/FalloutHackingGame/actions/workflows/codeql.yml/badge.svg?branch=master)](https://github.com/Joel-Schaltenbrand/FalloutHackingGame/actions/workflows/codeql.yml)

Welcome to the Fallout Hacking Game! This is a text-based version of the popular hacking mini-game from the Fallout
series. In this game, you will be tasked with guessing words to crack the code and gain access to the system.

## Installation

To play the game, you need to have Maven and Java 17 installed on your machine. Follow these steps to set up the game:

1. Clone the repository to your local machine:

```bash
git clone https://github.com/your-username/fallout-hacking-game.git
```

2. Navigate to the project directory:

```bash
cd fallout-hacking-game
```

3. Build the project using Maven:

```bash
mvn clean package
```

This will compile the code and package it into an executable JAR file.

## How to Play

1. Open a terminal or command prompt.
2. Navigate to the project directory.
3. Run the game using the following command:

```bash
java -jar target/fallout-hacking-game.jar
```

4. The game will start, and you will be presented with a series of words. Your goal is to guess the correct word to
   crack the code.
5. Enter your guess and press Enter.
6. The game will provide feedback on your guess, indicating how many letters are correct and in the correct position.
7. Keep guessing until you crack the code or run out of attempts.

## Game Rules

- The game will provide you with a list of words.
- You have a limited number of attempts to guess the correct word.
- After each guess, the game will indicate how many letters are correct and in the correct position.
- Use this feedback to eliminate incorrect words and narrow down your options.
- If you run out of attempts without guessing the correct word, the game will end.

## Additional Notes

- The game supports both English and German languages, but the words to be guessed are in English only.
- The project is built using Maven, a build automation tool for Java projects. It manages dependencies and builds the
  project using the specified Java version (Java 17 in this case).
- Feel free to explore the source code and make any modifications or improvements you want.

## Contributing

If you'd like to contribute to this project, you can fork the repository, make your changes, and submit a pull request.
Contributions are always welcome!

## License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for more information.
