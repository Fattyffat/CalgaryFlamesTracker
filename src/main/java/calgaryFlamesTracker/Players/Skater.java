package calgaryFlamesTracker.Players;

public class Skater extends Player {

    private int goals;

    private int assists;

    /**
     * A Skater is a child class of Player. Skaters uniquely have Goals and Assists.
     * @param name The name of the skater
     * @param jerseyNumber The jersey number of the skater
     * @param gamesPlayed The amount of games played for the skater
     * @param goals The amount of goals for the skater
     * @param assists The amount of assists for the skater
     */
    public Skater(String name, int jerseyNumber, int gamesPlayed, int goals, int assists) {
        super(name, jerseyNumber, gamesPlayed);
        this.goals = goals;
        this.assists = assists;
    }


    /**
     * Get the goals of a specific Skater
     * @return
     */
    public int getGoals() {

        return goals;
    }

    /**
     * Get the assists of a specific Skater
     * @return
     */
    public int getAssists() {

        return assists;
    }


    /**
     * Add an amount of goals to a Skater
     */
    public void setGoals(int amount) {

        this.goals = amount;

    }


    /**
     * Add an amount of assists to a Skater
     */
    public void setAssists(int amount) {

        this.assists = amount;

    }

    /**
     * Allows the player to be printed
     * @return A string with the player number and player name
     */
    @Override
    public String toString() {
        return ("#" + getJerseyNumber() + " " + getPlayerName());
    }

}
