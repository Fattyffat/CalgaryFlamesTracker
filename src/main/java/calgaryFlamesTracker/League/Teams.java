package calgaryFlamesTracker.League;

import calgaryFlamesTracker.Comparators.AssistComparator;
import calgaryFlamesTracker.Comparators.GoalComparator;
import calgaryFlamesTracker.Players.Goalie;
import calgaryFlamesTracker.Players.Player;
import calgaryFlamesTracker.Players.Skater;
import calgaryFlamesTracker.util.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class Teams implements Cloneable{

    private int totalTeamGoals;
    private int totalTeamGamesPlayed;
    private int totalAwayGames;
    private int totalHomeGames;
    private int totalGamesWon;
    private int totalGamesLost;
    private String shortName;
    private String teamName;
    private String arenaName;

    //Store the players into a Hashmap. Their jersey # will be the key, and their value will be the Player object.
    private HashMap<Integer, Player> playerName;

    /**
     * Constructor for any team to be created
     * @param shortName Short hand version of the team name. Example: Calgary Flames short name is CGY
     * @param teamName Full team name
     * @param arenaName Arena name for the team
     */
    public Teams(String shortName, String teamName, String arenaName) {
        this.shortName = shortName;
        this.teamName = teamName;
        this.totalTeamGoals = 0;
        this.totalTeamGamesPlayed = 0;
        this.totalHomeGames = 0;
        this.totalAwayGames = 0;
        this.arenaName = arenaName;

        //Create new Hashmap to be able to add players into it
        this.playerName = new HashMap<>();

    }

    /**
     * Adds a player to a team
     * @param player A player as a Player object
     */
    public void addPlayer(Player player) {
        playerName.put(player.getJerseyNumber(), player);
        //System.out.println(playerName.toString());

    }

    /**
     * Get a player based on their jersey number
     * @param playersNumber: The players jersey number
     * @return: The requested player as an Object. Use other functions to access its individual stats.
     */
    public Player getPlayer(int playersNumber) {
        return playerName.get(playersNumber);

    }

    /**
     * Get a player's stats based on their jersey number
     * @param jerseyNumber: The player's jersey number
     * @return: A string with the player's stats
     */
    public String getPlayerStats(int jerseyNumber) {

        String getPlayerStats = "";


        if (playerName.get(jerseyNumber) instanceof Skater) {

            getPlayerStats = getPlayerStats + String.format("Stats for #" + playerName.get(jerseyNumber).getJerseyNumber() + " " + playerName.get(jerseyNumber).getPlayerName() + ":" + "\n");
            getPlayerStats = getPlayerStats + "-----------------------------------------------------------------------" + "\n";
            getPlayerStats = getPlayerStats + "        Goals                    Assists                     Games Played" + "\n";
            getPlayerStats = getPlayerStats + "-----------------------------------------------------------------------" + "\n";
            getPlayerStats = getPlayerStats + String.format("%11s %25s %29s", ((Skater) playerName.get(jerseyNumber)).getGoals(), ((Skater) playerName.get(jerseyNumber)).getAssists(), playerName.get(jerseyNumber).getGamesPlayed() + "\n");

        } else if (playerName.get(jerseyNumber) instanceof Goalie) {

            double savePercentage = 0.00;

            getPlayerStats = getPlayerStats + "------------------------------------------------- Goalies --------------------------------------------------" + "\n";
            getPlayerStats = getPlayerStats + "       Player            Saves         Shots on Goal         Shutouts         Save %         Games Played" + "\n";
            getPlayerStats = getPlayerStats + "------------------------------------------------------------------------------------------------------------" + "\n";

            if (((Goalie) playerName.get(jerseyNumber)).getShotsOnGoal() == 0) {
                        savePercentage = 0.00;
            } else {
                        savePercentage = (double) (((Goalie) playerName.get(jerseyNumber)).getSaves()) / (double) (((Goalie) playerName.get(jerseyNumber)).getShotsOnGoal());
            }
                    //System.out.println(((Skater) playerName.get(key)).getGoals());

            getPlayerStats = getPlayerStats + String.format("%-26s %1s %17s %19s %16s %15s %n", jerseyNumber + ". " + playerName.get(jerseyNumber).getPlayerName(), ((Goalie) playerName.get(jerseyNumber)).getSaves(), ((Goalie) playerName.get(jerseyNumber)).getShotsOnGoal(), ((Goalie) playerName.get(jerseyNumber)).getShutouts(), String.format("%.3f", savePercentage), playerName.get(jerseyNumber).getGamesPlayed());



        }

        return getPlayerStats;


    }

    /**
     * Gets a team's arena name
     * @return: The team's arena name as a string
     */
    public String getArenaName() {
        return arenaName;
    }

    /**
     * Edits a player's stats, players can be goalies and skaters.
     * @param jerseyNumber: The player's jersey number
     */
    public void editPlayerStats(int jerseyNumber) {
        //If the player is a skater, we can edit only 3 stats.
        if (playerName.get(jerseyNumber) instanceof Skater) {
            System.out.println("Which stats do you want to edit for: " + playerName.get(jerseyNumber).getPlayerName());

            int optionToEdit = Menu.chooseWhichPlayerStatsToEdit();

            if (optionToEdit == 1) {

                int amountOfGoals = Menu.howMuchGoals();
                System.out.println("Edited the amount of goals for " + playerName.get(jerseyNumber) + " from " + ((Skater) playerName.get(jerseyNumber)).getGoals() + " to " + amountOfGoals);
                ((Skater) playerName.get(jerseyNumber)).setGoals(amountOfGoals);


            } else if (optionToEdit == 2) {
                int amountOfAssists = Menu.howMuchAssists();
                System.out.println("Edited the amount of assists for " + playerName.get(jerseyNumber) + " from " + ((Skater) playerName.get(jerseyNumber)).getAssists() + " to " + amountOfAssists);
                ((Skater) playerName.get(jerseyNumber)).setAssists(amountOfAssists);


            } else if (optionToEdit == 3) {
                int amountOfGamesPlayed = Menu.howMuchGamesPlayed();
                System.out.println("Edited the amount of games played for " + playerName.get(jerseyNumber) + " from " + playerName.get(jerseyNumber).getGamesPlayed() + " to " + amountOfGamesPlayed);
                playerName.get(jerseyNumber).setGamesPlayed(amountOfGamesPlayed);

            } else {
                System.err.println("Option does not exist!");
            }


        }
        //Else if the player is a goalie, we can edit only 4 stats.
        else if (playerName.get(jerseyNumber) instanceof Goalie) {
            System.out.println("Which stats do you want to edit for: " + playerName.get(jerseyNumber).getPlayerName());

            int optionToEdit = Menu.chooseWhichGoalieStatsToEdit();

            if (optionToEdit == 1) {
                int amountOfSaves = Menu.howMuchSaves();
                System.out.println("Edited the amount of saves for " + playerName.get(jerseyNumber) + " from " + ((Goalie) playerName.get(jerseyNumber)).getSaves() + " to " + amountOfSaves);
                ((Goalie) playerName.get(jerseyNumber)).setSaves(amountOfSaves);

            } else if (optionToEdit == 2) {
                int amountOfSOG = Menu.howMuchSOG();
                System.out.println("Edited the amount of shots on goal for " + playerName.get(jerseyNumber) + " from " + ((Goalie) playerName.get(jerseyNumber)).getShotsOnGoal() + " to " + amountOfSOG);
                ((Goalie) playerName.get(jerseyNumber)).setShotsOnGoal(amountOfSOG);


            } else if (optionToEdit == 3) {
                int amountOfShutouts = Menu.howMuchShutouts();
                System.out.println("Edited the amount of shutouts for " + playerName.get(jerseyNumber) + " from " + ((Goalie) playerName.get(jerseyNumber)).getShutouts() + " to " + amountOfShutouts);
                ((Goalie) playerName.get(jerseyNumber)).setShutouts(amountOfShutouts);

            } else if (optionToEdit == 4) {
                int amountOfGamesPlayed = Menu.howMuchGamesPlayed();
                System.out.println("Edited the amount of games played for " + playerName.get(jerseyNumber) + " from " + playerName.get(jerseyNumber).getGamesPlayed() + " to " + amountOfGamesPlayed);
                playerName.get(jerseyNumber).setGamesPlayed(amountOfGamesPlayed);

            } else {
                System.err.println("Option does not exist!");
            }

        }
        else {
            System.err.println("Player does not exist!");
        }
    }

    /**
     * Runs a game simulation for a team.
     * @param league A league with other teams in it.
     * @param calgaryFlames The team the opponent is going to play (Calgary Flames)
     */
    public void runGameSimulation(League league, Teams calgaryFlames) {
        //Making a new hashmap for the opponent, for ease of access.
        Scanner sc = new Scanner(System.in);
        HashMap<String, Teams> opponentHM = league.toHashMap();
        int i = 0;

        System.out.println("Who should CGY play? Enter in the 3 letter code for the team name.");
        System.out.println("Example, enter in EDM to play the Edmonton Oilers.");
        System.out.println(league);

        String choice = sc.nextLine();

        if (opponentHM.containsKey(choice)) {
            System.out.println("Is CGY home or away? Enter H for home, or A for away. Invalid input assumes away.");
            char hora = sc.nextLine().charAt(0);

            if (hora == 'H') {
                totalHomeGames += 1;
                totalTeamGamesPlayed +=1;
                Game cgyHome = new Game(calgaryFlames, opponentHM.get(choice));

                int[] gameShots = cgyHome.getGameShots();
                int[] gameScore = cgyHome.getGameGoals();
                ArrayList<Integer> scorers = cgyHome.askWhoScored();
                ArrayList<Integer> assisters = cgyHome.askWhoAssisted();
                ArrayList<Integer> playersThatPlayed = cgyHome.askWhoDidNotPlay();
                int goalieJerseyNumber = cgyHome.askWhoWasGoalie();
                boolean cgyWin = cgyHome.didHomeWin();
                cgyHome.gameString(scorers, cgyWin);

                ((Goalie) playerName.get(goalieJerseyNumber)).addShots(gameShots[1]);
                ((Goalie) playerName.get(goalieJerseyNumber)).addSaves(gameShots[1]-gameScore[1]);

                //If the opponents score was 0, our goalie earned a shutout
                if (gameScore[1] == 0) {
                    ((Goalie) playerName.get(goalieJerseyNumber)).addShutout();
                }

                if (cgyWin) {
                    totalGamesWon +=1;
                } else {
                    totalGamesLost +=1;
                }

                totalTeamGoals += gameScore[0];
                setPlayerStatsAfterGame(scorers, assisters, playersThatPlayed, goalieJerseyNumber);

            } else if (hora == 'A') {
                totalAwayGames += 1;
                totalTeamGamesPlayed +=1;
                Game cgyAway = new Game(opponentHM.get(choice), calgaryFlames);
                int[] gameShots = cgyAway.getGameShots();
                int[] gameScore = cgyAway.getGameGoals();
                ArrayList<Integer> scorers = cgyAway.askWhoScored();
                ArrayList<Integer> assisters = cgyAway.askWhoAssisted();
                ArrayList<Integer> playersThatPlayed = cgyAway.askWhoDidNotPlay();
                int goalieJerseyNumber = cgyAway.askWhoWasGoalie();
                boolean cgyWin = !(cgyAway.didHomeWin());
                cgyAway.gameString(scorers, cgyWin);

                ((Goalie) playerName.get(goalieJerseyNumber)).addShots(gameShots[0]);
                ((Goalie) playerName.get(goalieJerseyNumber)).addSaves(gameShots[0]-gameScore[0]);

                //If the opponents score was 0, our goalie earned a shutout
                if (gameScore[0] == 0) {
                    ((Goalie) playerName.get(goalieJerseyNumber)).addShutout();
                }

                if (cgyWin) {
                    totalGamesWon +=1;
                } else {
                    totalGamesLost +=1;
                }

                totalTeamGoals += gameScore[1];
                setPlayerStatsAfterGame(scorers, assisters, playersThatPlayed, goalieJerseyNumber);

            } else {
                totalAwayGames += 1;
                totalTeamGamesPlayed +=1;
                System.out.println("Invalid input. Assuming CGY is away.");
                Game cgyAway = new Game(opponentHM.get(choice), calgaryFlames);
                int[] gameShots = cgyAway.getGameShots();
                int[] gameScore = cgyAway.getGameGoals();
                ArrayList<Integer> scorers = cgyAway.askWhoScored();
                ArrayList<Integer> assisters = cgyAway.askWhoAssisted();
                ArrayList<Integer> playersThatPlayed = cgyAway.askWhoDidNotPlay();
                int goalieJerseyNumber = cgyAway.askWhoWasGoalie();
                boolean cgyWin = !(cgyAway.didHomeWin());
                cgyAway.gameString(scorers, cgyWin);

                ((Goalie) playerName.get(goalieJerseyNumber)).addShots(gameShots[0]);
                ((Goalie) playerName.get(goalieJerseyNumber)).addSaves(gameShots[0]-gameScore[0]);

                //If the opponents score was 0, our goalie earned a shutout
                if (gameScore[0] == 0) {
                    ((Goalie) playerName.get(goalieJerseyNumber)).addShutout();
                }

                if (cgyWin) {
                    totalGamesWon +=1;
                } else {
                    totalGamesLost +=1;
                }
                totalTeamGoals += gameScore[1];
                setPlayerStatsAfterGame(scorers, assisters, playersThatPlayed, goalieJerseyNumber);
            }

        } else {
            System.err.println("Team does not exist! Simulation canceled!");
        }

    }

    /**
     * Get a player number set.
     * @return: A set of player numbers.
     */
    public Set<Integer> getPlayerNumbers() {
        return playerName.keySet();
    }

    /**
     * Prints a roster without stats
     * @return
     */
    public String rosterWithoutStats() {
        ArrayList<String> players = new ArrayList<>();

        for (Integer key: playerName.keySet()) {


            String typeOfPlayer = "";

            if (playerName.get(key) instanceof Skater) {

                typeOfPlayer = "Skater";

            } else {
                typeOfPlayer = "Goalie";
            }

            players.add("#" + key + "\t" + playerName.get(key).getPlayerName() + "\t\t" + typeOfPlayer);

        }

        //System.out.println(players);
        return (players.toString().replace("[", "").replace(", ","\n").replace("]",""));

    }

    /**
     * Gets a roster(Skaters) with stats
     * @return: A string with the roster with stats.
     */
    public String rosterWithStats() {

        String rosterWithStats = "";


        rosterWithStats = rosterWithStats + "------------------------------ Roster with Stats ------------------------" + "\n";
        rosterWithStats = rosterWithStats + "        Player             Goals            Assists         Games Played" + "\n";
        rosterWithStats = rosterWithStats + "-------------------------------------------------------------------------" + "\n";

        for (Integer key : playerName.keySet()) {

            if (playerName.get(key) instanceof Skater) {

                //System.out.println(((Skater) playerName.get(key)).getGoals());

                rosterWithStats = rosterWithStats + String.format("%-25s %4s %17s %19s %n", key + ". " + playerName.get(key).getPlayerName(), ((Skater) playerName.get(key)).getGoals(), ((Skater) playerName.get(key)).getAssists(), playerName.get(key).getGamesPlayed());


            }

        }

        return rosterWithStats;

    }

    /**
     * Gets a roster(Goalies) with stats
     * @return: A string with the roster with stats.
     */
    public String goalieWithStats() {

        String goalieStats = "";

        goalieStats = goalieStats + "------------------------------------------------- Goalies ----------------------------------------" + "\n";
        goalieStats = goalieStats + "       Player            Saves         Shots on Goal         Shutouts         Save %         Games Played" + "\n";
        goalieStats = goalieStats + "--------------------------------------------------------------------------------------------------" + "\n";

        for (Integer key : playerName.keySet()) {

            if (playerName.get(key) instanceof Goalie) {

                double savePercentage;

                if (((Goalie) playerName.get(key)).getShotsOnGoal() == 0) {
                    savePercentage = 0.00;
                } else {
                    savePercentage = (double) (((Goalie) playerName.get(key)).getSaves()) / (double) (((Goalie) playerName.get(key)).getShotsOnGoal());
                }



                //System.out.println(((Skater) playerName.get(key)).getGoals());

                goalieStats = goalieStats + String.format("%-26s %1s %17s %19s %16s %15s %n", key + ". " + playerName.get(key).getPlayerName(), ((Goalie) playerName.get(key)).getSaves(), ((Goalie) playerName.get(key)).getShotsOnGoal(), ((Goalie) playerName.get(key)).getShutouts(), String.format("%.3f", savePercentage), playerName.get(key).getGamesPlayed());


            }

        }

        return goalieStats;

    }

    /**
     * Gets team stats as a whole
     */
    public String returnTeamStats() {

        double winPercentage;
        if (totalGamesLost == 0) {
            winPercentage = 100;
        } else {
            winPercentage = ((double)totalGamesWon/(double)totalGamesLost)*100;
        }

        return ("Team stats as a whole:\n" +
                "Total goals: " + totalTeamGoals + "\n" +
                "Total games played: " + totalTeamGamesPlayed + "\n" +
                "Total away games: " + totalAwayGames + "\n" +
                "Total home games: " + totalHomeGames + "\n" +
                "Total games won: " + totalGamesWon + "\n" +
                "Total games lost: " + totalGamesLost + "\n" +
                "Win percentage: " + String.format("%.2f", winPercentage) + "%");

    }

    /**
     * Gets the top 5 goal leaders
     * @return: An ArrayList of the top 5 goal leaders, null if everyone has 0 goals.
     */
    public ArrayList<Skater> getGoalLeader() {
        ArrayList skaters = new ArrayList<>();
        ArrayList<Skater> listToReturn = new ArrayList<>();
        //Getting only skaters, as only skaters can be compared
        for (int i : getPlayerNumbers()) {
            if (getPlayer(i) instanceof Skater) {
                skaters.add(getPlayer(i));
            }
        }

        //Sorting by goals
        Collections.sort(skaters, new GoalComparator());

        //Adding the top 5 to the list to return
        for (int i = 0; i < 5; i++) {
            listToReturn.add((Skater) skaters.get(i));

        }
        if (listToReturn.get(0).getGoals() == 0) {
            return null;
        }

        return listToReturn;
    }


    /**
     * Gets the top 5 assist leaders
     * @return: An ArrayList of the top 5 assist leaders, null if everyone has 0 assists.
     */
    public ArrayList<Skater> getAssistLeader() {
        ArrayList skaters = new ArrayList<>();
        ArrayList<Skater> listToReturn = new ArrayList<>();
        //Getting only skaters, as only skaters can be compared
        for (int i : getPlayerNumbers()) {
            if (getPlayer(i) instanceof Skater) {
                skaters.add(getPlayer(i));
            }
        }

        //Sorting by assists
        Collections.sort(skaters, new AssistComparator());

        //System.out.println(skaters.toString());

        //Adding the top 5 to the list to return
        for (int i = 0; i < 5; i++) {
            listToReturn.add((Skater) skaters.get(i));

        }
        if (listToReturn.get(0).getAssists() == 0) {
            return null;
        }

        return listToReturn;
    }


    /**
     * Sets the players' stats after a game
     * @param scorers An arraylist of all the scorers
     * @param assisters An arraylist of all the assisters
     * @param playersThatPlayed An arraylist of all the players that played
     * @param goalieNumber The goalie number that played
     */
    public void setPlayerStatsAfterGame(ArrayList<Integer> scorers, ArrayList<Integer> assisters, ArrayList<Integer> playersThatPlayed, Integer goalieNumber) {
        //Removing the goalie that didn't play from the players that playedlist.
        int goalieThatDidNotPlay = 0;
        if (goalieNumber == 25) {
            goalieThatDidNotPlay = 80;
        } else {
            goalieThatDidNotPlay = 25;
        }


        for (int i = 0; i < playersThatPlayed.size(); i++) {
            if (goalieThatDidNotPlay == playersThatPlayed.get(i)) {
                playersThatPlayed.remove(i);
            }
        }

        for (int jerseyNumber : scorers) {
            if (scorers != null) {
                ((Skater)getPlayer(jerseyNumber)).setGoals(((Skater)getPlayer(jerseyNumber)).getGoals()+1);
            }
        }

        for (int jerseyNumber : assisters) {
            if (assisters != null) {
                ((Skater)getPlayer(jerseyNumber)).setAssists(((Skater)getPlayer(jerseyNumber)).getAssists()+1);
            }
        }

        for (int jerseyNumber : playersThatPlayed) {
            getPlayer(jerseyNumber).setGamesPlayed(getPlayer(jerseyNumber).getGamesPlayed()+1);
        }



    }


    /**
     * Allows the team to be printed
     * @return A string with the short name and the full name of the team.
     */
    @Override
    public String toString() {
        return this.shortName + " " + this.teamName;
    }

    /**
     * Allows teams to be compared. In this function, teams are compared by their short name, as no teams can have
     * the same short name.
     * @param obj Another team as an object
     * @return True if the teams are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (getClass() != obj.getClass()) {
                Teams other = (Teams) obj;
                return Objects.equals(other.shortName, this.shortName);
            }
        }
        return false;
    }

    //Setter functions
    public void setTotalTeamGoals(int totalTeamGoals) {
        this.totalTeamGoals = totalTeamGoals;
    }

    public void setTotalTeamGamesPlayed(int totalTeamGamesPlayed) {
        this.totalTeamGamesPlayed = totalTeamGamesPlayed;
    }

    public void setTotalAwayGames(int totalAwayGames) {
        this.totalAwayGames = totalAwayGames;
    }

    public void setTotalHomeGames(int totalHomeGames) {
        this.totalHomeGames = totalHomeGames;
    }

    public void setTotalGamesWon(int totalGamesWon) {
        this.totalGamesWon = totalGamesWon;
    }

    public void setTotalGamesLost(int totalGamesLost) {
        this.totalGamesLost = totalGamesLost;
    }

    //Getter Functions
    public int getTotalTeamGoals() {
        return totalTeamGoals;
    }

    public int getTotalTeamGamesPlayed() {
        return totalTeamGamesPlayed;
    }

    public int getTotalAwayGames() {
        return totalAwayGames;
    }

    public int getTotalHomeGames() {
        return totalHomeGames;
    }

    public int getTotalGamesWon() {
        return totalGamesWon;
    }

    public int getTotalGamesLost() {
        return totalGamesLost;
    }
    
    public String getShortName() {
        return this.shortName;
    }

    public String getTeamName() {
        return this.teamName;
    }

    /**
     * Checks if a team is empty
     * @return True if team is empty, false otherwise
     */
    public Boolean isEmpty() {
        if (playerName.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if there is a goalie in the team
     * @return True if team has a goalie, false otherwise
     */
    public Boolean goalieExists() {
        for (Integer i : getPlayerNumbers()) {
            if (getPlayer(i) instanceof Goalie) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is a player in the team
     * @return True if the team has a player, false otherwise.
     */
    public Boolean playersExists() {
        for (Integer i : getPlayerNumbers()) {
            if (getPlayer(i) instanceof Skater) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a player exists in a team
     * @param playerNumberToCheck The jersey number of the player to check
     * @return True if the player exists, false otherwise.
     */
    public Boolean playerExists(int playerNumberToCheck) {
        for (int i : getPlayerNumbers()) {
            if (i == playerNumberToCheck) {
                return true;
            }
        }
        return false;

    }

    /**
     * How many skaters are in the team?
     * @return The amount of players(Skaters!!!!) in a team
     */
    public int getTeamSize() {
        int counter = 0;
        for (int i : getPlayerNumbers()) {
            if (getPlayer(i) instanceof Skater) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Clears a team.
     */
    public void clear() {
        playerName.clear();
    }

}
