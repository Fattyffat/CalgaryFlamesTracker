package com.cftp.calgaryflamestrackerproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static com.cftp.calgaryflamestrackerproject.HelloController.stage2;

public class ViewStatsController {


    @FXML
    private Text assistsText;

    @FXML
    private Button closeButton;

    @FXML
    private Text goalsText;

    @FXML
    private ImageView playerPicture;

    @FXML
    private Label showAssists;

    @FXML
    private Label showGamesPlayed;

    @FXML
    private Label showGoals;

    @FXML
    private Label showPlayerName;

    @FXML
    private Text savesText;

    @FXML
    private Text shotsFacedText;

    @FXML
    private Label showSaves;

    @FXML
    private Label showShotsFaced;

    @FXML
    private Text savePercentText;

    @FXML
    private Text shutoutsText;

    @FXML
    private Label showShutouts;

    @FXML
    private Label showSavePercent;


    private int jerseyNumber;


    @FXML
    void returnView(MouseEvent event) {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();


    }

    /**
     * Shows a Skater in a new scene, with the picture of the skater (considering it is a Flames skater)
     * @param jerseyNumberFromController Jersey number of the skater
     * @param goals Amount of goals for the skater
     * @param assists Amount of assists for the skater
     * @param gamePlayed Amount of games played for the skater
     * @param playerName Skater's player name
     */
    public void getPlayerJerseyNumber(int jerseyNumberFromController, int goals, int assists, int gamePlayed, String playerName) {

        goalsText.setVisible(true);
        assistsText.setVisible(true);
        showGoals.setVisible(true);
        showAssists.setVisible(true);

        showSavePercent.setVisible(false);
        showShutouts.setVisible(false);
        savePercentText.setVisible(false);
        shutoutsText.setVisible(false);
        showSaves.setVisible(false);
        showShotsFaced.setVisible(false);
        shotsFacedText.setVisible(false);
        savesText.setVisible(false);


        //System.out.println("This is: " + jerseyNumberFromController);
        jerseyNumber = jerseyNumberFromController;

        //System.out.println("Jerey now is" + jerseyNumber);

        File file = new File("src/main/java/calgaryFlamesTracker/playerPictures/" + jerseyNumberFromController + ".jpg");


        if (file.exists()) {
            Image image = new Image(file.toURI().toString());
            playerPicture.setImage(image);
        } else {

            file = new File("src/main/java/calgaryFlamesTracker/playerPictures/placeholder.png");
            Image image = new Image(file.toURI().toString());
            playerPicture.setImage(image);

        }

        showGoals.setText(String.valueOf(goals));
        showGoals.setAlignment(Pos.CENTER);

        showAssists.setText(String.valueOf(assists));
        showAssists.setAlignment(Pos.CENTER);

        showGamesPlayed.setText(String.valueOf(gamePlayed));
        showGamesPlayed.setAlignment(Pos.CENTER);

        showPlayerName.setText(playerName);
        showPlayerName.setAlignment(Pos.CENTER);

        showPlayerName.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
        showPlayerName.setTextFill(Color.color(0,0,0));


    }


    /**
     * Shows a Goalie in a new scene, with the picture of the goalie (considering it is a Flames goalie)
     * @param jerseyNumberFromController Jersey number of goalie
     * @param saves Amount of saves for the goalie
     * @param shotsFaced Shots faced for the goalie
     * @param savePercentage Save percentage for the goalie
     * @param shutouts Shutouts for the goalie
     * @param gamesPlayed Games played for the goalie
     * @param playerName Goalie player name
     */
    public void getGoalieJerseyNumber(int jerseyNumberFromController, int saves, int shotsFaced, double savePercentage, int shutouts, int gamesPlayed, String playerName) {

        showSaves.setVisible(true);
        showShotsFaced.setVisible(true);
        shotsFacedText.setVisible(true);
        savesText.setVisible(true);
        savePercentText.setVisible(true);
        shutoutsText.setVisible(true);
        showSavePercent.setVisible(true);
        showShutouts.setVisible(true);



        goalsText.setVisible(false);
        assistsText.setVisible(false);
        showGoals.setVisible(false);
        showAssists.setVisible(false);


        //System.out.println("This is: " + jerseyNumberFromController);
        jerseyNumber = jerseyNumberFromController;

        //System.out.println("Jerey now is" + jerseyNumber);

        File file = new File("src/main/java/calgaryFlamesTracker/playerPictures/" + jerseyNumberFromController + ".jpg");


        if (file.exists()) {
            Image image = new Image(file.toURI().toString());
            playerPicture.setImage(image);
        } else {

            file = new File("src/main/java/calgaryFlamesTracker/playerPictures/placeholder.png");
            Image image = new Image(file.toURI().toString());
            playerPicture.setImage(image);

        }

        showSaves.setText(String.valueOf(saves));
        showSaves.setAlignment(Pos.CENTER);

        showShotsFaced.setText(String.valueOf(shotsFaced));
        showShotsFaced.setAlignment(Pos.CENTER);

        showShutouts.setText(String.valueOf(shutouts));
        showShutouts.setAlignment(Pos.CENTER);

        showSavePercent.setText(String.valueOf(String.format("%.3f", savePercentage)));
        showSavePercent.setAlignment(Pos.CENTER);

        showGamesPlayed.setText(String.valueOf(gamesPlayed));
        showGamesPlayed.setAlignment(Pos.CENTER);

        showPlayerName.setText(playerName);
        showPlayerName.setAlignment(Pos.CENTER);

        showPlayerName.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
        showPlayerName.setTextFill(Color.color(0,0,0));


    }



}
