package com.cftp.calgaryflamestrackerproject;

import calgaryFlamesTracker.League.League;
import calgaryFlamesTracker.League.Teams;
import calgaryFlamesTracker.Players.Goalie;
import calgaryFlamesTracker.Players.Player;
import calgaryFlamesTracker.Players.Skater;
import calgaryFlamesTracker.util.Reader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static calgaryFlamesTracker.util.Saver.saveFile;
import static com.cftp.calgaryflamestrackerproject.GameController.*;
import static com.cftp.calgaryflamestrackerproject.HelloApplication.primaryStage;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;




public class HelloController {
    public static Stage stage2;
    public static Stage stage3;

    private Teams calgaryTeam = new Teams("CGY", "Calgary Flames", "Scotiabank Saddledome");
    private League NHL = new League("NHL");

    @FXML
    private TextField skaterAssists;

    @FXML
    private Button skaterButton;

    @FXML
    private TextField skaterGamesPlayed;

    @FXML
    private TextField skaterGoals;

    @FXML
    private TextField skaterJerseryNumber;

    @FXML
    private TextField skaterName;

    @FXML
    private TextArea teamStatsView;

    @FXML
    private Button goalieButton;

    @FXML
    private TextField goalieGamesPlayed;

    @FXML
    private TextField goalieJerseyNumber;

    @FXML
    private TextField goalieName;

    @FXML
    private TextField goalieSaved;

    @FXML
    private TextField goalieShotsFaced;

    @FXML
    private TextField goalieShutout;

    @FXML
    private Button viewRoster;

    @FXML
    private Button top5Goals;

    @FXML
    private Button top5Assists;

    @FXML
    private TextField jerseyNumberStatsToGet;

    @FXML
    private Label leftStatus;

    @FXML
    private Label rightStatus;

    @FXML
    private ComboBox<Teams> homeTeamChoice;

    @FXML
    private ComboBox<Teams> awayTeamChoice;

    @FXML
    private Button playGame;

    @FXML
    private MenuButton currentMusicPlaying;

    @FXML
    private RadioButton isGoalie;

    @FXML
    private RadioButton isPlayer;

    @FXML
    private TextField editAssists;

    @FXML
    private TextField editGamesPlayed;

    @FXML
    private TextField editGoals;

    @FXML
    private TextField editPlayerJerseyNumber;

    @FXML
    private Label goalsLabel;

    @FXML
    private Label assistsLabel;

    @FXML
    private Label gamesPlayedLabel;

    @FXML
    private TextField editSaves;

    @FXML
    private TextField editSOG;

    @FXML
    private TextField editShutouts;

    @FXML
    private Label savesLabel;

    @FXML
    private Label shotsOnGoalLabel;

    @FXML
    private Label shutoutsLabel;


    private static Boolean loadFromArgs = false;

    private static String teamName;

    private Clip clip;


