package com.cftp.calgaryflamestrackerproject;

import calgaryFlamesTracker.League.Game;
import calgaryFlamesTracker.League.Teams;
import calgaryFlamesTracker.Players.Goalie;
import calgaryFlamesTracker.Players.Skater;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static com.cftp.calgaryflamestrackerproject.GameController.scorer1;
import static com.cftp.calgaryflamestrackerproject.GameController.assister1;
import static com.cftp.calgaryflamestrackerproject.GameController.assister2;

import java.util.Set;

public class GoalController {
    private Teams homeTeam;

    private Teams awayTeam;

    private int jerseyNumberToReturn;
    private int assist1ToReturn;
    private int assist2ToReturn;

    private Teams teamToCheck;
    @FXML
    private TextField jerseyNumberScored;

    @FXML
    private RadioButton assist0;

    @FXML
    private RadioButton assist1;

    @FXML
    private RadioButton assist2;

    @FXML
    private TextField assistBy1;

    @FXML
    private TextField assistBy2;

    @FXML
    private Button confirmButton;

    /**
     * Disables the text fields based on the user's selection
     */
    @FXML
    void updateField() {
        if (assist0.isSelected()) {
            assistBy1.setDisable(true);
            assistBy2.setDisable(true);
        } else if (assist1.isSelected()) {
            assistBy1.setDisable(false);
            assistBy2.setDisable(true);
        } else if (assist2.isSelected()) {
            assistBy1.setDisable(false);
            assistBy2.setDisable(false);
        }


    }

    /**
     * Recieves data from another scene
     * @param team1 A team
     * @param team2 Other team
     */
    public void sendData(Teams team1, Teams team2) {
        //System.out.println("Send: " + team1);
        //System.out.println(team2);
        homeTeam = team1;
        awayTeam = team2;

        if (homeTeam.getShortName().equals("CGY")) {
            teamToCheck = homeTeam;
        } else {
            teamToCheck = awayTeam;
        }
        //System.out.println(homeTeam);
        //System.out.println(awayTeam);
    }

    /**
     * Gets all the final values to prepare it to be transferred to another scene. Includes error checking
     */
    @FXML
    public void confirmSelections() {
        try {
            Set<Integer> playerNumbers = teamToCheck.getPlayerNumbers();
            jerseyNumberToReturn = 0;
            assist1ToReturn = 0;
            assist2ToReturn = 0;

            //System.out.println(playerNumbers.toString());

            //This is getting the jersey number of the players to send back to GameController
            for (int i : playerNumbers) {
                if (teamToCheck.getPlayer(i).getJerseyNumber() == Integer.parseInt(jerseyNumberScored.getText())) {
                    //System.out.println(i);
                    if (teamToCheck.getPlayer(i) instanceof Goalie) {
                        throw new IllegalArgumentException("Cannot have a goalie score!");
                    }

                    jerseyNumberToReturn = i;
                }


                if (assist1.isSelected()) {
                    if (teamToCheck.getPlayer(i).getJerseyNumber() == Integer.parseInt(assistBy1.getText())) {
                        if (teamToCheck.getPlayer(i) instanceof Goalie) {
                            throw new IllegalArgumentException("Cannot have a goalie assist!");
                        }
                        assist1ToReturn = i;
                    }
                }


                if (assist2.isSelected()) {
                    if (teamToCheck.getPlayer(i).getJerseyNumber() == Integer.parseInt(assistBy1.getText())) {
                        if (teamToCheck.getPlayer(i) instanceof Goalie) {
                            throw new IllegalArgumentException("Cannot have a goalie assist!");
                        }
                        assist1ToReturn = i;
                    }
                    if (teamToCheck.getPlayer(i).getJerseyNumber() == Integer.parseInt(assistBy2.getText())) {
                        if (teamToCheck.getPlayer(i) instanceof Goalie) {
                            throw new IllegalArgumentException("Cannot have a goalie assist!");
                        }
                        assist2ToReturn = i;
                    }
                }
            }


            //Various error checking here
            if (jerseyNumberToReturn == 0) {
                throw new NumberFormatException("Scorer does not exist in the team!");
            }

            if (assist1.isSelected() && assist1ToReturn == 0) {
                throw new NumberFormatException("Assister #1 does not exist in the team!");
            }

            if (assist2.isSelected() && (assist1ToReturn == 0 || assist2ToReturn == 0)) {
                throw new NumberFormatException("Assister #1 or #2 does not exist in the team!");
            }

            if (assist1.isSelected() && assist1ToReturn == jerseyNumberToReturn) {
                throw new NumberFormatException("Cannot assist self!");
            }

            if (assist2.isSelected() && ((assist1ToReturn == jerseyNumberToReturn) || (assist2ToReturn == jerseyNumberToReturn))) {
                throw new NumberFormatException("Cannot assist self!");
            }

            //Once we finally get valid inputs, we close the window and return the values back.
            //Returning values accordingly, as we can have different number of assists
            if (assist0.isSelected()) {
                GameController.scorer1 = ((Skater) teamToCheck.getPlayer(jerseyNumberToReturn));

            } else if (assist1.isSelected()) {
                GameController.scorer1 = ((Skater) teamToCheck.getPlayer(jerseyNumberToReturn));
                GameController.assister1 =((Skater) teamToCheck.getPlayer(assist1ToReturn));

            } else if (assist2.isSelected()) {
                GameController.scorer1 = ((Skater) teamToCheck.getPlayer(jerseyNumberToReturn));
                GameController.assister1 = ((Skater) teamToCheck.getPlayer(assist1ToReturn));
                GameController.assister2 = ((Skater) teamToCheck.getPlayer(assist2ToReturn));

            }

            //Exiting stage
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            //Catch will create an alert box.
            Alert alert = new Alert(Alert.AlertType.ERROR, String.valueOf(e));
            alert.show();
        }
    }

}
