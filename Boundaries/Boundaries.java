// Project made by precisefox on GitHub

import java.util.*;

public class Boundaries {

	public static void main(String[] args) throws InterruptedException {
		
		// defaults
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_GRAY = "\u001B[38;2;128;128;128m";
	    
		// RGB!
	    final String ANSI_lGRAY = "\u001B[38;2;191;191;191m";
		final String ANSI_lRED = "\u001B[38;2;255;128;128m";
	    final String ANSI_lSKY = "\u001B[38;2;128;191;255m";
	    final String ANSI_lPURPLE = "\u001B[38;2;191;128;255m";
	    final String ANSI_lPINK = "\u001B[38;2;255;128;255m";
	
		Scanner input = new Scanner(System.in);
		
		System.out.print(ANSI_lPURPLE);
		printRoll("****************************************************************************************************", 10);
		System.out.println("");
		System.out.println(ANSI_RESET);
		printRoll("Hello! This is my first ever Java game: ", 10);
		System.out.println(ANSI_lPURPLE);
		System.out.println("");
		Thread.sleep(100);
		System.out.println("██████╗  ██████╗ ██╗   ██╗███╗   ██╗██████╗  █████╗ ██████╗ ██╗███████╗███████╗");
		Thread.sleep(100);
		System.out.println("██╔══██╗██╔═══██╗██║   ██║████╗  ██║██╔══██╗██╔══██╗██╔══██╗██║██╔════╝██╔════╝");
		Thread.sleep(100);
		System.out.println("██████╔╝██║   ██║██║   ██║██╔██╗ ██║██║  ██║███████║██████╔╝██║█████╗  ███████╗");
		Thread.sleep(100);
		System.out.println("██╔══██╗██║   ██║██║   ██║██║╚██╗██║██║  ██║██╔══██║██╔══██╗██║██╔══╝  ╚════██║");
		Thread.sleep(100);
		System.out.println("██████╔╝╚██████╔╝╚██████╔╝██║ ╚████║██████╔╝██║  ██║██║  ██║██║███████╗███████║");
		Thread.sleep(100);
		System.out.println("╚═════╝  ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝╚══════╝╚══════╝");
		Thread.sleep(100);
		System.out.print(ANSI_GRAY);
		printRoll("(v1.2)", 10);
		System.out.println("");
		System.out.println(ANSI_RESET);
		printRoll("Here's how it works:", 10);
		System.out.println(ANSI_lPURPLE);
		printRoll("1. ", 10);
		System.out.print(ANSI_lGRAY);
		printRoll("The game will repeatedly ask you to enter numbers that satisfy a given condition.", 10);
		System.out.println("");
		System.out.print(ANSI_lPURPLE);
		printRoll("2. ", 10);
		System.out.print(ANSI_lGRAY);
		printRoll("The game will time you to see how long it takes you to answer.", 10);
		System.out.println("");
		System.out.print(ANSI_lPURPLE);
		printRoll("3. ", 10);
		System.out.print(ANSI_lGRAY);
		printRoll("The faster you answer, the more points you get!", 10);
		System.out.println("");
		System.out.print(ANSI_lPURPLE);
		printRoll("4. ", 10);
		System.out.print(ANSI_lGRAY);
		printRoll("Some questions will try to trick you, so be careful!", 10);
		System.out.println("");
		System.out.print(ANSI_lPURPLE);
		printRoll("5. ", 10);
		System.out.print(ANSI_lGRAY);
		printRoll("If you take more than 5 seconds to answer or answer incorrectly, you lose!", 10);
		System.out.println("");
		System.out.println(ANSI_RESET);
		printRoll("Input 1 to begin. ", 10);
		System.out.print(ANSI_lSKY);
		double answer = input.nextInt();
		if (answer == 1) { 
			System.out.println(ANSI_lPURPLE);
			printRoll("****************************************************************************************************", 10);
			System.out.println("");
			System.out.println("");
		} else { 
			System.out.println(0 / 0);
		}
		
		boolean gameActive = true;
		int questionNum = 1;
		int score = 0;
		int scoreAdd = 0;
		double bonus = 1;
		double timeLimit = 5;
		double difficulty = 0;
		int questionType = 1;
		double trickQuestionChance = 1;
		
		/*
		 * Question types!
		 * 1. Default a < x < b
		 * 
		 * 2. (2) Tricky a > x < b (both are maximums)
		 * 3. (2) Tricky a < x > b (both are minimums)
		 * 4. (2) Tricky b > x > a (reverse)
		 * 5. (5) Decimals
		 * 6. (3) a < x + c < b
		 */
		
		while (gameActive) {
			
			if (difficulty >= 1000) {
				trickQuestionChance = 50;
			} else {
				trickQuestionChance = 0.049 * difficulty + 1;
			}
			
			if ((Math.random() * 100) < trickQuestionChance) {
				int questionRandomizer = (int)(Math.random() * 14) + 1;
				if (questionRandomizer <= 6) {
					questionType = (int)(Math.random() * 3) + 2;
				} else if (questionRandomizer <= 11) {
					questionType = 5;
				} else {
					questionType = 6;
				}
			} else {
				questionType = 1;
			}
			
			// questionType override
			questionType = 6;
			
			String bounds = generateQuestion(questionType, difficulty, questionNum);
			int indexOfSpace = bounds.indexOf(" ");
			String minStr = bounds.substring(0, indexOfSpace);
			String maxStr = bounds.substring(indexOfSpace + 1);
			double minimum = Double.parseDouble(minStr);
			double maximum = Double.parseDouble(maxStr);
			long startTime = System.currentTimeMillis();
			answer = input.nextDouble();
			long endTime = System.currentTimeMillis();
			double timeSpent = (endTime - startTime) / 1000.0;
			
			if (timeSpent > timeLimit) {
				
				System.out.print(ANSI_lRED);
				printRoll("GAME OVER: Out of time! (" + timeSpent + "s)", 10);
				System.out.println(ANSI_RESET);
				System.out.print(ANSI_lPURPLE);
				printRoll("Final Score: ", 10);
				System.out.print(ANSI_RESET);
				printRoll("" + score, 10);
				gameActive = false;
				
			} else {
			
				if (checkAnswer(questionType, answer, minimum, maximum)) {
				
					System.out.print(ANSI_GRAY);
					printRoll("Correct." + " (" + timeSpent + "s)", 10);
					System.out.println(ANSI_RESET);
				
					if ((questionType >= 2) && (questionType <= 4)) {
						bonus = 2;
					} else if ((questionType == 1) && (maximum - minimum == 1)) {
						bonus = 1.5;
					} else if (questionType == 5) {
						bonus = 3;
					} else {
						bonus = 1;
					}
					
					scoreAdd = (int)(Math.round((100 / ((timeSpent * timeSpent) + 0.1)) * bonus));
					score += scoreAdd;
					difficulty += ((100 / ((timeSpent * timeSpent) + 0.1)));
					questionNum++;
				
					score = (Math.round(score));
					if (bonus != 1) {
						System.out.print(ANSI_lPINK);
						printRoll("Bonus: x" + bonus, 10);
						System.out.println(ANSI_lPURPLE);
					}
					if (scoreAdd < 0) {
						System.out.print(ANSI_lPURPLE);
						printRoll("Score: ", 10);
						System.out.print(ANSI_RESET);
						printRoll("" + score, 10);
						System.out.print(ANSI_lPURPLE);
						printRoll(" (" + scoreAdd + ")", 10);
						System.out.println(ANSI_RESET);
					} else {
						System.out.print(ANSI_lPURPLE);
						printRoll("Score: ", 10);
						System.out.print(ANSI_RESET);
						printRoll("" + score, 10);
						System.out.print(ANSI_lPURPLE);
						printRoll(" (+" + scoreAdd + ")", 10);
						System.out.println(ANSI_RESET);
					}
					System.out.println("");
				
				} else {
					
					System.out.print(ANSI_lRED);
					printRoll("GAME OVER: Incorrect Answer!", 10);
					System.out.println(ANSI_RESET);
					System.out.print(ANSI_lPURPLE);
					printRoll("Final Score: ", 10);
					System.out.print(ANSI_RESET);
					printRoll("" + score, 10);
					gameActive = false;
					
				}
				
			}
			
		}
		
		input.close();

	}
	
