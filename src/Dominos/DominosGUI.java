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


public class DominosGUI extends Application{
    private Canvas canvas;
    private GraphicsContext gc;
    private MainGameLoop game = new MainGameLoop();
    private BorderPane screen;
    private VBox vbox;
    private HBox topRow;
    private HBox botRow;
    private ScrollPane scrollPane;
    private HBox gameTray;
    private VBox sidePanel;
    Button resetButton;
    Button rightButton;
    Button leftButton;
    Button drawButton;

    public Canvas drawBlank(){
        Canvas canvas = new Canvas(40,40);
        canvas.setOpacity(100);
        return canvas;
    }
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
        gc.setStroke(Color.RED);
        switch(num){

            case 1:
                gc.strokeOval(19,19,2,2);
                break;
            case 2:
                gc.strokeOval(10,10,2,2);
                gc.strokeOval(28,28,2,2);
                break;
            case 3:
                gc.strokeOval(19,19,2,2);
                gc.strokeOval(10,10,2,2);
                gc.strokeOval(28,28,2,2);
                break;
            case 4:
                gc.strokeOval(10,10,2,2);
                gc.strokeOval(28,28,2,2);
                gc.strokeOval(28,10,2,2);
                gc.strokeOval(10,28,2,2);
                break;
            case 5:
                gc.strokeOval(19,19,2,2);
                gc.strokeOval(10,10,2,2);
                gc.strokeOval(28,28,2,2);
                gc.strokeOval(28,10,2,2);
                gc.strokeOval(10,28,2,2);
                break;
            case 6:
                // make horizontal
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
    public void toggleDraw(){
        if(!game.getHuman().existLegal(game.getBoard(),game.getHuman())){
            drawButton.setDisable(false);
            game.getHuman().printTray();
        } else{
            drawButton.setDisable(true);
        }
    }

    public void updateBoard(){
        toggleDraw();
        topRow.getChildren().clear();
        botRow.getChildren().clear();
        HBox blank = new HBox();
        blank.setMinWidth(40);
        blank.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));
        botRow.getChildren().add(blank);
        for(Dominos d: game.getBoard()){
            HBox temp = new HBox();
            temp.getChildren().addAll(drawDomino(d.getLeftSide()),drawDomino(d.getRightSide()));
            if(game.getBoard().indexOf(d) % 2 == 0) {
                topRow.getChildren().add(temp);
            }else{
                botRow.getChildren().add(temp);
            }
        }
    }
    public void addGameTray(Dominos domino){
        HBox temp = new HBox();
        temp.getChildren().addAll(drawDomino(domino.getLeftSide()),drawDomino(domino.getRightSide()));

        temp.addEventHandler( MouseEvent . MOUSE_PRESSED , event -> {
            System . out . println (" pressed "
                    + event . getX () + " " + event . getY ());
            System.out.println(domino.toString());
            //play move and put into game board at this point
            boolean legal = game.playTurnGUI(domino, game.getHuman());
            if(legal){
                updateBoard();
                gameTray.getChildren().remove(temp);
            }


        });
        gameTray.getChildren().add(temp);
    }
    @Override
    public void start ( Stage stage ) {
        game.getHuman().setTray(game.getGrave().initDraw(game.getHuman().getTray()));
        game.getComputer().setTray(game.getGrave().initDraw(game.getComputer().getTray()));

        stage.setTitle("Domino Game");
        screen = new BorderPane();
        vbox = new VBox(1);
        vbox.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));

        vbox.setPadding(new Insets(20,0,20,0));




        topRow = new HBox(1);

        topRow.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));
        topRow.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        botRow = new HBox(1);

        botRow.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));
        botRow.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
//        botRow.setPrefHeight(new Insets(0,0,0,40));
        botRow.setPrefHeight(40);
        topRow.setPrefHeight(40);
//        BorderPane gamePane = new BorderPane();
//        BorderPane.setAlignment(topRow,Pos.TOP_CENTER);
//        BorderPane.setAlignment(botRow,Pos.BOTTOM_CENTER);
//        gamePane.setTop(topRow);
//        gamePane.setBottom(botRow);
        scrollPane = new ScrollPane();


        scrollPane.setContent(vbox);
        scrollPane.setPrefSize(vbox.getWidth(),vbox.getHeight());
        scrollPane.setMinHeight(140);
        scrollPane.setMaxHeight(140);
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

        gameTray = new HBox(2);
        gameTray.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));
        gameTray.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        gameTray.setPrefHeight(40);
        BorderPane.setAlignment(gameTray,Pos.BOTTOM_CENTER);

        sidePanel = new VBox(5);
        resetButton = new Button("Reset");
        rightButton = new Button("Right");
        leftButton = new Button("Left");
        drawButton = new Button("Draw");
        drawButton.setDisable(true);
        drawButton.setOnAction(event ->{
            // draw domino from graveyard and put in player tray
            Dominos hold = game.getGrave().draw();
            game.getHuman().getTray().add(hold);
            addGameTray(hold);
            toggleDraw();

        });




        rightButton.setDisable(true);
        leftButton.setDisable(true);
        resetButton.setDisable(true);
        BorderPane.setAlignment(sidePanel,Pos.CENTER_RIGHT);
        sidePanel.getChildren().addAll(resetButton,rightButton,leftButton,drawButton);

//        topRow.getChildren().addAll(new Label("This is top"));
//        botRow.getChildren().addAll(drawDomino(6), drawDomino(2),domino2,DominoMap.get("1:2"),DominoMap.get("0:0"));
//        botRow.getChildren().addAll(dominoMap.get("1:1"),dominoMap.get("1:2"));

        for(Dominos s: game.getHuman().getTray()){
            addGameTray(s);
//
        }




      //  vbox.getChildren().addAll(new Label("This is a "), new Button("is"), new Button("going"),drawDomino(1));




        vbox.getChildren().addAll(topRow,botRow);
        BorderPane.setAlignment(scrollPane, Pos.CENTER);
        screen.setRight(sidePanel);
        screen.setCenter(scrollPane);
        screen.setBottom(gameTray);



        stage.setScene(new Scene(new StackPane(screen)));
        stage.show();




//        AnimationTimer a = new AnimationTimer () {
//            private long startTime = -1;
//            @Override
//            public void handle ( long now ) {
//                if ( startTime < 0) {
//                    startTime = now;
//                }
//
//
////                for(Dominos d: game.getBoard()){
////                    HBox temp = new HBox();
////                    temp.getChildren().addAll(drawDomino(d.getLeftSide()),drawDomino(d.getRightSide()));
////                    gameTray.getChildren().add(temp);
////
////                }
////                gameTray.getChildren().clear();
////                for(Dominos s: game.getHuman().getTray()){
////                    gameTray.getChildren().add(dominoMap.get(s.toString()));
////                }
//
//
////                Duration elapsed = Duration . ofNanos (now - startTime );
////                text . setText ( String . format (" elapsed time %2d :%02 d",
////                        elapsed . toMinutesPart () ,
////                        elapsed . toSecondsPart ());
//            }
//        };
//        a. start ();

    }

    public static void main (String[] args){
        launch(args);
    }

}
