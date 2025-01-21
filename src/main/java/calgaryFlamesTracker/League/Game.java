package calgaryFlamesTracker.League;

import calgaryFlamesTracker.Players.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Game simulations have certain properties,
 * this class helps guide the game simulations
 */
public class Game {
    private static final int MAX_NUMBER_OF_ASSISTS = 2;
    private Teams homeTeam;
    private Teams awayTeam;
    private boolean isCalgaryHome;
    private int[] shots;
    private int[] goals;
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * A new game to play/simulate
     * @param homeTeam Home team
     * @param awayTeam Away team
     */
    public Game(Teams homeTeam, Teams awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.shots = new int[]{0,0};
        this.goals = new int[]{0,0};

        if (homeTeam.getShortName().equals("CGY")) {
            this.isCalgaryHome = true;
        } else {
            this.isCalgaryHome = false;
        }

        System.out.println("The " + homeTeam.getTeamName() + " are playing the " + awayTeam.getTeamName() + " at the " + homeTeam.getArenaName());

    }

    /**
     * Gets user input for the total number of shots for each team.
     * @return An integer array with the amount of shots for each team. Index 0 is the home team shots, index 1 is the
     * away team shots
     */
    public int[] getGameShots() {
        System.out.println("Enter in the total shots for the " + homeTeam.getTeamName());
        int homeShots = scanner.nextInt();
        shots[0] = homeShots;
        System.out.println("Enter in the total shots for the " + awayTeam.getTeamName());
        int awayShots = scanner.nextInt();
        shots[1] = awayShots;
        return shots;
    }

    /**
     * Gets the final score of the game
     * @return An integer array with the score for each team. Index 0 is the score for the home team, index 1 is the
     * score for the away team
     */
    public int[] getGameGoals() {
        System.out.println("How many goals did the " + homeTeam.getTeamName() + " score?");
        int homeScore = scanner.nextInt();
        goals[0] = homeScore;
        System.out.println("How many goals did the " + awayTeam.getTeamName() + " score?");
        int awayScore = scanner.nextInt();
        goals[1] = awayScore;
        return goals;

    }

    /**
     * Determines if the home team wins
     * @return True if the home team wins, false otherwise.
     */
    public boolean didHomeWin() {
        return goals[0] > goals[1];
    }


    /**
     * Asks the user for who scored in the game
     * @return An arrayList of players jersey #s who scored in the game
     */
    public ArrayList<Integer> askWhoScored() {
        ArrayList<Integer> listToReturn = new ArrayList<>();
        if (goals[0] != 0 && isCalgaryHome) {
            for (int i = 0; i < goals[0]; i++) {
                System.out.println("Enter in the jersey number for the Flames player who scored: ");
                int jerseyChoice = scanner.nextInt();
                listToReturn.add(jerseyChoice);

            }

        } else if (goals[1] != 0 && !isCalgaryHome) {
            for (int i = 0; i < goals[1]; i++) {
                System.out.println("Enter in the jersey number for the Flames player who scored: ");
                int jerseyChoice = scanner.nextInt();
                listToReturn.add(jerseyChoice);

            }

        }
        return listToReturn;
    }

    /**
     * Asks the user for who assisted in the game
     * @return An arrayList of players jersey #s who assisted in the game
     */
    public ArrayList<Integer> askWhoAssisted() {
        ArrayList<Integer> listToReturn = new ArrayList<>();
        int calgaryGoals = 0;
        if (isCalgaryHome) {
            calgaryGoals = goals[0];
        } else {
            calgaryGoals = goals[1];
        }

        if (calgaryGoals != 0) {
            for (int i = 0; i < calgaryGoals; i++) {

                System.out.println("How many Flame's players got an assist for goal #" + (i + 1) + "? 0, 1 or 2?");
                int howManyAssists = scanner.nextInt();

                if (howManyAssists == MAX_NUMBER_OF_ASSISTS) {

                    for (int j = 0; j < howManyAssists; j++) {
                        System.out.println("Enter the jersey number for the Flames player who got the assist: ");

                        int jerseyAssistNumber2 = scanner.nextInt();
                        listToReturn.add(jerseyAssistNumber2);
                    }

                } else if (howManyAssists == 1) {

                    System.out.println("Enter the jersey number for the Flames player who got the assist: ");
                    Scanner userWhoAssisted = new Scanner(System.in);
                    int jerseyAssistNumber = userWhoAssisted.nextInt();
                    listToReturn.add(jerseyAssistNumber);

                } else {
                    System.out.println("No assists on this goal.");
                }
            }
        }
        return listToReturn;
    }

    /**
     * Asks the user for who did not play the game
     * @return An arrayList of players jersey #s who played the game
     */
    public ArrayList<Integer> askWhoDidNotPlay() {
        ArrayList<Integer> playerNumbers;

        if (isCalgaryHome) {
            playerNumbers = new ArrayList<>(homeTeam.getPlayerNumbers());
        } else {
            playerNumbers = new ArrayList<>(awayTeam.getPlayerNumbers());
        }


        String userInput = null;
        System.out.println("Were there any player(s) that did not play during this game? Y/N");
        Scanner inputScanner = new Scanner(System.in);
        char notPlayed = scanner.next().charAt(0);
        if (notPlayed == 'Y') {
            while (userInput != ""){
                System.out.println("Enter the player number(s) that did not play one by one, or Enter to stop inputting:");
                userInput = inputScanner.nextLine();
                if (userInput != "") {
                    playerNumbers.remove(Integer.valueOf(userInput));
                    //System.out.println(playerNumbers.toString());
                }
            }
            return playerNumbers;
        } else {
            return playerNumbers;

        }
    }

    /**
     * Prints out the final game stats
     * @return Nothing, just prints the final game stats
     */
    public void gameString(ArrayList<Integer> scorers, boolean cgyWin) {
        int calgaryScore = 0;
        int opponentScore = 0;
        int calgaryShots = 0;
        int opponentShots = 0;

        if (isCalgaryHome) {
            calgaryScore = goals[0];
            opponentScore = goals[1];
            calgaryShots = shots[0];
            opponentShots = shots[1];

        } else {
            calgaryScore = goals[1];
            opponentScore = goals[0];
            calgaryShots = shots[1];
            opponentShots = shots[0];

        }

        System.out.println("----------------------------------------- Game Results -----------------------------------------");
        System.out.printf("%38s %12s %23s %n", homeTeam.getTeamName(), " vs ", awayTeam.getTeamName());
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %15s %19s %16s %n", "Score: ", calgaryScore, " - ", opponentScore);
        System.out.printf("%-15s %15s %19s %16s %n", "Shots: ", calgaryShots, " - ", opponentShots);
        System.out.println();
        System.out.println("Flames goals scored by: ");
        if (isCalgaryHome) {
            for (int jerseyNumber : scorers) {
                System.out.println(homeTeam.getPlayer(jerseyNumber).toString());
            }
        } else {
            for (int jerseyNumber : scorers) {
                System.out.println(awayTeam.getPlayer(jerseyNumber).toString());
            }
        }
        if (cgyWin) {
            System.out.println("Calgary wins!");
        } else {
            System.out.println("Calgary did not win :(");
        }

    }

    /**
     * Asks the user who the goalie was that played the game.
     * @return An integer of the goalie number that played the game.
     */
    public int askWhoWasGoalie() {

        System.out.println("Which goalie played? 80 for Dan Vladar. 25 for Jacob Markstrom");
        Scanner askWhichGoalie = new Scanner(System.in);
        int jerseyNumber = askWhichGoalie.nextInt();


        return jerseyNumber;

    }








}