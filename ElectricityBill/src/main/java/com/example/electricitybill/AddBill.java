
package com.example.electricitybill;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class AddBill {
    private Stage stage;
    private static final String FILENAME = "ElectricityBill.txt";

    public void show(Stage stage) {
        this.stage = stage;

        Image backgroundImage = new Image("Bill.png");
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitWidth(800);
        imageView.setFitHeight(800);
        imageView.setPreserveRatio(true);

        Label title = new Label("Add a new Bill");
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

        Label id = new Label("Customer Id:");
        Label name = new Label("Customer Name:");
        Label unit = new Label("Unit Consumed:");
        Label price = new Label("Unit Price:");
        Label bill = new Label("Total Bill:");
        styleLabels(id);
        styleLabels(name);
        styleLabels(unit);
        styleLabels(price);
        styleLabels(bill);

        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField unitField = new TextField();
        TextField priceField = new TextField();
        TextField billField = new TextField();

        Button button = new Button("Save Bill");
        Button button2 = new Button("Back to Main Menu");

        styleButtons(button);
        styleButtons(button2);


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(id, 0, 0);
        grid.add(idField, 1, 0);
        grid.add(name, 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(unit, 0, 2);
        grid.add(unitField, 1, 2);
        grid.add(price, 0, 3);
        grid.add(priceField, 1, 3);
        grid.add(bill, 0, 4);
        grid.add(billField, 1, 4);
        grid.add(button, 0, 7);
        grid.add(button2, 1, 7);

        button.setOnAction(e -> {
            String id_ = idField.getText();
            String name_ = nameField.getText();
            String unit_ = unitField.getText();
            String price_ = priceField.getText();
            String bill_ = billField.getText();

            if (id_.isEmpty() || name_.isEmpty() || unit_.isEmpty() || price_.isEmpty() || bill_.isEmpty()) {
                showAlertError();
                return;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))) {

                bw.write("Customer Id: " + id_ + "\n");
                bw.write("Customer Name: " + name_ + "\n");
                bw.write("Unit Consumed: " + unit_ + "\n");
                bw.write("Unit Price: " + price_ + "\n");
                bw.write("Total Bill: " + bill_ + "\n");
                bw.write("-------------------------\n");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Bill Saved Successfully");
                alert.showAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            idField.clear();
            nameField.clear();
            unitField.clear();
            priceField.clear();
            billField.clear();
        });

        button2.setOnAction(e->{
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(stage);
        });

        VBox layout = new VBox(20, title, grid);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25, 25, 25, 25));
        layout.setSpacing(10);

        StackPane pane = new StackPane();
        pane.getChildren().addAll(imageView, layout);

        Scene scene = new Scene(pane, 600, 600);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private void styleLabels(Label label) {
       // label.setStyle("-fx-text-fill: #e9ece8; -fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman'");
        label.setStyle("-fx-text-fill: #170f0f; -fx-font-size: 18px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

        //label.setEffect(new DropShadow());

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
    alert.setContentText("All Fields are required!");
    alert.showAndWait();
}

}
