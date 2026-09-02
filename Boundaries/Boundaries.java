// Project made by precisefox on GitHub

import java.util.*;

public class Boundaries {

	public static void main(String[] args) {
		
		// defaults
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_GRAY = "\u001B[38;2;128;128;128m";
	    
		// RGB!
	    final String ANSI_lGRAY = "\u001B[38;2;191;191;191m";
		final String ANSI_lRED = "\u001B[38;2;255;128;128m";
	    final String ANSI_lSKY = "\u001B[38;2;128;191;255m";
	    final String ANSI_lPURPLE = "\u001B[38;2;191;128;255m";
	
		Scanner input = new Scanner(System.in);
		boolean gameActive = true;
		double correctAnswers = 0;
		int score = 0;
		int scoreAdd = 0;
		double timeLimit = 5;
		double difficulty = 0;
		
		System.out.println(ANSI_lPURPLE + "****************************************************************************************************");
		System.out.println("");
		System.out.println(ANSI_RESET + "Hello! This is my first ever Java game: " + ANSI_lPURPLE);
		System.out.println("");
		System.out.println("██████╗  ██████╗ ██╗   ██╗███╗   ██╗██████╗  █████╗ ██████╗ ██╗███████╗███████╗");
		System.out.println("██╔══██╗██╔═══██╗██║   ██║████╗  ██║██╔══██╗██╔══██╗██╔══██╗██║██╔════╝██╔════╝");
		System.out.println("██████╔╝██║   ██║██║   ██║██╔██╗ ██║██║  ██║███████║██████╔╝██║█████╗  ███████╗");
		System.out.println("██╔══██╗██║   ██║██║   ██║██║╚██╗██║██║  ██║██╔══██║██╔══██╗██║██╔══╝  ╚════██║");
		System.out.println("██████╔╝╚██████╔╝╚██████╔╝██║ ╚████║██████╔╝██║  ██║██║  ██║██║███████╗███████║");
		System.out.println("╚═════╝  ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝╚══════╝╚══════╝");
		System.out.println("");
		System.out.println(ANSI_RESET + "Here's how it works:");
		System.out.println(ANSI_lPURPLE + "1. " + ANSI_lGRAY + "The game will repeatedly ask you to enter a number between two other numbers.");
		System.out.println(ANSI_lPURPLE + "2. " + ANSI_lGRAY + "The game will time you to see how long it takes you to answer.");
		System.out.println(ANSI_lPURPLE + "3. " + ANSI_lGRAY + "The faster you answer, the more points you get!");
		System.out.println(ANSI_lPURPLE + "4. " + ANSI_lGRAY + "Some questions will try to trick you, so be careful!");
		System.out.println(ANSI_lPURPLE + "5. " + ANSI_lGRAY + "If you take more than 5 seconds to answer or answer incorrectly, you lose!");
		System.out.print(ANSI_RESET + "\nType 1 to begin. " + ANSI_lSKY);
		double answer = input.nextInt();
		if (answer == 1) { 
			System.out.println("");
			System.out.println(ANSI_lPURPLE + "****************************************************************************************************");
			System.out.println(""); 
		} else { 
			System.out.println(0 / 0);
		}
		
		while (gameActive == true) {
			
			double upperBound = (int)(100 + difficulty);
			double lowerBound = 0 - upperBound;
			double range = 100 * Math.pow(2, ((0 - correctAnswers) / 20));		
			double finalRange = (int)(range * Math.random() + 1);
			int randomValue1 = (int)(Math.random() * (upperBound - lowerBound) - upperBound + 1);
			int randomValue2 = (int)(randomValue1 + finalRange);
			
			int minimum = Math.min(randomValue1, randomValue2);
			int maximum = Math.max(randomValue1, randomValue2);
			
			// debugging
			// System.out.println(ANSI_lSKY + "[UB: " + (int)(upperBound) + "], [Range: " + (int)(range) + "]" + ANSI_RESET);
			
			System.out.print(ANSI_RESET + "#" + (int)(correctAnswers + 1) + ") " + ANSI_lGRAY + "Enter a number where " + ANSI_lPURPLE 
					+ minimum + ANSI_RESET + " < x < " + ANSI_lPURPLE + maximum + ANSI_RESET + ": " + ANSI_lSKY);
			long startTime = System.currentTimeMillis();
			answer = input.nextDouble();
			long endTime = System.currentTimeMillis();
			double timeSpent = (endTime - startTime) / 1000.0;
			
			if (timeSpent > timeLimit) {
				
				System.out.println(ANSI_lRED + "GAME OVER: Out of time! (" + timeSpent + "s)" + ANSI_RESET);
				System.out.println(ANSI_lPURPLE + "Final Score: " + ANSI_RESET + (int)(score));
				gameActive = false;
				
			} else {
			
				if ((minimum < answer) && (answer < maximum)) {
				
					System.out.println(ANSI_GRAY + "Correct." + " (" + timeSpent + "s)" + ANSI_RESET);
				
					scoreAdd = (int)(Math.round((100 / ((timeSpent * timeSpent) + 0.1))));
					score += scoreAdd;
					difficulty += ((100 / ((timeSpent * timeSpent) + 0.1)));
					correctAnswers++;
				
					score = (Math.round(score));
					if ((int)(scoreAdd) < 0) {
						System.out.println(ANSI_lPURPLE + "Score: " + ANSI_RESET + (int)(score) + ANSI_lPURPLE + " (" + (int)(scoreAdd) + ")" + ANSI_RESET);
					} else {
						System.out.println(ANSI_lPURPLE + "Score: " + ANSI_RESET + (int)(score) + ANSI_lPURPLE + " (+" + (int)(scoreAdd) + ")" + ANSI_RESET);
					}
					System.out.println("");
				
				} else {
					
					System.out.println(ANSI_lRED + "GAME OVER: Incorrect Answer!" + ANSI_RESET);
					System.out.println(ANSI_lPURPLE + "Final Score: " + ANSI_RESET + (int)(score));
					gameActive = false;
					
				}
				
			}
			
		}
		
		input.close();

	}

}
