package com.example.electricitybill;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class MainMenu extends Application {

    private static final String FILENAME = "ElectricityBill.txt";
    public void start(Stage stage) {
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        Image backgroundImage = new Image("Bill.png");
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitWidth(800);
        imageView.setFitHeight(800);
        imageView.setPreserveRatio(true);

        Label title = new Label("Electricity Bill");

       // title.setStyle("-fx-font-weight: bold;-fx-font-size: 40px;-fx-text-fill: black;-fx-background-color: #36adda;-fx-background-size: 50px");
        title.setStyle(
                "-fx-font-weight: bold;" +
                        "-fx-font-size: 50px;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-color: linear-gradient(to right, #36adda, #097fbd);" +
                        "-fx-padding: 20px;" +
                        "-fx-border-color: #ffffff;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-alignment: center;"
        );
        title.setAlignment(Pos.TOP_CENTER);

        Button addBill = new Button("Add New Bill");
        Button viewBill = new Button("View All Bills");
        Button deleteBill = new Button("Delete Bill");
        Button searchBill = new Button("Search Bill");
        Button clearAllBill = new Button("Clear All Bills");
        Button exit = new Button("Exit");
        styleButtons(addBill);
        styleButtons(viewBill);
        styleButtons(deleteBill);
        styleButtons(searchBill);
        styleButtons(clearAllBill);
        exit.setStyle("-fx-text-fill: white;-fx-font-weight: bold;-fx-background-color: #aa2121;-fx-font-size: 15px");
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(20);
        pane.setVgap(20);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.add(addBill, 1, 0);
        pane.add(viewBill, 1, 1);
        pane.add(deleteBill, 1, 2);
        pane.add(searchBill, 2, 0);
        pane.add(clearAllBill, 2, 1);
        pane.add(exit, 2, 2);

        VBox layout = new VBox(20, title, pane);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.CENTER);
        StackPane root = new StackPane();

        root.getChildren().addAll(imageView, layout);


        addBill.setOnAction(event -> {
            AddBill addBill1 = new AddBill();
            addBill1.show(stage);
        });

        searchBill.setOnAction(event -> {
            SearchBill searchBill1 = new SearchBill();
            searchBill1.show(stage);
        });
        viewBill.setOnAction(event -> {
           AllBills allBills1 = new AllBills();
           allBills1.show(stage);
        });

        exit.setOnAction(event -> {
            System.exit(0);
        });
        clearAllBill.setOnAction(event -> {
            try(BufferedWriter br = new BufferedWriter(new FileWriter(FILENAME))) {
                br.write("");
                br.flush();
                br.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Bill Clear");
                alert.setHeaderText(null);
                alert.setContentText("All bills cleared");
                alert.showAndWait();
                
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("ElectricityBill");
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

    public static void main(String[] args) {
        launch(args);
    }
}