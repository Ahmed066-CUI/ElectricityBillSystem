package com.example.electricitybill;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;

public class SearchBill {
    private static String FILENAME = "ElectricityBill.txt";
    private Stage stage;

    public void show(Stage stage) {
        this.stage = stage;
        Image backgroundImage = new Image("Bill.png");
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitWidth(800);
        imageView.setFitHeight(800);
        imageView.setPreserveRatio(true);

        Label title = new Label("Search a previous Bill");
        title.setStyle(
                "-fx-font-weight: bold;" +
                        "-fx-font-size: 30px;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-color: linear-gradient(to right, #36adda, #097fbd);" +
                        "-fx-padding: 20px;" +
                        "-fx-border-color: #ffffff;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-alignment: center;"
        );
        //  title.setStyle("-fx-text-fill: #090707; -fx-font-weight: bold; -fx-font-size: 40px;");
        title.setAlignment(Pos.TOP_CENTER);

        Label id = new Label("Enter Customer Id:");
        id.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");

        TextField customerIdField = new TextField();
        customerIdField.setPromptText("Enter your Customer Id");

        Button searchButton = new Button("Search Bill");
        Button backButton = new Button("Back to Main Menu");
        styleButtons(searchButton);
        styleButtons(backButton);

        HBox hBox = new HBox(20, id, customerIdField);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);

        HBox hBox2 = new HBox(20, searchButton, backButton);

        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(50);


        VBox vBox = new VBox(25,title ,hBox,hBox2);
        vBox.setAlignment(Pos.CENTER);





        searchButton.setOnAction(e -> {
            vBox.getChildren().removeIf(node -> node instanceof VBox);//new thing
            String customerId = customerIdField.getText();

            if (customerId.isEmpty()) {
                showAlertError();
                return;
            }
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                boolean found = false;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains(customerId)) {


                        found = true;


                    }
                    if(found){
                        stringBuilder.append(line).append("\n");
                        if(line.trim().equals("-------------------------"))
                        break;

                    }
                }
                    if (found) {

                        Label billDetailsLabel = new Label("Bill Details:");
                        billDetailsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: white;");

                        TextArea billDetailsArea = new TextArea(stringBuilder.toString());
                        billDetailsArea.setEditable(false);
                        billDetailsArea.setWrapText(true);
                        billDetailsArea.setMaxWidth(500);
                        billDetailsArea.setMaxHeight(200);

                        VBox billDetailsBox = new VBox(10, billDetailsLabel, billDetailsArea);
                        billDetailsBox.setAlignment(Pos.CENTER);
                        billDetailsBox.setPadding(new Insets(20));
                       // billDetailsBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-border-radius: 10px; -fx-background-radius: 10px;");


                        vBox.getChildren().add(billDetailsBox);

                    }



                if (!found) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                   // alert.setGraphic();
                    alert.setContentText("No bill found for the entered Customer Id.");
                    alert.showAndWait();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }

            customerIdField.clear();


        });


        backButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(stage);
        });


        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, vBox);

        Scene scene = new Scene(stackPane, 600, 600);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    private void styleButtons(Button button) {
        button.setStyle("-fx-background-color: #36adda; -fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 15px");
        button.setEffect(new DropShadow());
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #097fbd; -fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 15px"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #36adda; -fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 15px"));
    }

    private void showAlertError() {
        Alert.AlertType alertType = Alert.AlertType.ERROR;
        Alert alert = new Alert(alertType);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Field cannot be empty");
        alert.showAndWait();
    }

}
