package com.cftp.calgaryflamestrackerproject;

import calgaryFlamesTracker.League.Teams;
import calgaryFlamesTracker.Players.Goalie;
import calgaryFlamesTracker.Players.Skater;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

import static com.cftp.calgaryflamestrackerproject.HelloController.stage2;

public class GameController {
    private Teams homeTeam;
    private Teams awayTeam;
    public static Skater scorer1;
    public static Skater assister1;
    public static Skater assister2;

    public static ArrayList<Integer> scorers = new ArrayList<>();
    public static ArrayList<Integer> assisters = new ArrayList<>();
    public static int goaliePlayed;
    public static ArrayList<Integer> playersPlayed;
    public static int flamesShots;
    public static int otherShots;
    public static int[] gameStats = new int[2];

    @FXML
    private ImageView awayTeamLogo;

    @FXML
    private ImageView homeTeamLogo;

    @FXML
    private Text homeTeamText;

    @FXML
    private Text awayTeamText;

    @FXML
    private Text homeTeamScoreText;

    @FXML
    private Text awayTeamScoreText;

    @FXML
    private TextField homeShots;

    @FXML
    private TextField awayShots;

    @FXML
    private Text arenaName;

    @FXML
    private TableView<GraphObject> scorersTable;

    @FXML
    private TableColumn<GraphObject, String> playerScoredCol = new TableColumn<>("Player Scored");
    @FXML
    private TableColumn<GraphObject, String> assistersCol1 = new TableColumn<>("Assist 1");
    @FXML
    private TableColumn<GraphObject, String> assistersCol2 = new TableColumn<>("Assist 2");

    @FXML
    private TableView<SimpleStringProperty> playersNotPlayedTable;

    @FXML
    private TableColumn<SimpleStringProperty, String> playerNotPlayedCol;

    @FXML
    private TextField jerseyNumberNotPlayed;

    @FXML
    private Label leftStatus;

    @FXML
    private Label rightStatus;

    @FXML
    private TextField goaliePlayedText;

    @FXML
    private Button finishGameButton;



    public GameController() {
    }


    /**
     * Initializing table values
     */
    public void initialize() {
        //From https://stackoverflow.com/questions/18971109/javafx-tableview-not-showing-data-in-all-columns
        playerScoredCol.setCellValueFactory(cellData -> cellData.getValue().scorerProperty());
        assistersCol1.setCellValueFactory(cellData -> cellData.getValue().a1Property());
        assistersCol2.setCellValueFactory(cellData -> cellData.getValue().a2Property());
        playerNotPlayedCol.setCellValueFactory(cellData -> cellData.getValue());


    }

    /**
     * Gets data from other controller to play a game
     * @param homeTeam1 Home team to play
     * @param awayTeam1 Away team to play
     */
    public void playGame(Teams homeTeam1, Teams awayTeam1) {
        homeTeam1.setTotalHomeGames(homeTeam1.getTotalHomeGames()+1);
        awayTeam1.setTotalAwayGames(awayTeam1.getTotalAwayGames()+1);

        homeTeamText.setText(String.valueOf(homeTeam1));
        awayTeamText.setText(String.valueOf(awayTeam1));

        homeTeam = homeTeam1;
        awayTeam = awayTeam1;

        File homeTeamPic = new File("src/main/java/calgaryFlamesTracker/teamLogo/" + homeTeam.getShortName() + ".png");
        File awayTeamPic = new File("src/main/java/calgaryFlamesTracker/teamLogo/" + awayTeam.getShortName() + ".png");



        if (homeTeamPic.exists()) {

            Image imageHome = new Image(homeTeamPic.toURI().toString());
            homeTeamLogo.setImage(imageHome);
        }


        if (awayTeamPic.exists()) {
            Image image = new Image(awayTeamPic.toURI().toString());
            awayTeamLogo.setImage(image);

        }



        if (homeTeam.getShortName().equals("CGY")) {
            playersPlayed = new ArrayList<>(homeTeam.getPlayerNumbers());
        } else {
            playersPlayed = new ArrayList<>(awayTeam.getPlayerNumbers());
        }

        arenaName.setText(homeTeam.getArenaName());
        leftStatus.setText("Please add a goal/set shots.");
        rightStatus.setText("New game! " + homeTeam.getShortName() + " vs " + awayTeam.getShortName());

    }

