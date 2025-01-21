package calgaryFlamesTracker.util;

import calgaryFlamesTracker.League.League;
import calgaryFlamesTracker.League.Teams;
import calgaryFlamesTracker.Players.Goalie;
import calgaryFlamesTracker.Players.Skater;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to assist reading in roster file
 * @author
 * @version 1.0
 */
public class Reader {

    /**
     * Loads a team (Calgary) with a player roster.
     * @param fileRoster File with the path of the roster.txt file
     * @return A full team with players/stats.
     */
    public static Teams loadTeam(File fileRoster) {
        int row;
        int column;
        ArrayList<String[]> lines = new ArrayList<>();

        int totalGoals = 0;
        int totalGamesPlayed = 0;
        int totalAwayGames = 0;
        int totalHomeGames = 0;
        int gamesLost = 0;
        int gamesWon = 0;

        try {
            FileReader file_reader = new FileReader(fileRoster);
            BufferedReader buffered_reader = new BufferedReader(file_reader);
            String fileLine;

            //First 6 lines are total team stats.
            for (int i = 0; i < 6; i++) {
                fileLine = buffered_reader.readLine();
                if (i == 0) {
                    totalGoals = Integer.parseInt(fileLine);
                } else if (i == 1) {
                    totalGamesPlayed = Integer.parseInt(fileLine);
                } else if (i == 2) {
                    totalAwayGames = Integer.parseInt(fileLine);
                } else if (i == 3) {
                    totalHomeGames = Integer.parseInt(fileLine);
                } else if (i == 4) {
                    gamesWon = Integer.parseInt(fileLine);
                } else if (i == 5) {
                    gamesLost = Integer.parseInt(fileLine);
                }

            }

            while ((fileLine = buffered_reader.readLine()) != null) {

                String[] splitLine = fileLine.split(",");
                lines.add(splitLine);


            }

            file_reader.close();
            buffered_reader.close();


        } catch (FileNotFoundException e) {
            System.err.println("File not found!");

        } catch (IOException e) {
            System.err.println("There was a problem with reading the file!");

        }


        //These 2 lines from here:
        //https://stackoverflow.com/questions/33034833/converting-csv-file-into-2d-array
        String[][] finalArray = new String[lines.size()][0];
        lines.toArray(finalArray);
        //System.out.println(Arrays.deepToString(finalArray));


        //System.out.println("column is" + column);
        Teams calgaryFlames = new Teams("CGY", "Calgary Flames", "Scotiabank Saddledome");
        calgaryFlames.setTotalTeamGoals(totalGoals);
        calgaryFlames.setTotalTeamGamesPlayed(totalGamesPlayed);
        calgaryFlames.setTotalAwayGames(totalAwayGames);
        calgaryFlames.setTotalHomeGames(totalHomeGames);
        calgaryFlames.setTotalGamesWon(gamesWon);
        calgaryFlames.setTotalGamesLost(gamesLost);

        for (int i = 0; i < finalArray.length; i++) {


            // This is a skater (not a goalie)!
            if (finalArray[i].length == 5) {

                int playerGoals = Integer.parseInt(finalArray[i][2]);
                int playerAssists = Integer.parseInt(finalArray[i][3]);
                int playerGamesPlayed = Integer.parseInt(finalArray[i][4]);
                int jerseyNumber = Integer.parseInt(finalArray[i][0]);


                Skater player = new Skater(finalArray[i][1], jerseyNumber, playerGamesPlayed, playerGoals, playerAssists);
                calgaryFlames.addPlayer(player);



                // This is a goalie, not a skater!

            } else if (finalArray[i].length == 6) {

                int goalieNumber = Integer.parseInt(finalArray[i][0]);
                int goalieSaves = Integer.parseInt(finalArray[i][2]);
                int goalieShots = Integer.parseInt(finalArray[i][3]);
                int shutouts = Integer.parseInt(finalArray[i][4]);
                int goalieGamesPlayed = Integer.parseInt(finalArray[i][5]);

                Goalie goalie = new Goalie(finalArray[i][1], goalieNumber, goalieGamesPlayed, goalieSaves, goalieShots, shutouts);

                calgaryFlames.addPlayer(goalie);


            }


        }


        //System.out.println(createdWorld);
        //System.out.println("createdWorld ");
        return calgaryFlames;


    }

    /**
     * Loads a team (opponent teams) without any players.
     * @param opponentTeams File with the path of the opponentTeams.txt file
     * @return An ArrayList containing Teams with only a short name and full name
     */
    public static ArrayList<Teams> readOpponentTeams(File opponentTeams) {
        ArrayList<Teams> listToReturn = new ArrayList<>();

        try {
            FileReader file_reader = new FileReader(opponentTeams);
            BufferedReader buffered_reader = new BufferedReader(file_reader);

            String fileLine = buffered_reader.readLine();

            while (fileLine != null) {

                String[] splitLine = fileLine.split(",");
                //Index 0 is the short name, index 1 is the full name.
                Teams teamToAdd = new Teams(splitLine[0], splitLine[1], splitLine[2]);

                listToReturn.add(teamToAdd);
                fileLine = buffered_reader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
        catch (IOException e) {
            System.err.println("Error reading opponents!");
            System.err.println("Format for csv should be <shortName>,<teamName>,<arenaName>");

        }

        return listToReturn;


    }

}

