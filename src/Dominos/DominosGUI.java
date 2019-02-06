package Dominos;

import javafx.application.Application;
import java.time.Duration;

import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
//import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.event.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.*;

public class DominosGUI extends Application{

public static void main(String[] args) { launch(args);}

@Override
    public void start(Stage primaryStage){
        MainGameLoop game = new MainGameLoop();
        game.newGame();
        Map<String,Canvas> dominoMap = new HashMap<>();
        Set<Canvas> dominoSet = new HashSet<>();




        for(int i = 0; i<=6; i++){
            Canvas temp = new Canvas(50, 25);
            GraphicsContext gc = temp.getGraphicsContext2D();
            gc.setFill(Color.WHITE);
            gc.fillRect(0,0,temp.getWidth(),temp.getHeight());
            gc.setStroke(Color.BLACK);



        }



        for(Dominos d: game.getGrave()){

        }

        primaryStage.setTitle("DominosGame");

//        HBox topRow = new HBox();
//        HBox botRow = new HBox();
        // probably wnat gridpane instead
        GridPane topRow = new GridPane();
        GridPane botRow = new GridPane();


        VBox screen = new VBox();

        Label text = new Label();
        Label text2 = new Label("Hello");
        topRow.getChildren().add(text);
        botRow.getChildren().add(text2);
        screen.getChildren().addAll(topRow,botRow);
        primaryStage.setScene(new Scene(screen, 200,100));
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