    /**
     * Adds a home goal
     */
    public void addHomeGoal() {
        if (homeTeam.getShortName().equals("CGY")) {
            flamesGoal();
            homeTeamScoreText.setText(String.valueOf((Integer.parseInt(homeTeamScoreText.getText()) + 1)));
        } else {
            homeTeamScoreText.setText(String.valueOf((Integer.parseInt(homeTeamScoreText.getText())+1)));
            leftStatus.setText("Added home goal!");
        }



    }

    /**
     * Adds an away goal
     */
    public void addAwayGoal() {
        if (awayTeam.getShortName().equals("CGY")) {
            flamesGoal();
            awayTeamScoreText.setText(String.valueOf((Integer.parseInt(awayTeamScoreText.getText()) + 1)));

        } else {
            awayTeamScoreText.setText(String.valueOf((Integer.parseInt(awayTeamScoreText.getText())+1)));
            leftStatus.setText("Added away goal!");
        }
    }

    /**
     * Case for when the flames score a goal
     */
    public void flamesGoal() {
        try {
            //Creating a new stage for the popout window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("goal-view.fxml"));

            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Goal!");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(stage2);

            GoalController goalController = fxmlLoader.getController();
            goalController.sendData(homeTeam, awayTeam);
            stage.showAndWait();



            //Set graph values (if assister = null, then there is no assist.)
            //Set ArrayList values for score, player stats, etc.
            if (scorer1 == null && assister1 == null && assister2 == null) {

                if (awayTeam.getShortName().equals("CGY")) {
                    awayTeamScoreText.setText(String.valueOf((Integer.parseInt(awayTeamScoreText.getText()) - 1)));
                } else {
                    homeTeamScoreText.setText(String.valueOf((Integer.parseInt(homeTeamScoreText.getText()) - 1)));
                }


            } else if (assister2 == null && assister1 == null) {
                scorersTable.getItems().add(new GraphObject(scorer1.toString(), "", ""));
                scorers.add(scorer1.getJerseyNumber());
            } else if (assister2 == null) {
                scorersTable.getItems().add(new GraphObject(scorer1.toString(), assister1.toString(), ""));
                scorers.add(scorer1.getJerseyNumber());
                assisters.add(assister1.getJerseyNumber());
            } else {
                scorersTable.getItems().add(new GraphObject(scorer1.toString(), assister1.toString(), assister2.toString()));
                scorers.add(scorer1.getJerseyNumber());
                assisters.add(assister1.getJerseyNumber());
                assisters.add(assister2.getJerseyNumber());
            }

            //Resetting the scorers and assisters
            scorer1 = null;
            assister1 = null;
            assister2 = null;
            leftStatus.setText("Flames goal!");
        } catch (Exception e) {
            leftStatus.setText(String.valueOf(e));
        }

    }

