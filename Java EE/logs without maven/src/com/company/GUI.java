package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.IOException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GUI extends Application implements EventHandler<ActionEvent> {

    private static final Logger log = LogManager.getLogger();

    private Button b1, b2, b3, b4, b5, b6, b7;
    private BankImpl bank = new BankImpl();
    private VBox topMenu = new VBox();
    private Label label = new Label();
    private Label warlab = new Label();
    private TextField text1 = new TextField();
    private TextField text2 = new TextField();
    private TextField text3 = new TextField();

//    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.setTitle("Title");

        b1 = new Button("Znajdź konto");
        b2 = new Button("Utwórz konto");
        b3 = new Button("Wypłać kwotę");
        b4 = new Button("Wpłać kwotę");
        b5 = new Button("Przelej kwotę");
        b6 = new Button("Sprawdz stan konta");
        b7 = new Button("Gotowe");

        b1.setPrefWidth(1000);
        b2.setPrefWidth(1000);
        b3.setPrefWidth(1000);
        b4.setPrefWidth(1000);
        b5.setPrefWidth(1000);
        b6.setPrefWidth(1000);
        b7.setPrefWidth(1000);

        topMenu.getChildren().addAll(b1,b2,b3,b4,b5,b6);
        String message;

        BorderPane borderpane = new BorderPane();
        borderpane.setCenter(topMenu);




     b1.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {

             if (event.getSource() == b1) {
                 clear();

                 String message = new String("wprowadz nazwe konta oraz adres");
                 label.setText(message);
                 topMenu.getChildren().addAll(label, text1, text2,b7);

                 b7.setOnAction(e->
                 {
                     clear();

                     if((bank.findAccount(text1.getText(),text2.getText()))!=null) {
                         String message2=new String("znaleziono konto");
                         warlab.setText(message2);
                         topMenu.getChildren().add(warlab);
                         log.info("opis");
                     }
                     else {
                         String message2=new String("nie znaleziono konta");
                         warlab.setText(message2);
                         topMenu.getChildren().add(warlab);
                     }

                 });
             }
         }
     });

     b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                clear();

                if (event.getSource() == b2) {

                    String message = new String("wprowadz nazwe konta oraz adres");
                    label.setText(message);
                    clear();
                    topMenu.getChildren().addAll(label, text1, text2,b7);

                    b7.setOnAction(e->
                    {
                        clear();

                        if((bank.createAccount(text1.getText(),text2.getText()))!=null) {
                            String message2=new String("utworzono konto");
                            warlab.setText(message2);
                            topMenu.getChildren().add(warlab);
                        }

                    });
                }
            }
        });


     b3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    clear();

                    if (event.getSource() == b3) {

                        String message = new String("Wprowadz id konta oraz kwotę");
                        label.setText(message);
                        topMenu.getChildren().addAll(label, text1, text2,b7);

                        b7.setOnAction(e->
                        {
                            clear();
                            try {
                                bank.withdraw(Integer.parseInt(text1.getText()), Long.parseLong(text2.getText()));
                                String message1=new String("Wypłacono");
                                warlab.setText(message1);
                                topMenu.getChildren().add(warlab);

                            } catch (Bank.InsufficientFundsException ex) {
                                String war = new String ("Brak wystarczajacych środków na koncie");
                                warlab.setText(war);
                                topMenu.getChildren().add(warlab);
                            }catch ( Bank.AccountIdException ex){
                                String war = new String ("Brak konta o podanym id");
                                warlab.setText(war);
                                topMenu.getChildren().add(warlab);
                            }

                        });
                    }
                }
            });

     b4.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                  clear();

                if (event.getSource() == b4) {

                    String message = new String("Wprowadz id konta oraz kwotę");
                    label.setText(message);
                    topMenu.getChildren().addAll(label, text1, text2,b7);

                    b7.setOnAction(e->
                    {
                        clear();
                        try {
                            bank.deposit(Integer.parseInt(text1.getText()), Long.parseLong(text2.getText()));
                            String message1=new String("Na konto wpłynęła nowe środki");
                            warlab.setText(message1);
                            topMenu.getChildren().add(warlab);
                        } catch ( Bank.AccountIdException ex){

                            String war = new String ("Brak konta o podanym id");
                            warlab.setText(war);
                            topMenu.getChildren().add(warlab);
                        }
                    });
                }
            }
        });

     b5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clear();
                if (event.getSource() == b5) {
                    String message = new String("Wprowadz id kont oraz kwotę");
                    label.setText(message);
                    topMenu.getChildren().addAll(label, text1, text2, text3, b7);

                    b7.setOnAction(e-> {
                        clear();
                        try {
                            bank.transfer(Integer.parseInt(text1.getText()),Integer.parseInt(text2.getText()), Long.parseLong(text3.getText()));
                            String message2=new String("Operacja przebiegła pomyślnie");
                            warlab.setText(message2);
                            topMenu.getChildren().add(warlab);
                        } catch ( Bank.AccountIdException ex){
                            String war = new String ("Brak konta o podanym id");
                            warlab.setText(war);
                            topMenu.getChildren().add(warlab);
                        } catch (Bank.InsufficientFundsException ex){
                            String war = new String ("Brak wystarczających środków na koncie");
                            warlab.setText(war);
                            topMenu.getChildren().add(warlab);
                        }
                    });
                }
            }
        });


        b6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clear();
                if (event.getSource() == b6) {
                    String message = new String("Wprowadz id konta");
                    clear();
                    label.setText(message);
                    topMenu.getChildren().addAll(label, text1, b7);

                    b7.setOnAction(e-> {
                        clear();
                        try {
                            Integer amount = new Integer(Integer.parseInt(text1.getText()));
                            warlab.setText(Long.toString(bank.getBalance(amount)));
                            topMenu.getChildren().add(warlab);
                        } catch ( Bank.AccountIdException ex){
                            String war = new String ("Brak konta o podanym id");
                            warlab.setText(war);
                            topMenu.getChildren().add(warlab);
                        }
                    });
                }
            }
        });


        Scene scene = new Scene(borderpane, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void clear(){

        topMenu.getChildren().remove(text1);
        topMenu.getChildren().remove(text2);
        topMenu.getChildren().remove(text3);
        topMenu.getChildren().remove(label);
        topMenu.getChildren().remove(warlab);
        topMenu.getChildren().remove(b7);

    }
    @Override
    public void handle(ActionEvent event) {



    }

}
