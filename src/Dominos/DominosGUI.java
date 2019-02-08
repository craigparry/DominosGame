package Dominos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
//import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import java.util.*;
import javafx.scene.input.MouseEvent;
import java.time.Duration;
import javafx.animation.AnimationTimer;
import javafx.scene.control.ScrollPane;

import javax.swing.*;

public class DominosGUI extends Application{
    private Canvas canvas;
    private GraphicsContext gc;
    private MainGameLoop game = new MainGameLoop();


    public Canvas drawDomino(int num){
        Canvas canvas;
        GraphicsContext gc;
        canvas = new Canvas(40,40);
        gc = canvas.getGraphicsContext2D();
        gc.setFill ( Color . WHITE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4);
        gc.strokeRect(0,0,canvas.getWidth(),canvas.getHeight());

        switch(num){

            case 1: gc.setStroke(Color.BLACK);
                gc.strokeOval(19,19,2,2);
                break;
            case 2: gc.setStroke(Color.BLACK);
                gc.strokeOval(10,10,2,2);
                gc.strokeOval(28,28,2,2);
                break;
            case 3: gc.setStroke(Color.BLACK);
                gc.strokeOval(19,19,2,2);
                gc.strokeOval(10,10,2,2);
                gc.strokeOval(28,28,2,2);
                break;
            case 4: gc.setStroke(Color.BLACK);
                gc.strokeOval(10,10,2,2);
                gc.strokeOval(28,28,2,2);
                gc.strokeOval(28,10,2,2);
                gc.strokeOval(10,28,2,2);
                break;
            case 5: gc.setStroke(Color.BLACK);
                gc.strokeOval(19,19,2,2);
                gc.strokeOval(10,10,2,2);
                gc.strokeOval(28,28,2,2);
                gc.strokeOval(28,10,2,2);
                gc.strokeOval(10,28,2,2);
                break;
            case 6: gc.setStroke(Color.BLACK);
                gc.strokeOval(10,19,2,2);
                gc.strokeOval(28,19,2,2);
                gc.strokeOval(10,10,2,2);
                gc.strokeOval(28,28,2,2);
                gc.strokeOval(28,10,2,2);
                gc.strokeOval(10,28,2,2);
                break;
            default: ;
                break;
        }

        return canvas;
    }


    @Override
    public void start ( Stage stage ) {
        game.getHuman().setTray(game.getGrave().initDraw(game.getHuman().getTray()));
        game.getComputer().setTray(game.getGrave().initDraw(game.getComputer().getTray()));

        stage.setTitle("Domino Game");
        BorderPane screen = new BorderPane();
        VBox vbox = new VBox(1);
        vbox.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));

        vbox.setPadding(new Insets(20,0,20,0));




        HBox topRow = new HBox(1);

        topRow.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));
        topRow.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        HBox botRow = new HBox(1);

        botRow.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));
        botRow.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        botRow.setPrefHeight(40);
        topRow.setPrefHeight(40);
//        BorderPane gamePane = new BorderPane();
//        BorderPane.setAlignment(topRow,Pos.TOP_CENTER);
//        BorderPane.setAlignment(botRow,Pos.BOTTOM_CENTER);
//        gamePane.setTop(topRow);
//        gamePane.setBottom(botRow);
        ScrollPane scrollPane = new ScrollPane();


        scrollPane.setContent(vbox);
        scrollPane.setPrefSize(vbox.getWidth(),vbox.getHeight());
        scrollPane.setMinHeight(140);
        scrollPane.setMinWidth(100);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);





//        HBox bigDomino = new HBox();


//        Map<String,HBox> dominoMap = new HashMap<>();
//
//
//        for(Dominos d: game.getGrave()){
//            HBox temp = new HBox();
//            temp.getChildren().addAll(drawDomino(d.getLeftSide()),drawDomino(d.getRightSide()));
//
//            temp.addEventHandler( MouseEvent . MOUSE_PRESSED , event -> {
//                System . out . println (" pressed "
//                        + event . getX () + " " + event . getY ());
//                System.out.println(d.toString());
//
//            });
//            dominoMap.put(d.toString(),temp);
//        }
//        Map<String,Button> DominoMap2 = new HashMap<>();
//        Button dominoButton = new Button();
//        dominoButton.setGraphic(drawDomino(1));



//        Canvas domino = drawDomino(6);
//        Canvas domino2 = drawDomino( 7);
//        bigDomino.getChildren().addAll(drawDomino(1),drawDomino(1));

//        DominoMap.put("1:2",bigDomino);
//        HBox temp = new HBox();
//        temp.addEventHandler( MouseEvent . MOUSE_PRESSED , event -> {
//            System . out . println (" pressed "
//                    + event . getX () + " " + event . getY ());
//        });

//        temp.getChildren().addAll(drawDomino(0),drawDomino(0));
//        DominoMap.put("0:0",temp);

        HBox gameTray = new HBox(2);
        gameTray.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));
        gameTray.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        gameTray.setPrefHeight(40);
        BorderPane.setAlignment(gameTray,Pos.BOTTOM_CENTER);

        VBox sidePanel = new VBox(5);
        Button resetButton = new Button("Reset");
        Button rightButton = new Button("Right");
        Button leftButton = new Button("Left");
        Button drawButton = new Button("Draw");
        BorderPane.setAlignment(sidePanel,Pos.CENTER_RIGHT);
        sidePanel.getChildren().addAll(resetButton,rightButton,leftButton,drawButton);

//        topRow.getChildren().addAll(new Label("This is top"));
//        botRow.getChildren().addAll(drawDomino(6), drawDomino(2),domino2,DominoMap.get("1:2"),DominoMap.get("0:0"));
//        botRow.getChildren().addAll(dominoMap.get("1:1"),dominoMap.get("1:2"));

        for(Dominos s: game.getHuman().getTray()){

            HBox temp = new HBox();
            temp.getChildren().addAll(drawDomino(s.getLeftSide()),drawDomino(s.getRightSide()));

            temp.addEventHandler( MouseEvent . MOUSE_PRESSED , event -> {
                System . out . println (" pressed "
                        + event . getX () + " " + event . getY ());
                System.out.println(s.toString());
                //play move and put into game board at this point

                game.playTurnGUI(s);
                gameTray.getChildren().remove(temp);

            });
            gameTray.getChildren().add(temp);
        }

        vbox.getChildren().addAll(topRow,botRow);


      //  vbox.getChildren().addAll(new Label("This is a "), new Button("is"), new Button("going"),drawDomino(1));





        BorderPane.setAlignment(scrollPane, Pos.CENTER);
        screen.setRight(sidePanel);
        screen.setCenter(scrollPane);
        screen.setBottom(gameTray);



        stage.setScene(new Scene(new StackPane(screen)));
        stage.show();

        AnimationTimer a = new AnimationTimer () {
            private long startTime = -1;
            @Override
            public void handle ( long now ) {
                if ( startTime < 0) {
                    startTime = now;
                }
//                gameTray.getChildren().clear();
//                for(Dominos s: game.getHuman().getTray()){
//                    gameTray.getChildren().add(dominoMap.get(s.toString()));
//                }


//                Duration elapsed = Duration . ofNanos (now - startTime );
//                text . setText ( String . format (" elapsed time %2d :%02 d",
//                        elapsed . toMinutesPart () ,
//                        elapsed . toSecondsPart ());
            }
        };
        a. start ();

    }

    public static void main (String[] args){
        launch(args);
    }

}
