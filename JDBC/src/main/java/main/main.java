package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class main extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] argv)  {

        main.ConnectionFactory.connect();
        launch(argv);



    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bank");

        Button b1 = new Button("save");
        Button b2 = new Button("update");
        Button b3 = new Button("remove");
        Button b4 = new Button("find");
        Button b5 = new Button("find All");
        Button b6 = new Button("ok");

        b1.setPrefWidth(1000);
        b2.setPrefWidth(1000);
        b3.setPrefWidth(1000);
        b4.setPrefWidth(1000);
        b5.setPrefWidth(1000);
        b6.setPrefWidth(1000);

        VBox topMenu = new VBox();
        topMenu.getChildren().addAll(b1,b2,b3,b4,b5,b6);

        BorderPane borderpane = new BorderPane();
        borderpane.setCenter(topMenu);

        b1.setOnAction(event -> {

                });

    Scene scene = new Scene(borderpane, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handle(ActionEvent event) {

    }
}