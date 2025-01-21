package calgaryFlamesTracker.League;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static calgaryFlamesTracker.util.Reader.readOpponentTeams;


/**
 * A League hold players other than Calgary.
 */
public class League {

    private ArrayList<Teams> teams;

    /**
     * Constructor for new league.
     * @param LeagueName The name of the league.
     */
    public League(String LeagueName) {
        teams = new ArrayList<>();

    }

    /**
     * Adds a team object to the league
     * @param team A team as a Teams object.
     */
    public void addTeam(Teams team) {
        teams.add(team);
    }

    /**
     * Gets a team from the league.
     * @param team A team as a Teams object.
     * @return A team if it exists in the league, null otherwise.
     */
    public Teams getTeam(Teams team) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).equals(team)) {
                return teams.get(i);
            }
        }
        return null;
    }

    /**
     * Makes the teams in the league able to be printed
     * @return A string of all the teams in the league.
     */
    @Override
    public String toString() {
        return teams.toString().replaceAll("\\[","").replaceAll(", ","\n").replaceAll("\\]","");
    }

    /**
     * Converts the league to a hashmap, for ease of access. The key is the short name of the team, the value is the
     * team as a Teams object.
     * @return A hashmap of the league.
     */
    public HashMap<String, Teams> toHashMap() {
        int i = 0;
        HashMap<String, Teams> hmToReturn = new HashMap<>();
        for(Teams opponent : teams) {
            hmToReturn.put(opponent.getShortName(), teams.get(i));
            i++;
        }

        return hmToReturn;

    }

    public ArrayList<Teams> getTeamsList() {
        return teams;
    }

    public void loadOpponentTeams() {
        File opponentTeams = new File("src/main/java/calgaryFlamesTracker/League/opponentTeams.txt");
        ArrayList<Teams> opponentTeamsList = readOpponentTeams(opponentTeams);
        for (int i = 0; i < opponentTeamsList.size(); i++) {
            addTeam(opponentTeamsList.get(i));
        }

    }

    public void removeTeam(Teams teamToRemove) {
        teams.removeIf(team -> team.equals(teamToRemove));

    }

}
