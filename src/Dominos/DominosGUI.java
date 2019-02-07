package Dominos;

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
        stage.setTitle("Domino Game");
        BorderPane screen = new BorderPane();
        VBox vbox = new VBox(1);
        HBox topRow = new HBox(1);
        HBox botRow = new HBox(1);
        HBox bigDomino = new HBox();


        Map<String,HBox> dominoMap = new HashMap<>();


        for(Dominos d: game.getGrave()){
            HBox temp = new HBox();
            temp.getChildren().addAll(drawDomino(d.getLeftSide()),drawDomino(d.getRightSide()));

            temp.addEventHandler( MouseEvent . MOUSE_PRESSED , event -> {
                System . out . println (" pressed "
                        + event . getX () + " " + event . getY ());
                System.out.println(d.toString());
            });
            dominoMap.put(d.toString(),temp);
        }
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

        HBox tray = new HBox();

        topRow.getChildren().addAll(new Label("This is top"));
//        botRow.getChildren().addAll(drawDomino(6), drawDomino(2),domino2,DominoMap.get("1:2"),DominoMap.get("0:0"));
        botRow.getChildren().addAll(dominoMap.get("1:1"),dominoMap.get("1:2"));
        vbox.getChildren().addAll(topRow,botRow);
      //  vbox.getChildren().addAll(new Label("This is a "), new Button("is"), new Button("going"),drawDomino(1));
        BorderPane.setAlignment(vbox, Pos.CENTER);
        screen.setCenter(vbox);


        stage.setScene(new Scene(new StackPane(screen)));
        stage.show();
    }

    public static void main (String[] args){
        launch(args);
    }

}
