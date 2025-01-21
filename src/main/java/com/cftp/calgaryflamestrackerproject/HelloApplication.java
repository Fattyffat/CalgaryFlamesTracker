/**
 * Calgary Flames Tracker program.
 * This program takes user input or files for data, and can perform various calculations on them
 * The user then has the options to view/modify data in a variety of ways (such as game simulations)
 * The program also has options to allow the user to save/load their data
 * Tutotial 06.
 * @author Edvin Sinko and Quang(Brandon) Nguyen
 * @version 1.0
 */

package com.cftp.calgaryflamestrackerproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;

public class HelloApplication extends Application {
    public static Stage primaryStage;


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("splashScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);


        //Get the parameters from the launch(args) function called in main
        Parameters getParam = getParameters();
        List<String> list = getParam.getRaw();

        //Check if the list is empty (ie: 0 parameters, prevents index out of bounds error)
        if (list.isEmpty()) {

        } else {

            //Create instance of HelloController
            HelloController helloController = new HelloController();
            helloController.loadArgs(list.get(0));


        }


        stage.setTitle("Calgary Flames Tracker!");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();


    }

    public static void main(String[] args) {

        if (args.length == 1) {
            //System.out.println("1 argument");
            launch(args);
        } else if (args.length == 0) {
            //System.out.println("0 arguments");
            launch();
        } else {
            //System.out.println("Should error");
            //TODO: Error check this?
            throw new IllegalArgumentException("Error. Can only have 1 or 0 arguments");
        }


    }
}