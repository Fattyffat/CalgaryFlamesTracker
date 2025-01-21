package com.cftp.calgaryflamestrackerproject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class splashScreenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new SplashScreen().start();


    }


    //Most code from: https://www.youtube.com/watch?v=f06uUtkmtDE&ab_channel=GenuineCoder

    class SplashScreen extends Thread {


        @Override
        public void run() {

            progressBar.setStyle("-fx-accent: red");
            for (double i = 0; i < 1; i = i + 0.01) {
                int numberToPrint = (int) Math.round(i*100);

                String numberToPrintString = String.valueOf(numberToPrint);



                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                //So, javaFX can only be modified by 1 thread at a time. Put this in runLater so that it can queue it up in the event queue
                //https://stackoverflow.com/questions/57064614/not-on-fx-application-thread-currentthread-thread-5

                Platform.runLater(()->progressLabel.setText(numberToPrintString + "%"));


                progressBar.setProgress(i);

            }


            try {
                Thread.sleep(500);


                    Platform.runLater(new Runnable() {



                        @Override
                        public void run() {

                            Parent root = null;
                            try {
                                root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));


                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Scene scene = new Scene(root);


                            Stage stage = new Stage();
                            stage.setTitle("Calgary Flames Tracker!");
                            stage.setScene(scene);
                            stage.show();

                            anchorPane.getScene().getWindow().hide();


                        }
                    });



            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }




}
