package group25.calculator.sccalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CalculatorInterface extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalculatorInterface.class.getResource("Calculator.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 446, 612);
        String css = Objects.requireNonNull(this.getClass().getResource("application.css")).toExternalForm();
        scene.getStylesheets().add(css);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/group25/calculator/sccalculator/Images/475497.png")));

        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("Scientific Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}