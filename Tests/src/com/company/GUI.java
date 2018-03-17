package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class GUI extends Application implements EventHandler<ActionEvent> {

    Button button1, button2, button3, button4;
    BankImpl bank = new BankImpl();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Title");

        // Button button1, button2, button3, button4;
        button1 = new Button("click me");
        button1.setOnAction(this);


        StackPane layout = new StackPane(); //panel
        layout.getChildren().add(button1);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    @Override
    public void handle(ActionEvent event) {

    }

}

