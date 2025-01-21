package calgaryFlamesTracker.Players;

/**
 * The Goalies and Skaters will be a child class of this Player class
 */


public abstract class Player {


    private String playerName;

    private int gamesPlayed;

    private int jerseyNumber;

    /**
     * Each Player share a name, jersey number and games played.
     * @param playerName The player's name
     * @param jerseyNumber The player's jersey number
     * @param gamesPlayed The player's games played
     */
    protected Player(String playerName, int jerseyNumber, int gamesPlayed) {
        this.playerName = playerName;
        this.jerseyNumber = jerseyNumber;
        this.gamesPlayed = gamesPlayed;
    }

    //Getter functions
    public String getPlayerName() {
        return this.playerName;
    }


    public int getJerseyNumber() {
        return this.jerseyNumber;
    }


    public int getGamesPlayed() {
        return this.gamesPlayed;
    }

    //Setter functions
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

}
