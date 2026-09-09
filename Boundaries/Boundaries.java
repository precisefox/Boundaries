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
		int typingDelay = 100;
		
		System.out.println(ANSI_lPURPLE + "****************************************************************************************************");
		Thread.sleep(typingDelay);
		System.out.println("");
		Thread.sleep(typingDelay);
		System.out.println(ANSI_RESET + "Hello! This is my first ever Java game: " + ANSI_lPURPLE);
		Thread.sleep(typingDelay);
		System.out.println("");
		Thread.sleep(typingDelay);
		System.out.println("██████╗  ██████╗ ██╗   ██╗███╗   ██╗██████╗  █████╗ ██████╗ ██╗███████╗███████╗");
		Thread.sleep(typingDelay);
		System.out.println("██╔══██╗██╔═══██╗██║   ██║████╗  ██║██╔══██╗██╔══██╗██╔══██╗██║██╔════╝██╔════╝");
		Thread.sleep(typingDelay);
		System.out.println("██████╔╝██║   ██║██║   ██║██╔██╗ ██║██║  ██║███████║██████╔╝██║█████╗  ███████╗");
		Thread.sleep(typingDelay);
		System.out.println("██╔══██╗██║   ██║██║   ██║██║╚██╗██║██║  ██║██╔══██║██╔══██╗██║██╔══╝  ╚════██║");
		Thread.sleep(typingDelay);
		System.out.println("██████╔╝╚██████╔╝╚██████╔╝██║ ╚████║██████╔╝██║  ██║██║  ██║██║███████╗███████║");
		Thread.sleep(typingDelay);
		System.out.println("╚═════╝  ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝╚══════╝╚══════╝");
		Thread.sleep(typingDelay);
		System.out.println(ANSI_GRAY + "(v1.1)\n");
		Thread.sleep(typingDelay);
		System.out.println(ANSI_RESET + "Here's how it works:");
		Thread.sleep(typingDelay);
		System.out.println(ANSI_lPURPLE + "1. " + ANSI_lGRAY + "The game will repeatedly ask you to enter a number between two other numbers.");
		Thread.sleep(typingDelay);
		System.out.println(ANSI_lPURPLE + "2. " + ANSI_lGRAY + "The game will time you to see how long it takes you to answer.");
		Thread.sleep(typingDelay);
		System.out.println(ANSI_lPURPLE + "3. " + ANSI_lGRAY + "The faster you answer, the more points you get!");
		Thread.sleep(typingDelay);
		System.out.println(ANSI_lPURPLE + "4. " + ANSI_lGRAY + "Some questions will try to trick you, so be careful!");
		Thread.sleep(typingDelay);
		System.out.println(ANSI_lPURPLE + "5. " + ANSI_lGRAY + "If you take more than 5 seconds to answer or answer incorrectly, you lose!");
		Thread.sleep(typingDelay);
		System.out.print(ANSI_RESET + "\nType 1 to begin. " + ANSI_lSKY);
		double answer = input.nextInt();
		if (answer == 1) { 
			System.out.println("");
			System.out.println(ANSI_lPURPLE + "****************************************************************************************************");
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
		 * 2. Tricky a > x < b (both are maximums)
		 * 3. Tricky a < x > b (both are minimums)
		 * 4. Tricky b > x > a (reverse)
		 */
		
		while (gameActive == true) {
			
			if (difficulty >= 1000) {
				trickQuestionChance = 50;
			} else {
				trickQuestionChance = 0.049 * difficulty + 1;
			}
			
			if ((Math.random() * 100) < trickQuestionChance) {
				questionType = (int)(Math.random() * 3) + 2;
			} else {
				questionType = 1;
			}
			
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
				
				System.out.println(ANSI_lRED + "GAME OVER: Out of time! (" + timeSpent + "s)" + ANSI_RESET);
				System.out.println(ANSI_lPURPLE + "Final Score: " + ANSI_RESET + score);
				gameActive = false;
				
			} else {
			
				if (checkAnswer(questionType, answer, minimum, maximum) == true) {
				
					System.out.println(ANSI_GRAY + "Correct." + " (" + timeSpent + "s)" + ANSI_RESET);
				
					if ((questionType >= 2) && (questionType <= 4)) {
						bonus = 5;
					} else if ((questionType == 1) && (maximum - minimum == 1)) {
						bonus = 2;
					} else {
						bonus = 1;
					}
					
					scoreAdd = (int)(Math.round((100 / ((timeSpent * timeSpent) + 0.1)) * bonus));
					score += scoreAdd;
					difficulty += ((100 / ((timeSpent * timeSpent) + 0.1)));
					questionNum++;
				
					score = (Math.round(score));
					if (bonus != 1) {
						System.out.println(ANSI_lPINK + "Bonus: x" + bonus + ANSI_lPURPLE);
					}
					if (scoreAdd < 0) {
						System.out.println(
								ANSI_lPURPLE + "Score: " 
								+ ANSI_RESET + score 
								+ ANSI_lPURPLE + " (" + scoreAdd + ")" 
								+ ANSI_RESET);
					} else {
						System.out.println(
								ANSI_lPURPLE + "Score: " 
								+ ANSI_RESET + score 
								+ ANSI_lPURPLE + " (+" + scoreAdd + ")" 
								+ ANSI_RESET);
					}
					System.out.println("");
				
				} else {
					
					System.out.println(ANSI_lRED + "GAME OVER: Incorrect Answer!" + ANSI_RESET);
					System.out.println(ANSI_lPURPLE + "Final Score: " + ANSI_RESET + score);
					gameActive = false;
					
				}
				
			}
			
		}
		
		input.close();

	}
	
	public static String generateQuestion(int questionType, double difficulty, int questionNum) {
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
			
			System.out.print(
					ANSI_RESET + "#" + questionNum + ") " 
					+ ANSI_lGRAY + "Enter a number where " 
					+ ANSI_lPURPLE 
					+ minimum 
					+ ANSI_RESET + " < x < " 
					+ ANSI_lPURPLE + maximum 
					+ ANSI_RESET + ": " 
					+ ANSI_lSKY);
			
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
			
			System.out.print(
					ANSI_RESET + "#" + questionNum + ") " 
					+ ANSI_lGRAY + "Enter a number where " 
					+ ANSI_lPURPLE 
					+ minimum 
					+ ANSI_RESET + " > x < " 
					+ ANSI_lPURPLE + maximum 
					+ ANSI_RESET + ": " 
					+ ANSI_lSKY);
			
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
			
			System.out.print(
					ANSI_RESET + "#" + questionNum + ") " 
					+ ANSI_lGRAY + "Enter a number where " 
					+ ANSI_lPURPLE 
					+ minimum 
					+ ANSI_RESET + " < x > " 
					+ ANSI_lPURPLE + maximum 
					+ ANSI_RESET + ": " 
					+ ANSI_lSKY);
			
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
			
			System.out.print(
					ANSI_RESET + "#" + questionNum + ") " 
					+ ANSI_lGRAY + "Enter a number where " 
					+ ANSI_lPURPLE 
					+ minimum 
					+ ANSI_RESET + " > x > " 
					+ ANSI_lPURPLE + maximum 
					+ ANSI_RESET + ": " 
					+ ANSI_lSKY);
			
			String output = maximum + " " + minimum;
			
			return output;
			
		} else if (questionType == 5) {
			return "";
		} else if (questionType == 6) {
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
			return false;
		} else if (questionType == 6) {
			return false;
		} else {
			return false;
		}
	}

}
