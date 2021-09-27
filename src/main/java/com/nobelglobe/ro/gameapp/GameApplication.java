package com.nobelglobe.ro.gameapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.Scanner;

import static com.nobelglobe.ro.gameapp.Configuration.*;
import static com.nobelglobe.ro.gameapp.Configuration.MAX_OF_NUMBER;

@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);

		System.out.println("Hi. Welcome to Rock,Paper, Scissors Game . ");

		Integer selectedObject;
		do {
			explain();
			selectedObject = new Scanner(System.in).nextInt();

			if (FINISH_GAME.equals( selectedObject)) {
				System.out.println("FINISH GAME , GOOD BYE :)!");
				break;
			}
			if (PERCENT_OF_WIN_GAME.equals(selectedObject)) {
				System.out.println("***************************\n" +
						  "* Percent Of Win     : % " + percentOfWin * 100 + "\n"
						+ "* Counter Of Win     : " + counterOfWin + "\n"
						+ "* Counter Of Defeat  : " + counterOfDefeat + "* \n"
						+ "***************************"
						+"\n");
				continue;
			}

			procces(selectedObject);
		} while (true);
	}

	private static void procces(Integer youSelected) {
		String humanSelected = humanResult(youSelected);
		String computerSelected = computerResult();
		System.out.println("You Selected       :  " + humanSelected + "\n"
				+ "Computer Selected  :  " + computerSelected + "\n");
		calculatePercent(humanSelected, computerSelected);
	}

	private static void calculatePercent(String humanSelected, String computerSelected) {
		if (humanSelected .equals( computerSelected)) {
			System.out.println(" Perfect You Win :) \n" );
			counterOfWin++;
		} else {
			System.out.println(" Try Again .You can do it.\n" );
			counterOfDefeat++;
		}
		percentOfWin = Double.valueOf(counterOfWin) / (Double.valueOf(counterOfDefeat) + Double.valueOf(counterOfWin));
	}


	private static int randomNumber() {
		return new Random().nextInt(MAX_OF_NUMBER) + 1;
	}

	private static String convert(Integer number) {
		String result;
		switch (number) {
			case 1:  result = "Rock";     break;
			case 2:  result = "Paper";    break;
			case 3:  result = "Scissors"; break;
			default: result = "unknown";  break;
		}
		return result;
	}

	private static String humanResult(Integer scannedNumber) {
		return convert(scannedNumber);
	}

	private static String computerResult() {
		int randomNumber = randomNumber();
		return convert(randomNumber);
	}

	private static void explain() {
		System.out.println("Please select choose one.\n"
				+ " Rock                 (1)\n"
				+ " Paper                (2)\n"
				+ " Scissors             (3)\n"
				+ " Percent of Win       (5)\n"
				+ " Exit Game            (0)\n"
				+ "-------------------------");

	}
}