    /**
     * Adds a player that did not play
     */
    public void addPlayerThatDidNotPlay() {
        try {
            if (homeTeam.getShortName().equals("CGY")) {
                if (homeTeam.getPlayer(Integer.parseInt(jerseyNumberNotPlayed.getText())) instanceof Goalie) {
                    throw new IllegalArgumentException("Cannot add a goalie here!");
                }

                for (int i = 0; i < playersPlayed.size(); i++) {
                    //System.out.println("Comparing " + playersPlayed.get(i) + " to " + jerseyNumberNotPlayed.getText());
                    if (playersPlayed.get(i) == Integer.parseInt(jerseyNumberNotPlayed.getText())) {
                        //System.out.println("Removing " + i);
                        playersPlayed.remove(i);
                    }
                }

                playersNotPlayedTable.getItems().add(new SimpleStringProperty(homeTeam.getPlayer(Integer.parseInt(jerseyNumberNotPlayed.getText())).toString()));

            } else {
                if (awayTeam.getPlayer(Integer.parseInt(jerseyNumberNotPlayed.getText())) instanceof Goalie) {
                    throw new IllegalArgumentException("Cannot add a goalie here!");
                }

                playersNotPlayedTable.getItems().add(new SimpleStringProperty(awayTeam.getPlayer(Integer.parseInt(jerseyNumberNotPlayed.getText())).toString()));

                for (int i = 0; i < playersPlayed.size(); i++) {
                    //System.out.println("Comparing " + playersPlayed.get(i) + " to " + jerseyNumberNotPlayed.getText());
                    if (playersPlayed.get(i) == Integer.parseInt(jerseyNumberNotPlayed.getText())) {
                        playersPlayed.remove(i);
                    }
                }
            }

            leftStatus.setText("Added #" + jerseyNumberNotPlayed.getText() + " to the list of players not played.");
        } catch (IllegalArgumentException e) {
            leftStatus.setText("Cannot add a goalie here!");
        } catch (NullPointerException e) {
            leftStatus.setText("Player does not exist in the team!");
        }
    }

    /**
     * Gets all the final values, to prepare it to be transferred to other scene. Includes error checking
     */
    public void finishGame() {
        //Transfer final stats
        //System.out.println(scorersTable.getItems());
        try {
            if (Integer.parseInt(homeShots.getText()) <= 0 || Integer.parseInt(awayShots.getText()) <= 0 ) {
                throw new IllegalArgumentException("Cannot have 0 or negative shots!");
            }

            if (Integer.parseInt(homeTeamScoreText.getText()) == 0 && Integer.parseInt(awayTeamScoreText.getText()) == 0) {
                throw new IllegalArgumentException("Cannot be 0-0!");
            }

            if (Integer.parseInt(homeTeamScoreText.getText()) == Integer.parseInt(awayTeamScoreText.getText())) {
                throw new IllegalArgumentException("Cannot be a tie game!");
            }

            //Game stats will have:
            //[Calgary score, Opponent Score]
            if (homeTeam.getShortName().equals("CGY")) {
                flamesShots = Integer.parseInt(homeShots.getText());
                otherShots = Integer.parseInt(awayShots.getText());
                gameStats[0] = Integer.parseInt(homeTeamScoreText.getText());
                gameStats[1] = Integer.parseInt(awayTeamScoreText.getText());

                if (homeTeam.playerExists(Integer.parseInt(goaliePlayedText.getText()))) {
                    goaliePlayed = Integer.parseInt(goaliePlayedText.getText());

                } else {
                    throw new IllegalArgumentException("Goalie does not exist in team!");
                }


            } else {
                flamesShots = Integer.parseInt(awayShots.getText());
                otherShots = Integer.parseInt(homeShots.getText());
                gameStats[0] = Integer.parseInt(awayTeamScoreText.getText());
                gameStats[1] = Integer.parseInt(homeTeamScoreText.getText());

                if (awayTeam.playerExists(Integer.parseInt(goaliePlayedText.getText()))) {
                    goaliePlayed = Integer.parseInt(goaliePlayedText.getText());

                } else {
                    throw new IllegalArgumentException("Goalie does not exist in team!");
                }

            }

            Stage stage = (Stage) homeTeamText.getScene().getWindow();
            stage.close();

            //System.out.println(playersPlayed);


        } catch (NumberFormatException e) {
            leftStatus.setText("Invalid input! Please check to make sure all fields are numbers!");
        } catch (IllegalArgumentException e) {
            leftStatus.setText(String.valueOf(e));
        }

    }





}
