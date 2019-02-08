package Dominos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ScrollPane;
//import javafx.scene.control.Dialogs;
import javafx.scene.control.Alert.*;

public class DominosGUI extends Application{

    private MainGameLoop game = new MainGameLoop();
    private BorderPane screen;
    private VBox vbox;
    private HBox topRow;
    private HBox botRow;
    private ScrollPane scrollPane;
    private HBox gameTray;
    private VBox sidePanel;
    private Button resetButton;
    private Button rightButton;
    private Button leftButton;
    private Button drawButton;
    private ScrollPane scrollTray;
    private Label empty;

    public void setGameTray(){
        gameTray.getChildren().clear();
        for(Dominos s: game.getHuman().getTray()){
            addGameTray(s);
        }
    }
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

                gc.strokeOval(10,10,2,2);
                gc.strokeOval(28,28,2,2);
                gc.strokeOval(19,10,2,2);
                gc.strokeOval(19,28,2,2);
                gc.strokeOval(28,10,2,2);
                gc.strokeOval(10,28,2,2);

                break;
            default:
                break;
        }

        return canvas;
    }
    public void toggleDraw(){
        if(game.getGrave().isEmpty()) {
          drawButton.setDisable(true);
          empty.setText("Grave Empty");
          return;
        }
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

        if(game.getGrave().isEmpty() && game.getComputer().existLegal(game.getBoard(),game.getComputer()) && !game.getHuman().existLegal(game.getBoard(),game.getHuman())){
            boolean compLegal = game.getComputer().computerMove(game.getBoard(),game.getComputer(),game.getGrave());
            if(compLegal){
                game.setWinner("Computer");
            }
            updateBoard();
        }

        if(game.getGrave().isEmpty() && !game.getHuman().existLegal(game.getBoard(),game.getHuman()) && !game.getComputer().existLegal(game.getBoard(),game.getComputer())){
            resetButton.setDisable(false);
//            Dialogs.showInformationDialog(stage, "I have a great message for you!",
//                    "Information Dialog", "title");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Winner, winner, chicken dinner!");
            alert.setHeaderText(null);
            alert.setContentText("The winner is "+ game.getWinner()+" player!");

            alert.showAndWait();
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
                game.setWinner("Human");
                gameTray.getChildren().remove(temp);
                boolean compLegal = game.getComputer().computerMove(game.getBoard(),game.getComputer(),game.getGrave());
                if(compLegal){
                    game.setWinner("Computer");
                }
                updateBoard();
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


//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Information Dialog");
//        alert.setHeaderText(null);
//        alert.setContentText("The winner is"+game.getWinner()+"!");
//
//        alert.showAndWait();



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
        scrollTray = new ScrollPane(gameTray);
        scrollTray.setContent(gameTray);
        scrollTray.setPrefSize(gameTray.getWidth(),gameTray.getHeight());
        scrollTray.setMinHeight(60);
        scrollTray.setMaxHeight(60);
        scrollTray.setMinWidth(100);
        scrollTray.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollTray.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollTray.setFitToWidth(true);

        screen.setBackground(new Background(new BackgroundFill(Color.BLACK, null,null)));
        gameTray.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));
        gameTray.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        gameTray.setPrefHeight(40);
        BorderPane.setAlignment(scrollTray,Pos.BOTTOM_CENTER);

        sidePanel = new VBox(5);
        sidePanel.setBackground(new Background(new BackgroundFill(Color.WHITE, null,null)));
        resetButton = new Button("Reset");
        rightButton = new Button("Right");
        leftButton = new Button("Left");
        drawButton = new Button("Draw");
        empty = new Label();
        drawButton.setDisable(true);
        drawButton.setOnAction(event ->{
            // draw domino from graveyard and put in player tray
            if(!game.getGrave().isEmpty()){
                Dominos hold = game.getGrave().draw();
                game.getHuman().getTray().add(hold);
                addGameTray(hold);
                toggleDraw();
            } else {
                toggleDraw();
            }


        });

        resetButton.setOnAction(event ->{
            // draw domino from graveyard and put in player tray
            game.newGame();
            game.getHuman().setTray(game.getGrave().initDraw(game.getHuman().getTray()));
            game.getComputer().setTray(game.getGrave().initDraw(game.getComputer().getTray()));
            updateBoard();
            empty = new Label();
            setGameTray();
            resetButton.setDisable(true);

        });

        rightButton.setDisable(true);
        leftButton.setDisable(true);
        resetButton.setDisable(true);
        BorderPane.setAlignment(sidePanel,Pos.CENTER_RIGHT);
        sidePanel.getChildren().addAll(resetButton,rightButton,leftButton,drawButton,empty);

        setGameTray();


        vbox.getChildren().addAll(topRow,botRow);
        BorderPane.setAlignment(scrollPane, Pos.CENTER);
        screen.setRight(sidePanel);
        screen.setCenter(scrollPane);
        screen.setBottom(scrollTray);

        stage.setScene(new Scene(new StackPane(screen)));
        stage.show();
    }

    public static void main (String[] args){
        launch(args);
    }
}
