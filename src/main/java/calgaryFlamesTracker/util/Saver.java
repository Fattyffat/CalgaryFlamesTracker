package calgaryFlamesTracker.util;

import calgaryFlamesTracker.League.Teams;
import calgaryFlamesTracker.Players.Goalie;
import calgaryFlamesTracker.Players.Skater;

import java.io.*;

/**
 * A utility class to assist with saving to a file.
 */
public class Saver {
    /**
     * Saves a roster to a file.
     * @param roster A roster to save
     * @param file The file to save to
     */
    public static void saveFile(Teams roster, File file) {

        try {
            PrintWriter writer = new PrintWriter(file);

            //First 6 lines are total team stats.
            writer.write(Integer.toString(roster.getTotalTeamGoals()));
            writer.println();
            writer.write(Integer.toString(roster.getTotalTeamGamesPlayed()));
            writer.println();
            writer.write(Integer.toString(roster.getTotalAwayGames()));
            writer.println();
            writer.write(Integer.toString(roster.getTotalHomeGames()));
            writer.println();
            writer.write(Integer.toString(roster.getTotalGamesWon()));
            writer.println();
            writer.write(Integer.toString(roster.getTotalGamesLost()));
            writer.println();

            for (int key : roster.getPlayerNumbers()) {
                writer.write(key + ",");
                writer.write(roster.getPlayer(key).getPlayerName() + ",");
                if (roster.getPlayer(key) instanceof Goalie) {
                    writer.write(((Goalie) roster.getPlayer(key)).getSaves()+ ",");
                    writer.write(((Goalie) roster.getPlayer(key)).getShotsOnGoal()+ ",");
                    writer.write(((Goalie) roster.getPlayer(key)).getShutouts()+ ",");
                    //For some reason, PrintWriter does not like writing this integer in specific,
                    //so I have to convert it to a string.
                    writer.write(Integer.toString(roster.getPlayer(key).getGamesPlayed()));
                    writer.println();
                } else {
                    writer.write(((Skater) roster.getPlayer(key)).getGoals()+ ",");
                    writer.write(((Skater) roster.getPlayer(key)).getAssists() + ",");
                    writer.write(Integer.toString(roster.getPlayer(key).getGamesPlayed()));
                    writer.println();
                }
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Could not save file!");
        }

    }


}
