package calgaryFlamesTracker.Players;

public class Goalie extends Player {

    private int saves;

    private int shotsOnGoal;

    private int shutouts;


    /**
     * A Goalie is a child class of Player. Goalies uniquely have saves, shots on goal, and shutouts.
     * @param name The name of the goalie
     * @param jerseyNumber The jersey number of the goalie
     * @param gamesPlayed The amount of games played for the goalie
     * @param saves The amount of saves for the goalie
     * @param shotsOnGoal The amount of shots on goal for the goalie
     * @param shutouts The amount of shutputs for the goalie
     */
    public Goalie(String name, int jerseyNumber, int gamesPlayed, int saves, int shotsOnGoal, int shutouts) {
        super(name, jerseyNumber, gamesPlayed);
        this.saves = saves;
        this.shotsOnGoal = shotsOnGoal;
        this.shutouts = shutouts;

    }


    /**
     * Adds shots to a goalie
     * @param shotsFaced The number to add by
     */
    public void addShots(int shotsFaced) {
        this.shotsOnGoal = this.shotsOnGoal + shotsFaced;
    }

    /**
     * Adds shots saved to a goalie
     * @param shotsSaved The number to add by
     */
    public void addSaves(int shotsSaved) {
        this.saves = this.saves + shotsSaved;
    }

    //Getter functions
    public int getSaves() {
        return saves;
    }

    public int getShotsOnGoal() {
        return shotsOnGoal;
    }


    public int getShutouts() {
        return shutouts;
    }

    public void addShutout() {
        this.shutouts = this.shutouts + 1;
    }

    //Setter functions
    public void setSaves(int saves) {
        this.saves = saves;
    }

    public void setShotsOnGoal(int shotsOnGoal) {
        this.shotsOnGoal = shotsOnGoal;
    }

    public void setShutouts(int shutouts) {
        this.shutouts = shutouts;
    }

    /**
     * Allows goalies to be printed
     * @return A string with the jersey number and goalie name.
     */
    @Override
    public String toString() {
        return "#" + getJerseyNumber() + " " + getPlayerName();
    }
}
