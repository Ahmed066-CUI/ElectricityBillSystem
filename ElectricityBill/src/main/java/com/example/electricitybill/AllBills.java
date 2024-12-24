package com.example.electricitybill;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;

public class AllBills {
    private Stage stage;
    private static final String FILENAME = "ElectricityBill.txt";

    public void show(Stage stage) {
        this.stage = stage;
        Image backgroundImage = new Image("Bill.png");
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitWidth(800);
        imageView.setFitHeight(800);
        imageView.setPreserveRatio(true);

        Label title = new Label("All Bills");
       // title.setStyle("-fx-text-fill: #090707; -fx-font-weight: bold; -fx-font-size: 40px;");
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
        title.setAlignment(Pos.TOP_CENTER);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        textArea.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;-fx-background-color: #36adda");
        textArea.setWrapText(true);



        Button back = new Button("Back to Main Menu");
        styleButtons(back);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            boolean found = false;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
                found = true;
                }
            if (found) {
                textArea.setText(stringBuilder.toString());
            }



            if (!found) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No Bills Found");
                alert.showAndWait();
                return;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        back.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(stage);
        });
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll( title, textArea, back);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView,vBox);

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
}