    //Sound code from: https://stackoverflow.com/questions/42955509/how-to-play-a-simple-audio-file-java
    public void playSound(String fileName) {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fileName));
            clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            clip.start();

            // Do we want song to loop after it ends?
            //clip.loop(Clip.LOOP_CONTINUOUSLY);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stopClip() {


        if (clip != null) {
            clip.stop();
        }

    }



    public void loadArgs(String argumentString) {

        //this.loadFromArgs = argumentString;
        //System.out.println(this.loadFromArgs);

        //Switch this to be true so that the initializer can run a block of code
        loadFromArgs = true;

        //System.out.println(argumentFile.exists());

        //Assign teamName the name of the argument so we can use this in the initializer
        teamName = argumentString;

        //Clearing the box, as the calgaryTeam will be different once we load it


    }


    @FXML
    void initialize() {


        NHL.addTeam(calgaryTeam);
        NHL.loadOpponentTeams();

        homeTeamChoice.setItems(FXCollections.observableList(NHL.getTeamsList()));
        awayTeamChoice.setItems(FXCollections.observableList(NHL.getTeamsList()));
        leftStatus.setText("Please select an option.");
        rightStatus.setText("Welcome to the Calgary Flames Tracker!");


        //If this is true, an argument was loaded so we execute this block of code
        if (loadFromArgs == true) {

            NHL.removeTeam(calgaryTeam);
            calgaryTeam.clear();


            //System.out.println(teamName);
            File loadTeam = new File(teamName);

            if (loadTeam.exists() && loadTeam.isFile()) {
                calgaryTeam = Reader.loadTeam(loadTeam);
                NHL.addTeam(calgaryTeam);
                rightStatus.setText("Successfully loaded team");
            } else {
                leftStatus.setText("Failed to load team from argument");
            }
        }

    }

    /**
     * Adds a skater to the roster
     * @param event a MouseEvent
     */
    @FXML
    void addSkaterButton(MouseEvent event) {
    try {
        String addSkaterName = skaterName.getText();

        int addSkaterJerseyNumber = Integer.parseInt(skaterJerseryNumber.getText());
        int addSkaterGoals = Integer.parseInt(skaterGoals.getText());
        int addSkaterAssists = Integer.parseInt(skaterAssists.getText());
        int addSkaterGamesPlayed = Integer.parseInt(skaterGamesPlayed.getText());

        if (addSkaterJerseyNumber < 0 || addSkaterGoals < 0 || addSkaterAssists < 0 || addSkaterGamesPlayed < 0) {
            throw new NumberFormatException("Cannot input a negative value!");
        }

        if (calgaryTeam.playerExists(addSkaterJerseyNumber)) {
            throw new IllegalArgumentException("Player number already exists in the team!");
        }

        Skater newSkater = new Skater(addSkaterName, addSkaterJerseyNumber, addSkaterGamesPlayed, addSkaterGoals, addSkaterAssists);

        calgaryTeam.addPlayer(newSkater);

        leftStatus.setText("Successfully added new skater!");
    } catch (NumberFormatException e){
        leftStatus.setText("Invalid input value(s)!");
        } catch (IllegalArgumentException e) {
        leftStatus.setText(String.valueOf(e));
    }
    }


    /**
     * Views roster with stats and prints it to teamStatsView
     * @param event a MouseEvent
     */
    @FXML
    void viewRosterWithStats(MouseEvent event) {

        teamStatsView.clear();

        teamStatsView.appendText(calgaryTeam.rosterWithStats());
        teamStatsView.appendText(calgaryTeam.goalieWithStats());


    }


    /**
     * Adds a goalie to the roster
     * @param event a MouseEvent
     */
    @FXML
    void addGoalieButton(MouseEvent event) {
        try {
            String addGoalieName = goalieName.getText();

            int addGoalieJerseyNumber = Integer.parseInt(goalieJerseyNumber.getText());
            int addGoalieShotsFaced = Integer.parseInt(goalieShotsFaced.getText());
            int addGoalieSaves = Integer.parseInt(goalieSaved.getText());
            int addShutouts = Integer.parseInt(goalieShutout.getText());
            int addGoalieGamesPlayed = Integer.parseInt(goalieGamesPlayed.getText());

            if (addGoalieJerseyNumber < 0 || addGoalieShotsFaced < 0 || addGoalieSaves < 0 || addShutouts < 0 || addGoalieGamesPlayed < 0) {
                throw new NumberFormatException("Cannot add a negative value!");
            }

            if (calgaryTeam.playerExists(addGoalieJerseyNumber)) {
                throw new IllegalArgumentException("Player number already exists in the team!");
            }

            Goalie newGoalie = new Goalie(addGoalieName, addGoalieJerseyNumber, addGoalieGamesPlayed, addGoalieSaves, addGoalieShotsFaced, addShutouts);

            calgaryTeam.addPlayer(newGoalie);
            leftStatus.setText("Successfully added goalie!");

        } catch (NumberFormatException e) {
            leftStatus.setText("Invalid input value(s)!");
        } catch (IllegalArgumentException e) {
            leftStatus.setText(String.valueOf(e));
        }


    }

    /**
     * Views roster without stats and prints it to teamStatsView
     * @param event a MouseEvent
     */
    @FXML
    void viewRosterButton(MouseEvent event) {

        teamStatsView.clear();

        teamStatsView.setText(calgaryTeam.rosterWithoutStats());
        leftStatus.setText("Viewed Roster!");

    }

    /**
     * Gets top 5 score leaders
     * @param event a MouseEvent
     */
    @FXML
    void top5GoalsButton(MouseEvent event) {

        try {
            teamStatsView.clear();

            ArrayList<Skater> goalLeader = calgaryTeam.getGoalLeader();

            if (calgaryTeam.getTeamSize() < 5) {
                throw new IllegalArgumentException("Please add at least 5 skaters in order to use this option.");
            }

            if (goalLeader != null) {

                teamStatsView.appendText("The current top 5 goal leaders are: " + "\n");

                for (int i = 0; i < 5; i++) {

                    teamStatsView.appendText(goalLeader.get(i).toString() + " with " + goalLeader.get(i).getGoals() + " goals(s)." + "\n");
                }

            } else {

                teamStatsView.appendText("Currently, there is no goal leader.");
            }

        } catch (IndexOutOfBoundsException e) {
            leftStatus.setText("Cannot check an empty team!");

        } catch (IllegalArgumentException e) {
            leftStatus.setText(String.valueOf(e));
        }
    }

    /**
     * Gets top 5 assist leaders
     * @param event a MouseEvent
     */
    @FXML
    void top5AssistsButton(MouseEvent event) {

        try {
            teamStatsView.clear();

            ArrayList<Skater> assistLeader = calgaryTeam.getAssistLeader();

            if (calgaryTeam.getTeamSize() < 5) {
                throw new IllegalArgumentException("Please add at least 5 skaters in order to use this option.");
            }

            if (assistLeader != null) {

                teamStatsView.appendText("The current top 5 assist leaders are: " + "\n");

                for (int i = 0; i < 5; i++) {

                    teamStatsView.appendText(assistLeader.get(i).toString() + " with " + assistLeader.get(i).getAssists() + " assists(s)." + "\n");
                }

            } else {

                teamStatsView.appendText("Currently, there is no assist leader.");
            }

        } catch (IndexOutOfBoundsException e) {
            leftStatus.setText("Cannot check an empty team!");

        } catch (IllegalArgumentException e) {
            leftStatus.setText(String.valueOf(e));
        }
    }

    /**
     * Gets a player's stats
     * @param event a MouseEvent
     */
    @FXML
    void getPlayerStats(MouseEvent event) {


        try {
            //Creating new window for player stats
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("playerStats-view.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = new Stage();
            stage3 = stage;
            stage.setScene(new Scene(parent));
            stage.setTitle("View stats");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            ViewStatsController viewStatsController = fxmlLoader.getController();



            int jerseyNumberToGet = Integer.parseInt(jerseyNumberStatsToGet.getText());

            //If the player does not exist, then throw an exception
            if (!calgaryTeam.playerExists(jerseyNumberToGet)) {
                throw new IllegalArgumentException("Cannot find player in team!");
            }

            //Getting stats based on if the player is a skater/goalie
            if (calgaryTeam.getPlayer(jerseyNumberToGet) instanceof Skater) {

                String playerName = calgaryTeam.getPlayer(jerseyNumberToGet).getPlayerName();

                int amountOfGoals = ((Skater) calgaryTeam.getPlayer(jerseyNumberToGet)).getGoals();
                int amountOfAssists = ((Skater) calgaryTeam.getPlayer(jerseyNumberToGet)).getAssists();
                int gamesPlayed = calgaryTeam.getPlayer(jerseyNumberToGet).getGamesPlayed();


                viewStatsController.getPlayerJerseyNumber(jerseyNumberToGet, amountOfGoals, amountOfAssists, gamesPlayed, playerName);

            } else if (calgaryTeam.getPlayer(jerseyNumberToGet) instanceof Goalie) {

                String playerName = calgaryTeam.getPlayer(jerseyNumberToGet).getPlayerName();

                int amountOfSaves = ((Goalie) calgaryTeam.getPlayer(jerseyNumberToGet)).getSaves();
                int amountOfShots = ((Goalie) calgaryTeam.getPlayer(jerseyNumberToGet)).getShotsOnGoal();
                double savePercentage = (double) amountOfSaves/ (double) amountOfShots;
                int amountOfShutOuts = ((Goalie) calgaryTeam.getPlayer(jerseyNumberToGet)).getShutouts();
                int gamesPlayed = calgaryTeam.getPlayer(jerseyNumberToGet).getGamesPlayed();

                viewStatsController.getGoalieJerseyNumber(jerseyNumberToGet, amountOfSaves, amountOfShots, savePercentage, amountOfShutOuts, gamesPlayed, playerName);

            }

            fxmlLoader.getController();

            stage.show();

        } catch (Exception e) {
            leftStatus.setText(String.valueOf(e));
        }



        /*
        try {
            teamStatsView.clear();
            int jerseyNumber = Integer.parseInt(jerseyNumberStatsToGet.getText());
            teamStatsView.appendText(calgaryTeam.getPlayerStats(jerseyNumber));
            leftStatus.setText("Found player #" + jerseyNumber);
        } catch (NumberFormatException e) {
            leftStatus.setText("Invalid input/Could not find player!");

        }



         */
    }

    /**
     * Opens a file given by the user
     * @param event a MouseEvent
     */
    @FXML
    void openFile(ActionEvent event) {
        try {
            FileChooser chooseFile = new FileChooser();

            //Make sure the user can only choose .txt file extensions
            chooseFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files (.*txt)", "*txt"));

            File chosenFile = chooseFile.showOpenDialog(null);

            //Removing and re-adding Calgary to the NHL once loaded, prevents null pointers later on.
            NHL.removeTeam(calgaryTeam);
            calgaryTeam.clear();

            calgaryTeam = Reader.loadTeam(chosenFile);
            NHL.addTeam(calgaryTeam);
            //Clearing the box, as the calgaryTeam will be different once we load it
            homeTeamChoice.valueProperty().set(null);
            awayTeamChoice.valueProperty().set(null);
            leftStatus.setText("Roster loaded!");
            rightStatus.setText("Loaded roster from " + chosenFile.getName());

        } catch (Exception e) {
            leftStatus.setText("Could not load a file!");
            rightStatus.setText("Error loading a file!");
        }

    }


    /**
     * Plays a game simulation
     */
    @FXML
    void playGame() {
        try {
            //Error checking for the values in the choice box.
            if (homeTeamChoice.getValue() == null || awayTeamChoice.getValue() == null) {
                throw new IllegalArgumentException("Please choose teams to play!");
            }

            if (homeTeamChoice.getValue().getShortName().equals("CGY") && homeTeamChoice.getValue().isEmpty()) {
                throw new IllegalArgumentException("Cannot play a game with an empty team!");
            } else if (awayTeamChoice.getValue().getShortName().equals("CGY") && awayTeamChoice.getValue().isEmpty()) {
                throw new IllegalArgumentException("Cannot play a game with an empty team!");
            }

            if (!homeTeamChoice.getValue().equals(calgaryTeam) && !awayTeamChoice.getValue().equals(calgaryTeam)) {
                throw new IllegalArgumentException("One team has to be Calgary!");
            }
            if (!calgaryTeam.goalieExists()) {
                throw new IllegalArgumentException("Cannot play a game without a goalie!");
            }
            if (!calgaryTeam.playersExists()) {
                throw new IllegalArgumentException("Cannot play a game without players!");
            }

            //Creating new window for the game
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage2 = stage;
            stage.setScene(new Scene(root));
            stage.setTitle("Play Game!");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            GameController gameController = fxmlLoader.getController();


            //Transferring team data to GameController
            gameController.playGame(homeTeamChoice.getValue(),awayTeamChoice.getValue());
            stage.showAndWait();


            //Setting stats after game

            if (gameStats[0] > gameStats[1]) {
                calgaryTeam.setTotalGamesWon(calgaryTeam.getTotalGamesWon()+1);

            } else {
                calgaryTeam.setTotalGamesLost(calgaryTeam.getTotalGamesLost()+1);
            }

            if (gameStats[1] == 0) {
                ((Goalie) calgaryTeam.getPlayer(goaliePlayed)).setShutouts(((Goalie) calgaryTeam.getPlayer(goaliePlayed)).getShutouts()+1);
            }
            ((Goalie) calgaryTeam.getPlayer(goaliePlayed)).addShots(otherShots);
            ((Goalie) calgaryTeam.getPlayer(goaliePlayed)).addSaves(otherShots-gameStats[1]);
            calgaryTeam.setTotalTeamGamesPlayed(calgaryTeam.getTotalTeamGamesPlayed()+1);
            calgaryTeam.setTotalTeamGoals(calgaryTeam.getTotalTeamGoals() + gameStats[0]);

            calgaryTeam.setPlayerStatsAfterGame(scorers, assisters, playersPlayed, goaliePlayed);

            rightStatus.setText("Played game! Score: " + gameStats[0] + "-" + gameStats[1]);
            //Resetting
            scorers.clear();
            assisters.clear();
            goaliePlayed = 0;
            playersPlayed.clear();
            flamesShots = 0;
            otherShots = 0;
            gameStats[0] = 0;
            gameStats[1] = 0;


        } catch (NumberFormatException e) {
            //System.out.println(e);
            leftStatus.setText(String.valueOf(e));
        } catch (NullPointerException e) {
            //System.out.println(e);
            leftStatus.setText("Error with the game!");
        } catch (IOException e) {
            leftStatus.setText("Error loading a game!");
        } catch (IllegalArgumentException e) {
            leftStatus.setText(String.valueOf(e));
        }




    }

    /**
     * Updates the labels according to if a goalie is selected or a skater.
     * @param event A mouse event
     */
    @FXML
    void updateField(MouseEvent event) {

        if (isGoalie.isSelected()) {

            goalsLabel.setVisible(false);
            assistsLabel.setVisible(false);

            savesLabel.setVisible(true);
            shotsOnGoalLabel.setVisible(true);
            shutoutsLabel.setVisible(true);




        } else {

            goalsLabel.setVisible(true);
            assistsLabel.setVisible(true);

            savesLabel.setVisible(false);
            shotsOnGoalLabel.setVisible(false);
            shutoutsLabel.setVisible(false);

        }


    }

    /**
     * Edits player's(Goalie or skater) stats.
     * @param event A MouseEvent
     */
    @FXML
    void editPlayerStatsButton(MouseEvent event) {

        try {
            int jerseyNumber = Integer.parseInt(editPlayerJerseyNumber.getText());

            //If skater, edit stats accordingly. If not, then edit goalie stats.
            if (calgaryTeam.getPlayer(jerseyNumber) instanceof Skater){

                if (isGoalie.isSelected()) {
                    throw new IllegalArgumentException("Cannot edit a skater type as a goalie!");
                }


                int setGoals = Integer.parseInt(editGoals.getText());
                ((Skater) calgaryTeam.getPlayer(jerseyNumber)).setGoals(setGoals);

                int setAssists = Integer.parseInt(editAssists.getText());
                ((Skater) calgaryTeam.getPlayer(jerseyNumber)).setAssists(setAssists);

                int setGamesPlayed = Integer.parseInt(editGamesPlayed.getText());
                calgaryTeam.getPlayer(jerseyNumber).setGamesPlayed(setGamesPlayed);


            } else {
                if (isPlayer.isSelected()) {
                    throw new IllegalArgumentException("Cannot edit a goalie type as a skater!");
                }
                int setSaves = Integer.parseInt(editSaves.getText());
                ((Goalie) calgaryTeam.getPlayer(jerseyNumber)).setSaves(setSaves);

                int setSOG = Integer.parseInt(editSOG.getText());
                ((Goalie) calgaryTeam.getPlayer(jerseyNumber)).setShotsOnGoal(setSOG);

                int setShutouts = Integer.parseInt(editShutouts.getText());
                ((Goalie) calgaryTeam.getPlayer(jerseyNumber)).setShutouts(setShutouts);

                int setGamesPlayed = Integer.parseInt(editGamesPlayed.getText());
                calgaryTeam.getPlayer(jerseyNumber).setGamesPlayed(setGamesPlayed);

            }

            leftStatus.setText("Edited #" + jerseyNumber);
            
        } catch (NumberFormatException e) {
            leftStatus.setText("Invalid input(s)!");
        } catch (NullPointerException e) {
            leftStatus.setText("Could not find player in team!");
            //System.out.println(e);
        } catch (IllegalArgumentException e) {
            leftStatus.setText(String.valueOf(e));
        }

    }

    //Plays calgaryFlamesTracker.music
    @FXML
    void playLevels(ActionEvent event) {

        stopClip();
        playSound("src/main/java/calgaryFlamesTracker/music/levels.wav");

        currentMusicPlaying.setText("Levels by Avicii");

    }

    @FXML
    void playFalls(ActionEvent event) {

        //TODO: Accidentally downloaded the explicit version of the song

        stopClip();
        playSound("src/main/java/calgaryFlamesTracker/music/Fall.wav");

        currentMusicPlaying.setText("Fall by Isaac App");

    }

    @FXML
    void playNightsLikeThis(ActionEvent event) {

        stopClip();
        playSound("src/main/java/calgaryFlamesTracker/music/nightsLikeThis.wav");

        currentMusicPlaying.setText("Nights like This by Loud Luxury");

    }

    @FXML
    void playMakeYourMove(ActionEvent event) {

        stopClip();
        playSound("src/main/java/calgaryFlamesTracker/music/makeYourMove.wav");

        currentMusicPlaying.setText("Make your Move by Anton Powers");

    }

    @FXML
    void stopMusicButton(MouseEvent event) {
        stopClip();
    }

    /**
     * Views team stats as a whole and prints it to teamStatsView
     * @param event a MouseEvent
     */
    @FXML
    void teamStatsAsWhole(MouseEvent event) {
        teamStatsView.setText(calgaryTeam.returnTeamStats());
    }

    /**
     * Loads the default roster
     * @param event an ActionEvent
     */
    @FXML
    void loadDefault(ActionEvent event) {
        try {
            File defaultFile = new File("src/main/java/calgaryFlamesTracker/defaultRoster/defaultroster.txt");
            NHL.removeTeam(calgaryTeam);
            calgaryTeam.clear();

            calgaryTeam = Reader.loadTeam(defaultFile);
            NHL.addTeam(calgaryTeam);
            //Clearing the box, as the calgaryTeam will be different once we load it
            homeTeamChoice.valueProperty().set(null);
            awayTeamChoice.valueProperty().set(null);
            leftStatus.setText("Loaded defaultroster.txt!");
        } catch (Exception e) {
            leftStatus.setText("Could not load default roster.");
        }
    }

    /**
     * Saves to the default roster.txt
     * @param event an ActionEvent
     */
    @FXML
    void saveDefault(ActionEvent event) {
        try {
            saveFile(calgaryTeam, new File("defaultroster.txt"));
            leftStatus.setText("Saved to roster.txt!");
        } catch (Exception e) {
            leftStatus.setText("Could not save to roster.txt.");
        }
    }

    /**
     * Views team stats as a whole and prints it to teamStatsView
     * @param event an ActionEvent
     */
    @FXML
    void saveAs(ActionEvent event) {
        try {
            FileChooser chooseFile = new FileChooser();

            //Make sure the user can only choose .txt file extensions
            chooseFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files (.*txt)", "*txt"));

            File chosenFile = chooseFile.showSaveDialog(null);
            saveFile(calgaryTeam, chosenFile);
            leftStatus.setText("Saved to " + chosenFile.getName() + "!");
        } catch (Exception e) {
            leftStatus.setText("Could not save file.");
        }
    }

    /**
     * Exits the program
     * @param event an ActionEvent
     */
    @FXML
    void exitMenu(ActionEvent event) {
        System.exit(1);
    }

    /**
     * Views program information
     * @param event an ActionEvent
     */
    @FXML
    public void aboutInfo(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Author: Quang(Brandon) Nguyen and Edvin Sinko\n" +
                "Version 1.0\n" +
                "This program takes user input or files for data, and can perform various calculations on them.\n" +
                "The user then has the options to view/modify data in a variety of ways (such as game simulations).\n" +
                "The program also has options to allow the user to save/load their data.\n");
        alert.show();
    }
}