	public static String generateQuestion(int questionType, double difficulty, int questionNum) throws InterruptedException {
		if (questionType == 1) {
			
			int upperBound = (int)(100 + difficulty);
			int lowerBound = 0 - upperBound;
			double range = 100 * Math.pow(2, ((0 - questionNum) / 20.0));		
			int finalRange = (int)(range * Math.random() + 1);
			int randomValue1 = (int)(Math.random() * (upperBound - lowerBound) - upperBound + 1);
			int randomValue2 = (int)(randomValue1 + finalRange);
			
			int minimum = Math.min(randomValue1, randomValue2);
			int maximum = Math.max(randomValue1, randomValue2);
			
			final String ANSI_RESET = "\u001B[0m";
		    final String ANSI_lGRAY = "\u001B[38;2;191;191;191m";
		    final String ANSI_lSKY = "\u001B[38;2;128;191;255m";
		    final String ANSI_lPURPLE = "\u001B[38;2;191;128;255m";
			
			// debugging
			// System.out.println(ANSI_lSKY + "[UB: " + (int)(upperBound) + "], [Range: " + (int)(range) + "]" + ANSI_RESET);
			
		    System.out.print(ANSI_RESET);
		    printRoll("#" + questionNum + ") ", 10);
		    System.out.print(ANSI_lGRAY);
		    printRoll("Enter a number where ", 10);
		    System.out.print(ANSI_lPURPLE);
		    printRoll("" + minimum, 10);
		    System.out.print(ANSI_RESET);
		    printRoll(" < x < ", 10);
		    System.out.print(ANSI_lPURPLE);
		    printRoll("" + maximum, 10);
		    System.out.print(ANSI_RESET);
		    printRoll(": ", 10);
		    System.out.print(ANSI_lSKY);
			
			String output = minimum + " " + maximum;
			
			return output;
			
		} else if (questionType == 2) {
			
			int upperBound = (int)(100 + difficulty);
			int lowerBound = 0 - upperBound;
			double range = 100 * Math.pow(2, ((0 - questionNum) / 20.0));		
			int finalRange = (int)(range * Math.random() + 1);
			int randomValue1 = (int)(Math.random() * (upperBound - lowerBound) - upperBound + 1);
			int randomValue2 = (int)(randomValue1 + finalRange);
			
			int minimum = Math.min(randomValue1, randomValue2);
			int maximum = Math.max(randomValue1, randomValue2);
			
			final String ANSI_RESET = "\u001B[0m";
		    final String ANSI_lGRAY = "\u001B[38;2;191;191;191m";
		    final String ANSI_lSKY = "\u001B[38;2;128;191;255m";
		    final String ANSI_lPURPLE = "\u001B[38;2;191;128;255m";
			
			// debugging
			// System.out.println(ANSI_lSKY + "[UB: " + (int)(upperBound) + "], [Range: " + (int)(range) + "]" + ANSI_RESET);
			
		    System.out.print(ANSI_RESET);
		    printRoll("#" + questionNum + ") ", 10);
		    System.out.print(ANSI_lGRAY);
		    printRoll("Enter a number where ", 10);
		    System.out.print(ANSI_lPURPLE);
		    printRoll("" + minimum, 10);
		    System.out.print(ANSI_RESET);
		    printRoll(" > x < ", 10);
		    System.out.print(ANSI_lPURPLE);
		    printRoll("" + maximum, 10);
		    System.out.print(ANSI_RESET);
		    printRoll(": ", 10);
		    System.out.print(ANSI_lSKY);
			
			String output = minimum + " " + maximum;
			
			return output;
			
		} else if (questionType == 3) {
			
			int upperBound = (int)(100 + difficulty);
			int lowerBound = 0 - upperBound;
			double range = 100 * Math.pow(2, ((0 - questionNum) / 20.0));		
			int finalRange = (int)(range * Math.random() + 1);
			int randomValue1 = (int)(Math.random() * (upperBound - lowerBound) - upperBound + 1);
			int randomValue2 = (int)(randomValue1 + finalRange);
			
			int minimum = Math.min(randomValue1, randomValue2);
			int maximum = Math.max(randomValue1, randomValue2);
			
			final String ANSI_RESET = "\u001B[0m";
		    final String ANSI_lGRAY = "\u001B[38;2;191;191;191m";
		    final String ANSI_lSKY = "\u001B[38;2;128;191;255m";
		    final String ANSI_lPURPLE = "\u001B[38;2;191;128;255m";
			
			// debugging
			// System.out.println(ANSI_lSKY + "[UB: " + (int)(upperBound) + "], [Range: " + (int)(range) + "]" + ANSI_RESET);
			
		    System.out.print(ANSI_RESET);
		    printRoll("#" + questionNum + ") ", 10);
		    System.out.print(ANSI_lGRAY);
		    printRoll("Enter a number where ", 10);
		    System.out.print(ANSI_lPURPLE);
		    printRoll("" + minimum, 10);
		    System.out.print(ANSI_RESET);
		    printRoll(" < x > ", 10);
		    System.out.print(ANSI_lPURPLE);
		    printRoll("" + maximum, 10);
		    System.out.print(ANSI_RESET);
		    printRoll(": ", 10);
		    System.out.print(ANSI_lSKY);
			
			String output = minimum + " " + maximum;
			
			return output;
			
		} else if (questionType == 4) {
			
			int upperBound = (int)(100 + difficulty);
			int lowerBound = 0 - upperBound;
			double range = 100 * Math.pow(2, ((0 - questionNum) / 20.0));		
			int finalRange = (int)(range * Math.random() + 1);
			int randomValue1 = (int)(Math.random() * (upperBound - lowerBound) - upperBound + 1);
			int randomValue2 = (int)(randomValue1 + finalRange);
			
			int minimum = Math.min(randomValue1, randomValue2);
			int maximum = Math.max(randomValue1, randomValue2);
			
			final String ANSI_RESET = "\u001B[0m";
		    final String ANSI_lGRAY = "\u001B[38;2;191;191;191m";
		    final String ANSI_lSKY = "\u001B[38;2;128;191;255m";
		    final String ANSI_lPURPLE = "\u001B[38;2;191;128;255m";
			
			// debugging
			// System.out.println(ANSI_lSKY + "[UB: " + (int)(upperBound) + "], [Range: " + (int)(range) + "]" + ANSI_RESET);
			
		    System.out.print(ANSI_RESET);
		    printRoll("#" + questionNum + ") ", 10);
		    System.out.print(ANSI_lGRAY);
		    printRoll("Enter a number where ", 10);
		    System.out.print(ANSI_lPURPLE);
		    printRoll("" + maximum, 10);
		    System.out.print(ANSI_RESET);
		    printRoll(" > x > ", 10);
		    System.out.print(ANSI_lPURPLE);
		    printRoll("" + minimum, 10);
		    System.out.print(ANSI_RESET);
		    printRoll(": ", 10);
		    System.out.print(ANSI_lSKY);
			
			String output = maximum + " " + minimum;
			
			return output;
			
		} else if (questionType == 5) {
			
			int upperBound = (int)(100 + difficulty);
			int lowerBound = 0 - upperBound;
			double randomValue1 = Math.random() * (upperBound - lowerBound) - upperBound + 1;
			double randomValue2 = randomValue1 + (Math.random() * (Math.ceil(randomValue1) - 0.001 - randomValue1)) + 0.001;
			
			double minimum = (int)(Math.min(randomValue1, randomValue2) * 1000) / 1000.0;
			double maximum = (int)(Math.max(randomValue1, randomValue2) * 1000) / 1000.0;
			
			final String ANSI_RESET = "\u001B[0m";
		    final String ANSI_lGRAY = "\u001B[38;2;191;191;191m";
		    final String ANSI_lSKY = "\u001B[38;2;128;191;255m";
		    final String ANSI_lPURPLE = "\u001B[38;2;191;128;255m";
			
			// debugging
			// System.out.println(ANSI_lSKY + "[UB: " + (int)(upperBound) + "], [Range: " + (int)(range) + "]" + ANSI_RESET);
			
		    System.out.print(ANSI_RESET);
		    printRoll("#" + questionNum + ") ", 10);
		    System.out.print(ANSI_lGRAY);
		    printRoll("Enter a number where ", 10);
		    System.out.print(ANSI_lPURPLE);
		    printRoll("" + minimum, 10);
		    System.out.print(ANSI_RESET);
		    printRoll(" < x < ", 10);
		    System.out.print(ANSI_lPURPLE);
		    printRoll("" + maximum, 10);
		    System.out.print(ANSI_RESET);
		    printRoll(": ", 10);
		    System.out.print(ANSI_lSKY);
			
			String output = minimum + " " + maximum;
			
			return output;
			
		} else if (questionType == 6) {
			
			int upperBound = (int)(100 + difficulty);
			int lowerBound = 0 - upperBound;
			int offset = (int)Math.round((Math.random() * 0.1 + 0.1) * upperBound);
			int addOrSubtract = (int)(Math.random() * 2);
			double range = 100 * Math.pow(2, ((0 - questionNum) / 20.0));		
			int finalRange = (int)(range * Math.random() + 1);
			int randomValue1 = (int)(Math.random() * (upperBound - lowerBound) - upperBound + 1);
			int randomValue2 = (int)(randomValue1 + finalRange);
			
			int minimum = Math.min(randomValue1, randomValue2);
			int maximum = Math.max(randomValue1, randomValue2);
			
			final String ANSI_RESET = "\u001B[0m";
		    final String ANSI_lGRAY = "\u001B[38;2;191;191;191m";
		    final String ANSI_lSKY = "\u001B[38;2;128;191;255m";
		    final String ANSI_lPURPLE = "\u001B[38;2;191;128;255m";
			
			// debugging
			// System.out.println(ANSI_lSKY + "[UB: " + (int)(upperBound) + "], [Range: " + (int)(range) + "]" + ANSI_RESET);
			
		    System.out.print(ANSI_RESET);
		    printRoll("#" + questionNum + ") ", 10);
		    System.out.print(ANSI_lGRAY);
		    printRoll("Enter a number where ", 10);
		    System.out.print(ANSI_lPURPLE);
		    printRoll("" + minimum, 10);
		    System.out.print(ANSI_RESET);
		    if (addOrSubtract == 0) { printRoll(" < (x + " + offset + ") < ", 10); } else {
		    	printRoll(" < (x - " + offset + ") < ", 10); }
		    System.out.print(ANSI_lPURPLE);
		    printRoll("" + maximum, 10);
		    System.out.print(ANSI_RESET);
		    printRoll(": ", 10);
		    System.out.print(ANSI_lSKY);
			
		    String output;
		    if (addOrSubtract == 0) { output = (minimum - offset) + " " + (maximum - offset); } else {
		    	output = (minimum + offset) + " " + (maximum + offset); }
			
			return output;
			
		} else if (questionType == 7) {
			return "";
		} else {
			return "";
		}
	}
	
	public static boolean checkAnswer(int questionType, double answer, double minimum, double maximum) {
		if (questionType == 1) {
			
			if ((minimum < answer) && (answer < maximum)) {
				return true;
			} else {
				return false;
			}
			
		} else if (questionType == 2) {

			if (answer < minimum) {
				return true;
			} else {
				return false;
			}
			
		} else if (questionType == 3) {

			if (answer > maximum) {
				return true;
			} else {
				return false;
			}
			
		} else if (questionType == 4) {
			
			if ((maximum < answer) && (answer < minimum)) {
				return true;
			} else {
				return false;
			}
			
		} else if (questionType == 5) {
			if ((minimum < answer) && (answer < maximum)) {
				return true;
			} else {
				return false;
			}
		} else if (questionType == 6) {
			if ((minimum < answer) && (answer < maximum)) {
				return true;
			} else {
				return false;
			}
		} else if (questionType == 7) {
			return false;
		} else {
			return false;
		}
	}
	
	public static void printRoll(String str, int delayMilliseconds) throws InterruptedException {
		int a = 0;
		for (int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(a));
			a++;
			Thread.sleep(delayMilliseconds);
		}

	}

}
