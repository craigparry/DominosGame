package Dominos;

import javafx.application.Application;
import java.time.Duration;

import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;



public class DominosGUI extends Application{

public static void main(String[] args) { launch(args);}

@Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("DominosGame");

        HBox topRow = new HBox();
        HBox botRow = new HBox();
        VBox screen = new VBox();

        Label text = new Label();
        topRow.getChildren().add(text);
        primaryStage.setScene(new Scene(text, 200,100));
        primaryStage.show();

        AnimationTimer a = new AnimationTimer(){
            private long startTime = -1;

            @Override
            public void handle(long now){
                if(startTime <0){
                    startTime = now;
                }
                Duration elapsed = Duration.ofNanos(now - startTime);
                text.setText(String.format("elapsed time %d:%02d",
                        elapsed.toMinutesPart(),
                        elapsed.toSecondsPart()));

            }
        };
        a.start();
    }




}
