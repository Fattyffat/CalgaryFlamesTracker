package calgaryFlamesTracker.util;

import calgaryFlamesTracker.League.Teams;
import calgaryFlamesTracker.Players.Goalie;
import calgaryFlamesTracker.Players.Skater;
import calgaryFlamesTracker.util.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HelloApplicationTest {

    Teams calgaryTeamToTest = new Teams("CGY", "Calgary Flames", "Scotiabank Saddledome");

    @BeforeEach

    void setup() {


        //Set up all the Skater and Goalies with default stats.
        Skater rasmusA = new Skater("Rasmus Andersson", 4, 0,0,0);
        Skater chrisT = new Skater("Chris Tanev", 8, 0,0,0);
        Skater tylerT = new Skater("Tyler Toffoli", 73, 0,0,0);
        Skater mikaelB = new Skater("Mikael Backlund", 11, 0,0,0);
        Skater ericG = new Skater("Eric Gudbranson", 44, 0,0,0);
        Skater johnnyG = new Skater("Johnny Gaudreau", 13, 0,0,0);
        Skater bradR = new Skater("Brad Richerdson", 15, 0,0,0);
        Skater nikitaZ = new Skater("Nikita Zadorov", 16, 0,0,0);
        Goalie danV = new Goalie("Dan Vladar", 80, 0,0,0, 0);
        Skater milanL = new Skater("Milan Lucic", 17, 0,0,0);
        Skater matthewT = new Skater("Matthew Tkachuk", 19, 0,0,0);
        Skater blakeC = new Skater("Blake Coleman", 20, 0,0,0);
        Skater trevorL = new Skater("Trevor Lewis", 22, 0,0,0);
        Skater seanM = new Skater("Sean Monahan", 23, 0,0,0);
        Skater noahH = new Skater("Noah Hanifin", 55, 0,0,0);
        Skater andrewM = new Skater("Andrew Mangiapane", 88, 0,0,0);
        Goalie jacobM = new Goalie("Jacob Markstrom", 25, 0,0,0,0 );
        Skater markS = new Skater("Mark Stone", 26, 0,0,0);
        Skater oliverK = new Skater("Oliver Kylington", 58, 0,0,0);
        Skater eliasL = new Skater("Elias Lindholm", 28, 0,0,0);
        Skater dillonD = new Skater("Dillon Dube", 29, 0,0,0);
        Skater adamR = new Skater("Adam Ruzicka", 63, 0,0,0);
        Skater calleJ = new Skater("Calle Jankrok", 91, 0,0,0);

        //Add the Players to the team that we will be performing tests on
        calgaryTeamToTest.addPlayer(rasmusA);
        calgaryTeamToTest.addPlayer(chrisT);
        calgaryTeamToTest.addPlayer(tylerT);
        calgaryTeamToTest.addPlayer(mikaelB);
        calgaryTeamToTest.addPlayer(ericG);
        calgaryTeamToTest.addPlayer(johnnyG);
        calgaryTeamToTest.addPlayer(bradR);
        calgaryTeamToTest.addPlayer(nikitaZ);
        calgaryTeamToTest.addPlayer(danV);
        calgaryTeamToTest.addPlayer(milanL);
        calgaryTeamToTest.addPlayer(matthewT);
        calgaryTeamToTest.addPlayer(blakeC);
        calgaryTeamToTest.addPlayer(trevorL);
        calgaryTeamToTest.addPlayer(seanM);
        calgaryTeamToTest.addPlayer(noahH);
        calgaryTeamToTest.addPlayer(andrewM);
        calgaryTeamToTest.addPlayer(jacobM);
        calgaryTeamToTest.addPlayer(markS);
        calgaryTeamToTest.addPlayer(oliverK);
        calgaryTeamToTest.addPlayer(eliasL);
        calgaryTeamToTest.addPlayer(dillonD);
        calgaryTeamToTest.addPlayer(adamR);
        calgaryTeamToTest.addPlayer(calleJ);

    }


    @Test
    /**
     * Test the reader function. We will do this by comparing the .rosterStats of the Reader team with our calgaryTeamToTest
     * @param: None
     * @return: 2 strings, contents should be equal to each other
     */
    void testReaderFunction() {

        assertEquals(calgaryTeamToTest.rosterWithStats(), Reader.loadTeam(new File("src/main/java/calgaryFlamesTracker/defaultRoster/rosterTest")).rosterWithStats());

    }


    @Test
    /**
     * Test the rosterWithStats function. Basically the exact same test as above.
     * @param: None
     * @return: 2 strings, contents should be equal to each other
     */
    void testRosterWithStats() {

        assertEquals(calgaryTeamToTest.rosterWithStats(), Reader.loadTeam(new File("src/main/java/calgaryFlamesTracker/defaultRoster/rosterTest")).rosterWithStats());

    }


    @Test
    /**
     * Test the goalieWithStats function.
     * @param: None
     * @return: 2 strings, should be identical to each other
     */
    void testGoalieStats() {

        assertEquals(calgaryTeamToTest.goalieWithStats(), Reader.loadTeam(new File("src/main/java/calgaryFlamesTracker/defaultRoster/rosterTest")).goalieWithStats());

    }


    @Test
    /**
     * Test the individual getPlayerStats function
     * @param: None
     * @return: Johnny's stats in 2 strings. Contents should be identical to each other
     */
    void getPlayerStat() {

        //Give some stats to Johnny
        ((Skater) calgaryTeamToTest.getPlayer(13)).setGoals(5);
        ((Skater) calgaryTeamToTest.getPlayer(13)).setAssists(2);
        calgaryTeamToTest.getPlayer(13).setGamesPlayed(3);

        //Store his stats in string format. We will compare this string with the getPlayerStats function.
        String getPlayerStats = "";

        getPlayerStats = getPlayerStats + String.format("Stats for #13 Johnny Gaudreau:" + "\n");
        getPlayerStats = getPlayerStats + "-----------------------------------------------------------------------" + "\n";
        getPlayerStats = getPlayerStats + "        Goals                    Assists                     Games Played" + "\n";
        getPlayerStats = getPlayerStats + "-----------------------------------------------------------------------" + "\n";
        getPlayerStats = getPlayerStats + String.format("%11s %25s %29s", 5, 2, 3 + "\n");


        assertEquals(calgaryTeamToTest.getPlayerStats(13), getPlayerStats);


    }


    @Test
    /**
     * Test the individual getGoalieStats function
     * @param: None
     * @return: Marky's stats in 2 strings. Contents should be identical to each other
     */
    void getGoalieStat() {

        //Set some stats for Markstrom
        ((Goalie) calgaryTeamToTest.getPlayer(25)).setSaves(120);
        ((Goalie) calgaryTeamToTest.getPlayer(25)).setShutouts(10);
        ((Goalie) calgaryTeamToTest.getPlayer(25)).setShotsOnGoal(150);
        ((Goalie) calgaryTeamToTest.getPlayer(25)).setSaves(120);
        ((Goalie) calgaryTeamToTest.getPlayer(25)).setGamesPlayed(5);


        //Store his stats in string format. We will compare this string with the other string of our function we are testing
        String goalieStats = "";

        goalieStats = goalieStats + "------------------------------------------------- Goalies --------------------------------------------------" + "\n";
        goalieStats = goalieStats + "       Player            Saves         Shots on Goal         Shutouts         Save %         Games Played" + "\n";
        goalieStats = goalieStats + "------------------------------------------------------------------------------------------------------------" + "\n";


        goalieStats = goalieStats + String.format("%-26s %1s %17s %19s %16s %15s %n", "25" + ". " + "Jacob Markstrom", "120", "150", "10", "0.800", "5");


        assertEquals(goalieStats, calgaryTeamToTest.getPlayerStats(25));

    }


    @Test
    /**
     * Get a list of the top 5 players (ordered) of who has the most goals
     * @param: None
     * @return: Should return true if the 2 ArrayLists are equal to each other
     */
    void getGoalLeader() {

        //Set Johnny to 4 goals, he should now be the goal leader, we'll set the goals for 4 other players also
        ((Skater) calgaryTeamToTest.getPlayer(13)).setGoals(5);

        ((Skater) calgaryTeamToTest.getPlayer(28)).setGoals(4);

        ((Skater) calgaryTeamToTest.getPlayer(19)).setGoals(3);

        ((Skater) calgaryTeamToTest.getPlayer(23)).setGoals(2);

        ((Skater) calgaryTeamToTest.getPlayer(73)).setGoals(1);

        //Add them to the ArrayList, we'll compare this to the getGoalLeader function
        ArrayList<Skater> flamesLeader = new ArrayList<>();
        flamesLeader.add((Skater) calgaryTeamToTest.getPlayer(13));
        flamesLeader.add((Skater) calgaryTeamToTest.getPlayer(28));
        flamesLeader.add((Skater) calgaryTeamToTest.getPlayer(19));
        flamesLeader.add((Skater) calgaryTeamToTest.getPlayer(23));
        flamesLeader.add((Skater) calgaryTeamToTest.getPlayer(73));

        assertEquals(flamesLeader, calgaryTeamToTest.getGoalLeader());


    }


    @Test
    /**
     * Get a list of the top 5 players (ordered) of who has the most assists
     * @param: None
     * @return: Should return true if the 2 ArrayLists are equal to each other
     */
    void getAssistLeader() {

        //Set Johnny to 4 goals, he should now be the goal leader, we'll set the goals for 4 other players also
        ((Skater) calgaryTeamToTest.getPlayer(19)).setAssists(15);

        ((Skater) calgaryTeamToTest.getPlayer(28)).setAssists(10);

        ((Skater) calgaryTeamToTest.getPlayer(11)).setAssists(7);

        ((Skater) calgaryTeamToTest.getPlayer(29)).setAssists(5);

        ((Skater) calgaryTeamToTest.getPlayer(58)).setAssists(1);

        //Add them to the ArrayList, we'll compare this to the getAssistLeader function
        ArrayList<Skater> flamesLeader = new ArrayList<>();
        flamesLeader.add((Skater) calgaryTeamToTest.getPlayer(19));
        flamesLeader.add((Skater) calgaryTeamToTest.getPlayer(28));
        flamesLeader.add((Skater) calgaryTeamToTest.getPlayer(11));
        flamesLeader.add((Skater) calgaryTeamToTest.getPlayer(29));
        flamesLeader.add((Skater) calgaryTeamToTest.getPlayer(58));

        assertEquals(flamesLeader, calgaryTeamToTest.getAssistLeader());


    }


}