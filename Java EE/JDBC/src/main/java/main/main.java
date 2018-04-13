package main;

import dao.AccountDao;
import dao.AccountDaoImpl;
import dao.ClientDaoImpl;
import exceptions.DataAccessException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Account;
import models.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class main extends Application implements EventHandler<ActionEvent> {

    private Label label1 = new Label();
    private Label label2 = new Label();
    private Label label3 = new Label();
    private Label warlab = new Label();

    private TextField text1 = new TextField();
    private TextField text2 = new TextField();
    private TextField text3 = new TextField();
    private TextField text4 = new TextField();
    private VBox topMenu = new VBox();
    private Button b6, b7, b8 ;
    private ListView listView = null;

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
        b7 = new Button("Client");
        b8 = new Button("Account");


        b1.setPrefWidth(1000);
        b2.setPrefWidth(1000);
        b3.setPrefWidth(1000);
        b4.setPrefWidth(1000);
        b5.setPrefWidth(1000);
        b6.setPrefWidth(1000);
        b7.setPrefWidth(1000);
        b8.setPrefWidth(1000);


        String title1 = " Menu ";
        label1.setText(title1);
        label1.setFont(Font.font ("Times New Roman" , 40));
        label1.setTextFill(Color.CORAL);
        label1.setTranslateX(165);

        topMenu.getChildren().addAll(label1,b1,b2,b3,b4,b5);

        BorderPane borderpane = new BorderPane();
        borderpane.setCenter(topMenu);

        b1.setOnAction(event -> {

                    clear();
                    String title = "Choose one";
                    label2.setText(title);
                    label2.setFont(Font.font ("Times New Roman" , 20));
                    label2.setTextFill(Color.CORAL);
                    label2.setTranslateX(170);

                    topMenu.getChildren().addAll(label2, b7, b8);

                    b7.setOnAction(e -> {
                        clear();
                        String message1 = "Save Client. \n Enter your data:(first name, last name, pesel, email ";
                        label3.setText(message1);
                        label3.setFont(Font.font ("Times New Roman" , 20));
                        label3.setTextFill(Color.CORAL);
                        //label3.setTranslateX(100);
                        topMenu.getChildren().addAll(label3, text1, text2, text3, text4, b6);

                        b6.setOnAction(ev -> {
                            clear();

                            if (text1.getText() != null && text2.getText() != null && text3.getText() != null && text4.getText() != null) {

                                Client c = new Client(text1.getText(), text2.getText(), text3.getText(), text4.getText());
                                ClientDaoImpl clientDao = new ClientDaoImpl();
                                try {
                                    clientDao.save(c);

                                    String war = "Saved ";
                                    warlab.setText(war);
                                    warlab.setFont(Font.font ("Times New Roman" , 20));
                                    warlab.setTextFill(Color.GREEN);
                                    topMenu.getChildren().add(warlab);

                                } catch (DataAccessException ex) {
                                    String war = "No access ";
                                    warlab.setText(war);
                                    warlab.setFont(Font.font ("Times New Roman" , 20));
                                    warlab.setTextFill(Color.RED);
                                    topMenu.getChildren().add(warlab);

                                } catch (SQLException e1) {
                                    String war = " Error ";
                                    warlab.setText(war);
                                    warlab.setFont(Font.font ("Times New Roman" , 20));
                                    warlab.setTextFill(Color.RED);
                                    topMenu.getChildren().add(warlab);
                                } catch (Exception e)
                                {
                                    String war = " Error ";
                                    warlab.setText(war);
                                    warlab.setFont(Font.font ("Times New Roman" , 20));
                                    warlab.setTextFill(Color.RED);
                                    topMenu.getChildren().add(warlab);
                                }

                            }
                        });
                    });

                    b8.setOnAction(e -> {
                        clear();
                        String message1 = "Save Account. \n Enter your data:(Notes, Balance, Client ID)";
                        label3.setText(message1);
                        label3.setFont(Font.font ("Times New Roman" , 20));
                        label3.setTextFill(Color.CORAL);
                        //label3.setTranslateX(100);

                        topMenu.getChildren().addAll(label3, text1, text2, text3, b6);

                        b6.setOnAction(ev -> {
                            clear();

                            if (text1.getText() != null && text2.getText() != null && text3.getText() != null) {
                                try {
                                    Account a = new Account(text1.getText(), Long.parseLong(text2.getText()),Integer.parseInt(text3.getText()));
                                    AccountDaoImpl accountDao = new AccountDaoImpl();
                                    accountDao.save(a);

                                    String war = "Saved ";
                                    warlab.setText(war);
                                    warlab.setFont(Font.font ("Times New Roman" , 20));
                                    warlab.setTextFill(Color.GREEN);
                                    topMenu.getChildren().add(warlab);

                                } catch (NumberFormatException ex) {
                                    String war = "Incorrect Data Format ";
                                    warlab.setText(war);
                                    warlab.setFont(Font.font ("Times New Roman" , 20));
                                    warlab.setTextFill(Color.RED);
                                    topMenu.getChildren().add(warlab);
                                }catch (DataAccessException ex) {
                                    String war = "No access ";
                                    warlab.setText(war);
                                    warlab.setFont(Font.font ("Times New Roman" , 20));
                                    warlab.setTextFill(Color.RED);
                                    topMenu.getChildren().add(warlab);
                                }
                            }
                        });
                    });
                });


        b2.setOnAction(event -> {

            clear();
            String title = "Choose one";
            label2.setText(title);
            label2.setFont(Font.font ("Times New Roman" , 20));
            label2.setTextFill(Color.CORAL);
            label2.setTranslateX(170);

            topMenu.getChildren().addAll(label2, b7, b8);

            //CLIENT
            b7.setOnAction(e -> {

                clear();
                String message1 = "Update. \n Enter your id)";
                label3.setText(message1);
                label3.setFont(Font.font ("Times New Roman" , 20));
                label3.setTextFill(Color.CORAL);
                topMenu.getChildren().addAll(label3, text1, b6);

                b6.setOnAction(ev -> {
                    clear();
                    if (text1.getText() != null){
                        ClientDaoImpl clientDao = new ClientDaoImpl();
                        try {
                            clientDao.update(clientDao.findById(Integer.parseInt(text1.getText())));

                            String war = "Updated ";
                            warlab.setText(war);
                            warlab.setFont(Font.font ("Times New Roman" , 20));
                            warlab.setTextFill(Color.GREEN);
                            topMenu.getChildren().add(warlab);
                        }catch (NullPointerException ex){
                            String war = " No account with given id";
                            warlab.setText(war);
                            warlab.setFont(Font.font ("Times New Roman" , 20));
                            warlab.setTextFill(Color.RED);
                            topMenu.getChildren().add(warlab);
                        }catch ( NumberFormatException ex) {
                            String war = " Incorrect Data Format";
                            warlab.setText(war);
                            warlab.setFont(Font.font ("Times New Roman" , 20));
                            warlab.setTextFill(Color.RED);
                            topMenu.getChildren().add(warlab);
                            // logger.error("Wprowadzono niepoprawny format danych ");
                        } catch (DataAccessException ex) {
                            String war = " No access";
                            warlab.setText(war);
                            warlab.setFont(Font.font ("Times New Roman" , 20));
                            warlab.setTextFill(Color.RED);
                            topMenu.getChildren().add(warlab);
                        }
                    }
                });

            });
            //ACCOUNT
            b8.setOnAction(e -> {
                clear();
                String message1 = "Update Account. \n Enter your Account ID";
                label3.setText(message1);
                label3.setFont(Font.font ("Times New Roman" , 20));
                label3.setTextFill(Color.CORAL);

                topMenu.getChildren().addAll(label3, text1, b6);

                b6.setOnAction(ev -> {
                    clear();

                    if (text1.getText() != null ) {
                        AccountDaoImpl accountDao = new AccountDaoImpl();
                        try {
                            accountDao.update(accountDao.findById(Integer.parseInt(text1.getText())));

                            String war = "Updated ";
                            warlab.setText(war);
                            warlab.setFont(Font.font ("Times New Roman" , 20));
                            warlab.setTextFill(Color.GREEN);
                            topMenu.getChildren().add(warlab);

                        } catch (NullPointerException ex){
                            String war = " No account with given id";
                            warlab.setText(war);
                            warlab.setFont(Font.font ("Times New Roman" , 20));
                            warlab.setTextFill(Color.RED);
                            topMenu.getChildren().add(warlab);

                        } catch (NumberFormatException ex ){
                            String war = " Incorrect Data Format ";
                            warlab.setText(war);
                            warlab.setFont(Font.font ("Times New Roman" , 20));
                            warlab.setTextFill(Color.RED);
                            topMenu.getChildren().add(warlab);
                        }catch (DataAccessException ex) {
                            String war = "No access ";
                            warlab.setText(war);
                            warlab.setFont(Font.font ("Times New Roman" , 20));
                            warlab.setTextFill(Color.RED);
                            topMenu.getChildren().add(warlab);
                        }
                    }
                });
            });
        });




        b3.setOnAction(event -> {
            clear();
            String title = "Choose one";
            label2.setText(title);
            label2.setFont(Font.font ("Times New Roman" , 20));
            label2.setTextFill(Color.CORAL);
            label2.setTranslateX(170);

            topMenu.getChildren().addAll(label2, b7, b8);

            b7.setOnAction(e -> {

                clear();
                String message1 = "Delete Client. \n Enter your id)";
                label3.setText(message1);
                label3.setFont(Font.font ("Times New Roman" , 20));
                label3.setTextFill(Color.CORAL);
                topMenu.getChildren().addAll(label3, text1, b6);


                b6.setOnAction(ev -> {
                    clear();
                    if (text1.getText() != null) try {
                        ClientDaoImpl clientDao = new ClientDaoImpl();
                        clientDao.delete(clientDao.findById(Integer.parseInt(text1.getText())));

                        String war = "Removed";
                        warlab.setText(war);
                        warlab.setFont(Font.font ("Times New Roman" , 20));
                        warlab.setTextFill(Color.GREEN);
                        topMenu.getChildren().add(warlab);
                    }catch (NumberFormatException ex) {
                        String war = "Incorrect Data Format ";
                        warlab.setText(war);
                        warlab.setFont(Font.font ("Times New Roman" , 20));
                        warlab.setTextFill(Color.RED);
                        topMenu.getChildren().add(warlab);
                    }catch (NullPointerException ex){
                        String war = " No account with given id";
                        warlab.setText(war);
                        warlab.setFont(Font.font ("Times New Roman" , 20));
                        warlab.setTextFill(Color.RED);
                        topMenu.getChildren().add(warlab);

                    } catch (DataAccessException ex) {
                        String war = "No Access ";
                        warlab.setText(war);
                        warlab.setFont(Font.font ("Times New Roman" , 20));
                        warlab.setTextFill(Color.RED);
                        topMenu.getChildren().add(warlab);
                    }   catch (SQLException e1) {
                        String war = " Something went wrong ";
                        warlab.setText(war);
                        warlab.setFont(Font.font ("Times New Roman" , 20));
                        warlab.setTextFill(Color.RED);
                        topMenu.getChildren().add(warlab);
                    }
                });
            });
            b8.setOnAction(e -> {

                clear();
                String message1 = "Delete Account. \n Enter your Account ID";
                label3.setText(message1);
                label3.setFont(Font.font ("Times New Roman" , 20));
                label3.setTextFill(Color.CORAL);
                topMenu.getChildren().addAll(label3, text1, b6);


                b6.setOnAction(ev -> {
                    clear();
                    if (text1.getText() != null)
                        try {
                        AccountDaoImpl accountDao = new AccountDaoImpl();
                        accountDao.delete(accountDao.findById(Integer.parseInt(text1.getText())));

                        String war = "Removed";
                        warlab.setText(war);
                        warlab.setFont(Font.font ("Times New Roman" , 20));
                        warlab.setTextFill(Color.GREEN);
                        topMenu.getChildren().add(warlab);
                    } catch (NumberFormatException ex) {
                            String war = "Wprowadzono niepoprawny format danych ";
                            warlab.setText(war);
                            warlab.setFont(Font.font ("Times New Roman" , 20));
                            warlab.setTextFill(Color.RED);
                            topMenu.getChildren().add(warlab);
                        } catch (NullPointerException ex){
                            String war = " No account with given id";
                            warlab.setText(war);
                            warlab.setFont(Font.font ("Times New Roman" , 20));
                            warlab.setTextFill(Color.RED);
                            topMenu.getChildren().add(warlab);
                        }catch (DataAccessException ex) {
                            String war = " Brak dostępu ";
                            warlab.setText(war);
                            topMenu.getChildren().add(warlab);
                    }
                });
            });

        });

        b4.setOnAction(event -> {
                clear();
                String title = "Choose one";
                label2.setText(title);
                label2.setFont(Font.font ("Times New Roman" , 20));
                label2.setTextFill(Color.CORAL);
                label2.setTranslateX(170);

                topMenu.getChildren().addAll(label2, b7, b8);

                b7.setOnAction(e -> {

                    clear();
                    String message1 = "Find Client \n Enter your id)";
                    label3.setText(message1);
                    label3.setFont(Font.font ("Times New Roman" , 20));
                    label3.setTextFill(Color.CORAL);
                    topMenu.getChildren().addAll(label3, text1, b6);


                    b6.setOnAction(ev -> {
                        clear();
                        if (text1.getText() != null)
                            try {
                            ClientDaoImpl clientDao = new ClientDaoImpl();

                            String war = clientDao.findById(Integer.parseInt(text1.getText())).toString();
                            warlab.setText(war);
                            warlab.setFont(Font.font ("Times New Roman" , 20));
                            warlab.setTextFill(Color.GREEN);
                            topMenu.getChildren().add(warlab);
                        }catch (NumberFormatException ex) {
                                String war = " Incorrect Data Format";
                                warlab.setText(war);
                                warlab.setFont(Font.font ("Times New Roman" , 20));
                                warlab.setTextFill(Color.RED);
                                topMenu.getChildren().add(warlab);

                            }catch (NullPointerException ex){
                                String war = " No account with given id";
                                warlab.setText(war);
                                warlab.setFont(Font.font ("Times New Roman" , 20));
                                warlab.setTextFill(Color.RED);
                                topMenu.getChildren().add(warlab);

                            } catch (DataAccessException ex) {
                                String war = " No Access ";
                                warlab.setText(war);
                                warlab.setFont(Font.font ("Times New Roman" , 20));
                                warlab.setTextFill(Color.RED);
                                topMenu.getChildren().add(warlab);
                        }
                    });
                });
                b8.setOnAction(e -> {

                    clear();
                    String message1 = "Find Account. \n Enter your Account ID";
                    label3.setText(message1);
                    label3.setFont(Font.font ("Times New Roman" , 20));
                    label3.setTextFill(Color.CORAL);
                    topMenu.getChildren().addAll(label3, text1, b6);


                    b6.setOnAction(ev -> {
                        clear();
                        if (text1.getText() != null)
                            try {
                            AccountDaoImpl accountDao = new AccountDaoImpl();

                            String war = accountDao.findById(Integer.parseInt(text1.getText())).toString();
                            warlab.setText(war);
                            warlab.setFont(Font.font ("Times New Roman" , 20));
                            warlab.setTextFill(Color.GREEN);
                            topMenu.getChildren().add(warlab);
                            }catch (NumberFormatException ex) {
                                String war = "Incorrect Data Format ";
                                warlab.setText(war);
                                warlab.setText(war);
                                warlab.setFont(Font.font ("Times New Roman" , 20));
                                warlab.setTextFill(Color.RED);
                                topMenu.getChildren().add(warlab);
                            }catch (NullPointerException ex){
                                String war = " No account with given id";
                                warlab.setText(war);
                                warlab.setFont(Font.font ("Times New Roman" , 20));
                                warlab.setTextFill(Color.RED);
                                topMenu.getChildren().add(warlab);

                            } catch (DataAccessException ex) {
                                String war = "No Access ";
                                warlab.setText(war);
                                warlab.setFont(Font.font ("Times New Roman" , 20));
                                warlab.setTextFill(Color.RED);
                                topMenu.getChildren().add(warlab);
                        }
                    });
                });

            });





        b5.setOnAction(event -> {
            clear();
            String title = "Choose one";
            label2.setText(title);
            label2.setFont(Font.font ("Times New Roman" , 20));
            label2.setTextFill(Color.CORAL);
            label2.setTranslateX(170);

            topMenu.getChildren().addAll(label2, b7, b8);

            b7.setOnAction(e-> {
                clear();
            try{
                ClientDaoImpl clientDao = new ClientDaoImpl();
                //clientDao.findAll();
                    if(clientDao.findAll().isEmpty()) {
                        String war = "Clients list is empty ";
                        warlab.setText(war);
                        warlab.setFont(Font.font ("Times New Roman" , 20));
                        warlab.setTextFill(Color.GREEN);
                        topMenu.getChildren().add(warlab);
                    }
                        else {
                        clear();
                        listView = new ListView();
                        listView.getItems().addAll(clientDao.findAll());
                        topMenu.getChildren().addAll(listView);
                        listView.getItems().removeAll();
                    }
                } catch (NumberFormatException ex) {
                    String war = " Incorrect Data Format ";
                    warlab.setText(war);
                    warlab.setFont(Font.font ("Times New Roman" , 20));
                    warlab.setTextFill(Color.RED);
                    topMenu.getChildren().add(warlab);
                }  catch (DataAccessException ex) {
                    String war = " Incorrect Data Format ";
                    warlab.setText(war);
                    warlab.setFont(Font.font ("Times New Roman" , 20));
                    warlab.setTextFill(Color.RED);
                    topMenu.getChildren().add(warlab);
                }
            });

            b8.setOnAction(e-> {
                clear();
                try{
                    AccountDaoImpl accountDao = new AccountDaoImpl();
                    if(accountDao.findAll().isEmpty())
                    {
                        String war = "Accounts list is empty ";
                        warlab.setText(war);
                        warlab.setFont(Font.font ("Times New Roman" , 20));
                        warlab.setTextFill(Color.GREEN);
                        topMenu.getChildren().add(warlab);}
                    else {
                        clear();
                        listView = new ListView();
                        listView.getItems().addAll(accountDao.findAll());
                        topMenu.getChildren().addAll(listView);
                        listView.getItems().removeAll();

                        }
                }catch (NumberFormatException ex) {
                    String war = " Incorrect Data Format ";
                    warlab.setText(war);
                    warlab.setFont(Font.font ("Times New Roman" , 20));
                    warlab.setTextFill(Color.RED);
                    topMenu.getChildren().add(warlab);
                }catch (DataAccessException ex) {
                    String war = " Incorrect Data Format ";
                    warlab.setText(war);
                    warlab.setFont(Font.font ("Times New Roman" , 20));
                    warlab.setTextFill(Color.RED);
                    topMenu.getChildren().add(warlab);
                }
            });
    });

    Scene scene = new Scene(borderpane, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clear() {
        topMenu.getChildren().remove(listView);
        topMenu.getChildren().remove(text1);
        topMenu.getChildren().remove(text2);
        topMenu.getChildren().remove(text3);
        topMenu.getChildren().remove(text4);
        //  topMenu.getChildren().remove(label1);
        topMenu.getChildren().remove(label2);
        topMenu.getChildren().remove(label3);
        topMenu.getChildren().remove(warlab);
        topMenu.getChildren().remove(b6);
        topMenu.getChildren().remove(b7);
        topMenu.getChildren().remove(b8);

    }


    public void handle(ActionEvent event) {

    }
}

