package Project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GUI extends Application implements EventHandler<ActionEvent> {

    private Button b7;
    private BankImpl bank = new BankImpl();
    private VBox topMenu = new VBox();
    private Label label = new Label();
    private Label warlab = new Label();
    private TextField text1 = new TextField();
    private TextField text2 = new TextField();
    private TextField text3 = new TextField();

    private static final Logger logger = LogManager.getLogger(GUI.class);

    public static void main(String[] args) {
        logger.info("Uruchomienie aplikacji");
        launch(args);
        logger.info("Zakończenie pracy aplikacji");
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Bank");

        Button b1 = new Button("Znajdź konto");
        Button b2 = new Button("Utwórz konto");
        Button b3 = new Button("Wypłać kwotę");
        Button b4 = new Button("Wpłać kwotę");
        Button b5 = new Button("Przelej kwotę");
        Button b6 = new Button("Sprawdz stan konta");
        b7 = new Button("Gotowe");

        b1.setPrefWidth(1000);
        b2.setPrefWidth(1000);
        b3.setPrefWidth(1000);
        b4.setPrefWidth(1000);
        b5.setPrefWidth(1000);
        b6.setPrefWidth(1000);
        b7.setPrefWidth(1000);

        topMenu.getChildren().addAll(b1,b2,b3,b4,b5,b6);

        BorderPane borderpane = new BorderPane();
        borderpane.setCenter(topMenu);


        b1.setOnAction(event ->{
            clear();

                logger.info("wybranie opcji szukania konta");
                String message17 = "wprowadz nazwe konta oraz adres";
                label.setText(message17);
                topMenu.getChildren().addAll(label, text1, text2,b7);

                b7.setOnAction(e->{
                    clear();

                    if((bank.findAccount(text1.getText(),text2.getText()))!=null) {
                        String message2="znaleziono konto";
                        warlab.setText(message2);
                        topMenu.getChildren().add(warlab);
                        logger.debug("znaleziono konto");
                    }
                    else {
                        String message2= "nie znaleziono konta";
                        warlab.setText(message2);
                        topMenu.getChildren().add(warlab);
                        logger.debug("nie znaleziono konta");
                    }
                });
        });

        b2.setOnAction(event ->{
            clear();
                logger.info("wybranie opcji tworzenia konta");

                String message16 = "wprowadz nazwe konta oraz adres";
                label.setText(message16);
                clear();
                topMenu.getChildren().addAll(label, text1, text2,b7);

                b7.setOnAction(e->{
                    clear();

                    if((bank.createAccount(text1.getText(),text2.getText()))!=null) {
                        String message2= "utworzono konto";
                        warlab.setText(message2);
                        topMenu.getChildren().add(warlab);
                        logger.debug("utworzono konto");
                    }
                });
        });

        b3.setOnAction(event -> {
            clear();
                logger.info("wybranie opcji wypłaty gotówki");

                String message15 = "Wprowadz id konta oraz kwotę";
                label.setText(message15);
                topMenu.getChildren().addAll(label, text1, text2,b7);

                b7.setOnAction(e->
                {
                    clear();
                    try {
                        bank.withdraw(Integer.parseInt(text1.getText()), Long.parseLong(text2.getText()));
                        String message1= "Wypłacono";
                        warlab.setText(message1);
                        topMenu.getChildren().add(warlab);
                        logger.debug("wypłacono gotówkę");

                    } catch (Bank.InsufficientFundsException ex) {
                        String war = "Brak wystarczajacych środków na koncie";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                        logger.error("Brak wystarczających środków na koncie, operacja przerwana");
                    }catch ( Bank.AccountIdException ex){
                        String war = "Brak konta o podanym id";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                        logger.error("Brak konta o podanym id, operacja przerwana");
                    }catch ( NumberFormatException ex) {
                        String war = "Wprowadzono niepoprawny format danych ";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                        logger.error("Wprowadzono niepoprawny format danych ");
                    }
                });
        });

        b4.setOnAction(event -> {
            clear();
                logger.info("wybranie opcji wpłaty gotówki");

                String message14 = "Wprowadz id konta oraz kwotę";
                label.setText(message14);
                topMenu.getChildren().addAll(label, text1, text2,b7);

                b7.setOnAction(e->
                {
                    clear();
                    try {
                        bank.deposit(Integer.parseInt(text1.getText()), Long.parseLong(text2.getText()));
                        String message1= "Na konto wpłynęła nowe środki";
                        warlab.setText(message1);
                        topMenu.getChildren().add(warlab);
                        logger.debug("Na konto wpłynęły nowe srodki");
                    } catch ( Bank.AccountIdException ex){
                        String war = "Brak konta o podanym id";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                        logger.error("Brak konta o podanym id, operacja przerwana");
                    }catch ( NumberFormatException ex) {
                        String war = "Wprowadzono niepoprawny format danych ";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                        logger.error("Wprowadzono niepoprawny format danych ");
                    }
                });
        });

        b5.setOnAction(event -> {
            clear();
                logger.info("wybranie opcji przelew");
                String message13 = "Wprowadz id kont oraz kwotę";
                label.setText(message13);
                topMenu.getChildren().addAll(label, text1, text2, text3, b7);

                b7.setOnAction(e-> {
                    clear();
                    try {
                        bank.transfer(Integer.parseInt(text1.getText()),Integer.parseInt(text2.getText()),Long.parseLong(text3.getText()));
                        String message2= "Operacja przebiegła pomyślnie";
                        warlab.setText(message2);
                        topMenu.getChildren().add(warlab);
                        logger.debug("Operacja przebiegła pomyślnie");
                    } catch ( Bank.AccountIdException ex){
                        String war = "Brak konta o podanym id";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                        logger.error("Brak konta o podanym id, operacja przerwana");
                    } catch (Bank.InsufficientFundsException ex){
                        String war = "Brak wystarczających środków na koncie";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                        logger.error("Brak wystarczających środków na koncie, operacja przerwana");
                    }catch ( NumberFormatException ex) {
                        String war = "Wprowadzono niepoprawny format danych ";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                        logger.error("Wprowadzono niepoprawny format danych ");
                    }
                });
        });


        b6.setOnAction(event -> {
            clear();
                logger.info("wybranie opcji sprawdź stan konta");
                String message12 = "Wprowadz id konta";
                clear();
                label.setText(message12);
                topMenu.getChildren().addAll(label, text1, b7);

                b7.setOnAction(e-> {
                    clear();
                    try {
                        Integer amount = Integer.parseInt(text1.getText());
                        warlab.setText(Long.toString(bank.getBalance(amount)));
                        topMenu.getChildren().add(warlab);
                        logger.debug("Operacja przebiegła pomyślnie");
                    } catch ( Bank.AccountIdException ex){
                        String war = "Brak konta o podanym id";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                        logger.error("Brak konta o podanym id, operacja przerwana");
                    }catch ( NumberFormatException ex) {
                        String war = "Wprowadzono niepoprawny format danych ";
                        warlab.setText(war);
                        topMenu.getChildren().add(warlab);
                        logger.error("Wprowadzono niepoprawny format danych ");
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
        topMenu.getChildren().remove(label);
        topMenu.getChildren().remove(warlab);
        topMenu.getChildren().remove(b7);

    }

    @Override
    public void handle(ActionEvent event) { }
}

