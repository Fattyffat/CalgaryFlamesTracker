package calgaryFlamesTracker.util;


import calgaryFlamesTracker.League.League;

import java.util.Scanner;

/**
 * Menu for the options that the user will have
 */

public final class Menu {


    private static final Scanner scanner = new Scanner(System.in);


    public static void userMenu() {
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Please select an option below:");
        System.out.println();
        System.out.println("1 to view the team roster");
        System.out.println("2 to simulate a hockey game");
        System.out.println("3 to show the top 5 leaders in goals");
        System.out.println("4 to show the top 5 leaders in assists");
        System.out.println("5 to show the roster + current stats");
        System.out.println("6 to get an individual player's stats");
        System.out.println("7 to edit an individual player's stats");
        System.out.println("8 for team stats as a whole");
        System.out.println("9 to view a list of all the teams in this league");
        System.out.println("10 to load a previously saved roster");
        System.out.println("11 to save your data");
        System.out.println("0 to exit the program");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------");

    }


    public static int chooseMenuOption() {

        int menuOption = scanner.nextInt();

        return menuOption;
    }


    public static int chooseWhichPlayersStats() {

        System.out.println("Enter in the jersey # for the player you want");
        int playerStat = scanner.nextInt();

        return playerStat;

    }

    public static int chooseWhichPlayerStatsToEdit() {

        System.out.println("1. Goals");
        System.out.println("2. Assists");
        System.out.println("3. Games Played");

        int optionToEdit = scanner.nextInt();

        return optionToEdit;

    }

    public static int chooseWhichGoalieStatsToEdit() {

        System.out.println("1. Saves");
        System.out.println("2. Shots on Goal");
        System.out.println("3. Shutouts");
        System.out.println("4. Games Played");

        int optionToEdit = scanner.nextInt();

        return optionToEdit;

    }

    public static int howMuchGoals() {
        System.out.println("Enter in the new amount of goals");

        int newAmountOfGoals = scanner.nextInt();

        return newAmountOfGoals;

    }

    public static int howMuchAssists() {
        System.out.println("Enter in the new amount of assists");

        int newAmountOfAssists = scanner.nextInt();

        return newAmountOfAssists;

    }

    public static int howMuchGamesPlayed() {
        System.out.println("Enter in the new amount of games played");

        int newAmountOfGamesPlayed = scanner.nextInt();

        return newAmountOfGamesPlayed;

    }

    public static int howMuchSaves() {
        System.out.println("Enter in the new amount of saves");

        int newAmountOfSaves = scanner.nextInt();

        return newAmountOfSaves;

    }

    public static int howMuchSOG() {
        System.out.println("Enter in the new amount of shots on goal");

        int newAmountOfSOG = scanner.nextInt();

        return newAmountOfSOG;

    }

    public static int howMuchShutouts() {
        System.out.println("Enter in the new amount of shutouts");

        int newAmountOfShutouts = scanner.nextInt();

        return newAmountOfShutouts;

    }



}
