package main;

import dao.ClientDaoImpl;
import exceptions.DataAccessException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Client;

import java.sql.Connection;
import java.sql.SQLException;

public class main extends Application implements EventHandler<ActionEvent> {

    private Label label = new Label();
    private Label warlab = new Label();

    private TextField text1 = new TextField();
    private TextField text2 = new TextField();
    private TextField text3 = new TextField();
    private TextField text4 = new TextField();
    private VBox topMenu = new VBox();
    private Button b6;

    public static void main(String[] argv)  {

        Connection connect = ConnectionFactory.connect();

        launch(argv);



    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bank");

        Button b1 = new Button("save");
        Button b2 = new Button("update");
        Button b3 = new Button("remove");
        Button b4 = new Button("find");
        Button b5 = new Button("find All");
        b6 = new Button("ok");

        b1.setPrefWidth(1000);
        b2.setPrefWidth(1000);
        b3.setPrefWidth(1000);
        b4.setPrefWidth(1000);
        b5.setPrefWidth(1000);
        b6.setPrefWidth(1000);


        topMenu.getChildren().addAll(b1,b2,b3,b4,b5);

        BorderPane borderpane = new BorderPane();
        borderpane.setCenter(topMenu);

        b1.setOnAction(event -> {
            clear();
                    String message1 = "Save. \n Enter your data:(first name, last name, pesel, email ";
                    label.setText(message1);
                    topMenu.getChildren().addAll(label, text1, text2, text3, text4, b6);

                    b6.setOnAction(e -> {
                        clear();

                        if (text1.getText() != null && text2.getText() != null && text3.getText() != null && text4.getText() != null) {

                            Client c = new Client(text1.getText(), text2.getText(), text3.getText(), text4.getText());
                            ClientDaoImpl clientDao = new ClientDaoImpl();
                            try {
                                clientDao.save(c);

                                String war = "Saved ";
                                warlab.setText(war);
                                topMenu.getChildren().add(warlab);

                            } catch (DataAccessException ex) {
                                String war = "No access ";
                                warlab.setText(war);
                                topMenu.getChildren().add(warlab);
                            } catch (SQLException e1) {
                               // e1.printStackTrace();
                            }
                        }
                    });

                });

        b2.setOnAction(event -> {
            String message1 = "Update. \n Enter your data:(first name, last name, pesel, email)";
            label.setText(message1);
            topMenu.getChildren().addAll(label, text1, text2, text3, text4, b6);

            b6.setOnAction(e -> {

                if (text1.getText() != null && text2.getText() != null && text3.getText() != null && text4.getText() != null) {

                    Client c = new Client(text1.getText(), text2.getText(), text3.getText(), text4.getText());
                    ClientDaoImpl clientDao = new ClientDaoImpl();
                    try {
                        clientDao.update(c);

                        String war = "Updated ";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                    } catch (DataAccessException ex) {
                        System.out.println("proba");
                    }catch ( NumberFormatException ex) {
                        String war = "Wprowadzono niepoprawny format danych ";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                       // logger.error("Wprowadzono niepoprawny format danych ");
                    }
                }
            });

        });
        b3.setOnAction(event -> {
            String message1 = "Remove. \n Enter your data:(first name, last name, pesel, email)";
            label.setText(message1);
            topMenu.getChildren().addAll(label, text1, text2, text3, text4, b6);

            b6.setOnAction(e -> {
/*
                if (text1.getText() != null && text2.getText() != null && text3.getText() != null && text4.getText() != null) {

                    Client c = new Client(text1.getText(), text2.getText(), text3.getText(), text4.getText());*/

                if(text1.getText()!=null){
                    ClientDaoImpl clientDao = new ClientDaoImpl();
                    try {
                        clientDao.delete(Integer.parseInt(text1.getText()));

                        String war = "Removed";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                    } catch (DataAccessException ex) {
                        System.out.println("proba");
                    }catch ( NumberFormatException ex) {
                        String war = "Wprowadzono niepoprawny format danych ";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                        // logger.error("Wprowadzono niepoprawny format danych ");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            });

        });


    Scene scene = new Scene(borderpane, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clear(){

        topMenu.getChildren().remove(text1);
        topMenu.getChildren().remove(text2);
        topMenu.getChildren().remove(text3);
        topMenu.getChildren().remove(text4);
        topMenu.getChildren().remove(label);
        topMenu.getChildren().remove(warlab);
        topMenu.getChildren().remove(b6);

    }


    public void handle(ActionEvent event) {

    }
}

